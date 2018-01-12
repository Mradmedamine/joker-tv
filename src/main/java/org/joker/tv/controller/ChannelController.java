package org.joker.tv.controller;

import java.net.URI;

import org.joker.tv.model.front.web.Device;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.vod.VodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ChannelController
{

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/channels")
	public String channels(Model model)
	{
		return "modules/channels/form";
	}

	@PostMapping("/channels")
	public String channelsList(Device product, Model model)
	{
		UriComponentsBuilder uriBuilder =
			UriComponentsBuilder.fromHttpUrl(Constants.CHANNELS_URL_7STAR).queryParam("login", product.getActiveCode()).queryParam("page", "channelsList")
				.queryParam("uid", product.getMacAddress()).queryParam("serial", product.getSerialNumber()).queryParam("model", product.getModel());
		URI url = uriBuilder.build().encode().toUri();
		ChannelsResult channels = restTemplate.getForEntity(url, ChannelsResult.class).getBody();
		model.addAttribute("channels", channels);
		return "modules/channels/dataTable :: content";
	}

	@GetMapping("/movies")
	public String movies(Model model)
	{
		return "modules/movies/form";
	}

	@PostMapping("/movies")
	public String moviesList(Device product, Model model)
	{
		UriComponentsBuilder uriBuilder =
			UriComponentsBuilder.fromHttpUrl(Constants.CHANNELS_URL_7STAR).queryParam("login", product.getActiveCode()).queryParam("page", "vodsMovieList")
				.queryParam("uid", product.getMacAddress()).queryParam("serial", product.getSerialNumber()).queryParam("category_id", 15)
				.queryParam("model", product.getModel());
		URI url = uriBuilder.build().encode().toUri();
		VodsResult result = restTemplate.getForEntity(url, VodsResult.class).getBody();
		if (result != null)
		{
			model.addAttribute("movies", result.getMovies());
		}
		return "modules/movies/dataTable :: content";
	}

}
