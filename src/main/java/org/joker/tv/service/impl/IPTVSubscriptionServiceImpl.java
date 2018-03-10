package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.HasSubscriptionAlreadyException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class IPTVSubscriptionServiceImpl extends BaseSubscriptionServiceImpl implements IPTVSubscriptionService {

	private static final Period DEFAULT_SUBSCRIPTION_PERIOD = Period.ofYears(1);

	@Autowired
	private IPTVSubscriptionRepository ipTvSubscriptionRepository;

	@Override
	public ActivationResult activateIPTVSubscription(SubscriptionDto device) {
		return getIPTVSubscription(device).map(this::getValidActivationResult).orElse(invalidActivationResult());
	}

	private ActivationResult getValidActivationResult(IPTVSubscription subscription) {
		ActivationResult activationResult = newActivationResult();
		if (subscription.getStatus() == ComponentStatus.ACTIVATED) {
			activationResult.setMessage("Code already activated. now Active until: " + subscription.getExpiration());
			activationResult.setStatus(ActivationStatus.OK.getValue());
		} else {
			activateNewIPTVSubscription(subscription);
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			activationResult.setMessage("ID:1 Your Account is now Active until: " + expiration);
			activationResult.setStatus(ActivationStatus.OK.getValue());
		}
		return activationResult;
	}

	private ActivationResult invalidActivationResult() {
		ActivationResult activationResult = newActivationResult();
		activationResult.setMessage("Subscription code is invalid. Please call customer care.");
		activationResult.setStatus(ActivationStatus.INVALID.getValue());
		return activationResult;
	}

	private ActivationResult newActivationResult() {
		ActivationResult activationResult = new ActivationResult();
		activationResult.setAccount(0);
		return activationResult;
	}

	private void activateNewIPTVSubscription(IPTVSubscription subscription) {
		subscription.setStatus(ComponentStatus.ACTIVATED);
		subscription.setExpiration(LocalDate.now().plus(DEFAULT_SUBSCRIPTION_PERIOD));
		ipTvSubscriptionRepository.save(subscription);
	}

	@Override
	public Long delete(Long id) {
		try {
			ipTvSubscriptionRepository.delete(id);
		} catch (DataIntegrityViolationException err) {
			return -1L;
		} catch (Exception err) {
			return -100L;
		}
		return id;
	}

	@Override
	public Optional<IPTVSubscription> getIPTVSubscription(SubscriptionDto device) {
		return getSubscription(device).map(v -> (IPTVSubscription) v);
	}

	@Override
	public Boolean isValidIPTVSubscription(SubscriptionDto subscription) {
		return isValidSubscription(subscription);
	}

	@Override
	public List<IPTVSubscription> getAllIPTVSubscriptions() {
		return ipTvSubscriptionRepository.findAll();
	}

	@Override
	public void newIPTVSubscription(DeviceDto deviceDto) throws HasSubscriptionAlreadyException {
		newSubscription(deviceDto, SubscriptionType.IPTV);
	}

	@Override
	protected BaseSubscriptionRepository getSubscriptionRepository() {
		return ipTvSubscriptionRepository;
	}

}
