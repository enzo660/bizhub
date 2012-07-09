package com.bizhub.bzwebapp.web;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bizhub.bzwebapp.dao.IdentifiableEntityDao;
import com.bizhub.bzwebapp.domain.IdentifiableEntity;


public abstract class IdentifiableEntityDaoSupportController extends
		ParameterizableViewController {
	protected IdentifiableEntityDao<? extends IdentifiableEntity> dao;

	@Required
	public void setDao(IdentifiableEntityDao<? extends IdentifiableEntity> dao) {
		this.dao = dao;
	}
}