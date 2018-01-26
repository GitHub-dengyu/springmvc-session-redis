package com.huato.inital;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.huato.config.WebConfig;

public class WebAppInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//Spring IOC容器初始化
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{};
	}
	//DispacherServert初始化
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}
    //DispacherSrervert拦截内容
	@Override
	protected String[] getServletMappings() {
		
		return new String[]{ "*.do" };
	}

}
