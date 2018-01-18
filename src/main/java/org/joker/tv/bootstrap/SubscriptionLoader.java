package org.joker.tv.bootstrap;

import org.apache.log4j.Logger;
import org.joker.tv.model.entity.DeviceSubscription;
import org.joker.tv.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger log = Logger.getLogger(SubscriptionLoader.class);

	private SubscriptionRepository subscriptionRepository;

	@Autowired
	public void setUserRepository(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		populateDate();
	}

	private void populateDate() {

		DeviceSubscription subscription = newDeviceSubscription("activeCode", "macAddress", "serialNumber");
		subscriptionRepository.save(subscription);

	}

	private DeviceSubscription newDeviceSubscription(String activeCode, String macAddress, String serialNumber) {
		DeviceSubscription subscription = new DeviceSubscription();
		subscription.setActiveCode(activeCode);
		subscription.setMacAddress(macAddress);
		subscription.setSerialNumber(serialNumber);
		return subscription;
	}

}
