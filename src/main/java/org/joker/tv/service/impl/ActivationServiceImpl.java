package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.joker.tv.model.entity.DeviceSubscription;
import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.SubscriptionStatus;
import org.joker.tv.repository.SubscriptionRepository;
import org.joker.tv.service.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationServiceImpl implements ActivationService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public ActivationResult activateDevice(DeviceDto device) {

//		List<DeviceSubscription> queryResult = subscriptionRepository.findByCriteria(device.getLogin(), device.getUid(),
//		        device.getSerial());

		 List<DeviceSubscription> queryResult =
		 subscriptionRepository.findAll();
		DeviceSubscription subscription = null;
		if (queryResult != null && !queryResult.isEmpty()) {
			subscription = queryResult.get(0);
		}

		ActivationResult activationResult = new ActivationResult();
		activationResult.setAccount(0);
		if (subscription == null) {
			activationResult.setMessage("Subscription code is invalid. Please call customer care.");
			activationResult.setStatus(SubscriptionStatus.INVALID.getValue());
		} else if (subscription.isActive()) {
			activationResult.setMessage("Code already actvivated. Please call customer care.");
			activationResult.setStatus(SubscriptionStatus.REPEATED.getValue());
		} else {
			subscription.setActive(true);
			subscription.setExpiration(LocalDate.now().plusYears(1));
			subscriptionRepository.save(subscription);
			activationResult.setMessage("ID:1 Your Account is now Active until: "
			        + subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
			activationResult.setStatus(SubscriptionStatus.OK.getValue());
		}
		return activationResult;
	}

}
