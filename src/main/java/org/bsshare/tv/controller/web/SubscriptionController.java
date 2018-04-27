package org.bsshare.tv.controller.web;

import org.bsshare.tv.common.Constants;
import org.bsshare.tv.model.front.web.SubscriptionType;
import org.bsshare.tv.service.IPTVSubscriptionService;
import org.bsshare.tv.service.SharingSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> addIptvSubscription(int number, Model model) {
		for (int i = 0; i < number; i++) {
			iptvSubscriptionService.newIPTVSubscription();
		}
		return new ResponseEntity<String>(Constants.SUBSCRIPTION_ADDED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
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
	public ResponseEntity<String> addSharingSubscription(int number, Model model) {
		for (int i = 0; i < number; i++) {
			sharingSubscriptionService.newSharingSubscription();
		}
		return new ResponseEntity<String>(Constants.SUBSCRIPTION_ADDED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
	}

	@DeleteMapping("/SHARING/subscriptions/{id}")
	public @ResponseBody Long deleteSharingSubscription(Model model, @PathVariable Long id) {
		return sharingSubscriptionService.delete(id);
	}

}
