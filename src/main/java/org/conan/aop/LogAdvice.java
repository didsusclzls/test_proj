package org.conan.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


@Log4j
@Aspect
@Component
public class LogAdvice {
	@Before( "execution(* org.conan.service.SampleService*.*(..))")
	public void logBefore() {
		
		log.info("====================a==========================");
	}
	
	@AfterThrowing(pointcut="execution(* org.conan.service.SampleService*.*(..))",
	throwing="exception")
public void logException(Exception exception) {
	log.info("exception..");
	log.info(exception+"exception");
}
//	@Around("execution(* org.conan.service.SampleService*.*(..))")
//	public Object log Time(ProceedingJoinPoint pjp) {
//		long start=System.currentTimeMillis();
//		log.info("Target:"+ pjp.getTarget());
//		log.info("Param:"+Arrays.toString(pjp.getArgs()));
//		Object result =null;
//		try {
//			result = pjp.proceed();
//		} catch (Throwable e) {
//			long end =System.currentTimeMillis();
//			log.info("Time:"+(end-start));
//			return result;
//		}
//	}
	
}
