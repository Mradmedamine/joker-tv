package org.bsshare.tv.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseChannel extends BaseEntity  {

	private String caption;
	private String streaming_url;
	private String status;
	private String icon_url;
	private String bitrate;
	private String number;
	private String num_future_epg_days;
	private String num_past_epg_days;
	private CategoryEntity category;
	
	public BaseChannel() {
		super();
	}

	public BaseChannel(String caption, String streaming_url) {
		super();
		this.caption = caption;
		this.streaming_url = streaming_url;
	}

	public String getNum_future_epg_days() {
		return num_future_epg_days;
	}

	public void setNum_future_epg_days(String num_future_epg_days) {
		this.num_future_epg_days = num_future_epg_days;
	}

	public String getNum_past_epg_days() {
		return num_past_epg_days;
	}

	public void setNum_past_epg_days(String num_past_epg_days) {
		this.num_past_epg_days = num_past_epg_days;
	}

	public String getStreaming_url() {
		return streaming_url;
	}

	public void setStreaming_url(String streaming_url) {
		this.streaming_url = streaming_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getBitrate() {
		return bitrate;
	}

	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
