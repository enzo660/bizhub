package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bizhub.bzwebapp.domain.Site;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;

public class HomeController extends ParameterizableViewController{
	
	private final UserContextService userContextService;
	
	User loggedInUser = null;
	
	@Autowired
	public HomeController(UserContextService userContextService){
		this.userContextService = userContextService;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		loggedInUser = this.userContextService.getUserFromContext();
		Site site = new Site();
		if(loggedInUser != null){
			site = loggedInUser.getSite();
		}
		
		return new ModelAndView(super.getViewName()).addObject("user", loggedInUser).addObject("site", site);
	}
}
