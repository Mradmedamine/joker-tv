package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.HasSubscriptionAlreadyException;
import org.joker.tv.model.entity.BaseSubscription;
import org.joker.tv.model.entity.DeviceEntity;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ComponentStatus;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.repository.BaseSubscriptionRepository;
import org.joker.tv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSubscriptionServiceImpl extends ServiceBaseImpl {

	@Autowired
	private DeviceRepository deviceRepo;

	protected Optional<BaseSubscription> getSubscription(SubscriptionDto device) {
		String login = device.getLogin();
		String uuid = device.getUid();
		String serial = device.getSerial();
		List<? extends BaseSubscription> queryResult = null;
		queryResult = getSubscriptionRepository().findOneByCriteria(login, uuid, serial);
		BaseSubscription subscription = null;
		if (queryResult != null && !queryResult.isEmpty()) {
			subscription = queryResult.get(0);
		}
		return Optional.ofNullable(subscription);
	}

	protected boolean isExpired(BaseSubscription subscription) {
		return subscription.getStatus() == ComponentStatus.ACTIVATED && subscription.getExpiration().isAfter(LocalDate.now());
	}

	protected boolean isNotExpired(BaseSubscription subscription) {
		return !isExpired(subscription);
	}

	protected Boolean isValidSubscription(SubscriptionDto subscriptionDto) {
		Optional<BaseSubscription> subscription = getSubscription(subscriptionDto);
		return subscription.isPresent() && !isExpired(subscription.get());
	}

	protected Boolean hasAnyValidOrNewSubscription(DeviceDto deviceDto) {
		List<? extends BaseSubscription> subscriptions = getSubscriptionRepository()
		        .findByDevice_SerialNumber(deviceDto.getSerialNumber());
		return subscriptions.stream().anyMatch(this::isNotExpired);
	}

	protected void newSubscription(DeviceDto deviceDto, SubscriptionType type) throws HasSubscriptionAlreadyException {
		if (hasAnyValidOrNewSubscription(deviceDto)) {
			throw new HasSubscriptionAlreadyException();
		}
		DeviceEntity entityDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
		BaseSubscription subscription = type == SubscriptionType.IPTV ? new IPTVSubscription()
		        : new SharingSubscription();
		subscription.setDevice(entityDevice);
		subscription.setStatus(ComponentStatus.NEW);
		getSubscriptionRepository().save(subscription);

	}

	protected DeviceEntity saveSubscriptionDeviceIfNotExistant(DeviceDto device) {
		Optional<DeviceEntity> entityDevice = deviceRepo.findOneBySerialNumber(device.getSerialNumber());
		return entityDevice.orElseGet(() -> deviceRepo.save(newDevice(device)));
	}

	private DeviceEntity newDevice(DeviceDto device) {
		DeviceEntity entityDevice = new DeviceEntity();
		entityDevice.setMacAddress(device.getMacAddress());
		entityDevice.setSerialNumber(device.getSerialNumber());
		entityDevice.setModel(device.getModel());
		return entityDevice;
	}

	protected abstract BaseSubscriptionRepository getSubscriptionRepository();

}
