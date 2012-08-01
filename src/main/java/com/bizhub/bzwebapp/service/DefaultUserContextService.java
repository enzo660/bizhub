package com.bizhub.bzwebapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bizhub.bzwebapp.domain.User;
import com.bizhub.bzwebapp.security.DefaultUserDetails;

public class DefaultUserContextService implements UserContextService {

	private static final Log logger = LogFactory
			.getLog(DefaultUserContextService.class);

	public User getUserFromContext() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Cannot get authenticated contact. Security context is not initialized.");
			}
			return null;
		}
		Authentication auth = ctx.getAuthentication();
		if (auth == null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Cannot get authenticated contact. Authentication info is not present.");
			}
			return null;
		}
		Object principal = auth.getPrincipal();
		if (principal == null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Cannot get authenticated contact. Principal info is not present.");
			}
			return null;
		}
		if (principal instanceof DefaultUserDetails) {
			return ((DefaultUserDetails) principal).getUser();
		} else if (principal instanceof String) {
			if (logger.isTraceEnabled()) {
				logger.trace("Not authenticated: [" + principal + "]");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Cannot get authenticated contact from authenticated principal ["
						+ principal + "]");
			}
		}
		return null;
	}
}