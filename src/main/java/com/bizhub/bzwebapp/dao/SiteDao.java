package com.bizhub.bzwebapp.dao;

import java.util.List;
import com.bizhub.bzwebapp.domain.Site;

import org.springframework.dao.DataAccessException;

public interface SiteDao {
    public Site getById(Long id) throws DataAccessException;

    public Site getByName(String name) throws DataAccessException;

    public List<Site> getAll() throws DataAccessException;

    public void save(Site site) throws DataAccessException;

    public void delete(Site site) throws DataAccessException;

    public void deleteById(Long id) throws DataAccessException;
}
