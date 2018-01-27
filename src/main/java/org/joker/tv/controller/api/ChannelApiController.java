package org.joker.tv.controller.api;

import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.channel.ChannelsResult;
import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unsecured/api")
public class ChannelApiController {

	@Autowired
	private ChannelService channelService;

	@GetMapping("/channels")
	@ResponseBody
	public ChannelsResult activation(DeviceDto device, Model model) {
		return channelService.getChannels();
	}

}
