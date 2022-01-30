package com.moviedb.exception;

public class InvalidImdbResponseException extends Exception{
	public InvalidImdbResponseException(String message) {
		super(message);
	}
	
	public InvalidImdbResponseException(String message, Throwable cause) {
		super(message, cause);
	}
}
