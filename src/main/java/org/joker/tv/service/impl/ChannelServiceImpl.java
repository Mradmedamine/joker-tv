package org.joker.tv.service.impl;

import java.net.URI;
import java.util.List;

import org.joker.tv.common.Constants;
import org.joker.tv.model.front.web.Device;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.vod.Movie;
import org.joker.tv.model.front.web.vod.VodsResult;
import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ChannelsResult getChannels(Device product) {
		UriComponentsBuilder uriBuilder = getBasicChannelsUriComponentsBuilder(product).queryParam("page",
		        "channelsList");
		URI url = uriBuilder.build().encode().toUri();
		ChannelsResult channels = restTemplate.getForObject(url, ChannelsResult.class);
		return channels;
	}

	@Override
	public List<Movie> getMovies(Device product, Model model) {
		UriComponentsBuilder uriBuilder = getBasicChannelsUriComponentsBuilder(product).queryParam("category_id", 15)
		        .queryParam("page", "vodMovieList");
		URI url = uriBuilder.build().encode().toUri();
		VodsResult result = restTemplate.getForObject(url, VodsResult.class);
		if (result != null) {
			return result.getMovies();
		}
		return null;
	}

	private UriComponentsBuilder getBasicChannelsUriComponentsBuilder(Device device) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(Constants._7STAR_URL)
		        .queryParam("login", device.getActiveCode()).queryParam("uid", device.getMacAddress())
		        .queryParam("serial", device.getSerialNumber()).queryParam("model", device.getModel());
		return uriBuilder;
	}

//	private String fixUrls(List<Movie> movies) {
//		String noise = "http://62.4.22.34:1980/images/";
//		for(Movie movie : movies) {
//		}
//	}
	
}
