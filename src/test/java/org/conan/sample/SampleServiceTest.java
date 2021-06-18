package org.conan.sample;

import org.aspectj.lang.annotation.Before;
import org.conan.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleServiceTest {

	@Setter(onMethod_= @Autowired)
	private SampleService service;

	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	@Before("execution(* org.conan.service.SampleService*.doAdd(String,String))&&args(str1,str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1:"+str1);
		log.info("str2:"+str2);
	}
	
	
	@Test
	public void testAddError()throws Exception{
		log.info(service.doAdd("123", "ABC"));
	}
}
