package com.moviedb.exception;

public class InvalidMovieException extends RuntimeException {
	
	public InvalidMovieException(String message) {
		super(message);
	}
	
	public InvalidMovieException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

