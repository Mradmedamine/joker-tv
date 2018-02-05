package org.joker.tv.model.front.web;

import java.io.Serializable;

public class MessageDetails implements Serializable {

	private static final long serialVersionUID = -7092337524227085819L;

	private String messageKey;
	private String messageBody;

	public MessageDetails() {
		super();
	}

	public MessageDetails(String messageKey, String messageBody) {
		super();
		this.messageKey = messageKey;
		this.messageBody = messageBody;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
