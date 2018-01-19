package org.joker.tv.model.front.web;

import java.io.Serializable;

public class ActivationResult implements Serializable {

	private static final long serialVersionUID = 8765467349525354470L;

	private int account;
	private int status;
	private String message;

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
