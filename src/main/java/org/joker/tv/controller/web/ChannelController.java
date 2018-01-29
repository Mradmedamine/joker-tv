package org.joker.tv.controller.web;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
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

	// private static final Logger logger =
	// LoggerFactory.getLogger(ChannelController.class);

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
		return "modules/channels/form";
	}

	@PostMapping("/channels")
	public String channelsList(DeviceDto device, Model model) {
		if (subscriptionService.hasSubscription(device)) {
			ChannelsResult channels = channelService.getChannels();
			model.addAttribute("channels", channels);
		}
		return "modules/channels/dataTable :: content";
	}

	@ResponseBody
	@PostMapping(value = "/channels/upload", consumes = { "multipart/mixed", MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadM3UFile(@RequestParam("physicalFile") MultipartFile file, Model model) throws IOException {
		channelService.processM3uFile(file);
		return StringUtils.EMPTY;
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		return "modules/movies/form";
	}

	@PostMapping("/movies")
	public String moviesList(DeviceDto product, Model model) {
		model.addAttribute("movies", channelService.getMoviesFromRemoteUrl(product, model));
		return "modules/movies/dataTable :: content";
	}

}
