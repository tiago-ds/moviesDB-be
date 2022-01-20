package com.moviedb.MovieDB.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private int id;
	private String name;
	private boolean favorite;
	private double IMDBNote;
	private int launchYear;
	private String image;
	
	public Movie() {}
	
	public Movie(String name, boolean favorite, double IMDBNote, int launchYear, String image) {
		super();
		this.name = name;
		this.favorite = favorite;
		this.IMDBNote = IMDBNote;
		this.launchYear = launchYear;
		this.image = image;
	}
	
	public int getMovieID() {
		return id;
	}
	public void setMovieID(int movieID) {
		this.id = movieID;
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
	public double getIMDBNote() {
		return IMDBNote;
	}
	public void setIMDBNote(double IMDBNote) {
		this.IMDBNote = IMDBNote;
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

	@Override
	public String toString() {
		return "Movie {movieID=" + id + ", name=" + name + ", favorite=" + favorite + ", IMDBNote=" + IMDBNote
				+ ", launchYear=" + launchYear + "}";
	}
	
}
