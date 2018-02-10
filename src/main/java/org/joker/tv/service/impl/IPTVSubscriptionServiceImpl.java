package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.entity.Device;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.ActivationStatus;
import org.joker.tv.model.front.web.ComponentStatus;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.repository.BaseSubscriptionRepository;
import org.joker.tv.repository.IPTVSubscriptionRepository;
import org.joker.tv.service.IPTVSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPTVSubscriptionServiceImpl extends BaseSubscriptionService implements IPTVSubscriptionService {

	private static final Period DEFAULT_SUBSCRIPTION_PERIOD = Period.ofYears(1);

	@Autowired
	private IPTVSubscriptionRepository ipTvSubscriptionRepository;

	@Override
	public ActivationResult activateIPTVSubscription(SubscriptionDto device) {
		IPTVSubscription subscription = getIPTVSubscription(device);
		ActivationResult activationResult = new ActivationResult();
		activationResult.setAccount(0);
		if (subscription == null) {
			activationResult.setMessage("Subscription code is invalid. Please call customer care.");
			activationResult.setStatus(ActivationStatus.INVALID.getValue());
		} else if (subscription.getStatus() == ComponentStatus.ACTIVATED) {
			activationResult.setMessage("Code already activated. Please call customer care.");
			activationResult.setStatus(ActivationStatus.REPEATED.getValue());
		} else {
			activateNewIPTVSubscription(subscription);
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			activationResult.setMessage("ID:1 Your Account is now Active until: " + expiration);
			activationResult.setStatus(ActivationStatus.OK.getValue());
		}
		return activationResult;
	}

	private void activateNewIPTVSubscription(IPTVSubscription subscription) {
		subscription.setStatus(ComponentStatus.ACTIVATED);
		subscription.setExpiration(LocalDate.now().plus(DEFAULT_SUBSCRIPTION_PERIOD));
		ipTvSubscriptionRepository.save(subscription);
	}

	@Override
	public IPTVSubscription getIPTVSubscription(SubscriptionDto device) {
		return (IPTVSubscription) getSubscriptionCommon(device, SubscriptionType.IPTV);
	}

	@Override
	public Boolean hasValidIPTVSubscription(SubscriptionDto subscription) {
		Optional<IPTVSubscription> iptvSubscription = Optional.ofNullable(getIPTVSubscription(subscription));
		return iptvSubscription.isPresent() && !isExpired(iptvSubscription.get());
	}

	@Override
	public List<IPTVSubscription> getAllIPTVSubscriptions() {
		return ipTvSubscriptionRepository.findAll();
	}

	@Override
	public void saveIPTVSubscription(DeviceDto deviceDto) {
		Device entityDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
		IPTVSubscription iptvSubscription = new IPTVSubscription();
		iptvSubscription.setDevice(entityDevice);
		iptvSubscription.setStatus(ComponentStatus.NEW);
	}

	@Override
	protected BaseSubscriptionRepository getSubscriptionRepository() {
		return ipTvSubscriptionRepository;
	}

}
