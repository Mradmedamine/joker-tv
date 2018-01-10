package org.joker.tv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/channels")
public class ChannelController
{

	@RequestMapping
	public String channels(Model model)
	{
		return "modules/channels/form";
	}

}
