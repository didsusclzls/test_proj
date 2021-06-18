package org.conan.mapper;

import java.util.List;

/*import org.apache.ibatis.annotations.Select;*/
import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;

public interface BoardMapper2 {
	/* @Select("select * from tbl_board where bno >0") 
	 	왜 안해도 돼냐면 boardMapper.xml에서 설정을 해서*/
public List<BoardVO> getList();
public void insert(BoardVO board);
public void insertSelectKey(BoardVO board);


public BoardVO read(Long bno);

public int delete(Long bno);

public int update(BoardVO board);

//public List<BoardVO> getListWithPaging(Criteria cri);

public int getTotalCount(Criteria cri);
//이런거 작성하면 BoardMapper.xml에서 추가혹은 수정을 해야함
}

