package org.joker.tv.controller.web;

import org.joker.tv.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerController {

	@Autowired
	private ServerRepository serverRepository;

	@GetMapping("/servers")
	public String iks(Model model) {
		model.addAttribute("servers", serverRepository.findAll());
		return "modules/sharing/main";
	}

}
