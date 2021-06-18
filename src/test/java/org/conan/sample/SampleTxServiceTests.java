package org.conan.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.conan.domain.Ticket;
import org.conan.service.SampleTxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class SampleTxServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private SampleTxService service;
	
	@Test
	public void testLong()
			 {
		String str=
			"Starry night\r\n"+
			 "paint your palette blue and grey\r\n"+
					"Look out on a summer' s day";
			log.info(str.getBytes().length);
			service.addData(str);
	}
	
	

}
