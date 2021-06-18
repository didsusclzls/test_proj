package org.conan.sample;


import java.util.List;
import java.util.stream.IntStream;

import org.conan.domain.Criteria;
import org.conan.domain.ReplyVO;
import org.conan.mapper.ReplyMapper;
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
public class ReplyMapperTest {
	@Setter(onMethod = @__({@Autowired}))
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	private Long[] bnoArr= {551L, 552L, 553L, 554L,555L};
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i->{ReplyVO vo =new ReplyVO();
		vo.setBno(bnoArr[i % 5]);
		vo.setReply("asd" + i);
		vo.setReplyer("replyer"+i);
		mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno=400L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targetRno=1L;
		mapper.delete(targetRno);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno=10L;
		ReplyVO vo=mapper.read(targetRno);
		vo.setReply("update Reply");
		int count=mapper.update(vo);
		log.info("Update Count : " + count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replise= mapper.getListWithPaging(cri, bnoArr[0]);
		replise.forEach(reply->log.info(reply));
	}
	
	@Test
	public void restList2() {
		Criteria cri = new Criteria(1,4);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 591L);
		replies.forEach(reply->log.info(reply));
	}
}
