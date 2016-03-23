package com.egen.LibraryManagement.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.egen.LibraryManagement.config.AppConfig;
import com.egen.LibraryManagement.config.SwaggerConfig;

public class AppInnit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{AppConfig.class,SwaggerConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/api/*"};
	}

}
