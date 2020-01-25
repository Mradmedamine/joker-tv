package org.bsshare.tv.model.front.web.iptv.channel;

import java.util.ArrayList;
import java.util.List;

public class TVCategories {

	private List<TVCategory> tv_category;

	public List<TVCategory> getTv_category() {
		if (tv_category == null) {
			tv_category = new ArrayList<>();
		}
		return tv_category;
	}

	public void setTv_category(List<TVCategory> tv_category) {
		this.tv_category = tv_category;
	}

}
