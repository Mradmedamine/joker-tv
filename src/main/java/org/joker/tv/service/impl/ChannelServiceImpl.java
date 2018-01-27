package org.joker.tv.service.impl;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.iptv.m3u.Encoding;
import org.iptv.m3u.Format;
import org.iptv.m3u.M3uParser;
import org.iptv.m3u.ParsingMode;
import org.iptv.m3u.PlaylistParser;
import org.iptv.m3u.data.Playlist;
import org.iptv.m3u.data.TrackData;
import org.joker.tv.common.Constants;
import org.joker.tv.common.util.MappingUtils;
import org.joker.tv.model.entity.Channel;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.channel.TVChannel;
import org.joker.tv.model.front.web.vod.Movie;
import org.joker.tv.model.front.web.vod.VodsResult;
import org.joker.tv.repository.ChannelRepository;
import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public void processChannelsFile(MultipartFile multipart) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(multipart.getBytes());
			PlaylistParser m3uParser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8,
			        ParsingMode.LENIENT);
			Playlist playlist = m3uParser.parse();
			inputStream.close();
			List<Channel> entityChannels = new ArrayList<>();
			for (TrackData trackData : playlist.getMediaPlaylist().getTracks()) {
				String name = "";
				if (trackData.getTrackInfo() != null) {
					name = trackData.getTrackInfo().getTitle();
				}
				String url = trackData.getUri();
				entityChannels.add(new Channel(name, url));
			}
			channelRepository.save(entityChannels);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public ChannelsResult getChannels() {
		List<Channel> channels = channelRepository.findAll();
		ChannelsResult channelsResult = new ChannelsResult();
		List<TVChannel> tvChannels = new ArrayList<TVChannel>(channels.size());
		channels.forEach(channel -> tvChannels.add(MappingUtils.map(channel, TVChannel.class)));
		channelsResult.setTv_channel(tvChannels);
		return channelsResult;
	}

	@Override
	public ChannelsResult getChannelsFromRemoteUrl(DeviceDto product) {
		UriComponentsBuilder uriBuilder = getBasicChannelsUriComponentsBuilder(product).queryParam("page",
		        "channelsList");
		URI url = uriBuilder.build().encode().toUri();
		ChannelsResult channels = restTemplate.getForObject(url, ChannelsResult.class);
		return channels;
	}

	@Override
	public List<Movie> getMoviesFromRemoteUrl(DeviceDto product, Model model) {
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
