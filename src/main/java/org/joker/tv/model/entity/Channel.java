package org.joker.tv.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel extends BaseChannel {

	public Channel(String name, String url) {
		super(name, url);
	}

	public Channel() {
		super();
	}

}