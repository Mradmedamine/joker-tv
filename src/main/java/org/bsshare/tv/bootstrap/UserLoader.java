package org.bsshare.tv.bootstrap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.bsshare.tv.model.entity.Role;
import org.bsshare.tv.model.entity.User;
import org.bsshare.tv.model.front.web.RoleEnum;
import org.bsshare.tv.repository.RoleRepository;
import org.bsshare.tv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserLoader extends BaseDataLoader {

	private static Logger log = Logger.getLogger(UserLoader.class);

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isCreateMode()) {
			populateUsersData();
		}
	}

	private void populateUsersData() {
		Role userRole = new Role();
		userRole.setName(RoleEnum.USER_ROLE.getValue());
		userRole = roleRepository.save(userRole);
		log.info("Saved Role :" + RoleEnum.USER_ROLE.getValue() + " id: " + userRole.getId());

		Role adminRole = new Role();
		adminRole.setName(RoleEnum.ADMIN_ROLE.getValue());
		adminRole = roleRepository.save(adminRole);
		log.info("Saved Role :" + RoleEnum.ADMIN_ROLE.getValue() + " id: " + adminRole.getId());

		User user = new User();
		user.setUsername("bsshareAdmin");
		user.setPassword(bCryptPasswordEncoder.encode("bsshare"));
		user.setPasswordConfirm(bCryptPasswordEncoder.encode("bsshare"));
		user.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
		user = userRepository.save(user);

		log.info("Saved User - id: " + user.getId());

		userRole.setUsers(Collections.singleton(user));
		roleRepository.save(userRole);
	}
}
