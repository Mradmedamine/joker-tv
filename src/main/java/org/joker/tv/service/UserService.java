package org.joker.tv.service;

import org.joker.tv.model.domain.entity.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);
}
