package org.bsshare.tv.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tv_category")
public class CategoryEntity extends BaseEntity {

	private String icon_url;
	private String caption;

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
