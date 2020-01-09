package org.bsshare.tv.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.bsshare.tv.model.HasSubscriptionAlreadyException;
import org.bsshare.tv.model.entity.DeviceEntity;
import org.bsshare.tv.model.entity.IPTVSubscription;
import org.bsshare.tv.model.front.web.ActivationResult;
import org.bsshare.tv.model.front.web.ActivationStatus;
import org.bsshare.tv.model.front.web.ComponentStatus;
import org.bsshare.tv.model.front.web.DeviceDto;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.model.front.web.SubscriptionType;
import org.bsshare.tv.repository.BaseSubscriptionRepository;
import org.bsshare.tv.repository.IPTVSubscriptionRepository;
import org.bsshare.tv.service.IPTVSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPTVSubscriptionServiceImpl extends BaseSubscriptionServiceImpl<IPTVSubscription>
		implements IPTVSubscriptionService {

	@Autowired
	private IPTVSubscriptionRepository ipTvSubscriptionRepository;

	@Override
	public ActivationResult activateIPTVSubscription(SubscriptionDto subscriptionDto) {
		Optional<IPTVSubscription> subscription = getSubscription(subscriptionDto);
		if (subscription.isPresent() && subscription.get().getDevice() == null) {
			DeviceDto deviceDto = new DeviceDto();
			deviceDto.setMacAddress(subscriptionDto.getUid());
			deviceDto.setSerialNumber(subscriptionDto.getSerial());
			deviceDto.setModel(subscriptionDto.getModel());
			DeviceEntity savedDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
			subscription.get().setDevice(savedDevice);
			getSubscriptionRepository().save(subscription.get());
		}
		return subscription.map(this::getValidActivationResult).orElse(invalidActivationResult());
	}

	private ActivationResult getValidActivationResult(IPTVSubscription subscription) {
		ActivationResult activationResult = newActivationResult();
		if (subscription.getStatus() == ComponentStatus.ACTIVATED) {
			activationResult.setMessage("Code already activated.Account Active until: " + subscription.getExpiration());
			activationResult.setStatus(ActivationStatus.OK.getValue());
		} else {
			activateNewIPTVSubscription(subscription);
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			activationResult.setMessage("ID:1 Your Account is now Active until: " + expiration);
			activationResult.setStatus(ActivationStatus.OK.getValue());
		}
		getLogger().debug("Activation Result Response :\n message" + activationResult.getMessage() + "\n Status : "
				+ activationResult.getStatus());
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
		subscription.setExpiration(LocalDate.now().plus(Period.ofMonths(subscription.getPeriodInMonths())));
		ipTvSubscriptionRepository.save(subscription);
	}

	@Override
	public Long delete(Long id) {
		return super.deleteSubscription(id);
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
	protected BaseSubscriptionRepository getSubscriptionRepository() {
		return ipTvSubscriptionRepository;
	}

	@Override
	public void newIPTVSubscription(int period) throws HasSubscriptionAlreadyException {
		newSubscription(SubscriptionType.IPTV, period);
	}

}
