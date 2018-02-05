package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.joker.tv.model.entity.BaseSubscription;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.ActivationStatus;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.repository.IPTVSubscriptionRepository;
import org.joker.tv.repository.SharingSubscriptionRepository;
import org.joker.tv.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private IPTVSubscriptionRepository ipTvSubscriptionRepository;

	@Autowired
	private SharingSubscriptionRepository sharingSubscriptionRepository;

	@Override
	public ActivationResult activateIPTVSubscription(DeviceDto device) {
		IPTVSubscription subscription = getIPTVSubscription(device);
		ActivationResult activationResult = new ActivationResult();
		activationResult.setAccount(0);
		if (subscription == null) {
			activationResult.setMessage("Subscription code is invalid. Please call customer care.");
			activationResult.setStatus(ActivationStatus.INVALID.getValue());
		} else if (subscription.getIsActive()) {
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
		subscription.setIsActive(true);
		subscription.setExpiration(LocalDate.now().plusYears(1));
		ipTvSubscriptionRepository.save(subscription);
	}

	@Override
	public IPTVSubscription getIPTVSubscription(DeviceDto device) {
		return (IPTVSubscription) getSubscriptionCommon(device, SubscriptionType.IPTV);
	}

	@Override
	public SharingSubscription getSharingSubscription(DeviceDto device) {
		return (SharingSubscription) getSubscriptionCommon(device, SubscriptionType.SHARING);
	}

	private BaseSubscription getSubscriptionCommon(DeviceDto device, SubscriptionType sharing) {
		String login = device.getLogin();
		String uuid = device.getUid();
		String serial = device.getSerial();
		List<? extends BaseSubscription> queryResult = null;
		switch (sharing) {
		case IPTV:
			queryResult = ipTvSubscriptionRepository.findByCriteria(login, uuid, serial);
			break;
		case SHARING:
			queryResult = sharingSubscriptionRepository.findByCriteria(login, uuid, serial);
			break;
		default:
			break;
		}
		BaseSubscription subscription = null;
		if (queryResult != null && !queryResult.isEmpty()) {
			subscription = queryResult.get(0);
		}
		return subscription;
	}

	@Override
	public Boolean hasIPTVSubscription(DeviceDto device) {
		return getIPTVSubscription(device) != null;
	}

	@Override
	public Boolean hasSharingSubscription(DeviceDto device) {
		return getSharingSubscription(device) != null;
	}

	@Override
	public List<IPTVSubscription> getAllIPTVSubscriptions() {
		return ipTvSubscriptionRepository.findAll();
	}

	@Override
	public List<SharingSubscription> getAllSharingSubscriptions() {
		return sharingSubscriptionRepository.findAll();
	}
}
