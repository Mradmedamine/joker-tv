package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.front.web.Device;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.model.front.web.vod.Movie;
import org.springframework.ui.Model;

public interface ChannelService {

	ChannelsResult getChannels(Device product);

	List<Movie> getMovies(Device product, Model model);

}
