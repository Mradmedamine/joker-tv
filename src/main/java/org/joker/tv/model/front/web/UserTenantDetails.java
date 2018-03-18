package org.joker.tv.model.front.web;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserTenantDetails extends User {

	private static final long serialVersionUID = -4462202991100777854L;

	private Tenant tenant;

	public UserTenantDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
