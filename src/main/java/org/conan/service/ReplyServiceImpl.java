package org.conan.service;

import java.util.List;


import org.conan.domain.Criteria;
import org.conan.domain.ReplyPageDTO;
import org.conan.domain.ReplyVO;
import org.conan.mapper.BoardMapper;
import org.conan.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;


	@Override
	public ReplyVO get(Long rno) {
		log.info("get........." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify.........."+vo);
		return mapper.update(vo);
	}
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove.........."+rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of Board........"+bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public int getTotal(Criteria cri) {
		return 0;
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info(bno + "�� ��� count ���� : "+ mapper.getCountByBno(bno));
		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
	}

	@Override
	public Integer doAdd(String str1, String str2)throws Exception {
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register...."+vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}
}
