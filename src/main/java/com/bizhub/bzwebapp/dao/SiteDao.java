package com.bizhub.bzwebapp.dao;

import com.bizhub.bzwebapp.domain.Site;

import org.springframework.dao.DataAccessException;

public interface SiteDao extends IdentifiableEntityDao<Site>{

    public Site getByName(String name) throws DataAccessException;

}
