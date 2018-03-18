package org.joker.tv.model.front.web;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum ComponentStatus {

	ACTIVATED(1L), NEW(10L), DEACTIVATED(0L);

	private long value;

	ComponentStatus(long value) {
		this.value = value;
	}

	public static ComponentStatus fromValue(Long value) {
		Objects.requireNonNull(value, "value cannot be null");
		Optional<ComponentStatus> status = Arrays.asList(ComponentStatus.values()).stream()
				.filter(e -> value.equals(e.getValue()))
		        .findFirst();
		return status.orElseThrow(() -> new IllegalArgumentException("no Component Status found for value " + value));
	}

	public long getValue() {
		return value;
	}

	public String getName() {
		return name();
	}

}
