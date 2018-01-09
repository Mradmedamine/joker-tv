package org.joker.tv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:/activation";
	}

	@RequestMapping("/activation")
	public String activation(Model model) {
		return "modules/activation/form";
	}
	
	@RequestMapping("/channels")
	public String channels(Model model) {
		return "modules/channels/form";
	}
	
}
