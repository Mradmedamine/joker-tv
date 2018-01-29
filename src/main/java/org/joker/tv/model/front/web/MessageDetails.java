package org.joker.tv.model.front.web;

public class MessageDetails {

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
