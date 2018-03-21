package org.bsshare.tv.controller.api;

import org.bsshare.tv.model.front.web.sharing.IksRequest;
import org.bsshare.tv.model.front.web.sharing.IksResponse;
import org.bsshare.tv.model.front.web.sharing.Servers;
import org.bsshare.tv.service.SharingSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/unsecured/api/{tenantId}", "/unsecured/api" })
public class SharingApiController {

	@Autowired
	private SharingSubscriptionService subscriptionService;

	@GetMapping("/iks")
	@ResponseBody
	public IksResponse activation(IksRequest iksData, Model model) {
		Servers servers = subscriptionService.activateSharingSubscription(iksData);
		IksResponse IksResponse = new IksResponse();
		IksResponse.setServers(servers);
		return IksResponse;
	}

}
