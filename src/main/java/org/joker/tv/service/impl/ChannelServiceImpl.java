package org.joker.tv.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.iptv.m3u.Encoding;
import org.iptv.m3u.Format;
import org.iptv.m3u.ParsingMode;
import org.iptv.m3u.PlaylistParser;
import org.iptv.m3u.data.Playlist;
import org.iptv.m3u.data.TrackData;
import org.joker.tv.common.util.MappingUtils;
import org.joker.tv.model.entity.BaseChannel;
import org.joker.tv.model.entity.ChannelEntity;
import org.joker.tv.model.entity.Vod;
import org.joker.tv.model.front.web.iptv.channel.ChannelsResult;
import org.joker.tv.model.front.web.iptv.channel.TVChannel;
import org.joker.tv.model.front.web.iptv.vod.Movie;
import org.joker.tv.repository.ChannelRepository;
import org.joker.tv.repository.VodRepository;
import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private VodRepository vodRepository;

	@Override
	public void extractChannelsFromM3uFile(MultipartFile multipart) {
		List<ChannelEntity> channels = processM3UFile(multipart, ChannelEntity.class);
		channelRepository.deleteAll();
		channelRepository.save(channels);
	}

	@Override
	public void extractVodsFromM3uFile(MultipartFile multipart) {
		List<Vod> vods = processM3UFile(multipart, Vod.class);
		vodRepository.deleteAll();
		vodRepository.save(vods);
	}

	private <T extends BaseChannel> List<T> processM3UFile(MultipartFile multipart, Class<T> clazz) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(multipart.getBytes());
			PlaylistParser m3uParser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8,
			        ParsingMode.LENIENT);
			Playlist playlist = m3uParser.parse();
			inputStream.close();
			return buildChannelList(playlist, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private <T extends BaseChannel> List<T> buildChannelList(Playlist playlist, Class<T> clazz)
	        throws InstantiationException, IllegalAccessException {
		List<T> entityChannels = new ArrayList<>();
		for (TrackData trackData : playlist.getMediaPlaylist().getTracks()) {
			String name = "";
			if (trackData.getTrackInfo() != null) {
				name = trackData.getTrackInfo().getTitle();
			}
			String url = trackData.getUri();
			T channelOb = newChannelObject(clazz, name, url);
			entityChannels.add(channelOb);
		}
		return entityChannels;
	}

	private <T extends BaseChannel> T newChannelObject(Class<T> clazz, String name, String url)
	        throws InstantiationException, IllegalAccessException {
		T channelOb = clazz.newInstance();
		channelOb.setStreaming_url(url);
		channelOb.setCaption(name);
		return channelOb;
	}

	@Override
	public ChannelsResult getChannels() {
		List<ChannelEntity> channels = channelRepository.findAll();
		ChannelsResult channelsResult = new ChannelsResult();
		List<TVChannel> tvChannels = new ArrayList<TVChannel>(channels.size());
		channels.forEach(channel -> tvChannels.add(MappingUtils.map(channel, TVChannel.class)));
		channelsResult.setTv_channel(tvChannels);
		return channelsResult;
	}

	@Override
	public List<Movie> getMovies() {
		List<Vod> vods = vodRepository.findAll();
		List<Movie> movies = new ArrayList<Movie>(vods.size());
		vods.forEach(vod -> {
			Movie movie = new Movie();
			movie.setCaption(vod.getCaption());
			movie.setV_url(vod.getStreaming_url());
			movies.add(movie);
		});
		return movies;
	}

}
