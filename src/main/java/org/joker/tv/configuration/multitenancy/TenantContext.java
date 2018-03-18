package org.joker.tv.configuration.multitenancy;

import java.util.Optional;

import org.joker.tv.model.front.web.Tenant;
import org.joker.tv.model.front.web.UserTenantDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class TenantContext {

	public static final String ANONYMOUS_TENANT_ID = Tenant.ANONYMOUS.getId();
	public static final String DEFAULT_TENANT_ID = Tenant.JOKER.getId();
	
	private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

	public static void setCurrentTenant(String tenantId) {
		logger.debug("Setting tenant to " + tenantId);
		Optional<Tenant> tenant = Tenant.fromId(tenantId);
		getUserTenantDetails().ifPresent(e -> 
		e.setTenant(tenant.orElse(Tenant.ANONYMOUS)));
	}

	public static void setDefaultTenant() {
		setCurrentTenant(DEFAULT_TENANT_ID);
	}

	public static String getCurrentTenant() {
		Optional<UserTenantDetails> userTenantDetails = getUserTenantDetails();
		if (userTenantDetails.isPresent()) {
			return userTenantDetails.get().getTenant().getId();
		}
		return ANONYMOUS_TENANT_ID;
	}

	private static Optional<UserTenantDetails> getUserTenantDetails() {
		Object userDetails = Optional.ofNullable(SecurityContextHolder.getContext())
				.map(x -> x.getAuthentication())
				.map(x -> x.getPrincipal())
				.orElse(null);
		if (userDetails instanceof UserTenantDetails) {
			return Optional.of(((UserTenantDetails) userDetails));
		}
		return Optional.empty();
	}
}