package org.bsshare.tv.controller.api;

import org.bsshare.tv.common.Constants;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.service.ChannelService;
import org.bsshare.tv.service.IPTVSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/unsecured/api/{tenantId}", "/unsecured/api" })
public class ChannelApiController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private IPTVSubscriptionService subscriptionService;

	@GetMapping("/channels")
	@ResponseBody
	public Object channels(SubscriptionDto subscriptionDto, Model model) {
		if (subscriptionService.isValidIPTVSubscription(subscriptionDto)) {
			return channelService.getChannels();
		}
		return Constants.INVALID_SUBSCRIPTION_MESSAGE;
	}

	@GetMapping("/movies")
	@ResponseBody
	public Object vods(SubscriptionDto device, Model model) {
		if (subscriptionService.isValidIPTVSubscription(device)) {
			return channelService.getMovies();
		}
		return Constants.INVALID_SUBSCRIPTION_MESSAGE;
	}

}
