package org.bsshare.tv.model.front.web;

import java.io.Serializable;

public class SignUpUser implements Serializable {

	private static final long serialVersionUID = 4228985133816224201L;

	private String username;
	private String password;
	private String passwordConfirm;
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
