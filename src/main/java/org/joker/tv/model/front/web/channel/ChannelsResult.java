package org.joker.tv.model.front.web.channel;

import java.io.Serializable;

public class ChannelsResult
	implements Serializable
{

	private static final long serialVersionUID = 2018779061400449111L;
	
	private TVCategories tv_categories;
	private TVChannel[] tv_channel;

	public TVCategories getTv_categories()
	{
		return tv_categories;
	}

	public void setTv_categories(TVCategories tv_categories)
	{
		this.tv_categories = tv_categories;
	}

	public TVChannel[] getTv_channel()
	{
		return tv_channel;
	}

	public void setTv_channel(TVChannel[] tv_channel)
	{
		this.tv_channel = tv_channel;
	}

}
