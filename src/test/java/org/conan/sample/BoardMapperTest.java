package org.conan.sample;

import java.util.List;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;
import org.conan.mapper.BoardMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	@Setter(onMethod = @__({@Autowired}))
	private BoardMapper mapper;
	@Test
	public void testGetList() {
		mapper.getList().forEach(board-> log.info(board));
	}
	@Test
	public void testInsert() {
		BoardVO board= new BoardVO();
		board.setTitle("새로작성하는 글");
		board.setContent("new write word");
		board.setWriter("newbie");
		mapper.insert(board);
		log.info(board);		
	}
	@Test
	public void insertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("new title");
		board.setContent("new content");
		board.setWriter("newbie2");
		mapper.insertSelectKey(board);
		log.info(board);
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(7L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("DELETE COUNT : " +mapper.delete(5L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(2L);
		board.setTitle("수정완료");
		board.setContent("수정수정");
		board.setWriter("rose");
		int count= mapper.update(board);
		log.info("UPDATE COUNT : " +count);
	}
	
	@Test
	public void testPaging() {
		Criteria cri=new Criteria();
		List<BoardVO> list=mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri= new Criteria();
		cri.setKeyword("나랏");
		cri.setType("W");
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	

}
