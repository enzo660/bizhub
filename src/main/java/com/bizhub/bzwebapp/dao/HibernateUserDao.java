package com.bizhub.bzwebapp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.bizhub.bzwebapp.domain.User;

@Transactional(readOnly = true)
public class HibernateUserDao extends AbstractHibernateDao<User> implements
		UserDao {

	public List<User> getAll() throws DataAccessException {
		return super.findAll("from User order by firstName, lastName");
	}

	public User getByEmail(String email) throws DataAccessException {
		return super.findOne("from User where email=?", email);
	}

	@Transactional(readOnly = false)
	@Override
	public void save(User user) throws DataAccessException {
		if (!user.isIdSet()) {
			user.setCreated(new Date());
		}
		super.save(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(User user) throws DataAccessException {
		super.delete(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) throws DataAccessException {
		super.deleteById(id);
	}
}

