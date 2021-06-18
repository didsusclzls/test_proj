package org.conan.service;


import java.util.List;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;
import org.conan.mapper.BoardMapper2;
import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl2 implements BoardService2 {
	private BoardMapper2 mapper;

//	@Override 
//	public List<BoardVO> getList(){
//		log.info("getList.....");
//		return mapper.getList();
//	}
	@Override 
	public void register(BoardVO board){
		log.info("register...."+board.getBno());
		mapper.insertSelectKey(board);
	}
	@Override 
	public BoardVO get(Long bno){
		log.info("getList....."+bno);
		return mapper.read(bno);
	}
	@Override 
	public boolean modify(BoardVO board){
		log.info("modify....."+board);
		return mapper.update(board)==1;
	}
	@Override 
	public boolean remove(Long bno){
		log.info("remove....."+bno);
		return mapper.delete(bno)==1;
	}
//	@Override
//	public List<BoardVO> getList(Criteria cri){
//		log.info("getList with criteria: "+cri);
//		return mapper.getListWithPaging(cri);
//	}
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

}
