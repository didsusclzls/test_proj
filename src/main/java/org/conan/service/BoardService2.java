package org.conan.service;

import java.util.List;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;



public interface BoardService2 {

	public void register(BoardVO board);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board);
	public boolean remove(Long bno);
	//public List<BoardVO> getList();
//	public List<BoardVO> getList(Criteria cri);
	int getTotal(Criteria cri);
	
	
	
}
