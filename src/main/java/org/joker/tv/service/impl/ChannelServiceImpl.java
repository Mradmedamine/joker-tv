package org.joker.tv.service.impl;

import java.net.URI;
import java.util.List;

import org.joker.tv.common.Constants;
import org.joker.tv.model.front.web.DeviceDto;
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
	public ChannelsResult getChannels(DeviceDto product) {
		UriComponentsBuilder uriBuilder = getBasicChannelsUriComponentsBuilder(product).queryParam("page",
		        "channelsList");
		URI url = uriBuilder.build().encode().toUri();
		ChannelsResult channels = restTemplate.getForObject(url, ChannelsResult.class);
		return channels;
	}

	@Override
	public List<Movie> getMovies(DeviceDto product, Model model) {
		UriComponentsBuilder uriBuilder = getBasicChannelsUriComponentsBuilder(product).queryParam("category_id", 15)
		        .queryParam("page", "vodMovieList");
		URI url = uriBuilder.build().encode().toUri();
		VodsResult result = restTemplate.getForObject(url, VodsResult.class);
		if (result != null) {
			return result.getMovies();
		}
		return null;
	}

	private UriComponentsBuilder getBasicChannelsUriComponentsBuilder(DeviceDto device) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(Constants._7STAR_RUN_URL)
		        .queryParam("login", device.getLogin()).queryParam("uid", device.getUid())
		        .queryParam("serial", device.getSerial()).queryParam("model", device.getModel());
		return uriBuilder;
	}

}
