package org.bsshare.tv.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
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
import org.bsshare.tv.model.front.web.iptv.vod.VodCategories;
import org.bsshare.tv.model.front.web.iptv.vod.VodCategory;
import org.bsshare.tv.model.front.web.iptv.vod.VodsResult;
import org.bsshare.tv.repository.CategoryRepository;
import org.bsshare.tv.repository.ChannelRepository;
import org.bsshare.tv.repository.VodRepository;
import org.bsshare.tv.service.ChannelService;
import org.iptv.m3u.Encoding;
import org.iptv.m3u.Format;
import org.iptv.m3u.ParseException;
import org.iptv.m3u.ParsingMode;
import org.iptv.m3u.PlaylistException;
import org.iptv.m3u.PlaylistParser;
import org.iptv.m3u.data.Playlist;
import org.iptv.m3u.data.TrackData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.w3ma.m3u8parser.data.Track;
import com.w3ma.m3u8parser.exception.PlaylistParseException;
import com.w3ma.m3u8parser.parser.M3U8Parser;
import com.w3ma.m3u8parser.scanner.M3U8ItemScanner;

@Service
public class ChannelServiceImpl implements ChannelService {

	public static final String TVG_TAG = "tvg-";
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
		List<TVChannel> tvChannels = new ArrayList<>(channels.size());
		TVCategories tvCategories = new TVCategories();
		channels.forEach(channel -> {
			TVChannel tvChannel = MappingUtils.map(channel, TVChannel.class);
			CategoryEntity channelCategory = channel.getCategory();
			if (channelCategory != null) {
				TVCategory tvCategory = new TVCategory();
				tvCategory.setCaption(channelCategory.getCaption());
				tvCategory.setIcon_url(channelCategory.getIcon_url());
				tvCategory.setId(channelCategory.getId().toString());
				tvChannel.setTv_categories(Collections.singletonList(new TVCategories()));
				tvChannel.getTv_categories().get(0).setTv_category(Collections.singletonList(tvCategory));
				tvCategories.getTv_category().add(tvCategory);
			}
			tvChannels.add(tvChannel);
		});
		channelsResult.setTv_channel(tvChannels);
		channelsResult.setTv_categories(tvCategories);
		return channelsResult;
	}

	@Override
	public VodsResult getMovies() {
		VodsResult vodsResult = new VodsResult();
		VodCategories vodCategories = new VodCategories();
		List<Vod> vods = vodRepository.findAll();
		List<Movie> movies = new ArrayList<Movie>(vods.size());
		vods.forEach(vod -> {
			Movie movie = new Movie();
			CategoryEntity category = vod.getCategory();
			if (category != null) {
				VodCategory vodCategory = new VodCategory();
				vodCategory.setCaption(category.getCaption());
				vodCategory.setIcon_url(category.getIcon_url());
				vodCategory.setId(category.getId().toString());
				movie.setVod_category(vodCategory);
				vodCategories.getVod_category().add(vodCategory);
			}
			movie.setCaption(vod.getCaption());
			movie.setV_url(vod.getStreaming_url());
			movie.setPoster_url(vod.getIcon_url());
			movies.add(movie);
		});
		vodsResult.setMovies(movies);
		vodsResult.setVod_categories(vodCategories);
		return vodsResult;
	}

	@Override
	@Transactional
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
			String fileContent = IOUtils.toString(multipart.getBytes(), "UTF-8");
			if (fileContent.contains(TVG_TAG)) {
				return parseTvgM3U(clazz, inputStream);
			} else {
				return parseSimpleM3u(clazz, inputStream);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private <T extends BaseChannel> List<T> parseTvgM3U(Class<T> clazz, ByteArrayInputStream inputStream)
			throws IOException, java.text.ParseException, PlaylistParseException {
		M3U8Parser parser = new M3U8Parser(inputStream, M3U8ItemScanner.Encoding.UTF_8);
		com.w3ma.m3u8parser.data.Playlist playlist = parser.parse();
		inputStream.close();
		return buildChannelList(playlist, clazz);
	}

	private <T extends BaseChannel> List<T> parseSimpleM3u(Class<T> clazz, ByteArrayInputStream inputStream)
			throws IOException, ParseException, PlaylistException {
		PlaylistParser simpleM3uParser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8,
				ParsingMode.LENIENT);
		Playlist playlist = simpleM3uParser.parse();
		inputStream.close();
		return buildChannelList(playlist, clazz);
	}

	private <T extends BaseChannel> List<T> buildChannelList(Playlist playlist, Class<T> clazz) {
		List<T> entityChannels = new ArrayList<>();
		CategoryEntity currentCategory = null;
		for (TrackData trackData : playlist.getMediaPlaylist().getTracks()) {
			String name = "";
			if (trackData.getTrackInfo() != null) {
				name = trackData.getTrackInfo().getTitle();
				if (isCategoryItem(name)) {
					currentCategory = new CategoryEntity();
					currentCategory.setCaption(trimCategoryName(name));
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

	private <T extends BaseChannel> List<T> buildChannelList(com.w3ma.m3u8parser.data.Playlist playlist,
			Class<T> clazz) {
		List<T> entityChannels = new ArrayList<>();
		CategoryEntity currentCategory;
		for (Map.Entry<String, Set<Track>> entry : playlist.getTrackSetMap().entrySet()) {
			Optional<Track> category = entry.getValue().stream()
					.filter(e -> isCategoryItem(e.getExtInfo().getTvgName())).findFirst();
			currentCategory = new CategoryEntity();
			if (category.isPresent()) {
				currentCategory.setIcon_url(category.get().getExtInfo().getTvgLogoUrl());
				currentCategory.setCaption(category.get().getExtInfo().getTvgName());
				entry.getValue().removeIf(e -> isCategoryItem(e.getExtInfo().getTvgName()));
			} else {
				currentCategory.setCaption(entry.getValue().stream().findFirst().get().getExtInfo().getGroupTitle());
			}
			currentCategory = categoryRepository.save(currentCategory);

			for (Track track : entry.getValue()) {
				T channelObject = newChannelObject(clazz, track.getExtInfo().getTvgName(), track.getUrl());
				channelObject.setIcon_url(track.getExtInfo().getTvgLogoUrl());
				channelObject.setCategory(currentCategory);
				entityChannels.add(channelObject);
			}
		}
		return entityChannels;
	}

	private String trimCategoryName(String name) {
		return name.replace("*", "").replace("-", "").replace("_", "");
	}

	private boolean isCategoryItem(String name) {
		return name.contains("*-") || name.contains("--");
	}

	private <T extends BaseChannel> T newChannelObject(Class<T> clazz, String name, String url) {
		try {
			T channelObject = clazz.newInstance();
			channelObject.setStreaming_url(url);
			channelObject.setCaption(name);
			return channelObject;
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new RuntimeException("Instantiation error", ex);
		}
	}

}
