package org.joker.tv.controller.web;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChannelController {

	@Autowired
	ChannelService channelService;

	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:/channels";
	}

	@GetMapping("/channels")
	public String channels(Model model) {
		return "modules/channels/form";
	}

	@PostMapping("/channels")
	public String channelsList(DeviceDto product, Model model) {
		ChannelsResult channels = channelService.getChannels();
		model.addAttribute("channels", channels);
		return "modules/channels/dataTable :: content";
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		return "modules/movies/form";
	}

	@PostMapping("/movies")
	public String moviesList(DeviceDto product, Model model) {
		model.addAttribute("movies", channelService.getMovies(product, model));
		return "modules/movies/dataTable :: content";
	}

}
