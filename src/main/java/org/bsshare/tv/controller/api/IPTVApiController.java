package org.bsshare.tv.controller.api;

import org.bsshare.tv.model.front.web.ActivationResult;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.service.IPTVSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/unsecured/api/{tenantId}", "/unsecured/api" })
public class IPTVApiController<T> {

	@Autowired
	private IPTVSubscriptionService activationService;

	@GetMapping("/activation")
	@ResponseBody
	public ActivationResult activation(SubscriptionDto device, Model model) {
		return activationService.activateIPTVSubscription(device);
	}

}
