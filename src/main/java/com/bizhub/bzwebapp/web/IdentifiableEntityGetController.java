package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.domain.IdentifiableEntity;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;

public class IdentifiableEntityGetController extends
		IdentifiableEntityDaoSupportController {
	
	private final UserContextService userContextService;
	
	@Autowired
	public IdentifiableEntityGetController(UserContextService userContextService){
		this.userContextService = userContextService;
	}
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User loggedInUser = this.userContextService.getUserFromContext();
		long id = 0;
		IdentifiableEntity result = null;
		
		if(!loggedInUser.isAdmin()){
			
			String className = this.dao.getClass().getName();
			
			id = loggedInUser.getId();
			result = this.dao.getById(id);
		}else{
			id = ServletRequestUtils.getRequiredLongParameter(request, "id");
			result = this.dao.getById(id);
			if(result == null){
				result = this.dao.getById(loggedInUser.getId());
			}
		}

		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Got [" + result + "] by id [" + id + "]");
		}
		return new ModelAndView(super.getViewName()).addObject(result);
	}
}
