package org.bsshare.tv.service;

import org.bsshare.tv.model.entity.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);
}
