package org.joker.tv.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joker.tv.model.entity.ServerEntity;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ComponentStatus;
import org.joker.tv.model.front.web.DeviceDto;
import org.joker.tv.model.front.web.MessageDetails;
import org.joker.tv.model.front.web.ServerSubscriptionInfo;
import org.joker.tv.model.front.web.SubscriptionDto;
import org.joker.tv.model.front.web.SubscriptionType;
import org.joker.tv.model.front.web.sharing.IksRequest;
import org.joker.tv.model.front.web.sharing.Servers;
import org.joker.tv.repository.BaseSubscriptionRepository;
import org.joker.tv.repository.ServerRepository;
import org.joker.tv.repository.SharingSubscriptionRepository;
import org.joker.tv.service.SharingSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SharingSubscriptionServiceImpl extends BaseSubscriptionServiceImpl implements SharingSubscriptionService {

	private static final Period DEFAULT_SUBSCRIPTION_PERIOD = Period.ofYears(1);

	@Autowired
	private SharingSubscriptionRepository sharingSubscriptionRepository;

	@Autowired
	private ServerRepository serverRepository;

	@Override
	public Boolean isValidSharingSubscription(SubscriptionDto subscriptionDto) {
		return isValidSubscription(subscriptionDto);
	}

	@Override
	public List<SharingSubscription> getAllSharingSubscriptions() {
		return sharingSubscriptionRepository.findAll();
	}

	@Override
	public void newSharingSubscription(DeviceDto deviceDto) {
		newSubscription(deviceDto, SubscriptionType.SHARING);
	}

	@Override
	protected BaseSubscriptionRepository getSubscriptionRepository() {
		return sharingSubscriptionRepository;
	}

	@Override
	public Servers activateSharingSubscription(IksRequest iksData) {
		SubscriptionDto subscriptionDto = mapToSubscriptionDto(iksData);
		Optional<SharingSubscription> subscription = getSharingSubscription(subscriptionDto);
		return subscription.isPresent() ? handleActivation(subscription.get()) : wrongActivationCode();
	}

	@Override
	public Long delete(Long authorId) {
		try {
			sharingSubscriptionRepository.delete(authorId);
		} catch (DataIntegrityViolationException err) {
			return -1L;
		} catch (Exception err) {
			return -100L;
		}
		return authorId;
	}

	private Servers wrongActivationCode() {
		Servers servers = new Servers();
		servers.setMessage(new MessageDetails("2", "Wrong activation code"));
		return servers;
	}

	private Servers handleActivation(SharingSubscription subscription) {
		Servers servers = new Servers();
		if (isExpired(subscription)) {
			servers.setMessage(new MessageDetails("3", "code Expired !"));
		} else {
			String messageBody = "account expiration : ";
			if (subscription.getStatus() == ComponentStatus.NEW) {
				subscription = doActivateNewSubscription(subscription);
				messageBody = "account registered successfully, it will expire: ";
			}
			String expiration = subscription.getExpiration().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			messageBody += expiration;
			servers.setMessage(new MessageDetails("1", messageBody));
			servers.setServer(getServerSubscriptionInfoList(subscription));
		}
		return servers;
	}

	private List<ServerSubscriptionInfo> getServerSubscriptionInfoList(SharingSubscription subscription) {
		List<ServerSubscriptionInfo> serversInfoList = new ArrayList<>();
		serverRepository.findAll().forEach(server -> {
			serversInfoList.add(newServerSubscriptionInfo(server, subscription));
		});
		return serversInfoList;
	}

	private ServerSubscriptionInfo newServerSubscriptionInfo(ServerEntity server, SharingSubscription subscription) {
		ServerSubscriptionInfo serverSubscription = new ServerSubscriptionInfo();
		serverSubscription.setServerid(server.getServerid());
		serverSubscription.setHost(server.getHost());
		serverSubscription.setPort(server.getPort());
		serverSubscription.setUser(subscription.getUser());
		serverSubscription.setPass(subscription.getPass());
		return serverSubscription;
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

	private Optional<SharingSubscription> getSharingSubscription(SubscriptionDto device) {
		return getSubscription(device).map(v -> (SharingSubscription) v);
	}
}
