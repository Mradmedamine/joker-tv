package org.bsshare.tv.model.entity;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "sharing_subscription")
public class SharingSubscription extends BaseSubscription {

	private String user;
	private String pass;

	@PrePersist
	void generateUserAndPass() {
		this.user = RandomStringUtils.randomAlphanumeric(6);
		this.pass = RandomStringUtils.randomAlphanumeric(8);
	}
	
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
