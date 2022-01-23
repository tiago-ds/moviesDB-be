package com.moviedb.MovieDB.models;

public class ImdbRatings {
	private String imdbId;
	private String title;
	private String fullTitle;
	private String type;
	private int year;
	private double imDb;
	private int metacritic;
	private double theMovieDb;
	private int rottenTomatoes;
	private String tvCom;
	private double filmAffinity;
	private String errorMessage;
	public ImdbRatings(String imdbId, String title, String fullTitle, String type, int year, double imDb,
			int metacritic, double theMovieDb, int rottenTomatoes, String tvCom, double filmAffinity,
			String errorMessage) {
		this.imdbId = imdbId;
		this.title = title;
		this.fullTitle = fullTitle;
		this.type = type;
		this.year = year;
		this.imDb = imDb;
		this.metacritic = metacritic;
		this.theMovieDb = theMovieDb;
		this.rottenTomatoes = rottenTomatoes;
		this.tvCom = tvCom;
		this.filmAffinity = filmAffinity;
		this.errorMessage = errorMessage;
	}
	public String getImDbId() {
		return imdbId;
	}
	public void setImDbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullTitle() {
		return fullTitle;
	}
	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getImDb() {
		return imDb;
	}
	public void setImDb(double imDb) {
		this.imDb = imDb;
	}
	public int getMetacritic() {
		return metacritic;
	}
	public void setMetacritic(int metacritic) {
		this.metacritic = metacritic;
	}
	public double getTheMovieDb() {
		return theMovieDb;
	}
	public void setTheMovieDb(double theMovieDb) {
		this.theMovieDb = theMovieDb;
	}
	public int getRottenTomatoes() {
		return rottenTomatoes;
	}
	public void setRottenTomatoes(int rottenTomatoes) {
		this.rottenTomatoes = rottenTomatoes;
	}
	public String getTvCom() {
		return this.tvCom;
	}
	public void setTvCom(String tvCom) {
		this.tvCom = tvCom;
	}
	public double getFilmAffinity() {
		return filmAffinity;
	}
	public void setFilmAffinity(double filmAffinity) {
		this.filmAffinity = filmAffinity;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
