package org.joker.tv.model.front.web.vod;

import java.io.Serializable;
import java.util.List;

public class VodsResult implements Serializable {

	private static final long serialVersionUID = -3170952264465887807L;

	private VodCategories vod_categories;
	private List<Movie> movies;

	public VodCategories getVod_categories() {
		return vod_categories;
	}

	public void setVod_categories(VodCategories vod_categories) {
		this.vod_categories = vod_categories;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
