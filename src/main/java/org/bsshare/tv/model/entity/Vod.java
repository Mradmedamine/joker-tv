package org.bsshare.tv.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vod")
public class Vod extends BaseChannel {

	public Vod(String name, String url) {
		super(name, url);
	}

	public Vod() {
		super();
	}

}
