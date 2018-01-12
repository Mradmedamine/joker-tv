package org.joker.tv.model.front.web.vod;

import java.io.Serializable;
import java.util.List;

public class VodCategories
	implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 730330245898835773L;
	private List<VodCategory> vodCategory;

	public List<VodCategory> getVodCategory()
	{
		return vodCategory;
	}

	public void setVodCategory(List<VodCategory> vodCategory)
	{
		this.vodCategory = vodCategory;
	}

}
