package com.bizhub.bzwebapp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.bizhub.bzwebapp.domain.IdentifiableEntity;

public class IdentifiableEntityListController extends
		IdentifiableEntityDaoSupportController {
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<? extends IdentifiableEntity> result = super.dao.getAll();
		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Got [" + result.size() + "] entities");
		}
		return new ModelAndView(super.getViewName()).addObject(result);
	}
}
