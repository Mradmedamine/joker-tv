package org.bsshare.tv.model.front.web;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum RoleEnum {

	USER_ROLE("USER_ROLE"), ADMIN_ROLE("ADMIN_ROLE"), WATCHER_ROLE("WATCHER_ROLE");

	private String value;

	private RoleEnum(String value) {
		this.value = value;
	}

	public static RoleEnum fromValue(String value) {
		Objects.requireNonNull(value, "value cannot be null");
		Optional<RoleEnum> status = Arrays.asList(RoleEnum.values()).stream().filter(e -> value.equals(e.getValue()))
				.findFirst();
		return status.orElseThrow(() -> new IllegalArgumentException("no RoleEnum found for value " + value));
	}

	public String getValue() {
		return value;
	}

}
