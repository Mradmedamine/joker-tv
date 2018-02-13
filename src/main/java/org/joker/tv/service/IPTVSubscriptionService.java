package org.joker.tv.service;

import java.util.List;
import java.util.Optional;

import org.joker.tv.model.HasSubscriptionAlreadyException;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;

public interface IPTVSubscriptionService {

	ActivationResult activateIPTVSubscription(SubscriptionDto subscription);

	Optional<IPTVSubscription> getIPTVSubscription(SubscriptionDto subscription);

	Boolean isValidIPTVSubscription(SubscriptionDto subscription);

	List<IPTVSubscription> getAllIPTVSubscriptions();

	void newIPTVSubscription(DeviceDto device) throws HasSubscriptionAlreadyException;
	
}
