package com.bizhub.bzwebapp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.bizhub.bzwebapp.domain.Site;


@Transactional(readOnly = true)
public class HibernateSiteDao extends AbstractHibernateDao<Site>
		implements SiteDao {

	public Site getByName(String name) throws DataAccessException {
		return super.findOne("from Site where name=?", name);
	}
	
	public Site getByAddress(String address) throws DataAccessException{
		return super.findOne("from Site where address=?", address);
	}

	public List<Site> getAll() throws DataAccessException {
		return super.findAll("from Site order by name");
	}

	@Transactional(readOnly = false)
	@Override
	public void save(Site site) throws DataAccessException {
		super.save(site);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Site site) throws DataAccessException {
		super.delete(site);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) throws DataAccessException {
		super.deleteById(id);
	}
}
