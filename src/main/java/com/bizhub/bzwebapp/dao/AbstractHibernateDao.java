package com.bizhub.bzwebapp.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizhub.bzwebapp.domain.IdentifiableEntity;


public abstract class AbstractHibernateDao<E extends IdentifiableEntity>
		extends HibernateDaoSupport {

	// this gives us the class of E
	@SuppressWarnings("unchecked")
	private Class<E> domainClass = (Class<E>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	protected void save(E entity) throws DataAccessException {
		try {
			super.getHibernateTemplate().saveOrUpdate(entity);
			super.getHibernateTemplate().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("Stored: " + entity);
			}
		} catch (HibernateOptimisticLockingFailureException e) {
			throw new ConcurrencyFailureException(
					"Already modified: " + entity, e);
		}
	}

	protected void delete(E entity) throws DataAccessException {
		super.getHibernateTemplate().delete(entity);
		if (logger.isDebugEnabled()) {
			logger.debug("Deleted: " + entity);
		}
	}

	public void deleteById(Long id) throws DataAccessException {
		Object entity = super.getHibernateTemplate().load(this.domainClass, id);
		super.getHibernateTemplate().delete(entity);
		if (logger.isDebugEnabled()) {
			logger.debug("Deleted by id: " + id);
		}
	}

	public E getById(Long id) throws DataAccessException {
		E entity = (E) super.getHibernateTemplate().get(this.domainClass, id);
		if (logger.isDebugEnabled()) {
			logger.debug("Got " + entity + " by id " + id);
		}
		return entity;
	}

	protected List<E> findAll(String hqlQuery, Object... params)
			throws DataAccessException {
		@SuppressWarnings("unchecked")
		List<E> result = (List<E>) super.getHibernateTemplate().find(hqlQuery,
				params);
		if (logger.isDebugEnabled()) {
			logger.debug("Found " + result.size() + " entities by query: "
					+ hqlQuery);
		}
		return result;
	}

	protected E findOne(String hqlQuery, Object... params)
			throws DataAccessException {
		@SuppressWarnings("unchecked")
		List<E> results = (List<E>) super.getHibernateTemplate().find(hqlQuery,
				params);
		if (results == null || results.isEmpty()) {
			return null;
		} else if (results.size() == 1) {
			E result = results.get(0);
			if (logger.isDebugEnabled()) {
				logger.debug("Found " + result + " by query: " + hqlQuery);
			}
			return result;
		} else {
			throw new IllegalArgumentException(
					"More than one result returned by query: " + hqlQuery);
		}
	}
}