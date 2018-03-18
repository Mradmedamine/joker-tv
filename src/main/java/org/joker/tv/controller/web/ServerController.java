package org.joker.tv.controller.web;

import org.joker.tv.common.Constants;
import org.joker.tv.model.entity.ServerEntity;
import org.joker.tv.model.front.web.ServerDto;
import org.joker.tv.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServerController {

	@Autowired
	private ServerRepository serverRepository;

	@GetMapping("/servers")
	public String servers(Model model) {
		model.addAttribute("servers", serverRepository.findAll());
		return "modules/sharing/main";
	}

	@PostMapping("/servers")
	public @ResponseBody String addServer(ServerDto server, Model model) {
		ServerEntity entity = new ServerEntity();
		entity.setServerid(server.getServerid());
		entity.setHost(server.getHost());
		entity.setPort(server.getPort());
		serverRepository.save(entity);
		return Constants.SERVER_ADDED_SUCCESSFULLY_MESSAGE;
	}
	
	@DeleteMapping("/servers/{id}")
	public @ResponseBody Long delete(Model model, @PathVariable Long id) {
		try {
			serverRepository.delete(id);
		} catch (DataIntegrityViolationException err) {
			return -1L;
		} catch (Exception err) {
			return -100L;
		}
		return id;
	}
	
}
