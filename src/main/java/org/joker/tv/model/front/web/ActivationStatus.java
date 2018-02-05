package org.joker.tv.model.front.web;

public enum ActivationStatus {

	OK("100"), INVALID("000"), REPEATED("550");

	private String value;

	ActivationStatus(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



}
