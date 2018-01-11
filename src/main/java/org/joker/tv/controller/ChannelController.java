package org.joker.tv.controller;

import java.net.URI;

import org.joker.tv.model.ChannelsResult;
import org.joker.tv.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/channels")
public class ChannelController
{

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public String channels(Model model)
	{
		return "modules/channels/form";
	}

	@PostMapping
	public String channelsList(Product product, Model model)
	{
		UriComponentsBuilder uriBuilder =
			UriComponentsBuilder.fromHttpUrl(Constants.CHANNELS_URL_7STAR).queryParam("login", product.getActiveCode()).queryParam("page", "channelsList")
				.queryParam("uid", product.getMacAddress()).queryParam("serial", product.getSerialNumber()).queryParam("model", product.getModel());
		URI url = uriBuilder.build().encode().toUri();
		ChannelsResult channels = restTemplate.getForEntity(url, ChannelsResult.class).getBody();
		model.addAttribute("channels", channels);
		return "modules/channels/dataTable :: content";
	}
}
