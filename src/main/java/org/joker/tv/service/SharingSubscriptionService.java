package org.joker.tv.service;

import java.util.List;

import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.sharing.IksRequest;
import org.joker.tv.model.front.web.sharing.Servers;

public interface SharingSubscriptionService {

	SharingSubscription getSharingSubscription(SubscriptionDto device);

	List<SharingSubscription> getAllSharingSubscriptions();

	Boolean hasValidSharingSubscription(SubscriptionDto device);

	void saveSharingSubscription(DeviceDto deviceDto);

	Servers activateSharing(IksRequest iksData);
	
}
