package org.joker.tv.model.front.web.vod;

import java.io.Serializable;
import java.util.List;

public class VodsResult
	implements Serializable
{

	private static final long serialVersionUID = -3170952264465887807L;

	private VodCategories vodCategories;
	private List<Movie> movies;

	public VodCategories getVodCategories()
	{
		return vodCategories;
	}

	public void setVodCategories(VodCategories vodCategories)
	{
		this.vodCategories = vodCategories;
	}

	public List<Movie> getMovies()
	{
		return movies;
	}

	public void setMovies(List<Movie> movies)
	{
		this.movies = movies;
	}

}
