package org.bsshare.tv.model.front.web;

public class ServerSubscriptionInfo extends ServerDto {

	private static final long serialVersionUID = 6875602107597216231L;

	private String user;
	private String pass;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
