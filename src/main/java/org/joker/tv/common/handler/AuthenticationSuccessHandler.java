package org.joker.tv.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joker.tv.configuration.multitenancy.TenantContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	public AuthenticationSuccessHandler() {
		super("/");
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		TenantContext.setDefaultTenant();
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
