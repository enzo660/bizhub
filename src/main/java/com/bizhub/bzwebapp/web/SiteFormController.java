package com.bizhub.bzwebapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.dao.SiteDao;
import com.bizhub.bzwebapp.domain.Site;
import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.service.UserContextService;

@Controller
@RequestMapping("/site_form")
@SessionAttributes("site")
public class SiteFormController {

	private final SiteDao dao;
	private final UserContextService userContextService;


	@Autowired
	public SiteFormController(SiteDao dao, UserContextService userContextService) {
		this.dao = dao;
		this.userContextService = userContextService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm(
			@RequestParam(value = "id", required = false) Long id) {

		User loggedInUser = this.userContextService.getUserFromContext();
		Site site = new Site();

		//If the user is not creating a new site
		//Non-admin user
		if(!loggedInUser.isAdmin() && loggedInUser.getSite() != null){	
			site = loggedInUser.getSite();				
		}else{ //admin user

			if(id != null){
				site = this.dao.getById(id);
			}else{
				if(loggedInUser.getSite() != null){
					site = loggedInUser.getSite();
				}
			}
		}

		return new ModelAndView("siteForm").addObject(site);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("site") @Valid Site site,
			BindingResult result, SessionStatus status) {

		User loggedInUser = this.userContextService.getUserFromContext();

		//If it's a new site creation and not an edit
		if(site.getUser() == null){
			site.setUser(loggedInUser);
		}

		if (!result.hasErrors()) {
			try {
				this.dao.save(site);
				status.setComplete();
				//If this is not done, then the user would have to log out and then log in again.
				loggedInUser.setSite(site);
				return "redirect:site";
			} catch (DataIntegrityViolationException e) {
				result.rejectValue("name", "DuplicateNameFailure");
			} catch (ConcurrencyFailureException e) {
				result.reject("ConcurrentModificatonFailure",
						new String[] { "site" }, null);
			}
		}
		return "siteForm";
	}
}