package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.entity.Device;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ComponentStatus;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.MessageDetails;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.model.front.web.sharing.IksRequest;
import org.joker.tv.model.front.web.sharing.Servers;
import org.joker.tv.repository.BaseSubscriptionRepository;
import org.joker.tv.repository.ServerRepository;
import org.joker.tv.repository.SharingSubscriptionRepository;
import org.joker.tv.service.SharingSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharingSubscriptionServiceImpl extends BaseSubscriptionService implements SharingSubscriptionService {

	private static final Period DEFAULT_SUBSCRIPTION_PERIOD = Period.ofYears(1);

	@Autowired
	private SharingSubscriptionRepository sharingSubscriptionRepository;

	@Autowired
	private ServerRepository serverRepository;

	@Override
	public SharingSubscription getSharingSubscription(SubscriptionDto device) {
		return (SharingSubscription) getSubscriptionCommon(device, SubscriptionType.SHARING);
	}

	@Override
	public Boolean hasValidSharingSubscription(SubscriptionDto device) {
		Optional<SharingSubscription> subscription = Optional.ofNullable(getSharingSubscription(device));
		return subscription.isPresent() && !isExpired(subscription.get());
	}

	@Override
	public List<SharingSubscription> getAllSharingSubscriptions() {
		return sharingSubscriptionRepository.findAll();
	}

	@Override
	public void saveSharingSubscription(DeviceDto deviceDto) {
		Device entityDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
		SharingSubscription sharingSubscription = new SharingSubscription();
		sharingSubscription.setDevice(entityDevice);
		sharingSubscription.setStatus(ComponentStatus.NEW);
		sharingSubscriptionRepository.save(sharingSubscription);
	}

	@Override
	protected BaseSubscriptionRepository getSubscriptionRepository() {
		return sharingSubscriptionRepository;
	}

	@Override
	public Servers activateSharing(IksRequest iksData) {
		SubscriptionDto subscriptionDto = mapToSubscriptionDto(iksData);
		Optional<SharingSubscription> subscription = Optional.ofNullable(getSharingSubscription(subscriptionDto));
		Servers servers = new Servers();
		return subscription.map(subscr -> handleActivation(subscr, servers)).orElse(wrongActivation(servers));
	}

	private Servers wrongActivation(Servers servers) {
		servers.setMessage(new MessageDetails("2", "Wrong activation code"));
		return servers;

	}

	private Servers handleActivation(SharingSubscription subscription, Servers servers) {
		if (isExpired(subscription)) {
			servers.setMessage(new MessageDetails("3", "code Expired !"));
		} else {
			if (subscription.getStatus() == ComponentStatus.NEW) {
				subscription = doActivateNewSubscription(subscription);
			}
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			servers.setMessage(
			        new MessageDetails("1", "account registered successfully, it will expire: " + expiration));
			servers.setServer(serverRepository.findAll());
		}
		return servers;
	}

	private SharingSubscription doActivateNewSubscription(SharingSubscription subscription) {
		LocalDate expirationDate = LocalDate.now().plus(DEFAULT_SUBSCRIPTION_PERIOD);
		subscription.setExpiration(expirationDate);
		subscription.setStatus(ComponentStatus.ACTIVATED);
		return sharingSubscriptionRepository.save(subscription);
	}

	private SubscriptionDto mapToSubscriptionDto(IksRequest iksData) {
		SubscriptionDto device = new SubscriptionDto();
		device.setLogin(iksData.getAc());
		device.setUid(iksData.getMa());
		device.setSerial(iksData.getSn());
		return device;
	}

}
