package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.vod.Movie;
import org.springframework.ui.Model;

public interface ChannelService {

	ChannelsResult getChannels(DeviceDto product);

	List<Movie> getMovies(DeviceDto product, Model model);

}
