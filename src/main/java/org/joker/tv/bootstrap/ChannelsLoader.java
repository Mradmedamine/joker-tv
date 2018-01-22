package org.joker.tv.bootstrap;

import org.apache.log4j.Logger;
import org.joker.tv.model.entity.Channel;
import org.joker.tv.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ChannelsLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger logger = Logger.getLogger(ChannelsLoader.class);

	private ChannelRepository channelRepository;

	@Autowired
	public void setSubscriptionRepository(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		populateDate();
	}

	private void populateDate() {
		Channel channel;
		channel = newChannel("AR_Tunisie_Nat_1", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/63.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Tunisie_Nat_2", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/518.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_EL_HIWAR_ETTOUNSI", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/519.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_NESSMA_TV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/520.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_ATTASSIA_TV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/522.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_HANNIBAL_TV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/521.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_AL_JANOUBIA", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/523.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_M_TUNISA", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1210.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_TELVZA_TV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/525.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_TOUNESNA", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/524.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Carthage_Plus", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/442.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_ZAYTOONA", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/527.ts");
		channelRepository.save(channel);
		channel = newChannel("TN_ALINSEN", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1209.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_TT1_TV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/526.ts");
		channelRepository.save(channel);
		channel = newChannel("R_TN_Radio_National", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1846.ts");
		channelRepository.save(channel);
		channel = newChannel("R_TN_Mosaique_FM", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1847.ts");
		channelRepository.save(channel);
		channel = newChannel("R_TN_Jeunes_FM", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1850.ts");
		channelRepository.save(channel);
		channel = newChannel("R_TN_Jawhara_FM", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1848.ts");
		channelRepository.save(channel);
		channel = newChannel("R_TN_Sfax_FM", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1849.ts");
		channelRepository.save(channel);

		channel = newChannel("*-*-*-*-*_beIN-ViP_*-*-*-*-*", "http://w.bsshare.biz/229988547/51251O5478/promo.flv");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sports_News", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/131.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_1_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1830.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_2_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1831.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_3_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1832.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_4_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1833.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_5_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1834.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_6_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1835.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_7_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1836.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_8_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1837.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_9_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1838.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_10_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1839.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_11_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1840.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_12_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1841.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_1_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1216.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_2_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1215.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_3_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1214.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_4_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1213.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_5_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1212.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_6_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1211.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_7_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2008.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_8_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2009.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_9_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2010.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_10_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2011.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_MAX1_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2006.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_MAX2_SD", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2007.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_MAX1_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1843.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Sport_MAX2_Gold", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1844.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Movies1", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/718.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Movies2", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/719.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Movies3", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/720.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Movies4", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/1960.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_beIN_Series", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2856.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_beIN_Dlife", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2874.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_bein_AMC", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2880.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_bein_DMAX", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2883.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_bein_GROUMMET", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2885.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_bein_HGTV", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2886.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Fatafeat", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2884.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Animal_Planet", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2881.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_BEIN_FOX_Movie", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2878.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_BEIN_Jeem", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2879.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_Bein_Baraim", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2882.ts");
		channelRepository.save(channel);
		channel = newChannel("AR_bein_JUNIOR", "http://w.bs-share.com:8000/live/q7/HkyJm6ztfM/2887.ts");
		channelRepository.save(channel);
		logger.info(channelRepository.count() + " channels were added");

	}

	private Channel newChannel(String caption, String link) {
		Channel channel = new Channel();
		channel.setCaption(caption);
		channel.setStreaming_url(link);
		return channel;
	}

}
