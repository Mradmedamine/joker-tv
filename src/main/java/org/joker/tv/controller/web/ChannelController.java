package org.joker.tv.controller.web;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.iptv.channel.ChannelsResult;
import org.joker.tv.model.front.web.iptv.vod.Movie;
import org.joker.tv.service.ChannelService;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:/channels";
	}

	@GetMapping("/channels")
	public String channels(Model model) {
		ChannelsResult channels = channelService.getChannels();
		model.addAttribute("channels", channels);
		return "modules/channels/main";
	}

	@PostMapping("/channels")
	public String channelsList(DeviceDto device, Model model) {
		if (subscriptionService.hasIPTVSubscription(device)) {
			ChannelsResult channels = channelService.getChannels();
			model.addAttribute("channels", channels);
		}
		return "modules/channels/dataTable :: content";
	}

	@ResponseBody
	@PostMapping(value = "/channels/upload", consumes = { "multipart/mixed", MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadChannelsM3UFile(@RequestParam("physicalFile") MultipartFile file, Model model) throws IOException {
		channelService.extractChannelsFromM3uFile(file);
		return StringUtils.EMPTY;
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		List<Movie> movies = channelService.getMovies();
		model.addAttribute("movies", movies);
		return "modules/movies/main";
	}

	@ResponseBody
	@PostMapping(value = "/movies/upload", consumes = { "multipart/mixed", MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadMovieM3UFile(@RequestParam("physicalFile") MultipartFile file, Model model) throws IOException {
		channelService.extractVodsFromM3uFile(file);
		return StringUtils.EMPTY;
	}

}
