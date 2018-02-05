package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.front.web.iptv.channel.ChannelsResult;
import org.joker.tv.model.front.web.iptv.vod.Movie;
import org.springframework.web.multipart.MultipartFile;

public interface ChannelService {

	void extractChannelsFromM3uFile(MultipartFile file);

	void extractVodsFromM3uFile(MultipartFile multipart);

	ChannelsResult getChannels();

	List<Movie> getMovies();

}
