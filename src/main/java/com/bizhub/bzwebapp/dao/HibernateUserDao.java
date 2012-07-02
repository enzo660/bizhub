package com.bizhub.bzwebapp.dao;

import java.util.Date;
import java.util.List;

import com.bizhub.bzwebapp.domain.User;

public class HibernateUserDao extends AbstractHibernateDao implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> getAll() throws DataAccessException {
		return (List<User>) super
				.findAll("from User order by firstName, lastName");
	}

	public User getByEmail(String email) throws DataAccessException {
		return (User) super.findOne("from User where email=?", email);
	}

	public User getById(Long id) throws DataAccessException {
		return (User) super.getById(User.class, id);
	}

	public void save(User user) throws DataAccessException {
		if (!user.isIdSet()) {
			user.setCreated(new Date());
		}
		super.save(user);
	}

	public void delete(User user) throws DataAccessException {
		super.delete(user);
	}

	public void deleteById(Long id) throws DataAccessException {
		super.deleteById(User.class, id);
	}
}
