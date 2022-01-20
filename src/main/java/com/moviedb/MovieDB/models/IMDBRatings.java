package com.moviedb.MovieDB.models;

public class IMDBRatings {
	private String imDbId;
	private String title;
	private String fullTitle;
	private String type;
	private int year;
	private double imDb;
	private int metacritic;
	private double theMovieDb;
	private int rottenTomatoes;
	private String tV_com;
	private double filmAffinity;
	private String errorMessage;
	public IMDBRatings(String imDbId, String title, String fullTitle, String type, int year, double imDb,
			int metacritic, double theMovieDb, int rottenTomatoes, String tV_com, double filmAffinity,
			String errorMessage) {
		super();
		this.imDbId = imDbId;
		this.title = title;
		this.fullTitle = fullTitle;
		this.type = type;
		this.year = year;
		this.imDb = imDb;
		this.metacritic = metacritic;
		this.theMovieDb = theMovieDb;
		this.rottenTomatoes = rottenTomatoes;
		this.tV_com = tV_com;
		this.filmAffinity = filmAffinity;
		this.errorMessage = errorMessage;
	}
	public String getImDbId() {
		return imDbId;
	}
	public void setImDbId(String imDbId) {
		this.imDbId = imDbId;
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
	public String gettV_com() {
		return tV_com;
	}
	public void settV_com(String tV_com) {
		this.tV_com = tV_com;
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
