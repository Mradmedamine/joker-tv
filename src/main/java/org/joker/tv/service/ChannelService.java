package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.vod.Movie;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface ChannelService {

	ChannelsResult getChannels();

	void processM3uFile(MultipartFile file);
	
	ChannelsResult getChannelsFromRemoteUrl(DeviceDto product);

	List<Movie> getMoviesFromRemoteUrl(DeviceDto product, Model model);



}
