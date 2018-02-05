package org.joker.tv.controller.api;

import java.time.format.DateTimeFormatter;

import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.MessageDetails;
import org.joker.tv.model.front.web.sharing.IksRequest;
import org.joker.tv.model.front.web.sharing.IksResponse;
import org.joker.tv.model.front.web.sharing.Servers;
import org.joker.tv.repository.ServerRepository;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unsecured/api")
public class SharingApiController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private ServerRepository serverRepository;

	@GetMapping("/iks")
	@ResponseBody
	public IksResponse iks(IksRequest iksData, Model model) {
		DeviceDto device = mapToDeviceDto(iksData);
		IksResponse IksResponse = new IksResponse();
		IksResponse.setServers(getSharingServers(device));
		return IksResponse;
	}

	private DeviceDto mapToDeviceDto(IksRequest iksData) {
		DeviceDto device = new DeviceDto();
		device.setLogin(iksData.getAc());
		device.setUid(iksData.getMa());
		device.setSerial(iksData.getSn());
		return device;
	}

	public Servers getSharingServers(DeviceDto device) {
		Servers servers = new Servers();
		SharingSubscription subscription = subscriptionService.getSharingSubscription(device);
		if (subscription != null) {
			servers.setServer(serverRepository.findAll());
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			servers.setMessage(
			        new MessageDetails("1", "account registered successfully, it will expire: " + expiration));
		} else {
			servers.setMessage(new MessageDetails("2", "Wrong activation code"));
		}
		return servers;
	}
	
}
