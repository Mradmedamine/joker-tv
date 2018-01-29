package org.joker.tv.model.front.web.iks;

import java.util.List;

import org.joker.tv.model.entity.Server;
import org.joker.tv.model.front.web.MessageDetails;

public class Servers {

	private List<Server> server;
	private MessageDetails message;

	public List<Server> getServer() {
		return server;
	}

	public void setServer(List<Server> server) {
		this.server = server;
	}

	public MessageDetails getMessage() {
		return message;
	}

	public void setMessage(MessageDetails message) {
		this.message = message;
	}

}