package org.bsshare.tv.controller.tenant;

import org.bsshare.tv.configuration.multitenancy.TenantContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internal/multitenancy")
public class MultiTenancyController {

	@GetMapping("/{tenantId}")
	public String switchTenant(@PathVariable("tenantId") String tenantId) {
		TenantContext.setCurrentTenant(tenantId);
		return "redirect:/";
	}

}