package org.joker.tv.controller.web;

import org.joker.tv.common.Constants;
import org.joker.tv.model.HasSubscriptionAlreadyException;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.service.IPTVSubscriptionService;
import org.joker.tv.service.SharingSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubscriptionController {

	@Autowired
	private IPTVSubscriptionService iptvSubscriptionService;

	@Autowired
	private SharingSubscriptionService sharingSubscriptionService;

	@GetMapping("/iptv/subscriptions")
	public String iptvSubscriptions(Model model) {
		model.addAttribute("subscriptions", iptvSubscriptionService.getAllIPTVSubscriptions());
		model.addAttribute("subscriptionType", SubscriptionType.IPTV);
		return "modules/subscriptions/main";
	}

	@PostMapping("/iptv/subscriptions")
	@ResponseBody
	public String addIptvSubscription(DeviceDto device, Model model) {
		try {
			iptvSubscriptionService.newIPTVSubscription(device);
		} catch (HasSubscriptionAlreadyException ex) {
			return Constants.DEVICE_HAS_ALREADY_SUBSCRIPTION_MESSAGE;
		}
		return Constants.SUBSCRIPTION_ADDED_SUCCESSFULLY_MESSAGE;
	}

	@DeleteMapping("/IPTV/subscriptions/{id}")
	public @ResponseBody Long delete(Model model, @PathVariable Long id) {
		return iptvSubscriptionService.delete(id);
	}

	@GetMapping("/sharing/subscriptions")
	public String sharingSubscriptions(Model model) {
		model.addAttribute("subscriptions", sharingSubscriptionService.getAllSharingSubscriptions());
		model.addAttribute("subscriptionType", SubscriptionType.SHARING);
		return "modules/subscriptions/main";
	}

	@PostMapping("/sharing/subscriptions")
	@ResponseBody
	public String addSharingSubscription(DeviceDto device, Model model) {
		try {
			sharingSubscriptionService.newSharingSubscription(device);
		} catch (HasSubscriptionAlreadyException ex) {
			return Constants.DEVICE_HAS_ALREADY_SUBSCRIPTION_MESSAGE;
		}
		return Constants.SUBSCRIPTION_ADDED_SUCCESSFULLY_MESSAGE;
	}

	@DeleteMapping("/SHARING/subscriptions/{id}")
	public @ResponseBody Long deleteSharingSubscription(Model model, @PathVariable Long id) {
		return sharingSubscriptionService.delete(id);
	}

}
