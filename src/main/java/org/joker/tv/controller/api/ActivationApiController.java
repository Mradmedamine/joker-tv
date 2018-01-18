package org.joker.tv.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ActivationApiController {

	// @GetMapping("/activation")
	// public String activation(Model model) {
	// return "modules/activation/form";
	// }
	//
	// @PostMapping("/activation")
	// @ResponseBody
	// public ActivationResult activateProduct(Device device, Model model) {
	// UriComponentsBuilder uriBuilder =
	// UriComponentsBuilder.fromHttpUrl(Constants._7STAR_ACTIVATION_URL).queryParam("login",
	// device.getActiveCode())
	// .queryParam("uid", device.getMacAddress()).queryParam("serial",
	// device.getSerialNumber()).queryParam("model", device.getModel());
	// URI url = uriBuilder.build().encode().toUri();
	// return restTemplate.getForEntity(url, ActivationResult.class).getBody();
	// }
	//
	// @GetMapping("/iks")
	// public String iks(Model model) {
	// return "modules/iks/form";
	// }
	//
	// @PostMapping("/iks")
	// @ResponseBody
	// public ActivationResult iks(Device device, Model model) {
	// UriComponentsBuilder uriBuilder =
	// UriComponentsBuilder.fromHttpUrl(Constants._7STAR_ACTIVATION_URL).queryParam("ac",
	// device.getActiveCode())
	// .queryParam("ma", device.getMacAddress()).queryParam("sn",
	// device.getSerialNumber());
	// URI url = uriBuilder.build().encode().toUri();
	// return restTemplate.getForEntity(url, ActivationResult.class).getBody();
	// }

}
