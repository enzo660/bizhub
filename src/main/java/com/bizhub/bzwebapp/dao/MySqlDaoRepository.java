package com.bizhub.bzwebapp.dao;


public class MySqlDaoRepository implements DaoRepository {

    private SiteDao siteDao = new MySqlSiteDao();
    
    private UserDao userDao = new MySqlUserDao();

    public SiteDao getSiteDao() {
        return siteDao;
    }
    
    public UserDao getUserDao() {
        return this.userDao;
    }

}
