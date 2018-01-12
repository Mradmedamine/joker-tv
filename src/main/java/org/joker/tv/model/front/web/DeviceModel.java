package org.joker.tv.model.front.web;

public enum DeviceModel {

	K0("K0"), K3("K3");

	private String value;

	private DeviceModel(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
