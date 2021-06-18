//package org.conan.service;
//
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletRegistration;
//
//public class WebConfig {
//
//	@Override
//	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//		
//		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
//		
//		MultipartConfigElement multipartConfig = new MultipartConfigElement("C:\\upload\\temp", 20971520,41943040,20971520);
//		registration.setMultipartConfig(multipartConfig);
//	}
//	
//}
