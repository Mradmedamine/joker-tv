package org.joker.tv.model.front.web.dto;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 2961978224247217895L;

	private String login;
	private String macAddress;
	private String serialNumber;
	private String model;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
