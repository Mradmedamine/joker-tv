package org.joker.tv.model.front.web;

public enum SubscriptionStatus {

	OK("100"), INVALID("000"), REPEATED("550");

	private String value;

	SubscriptionStatus(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



}
