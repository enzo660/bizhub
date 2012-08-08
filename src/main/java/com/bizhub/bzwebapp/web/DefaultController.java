package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class DefaultController extends ParameterizableViewController{
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Object testString = new String("<html><head></head><body><p>pra</p><b>teek</b></body></html>");
		
		return new ModelAndView(super.getViewName()).addObject("siteHtml", testString);
		
	}

}
