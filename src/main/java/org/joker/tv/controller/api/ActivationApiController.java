package org.joker.tv.controller.api;

import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured/api")
public class ActivationApiController {

	@Autowired
	private SubscriptionService activationService;

	@GetMapping("/activation")
	@ResponseBody
	public ActivationResult activation(DeviceDto device, Model model) {
		return activationService.activateIPTVSubscription(device);
	}

}
