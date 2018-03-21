package org.bsshare.tv.model.front.web;

import java.io.Serializable;

public class SubscriptionDto implements Serializable {

	private static final long serialVersionUID = 2961978224247217895L;

	private String login;
	private String uid;
	private String serial;
	private String model;
	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
