package com.bizhub.bzwebapp.dao;

public class HibernateDaoRepository implements DaoRepository {
	
	private UserDao userDao = new HibernateUserDao();

	private SiteDao siteDao = new HibernateSiteDao();

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public UserDao getUserDao() {
		return this.userDao;
	}
}
