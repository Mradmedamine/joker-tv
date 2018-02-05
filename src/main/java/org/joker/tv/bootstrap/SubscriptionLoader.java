package org.joker.tv.bootstrap;

import org.apache.log4j.Logger;
import org.joker.tv.model.entity.IPTVSubscription;
import org.joker.tv.model.entity.Server;
import org.joker.tv.model.entity.SharingSubscription;
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

	@Autowired
	public void setServerRepository(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}

	@Autowired
	public void setSharingSubscriptionRepository(SharingSubscriptionRepository sharingSubscriptionRepository) {
		this.sharingSubscriptionRepository = sharingSubscriptionRepository;
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
		subscription = newIPTVSubscription("0732615849", "10:18:94:C9:44:16", "4C76C2FD16AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4519628306", "10:18:94:C9:44:17", "4C76C2FD17AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5843796012", "10:18:94:C9:44:18", "4C76C2FD18AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4396762580", "10:18:94:C9:44:19", "4C76C2FD19AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4832350167", "10:18:94:C9:44:20", "4C76C2FD20AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0513216948", "10:18:94:C9:44:21", "4C76C2FD21AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0163495298", "10:18:94:C9:44:22", "4C76C2FD22AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("9083042571", "10:18:94:C9:44:23", "4C76C2FD23AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("9043526185", "10:18:94:C9:44:24", "4C76C2FD24AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5604973258", "10:18:94:C9:44:25", "4C76C2FD25AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8546172093", "10:18:94:C9:44:26", "4C76C2FD26AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("6535801927", "10:18:94:C9:44:27", "4C76C2FD27AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5172309608", "10:18:94:C9:44:28", "4C76C2FD28AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("2859173469", "10:18:94:C9:44:29", "4C76C2FD29AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("6512730198", "10:18:94:C9:44:30", "4C76C2FD30AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("7405349186", "10:18:94:C9:44:31", "4C76C2FD31AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0138967345", "10:18:94:C9:44:32", "4C76C2FD32AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("2438698710", "10:18:94:C9:44:33", "4C76C2FD33AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3058946732", "10:18:94:C9:44:34", "4C76C2FD34AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3471896251", "10:18:94:C9:44:35", "4C76C2FD35AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8490721693", "10:18:94:C9:44:36", "4C76C2FD36AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("4963721570", "10:18:94:C9:44:37", "4C76C2FD37AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8462593150", "10:18:94:C9:44:38", "4C76C2FD38AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("1753609248", "10:18:94:C9:44:39", "4C76C2FD39AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("9804154637", "10:18:94:C9:44:40", "4C76C2FD40AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3410899672", "10:18:94:C9:44:41", "4C76C2FD41AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3027461578", "10:18:94:C9:44:42", "4C76C2FD42AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5872643159", "10:18:94:C9:44:43", "4C76C2FD43AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("2081439176", "10:18:94:C9:44:44", "4C76C2FD44AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("1920462735", "10:18:94:C9:44:45", "4C76C2FD45AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5421713096", "10:18:94:C9:44:46", "4C76C2FD46AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("7824568139", "10:18:94:C9:44:47", "4C76C2FD47AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0257683492", "10:18:94:C9:44:48", "4C76C2FD48AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8473560928", "10:18:94:C9:44:49", "4C76C2FD49AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0783654321", "10:18:94:C9:44:50", "4C76C2FD50AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("5031478869", "10:18:94:C9:44:51", "4C76C2FD51AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("7430924561", "10:18:94:C9:44:52", "4C76C2FD52AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3460758091", "10:18:94:C9:44:53", "4C76C2FD53AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3892160457", "10:18:94:C9:44:54", "4C76C2FD54AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("0654717892", "10:18:94:C9:44:55", "4C76C2FD55AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8306243957", "10:18:94:C9:44:56", "4C76C2FD56AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("9735228406", "10:18:94:C9:44:57", "4C76C2FD57AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("6415207683", "10:18:94:C9:44:58", "4C76C2FD58AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("8407653791", "10:18:94:C9:44:59", "4C76C2FD59AD85E4");
		iptvSubscriptionRepository.save(subscription);
		subscription = newIPTVSubscription("3892560457", "10:18:94:C9:44:60", "4C76C2FD60AD85E4");
		iptvSubscriptionRepository.save(subscription);
		logger.info(iptvSubscriptionRepository.count() + " subscriptions were added");
	}

	private IPTVSubscription newIPTVSubscription(String activeCode, String macAddress, String serial) {
		IPTVSubscription iptvSubscription = new IPTVSubscription();
		iptvSubscription.setActiveCode(activeCode);
		iptvSubscription.setMacAddress(macAddress);
		iptvSubscription.setSerialNumber(serial);
		return iptvSubscription;
	}

	private void addSharingSubscriptions() {
		SharingSubscription subscription;
		subscription = newSharingSubscription("8035197426", "10:18:94:C9:44:10", "4C76C2FD10AD85E4");
		sharingSubscriptionRepository.save(subscription);
	}

	private SharingSubscription newSharingSubscription(String activeCode, String macAddress, String serial) {
		SharingSubscription sharingSubscription = new SharingSubscription();
		sharingSubscription.setActiveCode(activeCode);
		sharingSubscription.setMacAddress(macAddress);
		sharingSubscription.setSerialNumber(serial);
		return sharingSubscription;
	}

	private void addServers() {
		serverRepository.save(newServer());
	}

	private Server newServer() {
		Server server = new Server();
		server.setServerid("1");
		server.setHost("iks.7starstb.net");
		server.setPort("18021");
		server.setUser("test");
		server.setPass("test");
		return server;
	}

}
