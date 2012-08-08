package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bizhub.bzwebapp.dao.SiteDao;
import com.bizhub.bzwebapp.domain.Site;

/*
 * This class is responsible to get the content for a Bizvez-site when the user accesses the site address
 * For example, http://www.bizvez.com/site/bestsandwiches will get the content for the site with the address "bestsandwiches"
 */
public class DefaultController extends ParameterizableViewController{
	
	protected SiteDao siteDao;

	@Required
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String servletPath = request.getServletPath();
		String[] servletPathSplit = servletPath.split("/site/"); 
				
		Site site = siteDao.getByAddress(servletPathSplit[1]);
		
		Object testString = new Object();
		if(site != null){
			testString = site.getContent();
		}else{
			testString = "<html><head></head><body>This is not a valid Bizvez site address</body></html>";
		}
		
		return new ModelAndView(super.getViewName()).addObject("siteHtml", testString);
		
	}

}
