package org.joker.tv.model.front.web;

public enum SubscriptionStatus {

	OK(100), INVALID(000), REPEATED(550);

	private int value;

	SubscriptionStatus(int value) {
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
