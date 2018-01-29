package org.joker.tv.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IksController {

	@GetMapping("/iks")
	public String iks(Model model) {
		return "modules/iks/form";
	}

//	@PostMapping("/iks")
//	@ResponseBody
//	public ActivationResult iks(DeviceDto device, Model model) {
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(Constants._7STAR_ACTIVATION_URL)
//		        .queryParam("ac", device.getLogin()).queryParam("ma", device.getUid())
//		        .queryParam("sn", device.getSerial());
//		URI url = uriBuilder.build().encode().toUri();
//		return restTemplate.getForEntity(url, ActivationResult.class).getBody();
//	}

}
