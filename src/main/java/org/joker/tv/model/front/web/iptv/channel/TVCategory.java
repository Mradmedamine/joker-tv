package org.joker.tv.model.front.web.iptv.channel;

import java.io.Serializable;

public class TVCategory implements Serializable {

	private static final long serialVersionUID = -7549907260793176857L;

	private String id;
	private String icon_url;
	private String caption;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
