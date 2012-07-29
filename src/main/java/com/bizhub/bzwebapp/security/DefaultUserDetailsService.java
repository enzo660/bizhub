package com.bizhub.bzwebapp.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bizhub.bzwebapp.dao.UserDao;
import com.bizhub.bzwebapp.domain.User;

public class DefaultUserDetailsService implements UserDetailsService {
	private static final Log logger = LogFactory
			.getLog(DefaultUserDetailsService.class);

	private final UserDao dao;

	@Autowired
	public DefaultUserDetailsService(UserDao dao) {
		this.dao = dao;
	}

	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Loading user details for [" + email + "]");
		}
		try {
			User user = this.dao.getByEmail(email);
			if (user == null) {
				if (logger.isDebugEnabled()) {
					logger.debug("No user found by email [" + email
							+ "]. Nothing to load.");
				}
				throw new UsernameNotFoundException(email);
			}
			return new DefaultUserDetails(user);
		} catch (DataAccessException e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Failed to load user by email [" + email
						+ "] due to a data access error", e);
			}
			throw e;
		}
	}
}