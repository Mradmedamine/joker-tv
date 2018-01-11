package org.joker.tv.model;

public enum ProductModel {

	K0("K0"), K3("K3");

	private String value;

	private ProductModel(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
