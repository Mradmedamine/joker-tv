package org.bsshare.tv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bsshare.tv.model.DeviceDataConsistancyException;
import org.bsshare.tv.model.HasSubscriptionAlreadyException;
import org.bsshare.tv.model.entity.BaseSubscription;
import org.bsshare.tv.model.entity.DeviceEntity;
import org.bsshare.tv.model.entity.IPTVSubscription;
import org.bsshare.tv.model.entity.SharingSubscription;
import org.bsshare.tv.model.front.web.ComponentStatus;
import org.bsshare.tv.model.front.web.DeviceDto;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.model.front.web.SubscriptionType;
import org.bsshare.tv.repository.BaseSubscriptionRepository;
import org.bsshare.tv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class BaseSubscriptionServiceImpl<T extends BaseSubscription> extends ServiceBaseImpl {

	@Autowired
	private DeviceRepository deviceRepository;

	public Optional<T> getSubscription(SubscriptionDto subscriptionDto) {
		Optional<T> subscription = Optional
				.ofNullable(getSubscriptionRepository().findOneByActiveCode(subscriptionDto.getLogin()));
		if (subscription.isPresent() && subscription.get().getDevice() == null) {
			DeviceDto deviceDto = new DeviceDto();
			deviceDto.setMacAddress(subscriptionDto.getUid());
			deviceDto.setSerialNumber(subscriptionDto.getSerial());
			deviceDto.setModel(subscriptionDto.getModel());
			DeviceEntity savedDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
			subscription.get().setDevice(savedDevice);
			getSubscriptionRepository().save(subscription.get());
		}
		return subscription;
	}

	protected boolean isExpired(BaseSubscription subscription) {
		return subscription.getStatus() == ComponentStatus.ACTIVATED
				&& subscription.getExpiration().isBefore(LocalDate.now());
	}

	protected boolean isNotExpired(BaseSubscription subscription) {
		return !isExpired(subscription);
	}

	protected Boolean isValidSubscription(SubscriptionDto subscriptionDto) {
		Optional<? extends BaseSubscription> subscription = getSubscription(subscriptionDto);
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

	protected void newSubscription(SubscriptionType type) {
		BaseSubscription subscription = type == SubscriptionType.IPTV ? new IPTVSubscription()
				: new SharingSubscription();
		subscription.setStatus(ComponentStatus.NEW);
		getSubscriptionRepository().save(subscription);
	}

	protected DeviceEntity saveSubscriptionDeviceIfNotExistant(DeviceDto device) {
		Optional<DeviceEntity> entityDevice = deviceRepository.findOneBySerialNumber(device.getSerialNumber());
		return entityDevice.orElseGet(() -> saveNewDevice(device));
	}

	private DeviceEntity saveNewDevice(DeviceDto device) {
		try {
			DeviceEntity entityDevice = new DeviceEntity();
			entityDevice.setMacAddress(device.getMacAddress().trim());
			entityDevice.setSerialNumber(device.getSerialNumber().trim());
			entityDevice.setModel(device.getModel());
			return deviceRepository.save(entityDevice);
		} catch (Exception ex) {
			throw new DeviceDataConsistancyException();
		}
	}

	protected void deleteCorrespondingDevice(Long deviceId) {
		try {
			deviceRepository.delete(deviceId);
			getLogger().debug("Subscription Corresponding Device has been deleted deviceId :" + deviceId);
		} catch (DataIntegrityViolationException err) {
			// DO NOTHING
		}
	}

	protected abstract BaseSubscriptionRepository getSubscriptionRepository();

}
