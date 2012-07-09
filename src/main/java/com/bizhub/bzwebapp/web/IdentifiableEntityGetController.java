package com.bizhub.bzwebapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.domain.IdentifiableEntity;

public class IdentifiableEntityGetController extends
		IdentifiableEntityDaoSupportController {
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		IdentifiableEntity result = this.dao.getById(id);
		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Got [" + result + "] by id [" + id + "]");
		}
		return new ModelAndView(super.getViewName()).addObject(result);
	}
}
