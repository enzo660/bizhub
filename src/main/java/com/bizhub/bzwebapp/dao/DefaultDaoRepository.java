package com.bizhub.bzwebapp.dao;

import org.springframework.beans.factory.annotation.Required;

public class DefaultDaoRepository implements DaoRepository {

    private SiteDao siteDao;

    private UserDao userDao;

    public SiteDao getSiteDao() {
        return this.siteDao;
    }

    @Required
    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
