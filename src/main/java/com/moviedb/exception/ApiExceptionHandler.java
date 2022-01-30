package com.moviedb.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {MovieNotFoundException.class})
	public ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException apiException = new ApiException(e.getMessage(), notFound, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, notFound);
	}
	
	@ExceptionHandler(value = {InvalidImdbResponseException.class})
	public ResponseEntity<Object> handleInvalidImdbResponseException(InvalidImdbResponseException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, badRequest);
	}
	
	@ExceptionHandler(value = {InvalidMovieException.class})
	public ResponseEntity<Object> handleInvalidMovieException(InvalidMovieException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, badRequest);
	}
	
	@ExceptionHandler(value = {ServiceException.class})
	public ResponseEntity<Object> handleServiceException(ServiceException e) {
		HttpStatus internalError = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException apiException = new ApiException(e.getMessage(), internalError, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, internalError);
	}
}
