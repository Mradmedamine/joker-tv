package org.joker.tv.model.front.web;

import java.io.Serializable;

public class DeviceDto implements Serializable {

	private static final long serialVersionUID = 5955452684230020088L;

	private String serialNumber;
	private String macAddress;
	private String model;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
