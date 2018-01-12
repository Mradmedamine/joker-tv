package org.joker.tv.model.front.web.vod;

import java.io.Serializable;

public class Movie
	implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4751593212740085155L;
	private String id;
	private String caption;
	private String vUrl;
	private String posterUrl;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCaption()
	{
		return caption;
	}

	public void setCaption(String caption)
	{
		this.caption = caption;
	}

	public String getVUrl()
	{
		return vUrl;
	}

	public void setVUrl(String vUrl)
	{
		this.vUrl = vUrl;
	}

	public String getPosterUrl()
	{
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl)
	{
		this.posterUrl = posterUrl;
	}

}
