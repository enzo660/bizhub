package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.GlobalConstants;
import com.bizhub.bzwebapp.domain.IdentifiableEntity;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;

public class IdentifiableEntityGetController extends
IdentifiableEntityDaoSupportController {

	private final UserContextService userContextService;
	
	User loggedInUser = null;

	@Autowired
	public IdentifiableEntityGetController(UserContextService userContextService){
		this.userContextService = userContextService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		loggedInUser = this.userContextService.getUserFromContext();
		long id = 0;
		IdentifiableEntity result = null;

		if(!loggedInUser.isAdmin()){

			id = getId();
			result = this.dao.getById(id);
		}else{
			if (request.getParameterMap().containsKey("id")){
				id = ServletRequestUtils.getRequiredLongParameter(request, "id");
				result = this.dao.getById(id);
			}	
			if(result == null){
				
				id = getId();
				result = this.dao.getById(id);
			}
		}

		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Got [" + result + "] by id [" + id + "]");
		}
		return new ModelAndView(super.getViewName()).addObject(result);
	}
	
	private long getId(){
		
		long id=0;
		loggedInUser = this.userContextService.getUserFromContext();
		
		if(this.getViewName().equals(GlobalConstants.USER_VIEW)){
			id = loggedInUser.getId();
		}else if(this.getViewName().equals(GlobalConstants.SITE_VIEW)){
			id = loggedInUser.getSite().getId();
		}
		
		return id;
	}
}
