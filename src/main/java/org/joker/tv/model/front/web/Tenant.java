package org.joker.tv.model.front.web;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Tenant {

	ANONYMOUS("application", false), JOKER("joker", true), STAR("star", true);

	private String id;
	private boolean visible;

	private Tenant(String id, boolean visible) {
		this.id = id;
		this.visible = visible;
	}

	public String getId() {
		return id;
	}

	public boolean getVisible() {
		return visible;
	}

	public static Optional<Tenant> fromId(String id) {
		Objects.requireNonNull(id, "id cannot be null");
		return Arrays.asList(values()).stream().filter(e -> id.equals(e.getId())).findFirst();
	}

}
