package com.moviedb.moviedb.models;

public class ImdbResponse {
	private String searchType;
	private ImdbMovie[] results;
	private String expression;
	private String errorMessage;
	
	public ImdbResponse(String searchType, ImdbMovie[] results, String expression) {
		this.searchType = searchType;
		this.results = results;
		this.expression = expression;
	}

	public ImdbMovie[] getResults() {
		return results;
	}

	public void setResults(ImdbMovie[] results) {
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
