package org.joker.tv.bootstrap;

import org.apache.log4j.Logger;
import org.joker.tv.model.entity.DeviceEntity;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.entity.ServerEntity;
import org.joker.tv.model.entity.SharingSubscription;
import org.joker.tv.model.front.web.ComponentStatus;
import org.joker.tv.repository.DeviceRepository;
import org.joker.tv.repository.IPTVSubscriptionRepository;
import org.joker.tv.repository.ServerRepository;
import org.joker.tv.repository.SharingSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionLoader extends BaseDataLoader {

	private static Logger logger = Logger.getLogger(SubscriptionLoader.class);

	private SharingSubscriptionRepository sharingSubscriptionRepository;

	private IPTVSubscriptionRepository iptvSubscriptionRepository;

	private ServerRepository serverRepository;

	private DeviceRepository deviceRepository;
	
	@Autowired
	public void setServerRepository(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}

	@Autowired
	public void setSharingSubscriptionRepository(SharingSubscriptionRepository sharingSubscriptionRepository) {
		this.sharingSubscriptionRepository = sharingSubscriptionRepository;
	}


	@Autowired
	public void setDeviceRepository(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
	@Autowired
	public void setSubscriptionRepository(IPTVSubscriptionRepository subscriptionRepository) {
		this.iptvSubscriptionRepository = subscriptionRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isCreateMode()) {
			addIPTVSubscriptions();
			addSharingSubscriptions();
			addServers();
		}
	}

	private void addIPTVSubscriptions() {
		IPTVSubscription subscription;
		subscription = newIPTVSubscription("8035197426", "10:18:94:C9:44:10", "4C76C2FD10AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8103249467", "10:18:94:C9:44:11", "4C76C2FD11AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4610582037", "10:18:94:C9:44:12", "4C76C2FD12AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4459068721", "10:18:94:C9:44:13", "4C76C2FD13AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8946805713", "10:18:94:C9:44:14", "4C76C2FD14AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("9842675351", "10:18:94:C9:44:15", "4C76C2FD15AD85E4");
		iptvSubscriptionRepository.save(subscription);
		logger.info(iptvSubscriptionRepository.count() + " subscriptions were added");
	}

	private IPTVSubscription newIPTVSubscription(String activeCode, String macAddress, String serial) {
		DeviceEntity device = new DeviceEntity();
		device.setMacAddress(macAddress);
		device.setSerialNumber(serial);
		device.setModel("K3");
		deviceRepository.save(device);
		IPTVSubscription iptvSubscription = new IPTVSubscription();
		iptvSubscription.setActiveCode(activeCode);
		iptvSubscription.setDevice(device);
		iptvSubscription.setStatus(ComponentStatus.NEW);
		return iptvSubscription;
	}

	private void addSharingSubscriptions() {
		SharingSubscription subscription;
		subscription = newSharingSubscription("8035197426", "10:18:94:C9:B2:10", "4C76C2X520AD85E4");
		sharingSubscriptionRepository.save(subscription);
	}

	private SharingSubscription newSharingSubscription(String activeCode, String macAddress, String serial) {
		DeviceEntity device = new DeviceEntity();
		device.setMacAddress(macAddress);
		device.setSerialNumber(serial);
		device.setModel("K3");
		deviceRepository.save(device);
		SharingSubscription sharingSubscription = new SharingSubscription();
		sharingSubscription.setActiveCode(activeCode);
		sharingSubscription.setDevice(device);
		sharingSubscription.setStatus(ComponentStatus.NEW);
		return sharingSubscription;
	}

	private void addServers() {
		serverRepository.save(newServer());
	}

	private ServerEntity newServer() {
		ServerEntity server = new ServerEntity();
		server.setServerid("1");
		server.setHost("iks.7starstb.net");
		server.setPort("18021");
		return server;
	}


}
