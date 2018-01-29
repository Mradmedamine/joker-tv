package org.joker.tv.service;

import org.joker.tv.model.entity.DeviceSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.iks.Servers;

public interface SubscriptionService {

	ActivationResult activateDevice(DeviceDto device);

	DeviceSubscription getDeviceSubscription(DeviceDto device);

	Boolean hasSubscription(DeviceDto device);

	Servers getIksData(DeviceDto device);

}
