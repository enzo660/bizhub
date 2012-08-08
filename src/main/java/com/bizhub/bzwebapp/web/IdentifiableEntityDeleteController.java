package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.GlobalConstants;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;

public class IdentifiableEntityDeleteController extends
IdentifiableEntityDaoSupportController {

	private final UserContextService userContextService;
	User loggedInUser = null;
	
	//To identify whether a user or a site is being deleted
	private String entityDeleted = new String();

	@Autowired
	public IdentifiableEntityDeleteController(UserContextService userContextService){
		this.userContextService = userContextService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		loggedInUser = this.userContextService.getUserFromContext();
		long id = 0;
		
		if(!loggedInUser.isAdmin()){
			id = getId();
		}else{
			if (request.getParameterMap().containsKey("id")){
				id = ServletRequestUtils.getRequiredLongParameter(request, "id");
			}else{
				id = getId();
			}
		}
		
		//If the user is not an Admin, and he/she deletes the site, then set the Site to null so that the loggedInUser is up to date
		if(!loggedInUser.isAdmin() && this.getEntityDeleted().equals(GlobalConstants.SITE)){
			loggedInUser.setSite(null);
		}
		
		this.dao.deleteById(id);
		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Deleted  entity by id [" + id + "]");
		}
		return new ModelAndView(super.getViewName());
	}
	public String getEntityDeleted() {
		return entityDeleted;
	}
	public void setEntityDeleted(String entityDeleted) {
		this.entityDeleted = entityDeleted;
	}
	
	private long getId(){
		
		long id=0;
		loggedInUser = this.userContextService.getUserFromContext();
		
		if(this.getEntityDeleted().equals(GlobalConstants.USER)){
			id = loggedInUser.getId();
		}else if(this.getEntityDeleted().equals(GlobalConstants.SITE)){
			id = loggedInUser.getSite().getId();
		}
		
		return id;
	}
}
