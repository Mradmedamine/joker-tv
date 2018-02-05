package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;

public interface SubscriptionService {

	ActivationResult activateIPTVSubscription(DeviceDto device);

	IPTVSubscription getIPTVSubscription(DeviceDto device);

	Boolean hasIPTVSubscription(DeviceDto device);

	SharingSubscription getSharingSubscription(DeviceDto device);

	List<IPTVSubscription> getAllIPTVSubscriptions();

	List<SharingSubscription> getAllSharingSubscriptions();

	Boolean hasSharingSubscription(DeviceDto device);

}
