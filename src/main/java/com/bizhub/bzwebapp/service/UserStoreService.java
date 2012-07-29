package com.bizhub.bzwebapp.service;

import com.bizhub.bzwebapp.domain.User;

public interface UserStoreService {
	public void store(User user, String password);
}