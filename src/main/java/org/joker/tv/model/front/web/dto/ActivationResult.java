package org.joker.tv.model.front.web.dto;

import java.io.Serializable;

public class ActivationResult implements Serializable {

	private static final long serialVersionUID = 8765467349525354470L;

	private int account;
	private String status;
	private String message;

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
