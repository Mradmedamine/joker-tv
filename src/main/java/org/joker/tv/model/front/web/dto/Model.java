package org.joker.tv.model.front.web.dto;

public enum Model {

	K0("K0"), K3("K3");

	private String value;

	private Model(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
