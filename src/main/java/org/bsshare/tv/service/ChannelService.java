package org.bsshare.tv.service;

import org.bsshare.tv.model.front.web.iptv.channel.ChannelsResult;
import org.bsshare.tv.model.front.web.iptv.vod.VodsResult;
import org.springframework.web.multipart.MultipartFile;

public interface ChannelService {

	void extractChannelsFromM3uFile(MultipartFile file);

	void extractVodsFromM3uFile(MultipartFile multipart);

	ChannelsResult getChannels();

	VodsResult getMovies();

}
