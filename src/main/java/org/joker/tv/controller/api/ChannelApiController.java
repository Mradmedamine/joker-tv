package org.joker.tv.controller.api;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.iptv.channel.ChannelsResult;
import org.joker.tv.service.ChannelService;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unsecured/api")
public class ChannelApiController {

	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping("/channels")
	@ResponseBody
	public ChannelsResult channels(DeviceDto device, Model model) {
		if (subscriptionService.hasIPTVSubscription(device)) {
			ChannelsResult channels = channelService.getChannels();
			return channels;
		}
		return null;
	}

}
