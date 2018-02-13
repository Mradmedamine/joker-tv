package org.joker.tv.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class ChannelEntity extends BaseChannel {

	public ChannelEntity(String name, String url) {
		super(name, url);
	}

	public ChannelEntity() {
		super();
	}

}
