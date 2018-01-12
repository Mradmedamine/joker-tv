package org.joker.tv.controller;

import java.net.URI;

import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ActivationController
{

	@Autowired
	private RestTemplate restTemplate;

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

	@PostMapping("/activation")
	@ResponseBody
	public ActivationResult activateProduct(Device product, Model model)
	{
		UriComponentsBuilder uriBuilder =
			UriComponentsBuilder.fromHttpUrl(Constants.ACTIVATION_URL_7STAR).queryParam("login", product.getActiveCode())
				.queryParam("uid", product.getMacAddress()).queryParam("serial", product.getSerialNumber()).queryParam("model", product.getModel());
		URI url = uriBuilder.build().encode().toUri();
		return restTemplate.getForEntity(url, ActivationResult.class).getBody();
	}

}
