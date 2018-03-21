package org.bsshare.tv.controller.api;

import java.util.List;

import org.bsshare.tv.common.Constants;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.model.front.web.iptv.channel.ChannelsResult;
import org.bsshare.tv.model.front.web.iptv.vod.Movie;
import org.bsshare.tv.model.front.web.iptv.vod.VodsResult;
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
	public Object channels(SubscriptionDto device, Model model) {
		if (subscriptionService.isValidIPTVSubscription(device)) {
			ChannelsResult channels = channelService.getChannels();
			return channels;
		}
		return Constants.INVALID_SUBSCRIPTION_MESSAGE;
	}

	@GetMapping("/movies")
	@ResponseBody
	public Object vods(SubscriptionDto device, Model model) {
		if (subscriptionService.isValidIPTVSubscription(device)) {
			List<Movie> movies = channelService.getMovies();
			VodsResult vodsResult = new VodsResult();
			vodsResult.setMovies(movies);
			return vodsResult;
		}
		return Constants.INVALID_SUBSCRIPTION_MESSAGE;
	}

}
