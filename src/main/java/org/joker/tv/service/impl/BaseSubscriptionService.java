package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.entity.BaseSubscription;
import org.joker.tv.model.entity.Device;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.repository.BaseSubscriptionRepository;
import org.joker.tv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSubscriptionService extends ServiceBaseImpl {

	@Autowired
	private DeviceRepository deviceRepo;

	protected BaseSubscription getSubscriptionCommon(SubscriptionDto device, SubscriptionType sharing) {
		String login = device.getLogin();
		String uuid = device.getUid();
		String serial = device.getSerial();
		List<? extends BaseSubscription> queryResult = null;
		queryResult = getSubscriptionRepository().findOneByCriteria(login, uuid, serial);
		BaseSubscription subscription = null;
		if (queryResult != null && !queryResult.isEmpty()) {
			subscription = queryResult.get(0);
		}
		return subscription;
	}

	protected boolean isExpired(BaseSubscription subscription) {
		return subscription.getExpiration().isAfter(LocalDate.now());
	}
	
	protected Device saveSubscriptionDeviceIfNotExistant(DeviceDto device) {
		Optional<Device> entityDevice = deviceRepo.findOneBySerialNumber(device.getSerialNumber());
		return entityDevice.orElseGet(() -> deviceRepo.save(newDevice(device)));
	}

	private Device newDevice(DeviceDto device) {
		Device entityDevice = new Device();
		device.setMacAddress(device.getMacAddress());
		device.setSerialNumber(device.getSerialNumber());
		device.setModel(device.getModel());
		return entityDevice;
	}

	protected abstract BaseSubscriptionRepository getSubscriptionRepository();

}
