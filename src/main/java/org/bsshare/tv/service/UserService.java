package org.bsshare.tv.service;

import org.bsshare.tv.model.entity.User;

public interface UserService {

	void saveUser(User user);

	User findByUsername(String username);
	
	void changePassword(String password);
	
}
