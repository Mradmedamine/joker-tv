package org.bsshare.tv.service.impl;

import java.util.HashSet;

import org.bsshare.tv.configuration.multitenancy.TenantContext;
import org.bsshare.tv.model.entity.User;
import org.bsshare.tv.model.front.web.Tenant;
import org.bsshare.tv.repository.RoleRepository;
import org.bsshare.tv.repository.UserRepository;
import org.bsshare.tv.service.SecurityService;
import org.bsshare.tv.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
		TenantContext.setDefaultTenant();
	}

	@Override
	public User findByUsername(String username) {
		TenantContext.setCurrentTenant(Tenant.ANONYMOUS.getId());
		return userRepository.findByUsername(username);
	}

	@Override
	public void changePassword(String password) {
		logger.debug("changing password for current user");
		User currentUser = this.findByUsername(SecurityService.findLoggedInUsername());
		currentUser.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(currentUser);
		TenantContext.setDefaultTenant();
	}
}
