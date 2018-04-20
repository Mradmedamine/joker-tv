package org.bsshare.tv.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.apache.commons.lang3.RandomStringUtils;
import org.bsshare.tv.model.front.web.ComponentStatus;

@MappedSuperclass
public abstract class BaseSubscription extends BaseEntity {

	private String activeCode;
	private LocalDate expiration;
	private ComponentStatus status;
	private DeviceEntity device;

	@PrePersist
	private void generateActiveCode() {
		activeCode = RandomStringUtils.randomNumeric(10);
	}

	@Column(unique = true)
	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public ComponentStatus getStatus() {
		return status;
	}

	public void setStatus(ComponentStatus status) {
		this.status = status;
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	public DeviceEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
}
