package org.joker.tv.model.front.web;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum ActivationStatus {

	OK("100"), INVALID("000"), REPEATED("550");

	private String value;

	ActivationStatus(String value) {
		setValue(value);
	}

	public static ActivationStatus fromValue(String value) {
		Objects.requireNonNull(value, "value cannot be null");
		Optional<ActivationStatus> status = Arrays.asList(ActivationStatus.values()).stream().filter(value::equals)
		        .findFirst();
		return status.orElseThrow(() -> new IllegalArgumentException("no Component Status found for value " + value));
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



}
