package org.bsshare.tv.service;

import org.bsshare.tv.model.entity.User;
import org.bsshare.tv.model.front.web.SignUpUser;

public interface UserService {

	void saveUser(SignUpUser user);

	User findByUsername(String username);

	void changePassword(String password);

}
