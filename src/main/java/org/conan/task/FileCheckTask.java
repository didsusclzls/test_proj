//package org.conan.task;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.conan.domain.BoardAttachVO;
//import org.conan.mapper.BoardAttachMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import lombok.Setter;
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@Component
//public class FileCheckTask {
//
//	@Setter(onMethod_= { @Autowired })
//	private BoardAttachMapper attachMapper;
//	
//	private String getFolderYesterday() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		String str= sdf.format(cal.getTime());
//		return str.replace("-",File.separator);
//	}
//	//위의 것은 데이터베이스에서 어제 사용된 파일의 목록을 얻어옴
//
//	@Scheduled(cron="0 * * * * *")
//	public void checkFiles() throws Exception {
//		log.warn("file check task run.....");
//		log.warn("==========================");
//		//file list in database
//		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
//
//		//ready fir cgecj fuke ub durectiry with database file list
//		List<Path> fileListPaths = fileList.stream()
//				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_"+ vo.getFileName())).collect(Collectors.toList());
//		//ㄴdb의 정보를 파일 이름 즉 디렉토리 포함서버에 저장되어야 할 정보로 바뀜
//
//		//image file has thumnail file
//		fileList.stream().filter(vo -> vo.isFileType() == true)
//		.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_"+ vo.getUuid() + "_" + vo.getFileName()))
//		.forEach(p -> fileListPaths.add(p));
////데이터베이스에 있으면 그냥 ================
//		log.warn("====================");
//
//		fileListPaths.forEach(p -> log.warn(p));
//
//		//files in yesterday directory
//		File targetDir = Paths.get("C:\\upload", getFolderYesterday()).toFile();
//
//		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
//
//		log.warn("----------------------------");
//		for(File file:removeFiles) {
//
//			log.warn(file.getAbsolutePath());
//
//			file.delete();
//		}
//
//	}
//}
