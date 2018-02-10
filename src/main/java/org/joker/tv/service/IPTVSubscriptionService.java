package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;

public interface IPTVSubscriptionService {

	ActivationResult activateIPTVSubscription(SubscriptionDto device);

	IPTVSubscription getIPTVSubscription(SubscriptionDto device);

	Boolean hasValidIPTVSubscription(SubscriptionDto device);

	List<IPTVSubscription> getAllIPTVSubscriptions();

	void saveIPTVSubscription(DeviceDto subscription);
	
}
