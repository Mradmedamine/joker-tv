package org.bsshare.tv.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bsshare.tv.common.util.MappingUtils;
import org.bsshare.tv.model.entity.BaseChannel;
import org.bsshare.tv.model.entity.CategoryEntity;
import org.bsshare.tv.model.entity.ChannelEntity;
import org.bsshare.tv.model.entity.Vod;
import org.bsshare.tv.model.front.web.iptv.channel.ChannelsResult;
import org.bsshare.tv.model.front.web.iptv.channel.TVCategories;
import org.bsshare.tv.model.front.web.iptv.channel.TVCategory;
import org.bsshare.tv.model.front.web.iptv.channel.TVChannel;
import org.bsshare.tv.model.front.web.iptv.vod.Movie;
import org.bsshare.tv.repository.CategoryRepository;
import org.bsshare.tv.repository.ChannelRepository;
import org.bsshare.tv.repository.VodRepository;
import org.bsshare.tv.service.ChannelService;
import org.iptv.m3u.Encoding;
import org.iptv.m3u.Format;
import org.iptv.m3u.ParsingMode;
import org.iptv.m3u.PlaylistParser;
import org.iptv.m3u.data.Playlist;
import org.iptv.m3u.data.TrackData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private VodRepository vodRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ChannelsResult getChannels() {
		List<ChannelEntity> channels = channelRepository.findAll();
		ChannelsResult channelsResult = new ChannelsResult();
		List<TVChannel> tvChannels = new ArrayList<TVChannel>(channels.size());
		channels.forEach(channel -> {
			TVChannel tvChannel = MappingUtils.map(channel, TVChannel.class);
			TVCategory tvCategory = new TVCategory();
			tvCategory.setCaption(channel.getCategory().getCaption());
			tvCategory.setId(channel.getCategory().getId().toString());
			tvChannel.setTv_categories(Collections.singletonList(new TVCategories()));
			tvChannel.getTv_categories().get(0).setTv_category(Collections.singletonList(tvCategory));
			tvChannels.add(tvChannel);
		});
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
		categoryRepository.deleteAll();
		CategoryEntity currentCategory = null;
		for (TrackData trackData : playlist.getMediaPlaylist().getTracks()) {
			String name = "";
			if (trackData.getTrackInfo() != null) {
				name = trackData.getTrackInfo().getTitle();
				if (isCategoryItem(name)) {
					currentCategory = new CategoryEntity();
					currentCategory.setCaption(name);
					currentCategory = categoryRepository.save(currentCategory);
					continue;
				}
			}
			String url = trackData.getUri();
			T channelObject = newChannelObject(clazz, name, url);
			channelObject.setCategory(currentCategory);
			entityChannels.add(channelObject);
		}
		return entityChannels;
	}

	private boolean isCategoryItem(String name) {
		return name.contains("*-*-*");
	}

	private <T extends BaseChannel> T newChannelObject(Class<T> clazz, String name, String url)
			throws InstantiationException, IllegalAccessException {
		T channelObject = clazz.newInstance();
		channelObject.setStreaming_url(url);
		channelObject.setCaption(name);
		return channelObject;
	}

}
