package org.joker.tv.controller.api;

import org.joker.tv.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/unsecured/api")
public class ChannelApiController {

	@Autowired
	ChannelService channelService;

}
