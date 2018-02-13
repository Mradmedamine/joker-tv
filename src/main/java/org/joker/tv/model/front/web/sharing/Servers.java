package org.joker.tv.model.front.web.sharing;

import java.util.List;

import org.joker.tv.model.front.web.MessageDetails;
import org.joker.tv.model.front.web.ServerSubscriptionInfo;

public class Servers {

	private List<ServerSubscriptionInfo> server;
	private MessageDetails message;

	public List<ServerSubscriptionInfo> getServer() {
		return server;
	}

	public void setServer(List<ServerSubscriptionInfo> server) {
		this.server = server;
	}

	public MessageDetails getMessage() {
		return message;
	}

	public void setMessage(MessageDetails message) {
		this.message = message;
	}

}