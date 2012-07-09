package com.bizhub.bzwebapp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bizhub.bzwebapp.domain.IdentifiableEntity;


public interface IdentifiableEntityDao<E extends IdentifiableEntity> {
	public E getById(Long id) throws DataAccessException;

	public List<E> getAll() throws DataAccessException;

	public void save(E site) throws DataAccessException;

	public void delete(E site) throws DataAccessException;

	public void deleteById(Long id) throws DataAccessException;
}
