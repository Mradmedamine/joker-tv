package org.joker.tv.model.front.web.iptv.vod;

import java.io.Serializable;

public class Movie implements Serializable {

	private static final long serialVersionUID = 4751593212740085155L;
	private String id;
	private String caption;
	private String v_url;
	private String poster_url;
	private MovieDetails[] details;

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

	public String getV_url() {
		return v_url;
	}

	public void setV_url(String v_url) {
		this.v_url = v_url;
	}

	public String getPoster_url() {
		return poster_url;
	}

	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}

	public MovieDetails[] getDetails() {
		return details;
	}

	public void setDetails(MovieDetails[] details) {
		this.details = details;
	}

}
