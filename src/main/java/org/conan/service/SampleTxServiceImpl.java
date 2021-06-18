package org.conan.service;

import java.util.List;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;
import org.conan.mapper.Sample1Mapper;
import org.conan.mapper.Sample2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class SampleTxServiceImpl implements SampleTxService {
	
	@Setter(onMethod_= {@Autowired })
	private Sample1Mapper mapper1;
	@Setter(onMethod_= { @Autowired})
	private Sample2Mapper mapper2;
	
	@Override
	public void addData(String value) {
		log.info("mapper1.........");
		mapper1.insertCol1(value);
		log.info("mapper2.........");
		mapper2.insertCol2(value);
		log.info("end.........");

	}

}
