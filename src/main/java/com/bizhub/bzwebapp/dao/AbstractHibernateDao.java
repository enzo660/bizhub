package com.bizhub.bzwebapp.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbstractHibernateDao {
	private static final Log classLogger = LogFactory
			.getLog(AbstractHibernateDao.class);

	private static final SessionFactory sessionFactory;

	static {
		try {
			if (classLogger.isDebugEnabled()) {
				classLogger.debug("Initializing Hibernate session factory");
			}
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			if (classLogger.isDebugEnabled()) {
				classLogger.debug("Initialized Hibernate session factory");
			}
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	protected final Log logger = LogFactory.getLog(this.getClass());

	protected Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	protected void save(Object entity) throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					session.saveOrUpdate(entity);
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Stored: " + entity);
					}
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to save: " + entity, e);
		}
	}

	protected void delete(Object entity) throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					session.delete(entity);
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Deleted: " + entity);
					}
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to delete: " + entity, e);
		}
	}

	protected void deleteById(Class<?> clazz, Serializable id)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					session.delete(session.load(clazz, id));
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Deleted by id: " + id);
					}
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to delete by id: " + id, e);
		}
	}

	protected Object getById(Class<?> clazz, Serializable id)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					Object result = session.get(clazz, id);
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Got " + result + " by id " + id);
					}
					return result;
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to get by id: " + id, e);
		}
	}

	protected List<?> findAll(String hqlQuery, Object... params)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					Query query = session.createQuery(hqlQuery);
					this.initQueryParams(query, params);
					List<?> result = query.list();
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Found " + result.size()
								+ " entities by query: " + hqlQuery);
					}
					return result;
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to find all using query: "
					+ hqlQuery, e);
		}
	}

	protected Object findOne(String hqlQuery, Object... params)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			try {
				session.beginTransaction();
				try {
					Query query = session.createQuery(hqlQuery);
					this.initQueryParams(query, params);
					Object result = query.uniqueResult();
					session.getTransaction().commit();
					if (logger.isDebugEnabled()) {
						logger.debug("Found " + result + " by query: "
								+ hqlQuery);
					}
					return result;
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} catch (RuntimeException e) {
			throw new DataAccessException("Failed to find one using query: "
					+ hqlQuery, e);
		}
	}

	private void initQueryParams(Query query, Object... params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}
}