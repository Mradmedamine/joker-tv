package org.bsshare.tv.service;

import java.util.List;
import java.util.Optional;

import org.bsshare.tv.model.HasSubscriptionAlreadyException;
import org.bsshare.tv.model.entity.IPTVSubscription;
import org.bsshare.tv.model.front.web.ActivationResult;
import org.bsshare.tv.model.front.web.DeviceDto;
import org.bsshare.tv.model.front.web.SubscriptionDto;

public interface IPTVSubscriptionService {

	ActivationResult activateIPTVSubscription(SubscriptionDto subscription);

	Optional<IPTVSubscription> getIPTVSubscription(SubscriptionDto subscription);

	Boolean isValidIPTVSubscription(SubscriptionDto subscription);

	List<IPTVSubscription> getAllIPTVSubscriptions();

	void newIPTVSubscription(DeviceDto device) throws HasSubscriptionAlreadyException;

	Long delete(Long id);
	
}
