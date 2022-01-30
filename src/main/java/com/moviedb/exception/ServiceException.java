package com.moviedb.exception;

public class ServiceException extends RuntimeException {
	public ServiceException(String msg) { super(msg); }
    public ServiceException(Exception ex) { super(ex); }
}
