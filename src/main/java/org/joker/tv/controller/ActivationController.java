package org.joker.tv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActivationController
{

	@RequestMapping("/")
	public String index(Model model)
	{
		return "redirect:/activation";
	}

	@GetMapping("/activation")
	public String activation(Model model)
	{
		return "modules/activation/form";
	}

	//

	@PostMapping("/activation")
	public String activateProduct(Model model)
	{
		//		String url = getAbsoluteUrl(getConnectorConfig().getFilePath() + getConnectorConfig().getAdd());
		//UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("parentId", file.getDocumentId());
		//String builtUrl = uriBuilder.build().encode().toUriString();
		return null;
	}

}
