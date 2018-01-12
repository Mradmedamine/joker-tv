package org.joker.tv.model.front.web;

import java.io.Serializable;

public class Device
	implements Serializable
{

	private static final long serialVersionUID = 2961978224247217895L;

	private String activeCode;
	private String macAddress;
	private String serialNumber;
	private String model;

	public String getActiveCode()
	{
		return activeCode;
	}

	public void setActiveCode(String activeCode)
	{
		this.activeCode = activeCode;
	}

	public String getMacAddress()
	{
		return macAddress;
	}

	public void setMacAddress(String macAddress)
	{
		this.macAddress = macAddress;
	}

	public String getSerialNumber()
	{
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

}
