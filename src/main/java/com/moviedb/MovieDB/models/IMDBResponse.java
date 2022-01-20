package com.moviedb.MovieDB.models;

import java.io.Serializable;

public class IMDBResponse implements Serializable{
	private String searchType;
	private IMDBMovie[] results;
	private String expression;
	private String errorMessage;
	
	public IMDBResponse(String searchType, IMDBMovie[] results, String expression) {
		super();
		this.searchType = searchType;
		this.results = results;
		this.expression = expression;
	}

	public IMDBMovie[] getResults() {
		return results;
	}

	public void setResults(IMDBMovie[] results) {
		this.results = results;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
