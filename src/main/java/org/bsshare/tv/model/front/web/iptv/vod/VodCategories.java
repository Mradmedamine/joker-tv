package org.bsshare.tv.model.front.web.iptv.vod;

import java.io.Serializable;
import java.util.List;

public class VodCategories implements Serializable {

	private static final long serialVersionUID = 730330245898835773L;

	private List<VodCategory> vod_category;

	public List<VodCategory> getVod_category() {
		return vod_category;
	}

	public void setVod_category(List<VodCategory> vod_category) {
		this.vod_category = vod_category;
	}

}
