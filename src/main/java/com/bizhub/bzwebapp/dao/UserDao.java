package com.bizhub.bzwebapp.dao;

import org.springframework.dao.DataAccessException;

import com.bizhub.bzwebapp.domain.User;

public interface UserDao extends IdentifiableEntityDao<User>{

    public User getByEmail(String email) throws DataAccessException;

}
