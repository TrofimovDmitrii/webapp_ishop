package com.dmitriitrofimov.config;

import com.dmitriitrofimov.filter.SimpleFilter3;
import com.dmitriitrofimov.servlet.JavaConfigServlet;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationConfigInitializer implements ServletContainerInitializer {
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		JavaConfigServlet servlet = new JavaConfigServlet();
		ServletRegistration.Dynamic servletConfig = ctx.addServlet("JavaConfigServlet", servlet);
		servletConfig.addMapping("/java");
		System.out.println("ApplicationConfigInitializer");

		FilterRegistration.Dynamic filterConfig = ctx.addFilter("SimpleFilter3", new SimpleFilter3());
		filterConfig.addMappingForUrlPatterns(null, true, "/*");
		
//		ctx.addListener(new ServletContextListenerImpl3());
	}
}
