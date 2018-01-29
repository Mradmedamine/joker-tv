package org.joker.tv.controller.api;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.iks.IksResponse;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unsecured/api")
public class IksApiController {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping("/iks")
	@ResponseBody
	public IksResponse iks(DeviceDto device, Model model) {
		IksResponse IksResponse = new IksResponse();
		IksResponse.setServers(subscriptionService.getIksData(device));
		return IksResponse;
	}

}
