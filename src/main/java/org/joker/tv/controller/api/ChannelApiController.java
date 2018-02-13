package org.joker.tv.controller.api;

import java.util.List;

import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.iptv.channel.ChannelsResult;
import org.joker.tv.model.front.web.iptv.vod.Movie;
import org.joker.tv.model.front.web.iptv.vod.VodsResult;
import org.joker.tv.service.ChannelService;
import org.joker.tv.service.IPTVSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unsecured/api/iptv")
public class ChannelApiController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private IPTVSubscriptionService subscriptionService;

	@GetMapping("/channels")
	@ResponseBody
	public ChannelsResult channels(SubscriptionDto device, Model model) {
		if (subscriptionService.isValidIPTVSubscription(device)) {
			ChannelsResult channels = channelService.getChannels();
			return channels;
		}
		return null;
	}

	@GetMapping("/movies")
	@ResponseBody
	public VodsResult vods(SubscriptionDto device, Model model) {
		if (subscriptionService.isValidIPTVSubscription(device)) {
			List<Movie> movies = channelService.getMovies();
			VodsResult vodsResult = new VodsResult();
			vodsResult.setMovies(movies);
			return vodsResult;
		}
		return null;
	}

}
