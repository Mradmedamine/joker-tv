package org.bsshare.tv.service;

import java.util.List;

import org.bsshare.tv.model.entity.SharingSubscription;
import org.bsshare.tv.model.front.web.DeviceDto;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.model.front.web.sharing.IksRequest;
import org.bsshare.tv.model.front.web.sharing.Servers;

public interface SharingSubscriptionService {

	List<SharingSubscription> getAllSharingSubscriptions();

	Boolean isValidSharingSubscription(SubscriptionDto subscription);

	void newSharingSubscription(DeviceDto deviceDto);

	Servers activateSharingSubscription(IksRequest iksData);
	
	Long delete(Long id);
	
}
