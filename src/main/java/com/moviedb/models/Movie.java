package com.moviedb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	private String name;
	private boolean favorite;
	private double imdbRate;
	private int launchYear;
	private String image;
	private String description;
	
	public Movie() {}
	
	public Movie(String name, boolean favorite, double imdbRate, int launchYear, String image, String description) {
		this.name = name;
		this.favorite = favorite;
		this.imdbRate = imdbRate;
		this.launchYear = launchYear;
		this.image = image;
		this.description = description;
	}
	
	public int getMovieId() {
		return id;
	}
	public void setMovieID(int movieId) {
		this.id = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public double getImbdRate() {
		return imdbRate;
	}
	public void setImdbRate(double imdbRate) {
		this.imdbRate = imdbRate;
	}
	public int getLaunchYear() {
		return launchYear;
	}
	public void setLaunchYear(int launchYear) {
		this.launchYear = launchYear;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Movie {movieId=" + id + ", name=" + name + ", favorite=" + favorite + ", ImdbRate=" + imdbRate
				+ ", launchYear=" + launchYear + "}";
	}
	
}
