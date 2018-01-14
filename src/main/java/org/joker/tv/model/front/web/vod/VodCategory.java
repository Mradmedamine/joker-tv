package org.joker.tv.model.front.web.vod;

import java.io.Serializable;

public class VodCategory implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3436761563676122465L;
	private String id;
	private String caption;
	private String icon_url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

}
