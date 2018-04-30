package org.bsshare.tv.service;

import java.util.Optional;

import org.bsshare.tv.model.front.web.UserTenantDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SecurityService {

	void autologin(String username, String password);

	public static String findLoggedInUsername() {
		return getUserTenantDetails().map(x -> x.getUsername()).orElse(null);
	}

	public static Optional<UserTenantDetails> getUserTenantDetails() {
		Object userDetails = Optional.ofNullable(SecurityContextHolder.getContext()).map(x -> x.getAuthentication())
				.map(x -> x.getPrincipal()).orElse(null);
		if (userDetails instanceof UserTenantDetails) {
			return Optional.of(((UserTenantDetails) userDetails));
		}
		return Optional.empty();
	}

}
