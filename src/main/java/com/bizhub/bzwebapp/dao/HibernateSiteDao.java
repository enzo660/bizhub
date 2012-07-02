package com.bizhub.bzwebapp.dao;

import java.util.List;

import com.bizhub.bzwebapp.domain.Site;


public class HibernateSiteDao extends AbstractHibernateDao implements
		SiteDao {

	public Site getById(Long id) throws DataAccessException {
		return (Site) super.getById(Site.class, id);
	}

	public Site getByName(String name) throws DataAccessException {
		return (Site) super.findOne("from Site where name=?", name);
	}

	@SuppressWarnings("unchecked")
	public List<Site> getAll() throws DataAccessException {
		return (List<Site>) super.findAll("from Site order by name");
	}

	public void save(Site site) throws DataAccessException {
		super.save(site);
	}

	public void delete(Site site) throws DataAccessException {
		super.delete(site);
	}

	public void deleteById(Long id) throws DataAccessException {
		super.deleteById(Site.class, id);
	}
}
