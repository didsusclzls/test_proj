package org.conan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.conan.domain.AttachFileDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload uploadAjax");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for (MultipartFile multipartFile : uploadFile) {
			
			log.info("----------------------------------");
			log.info("Upload File Name :"+multipartFile.getOriginalFilename());
			log.info("Upload File Size: "+multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
		}//end for
	}
	

	
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	private boolean checkImageType(File file) {
		try {
		String contentType = Files.probeContentType(file.toPath());
		
		
			return contentType.startsWith("image");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("fileName:"+fileName);
		
		File file = new File("c:/upload/"+fileName);
		
		log.info("file :"+file);
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
					header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile:"+fileName);
		File file;
		try {
			file = new File("C:/upload/"+URLDecoder.decode(fileName,"UTF-8"));
			file.delete();
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName:"+largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/download", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName) {
		
		Resource resource = new FileSystemResource("C:\\upload\\"+ fileName);
		log.info("download file: "+fileName);
		
		if(resource.exists() == false) {
			String resourceName = resource.getFilename();
			log.info("resource:" + resource);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}
		String resourceName = resource.getFilename();
		
		//remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		HttpHeaders headers = new HttpHeaders();		
		log.info("resource:" + resource);
		
		try {
			
			String downloadName= null;
			
			if(userAgent.contains("Trident")) {
				log.info("IE brwser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replace("\\+", " ");
				
			}else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8");
				log.info("Edge name:"+downloadName);
			}else {
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),
						"ISO-8859-1");
			}
			
			headers.add("Content-Disposition",
					"attachment; filename="+ downloadName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value= "/uploadAjaxAction", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		String uploadFolder = "C:\\upload";
		List<AttachFileDTO> list = new ArrayList<>();
		//make folder
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path:" + uploadPath);
		
		String uploadFilderPath = getFolder();
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		//make yyyy/mm//dd folder
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("--------------------");;
			log.info("Upload File Name:" + multipartFile.getOriginalFilename());
			log.info("file size"+multipartFile.getSize());
		AttachFileDTO attachDTO = new AttachFileDTO();
		
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//Ie has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name:"+ uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();//즁복제거
			
			uploadFileName = uuid.toString() + "_"+ uploadFileName;
			
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFilderPath);
				
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath
							,"s_"+uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,100);
					
					thumbnail.close();
					
				}
				
				
			} catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
			/* add to List */
			list.add(attachDTO);
		}//end for
		return new ResponseEntity<>(list,HttpStatus.OK);
	}//end Post

	
			
			
			
		
	
}
