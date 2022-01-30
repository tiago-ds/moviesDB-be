package com.moviedb.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviedb.exception.InvalidImdbResponseException;
import com.moviedb.models.ImdbMovie;
import com.moviedb.models.ImdbRatings;
import com.moviedb.models.ImdbResponse;


@Service
public class ImdbService {
	
	private final RestTemplate restTemplate;
	
	private final String searchMoviesURL = "https://imdb-api.com/en/API/SearchMovie/";
	private final String getMovieRatingsURL = "https://imdb-api.com/en/API/Ratings/";
	private final String apiKey;
	
	public ImdbService(RestTemplateBuilder restTemplateBuilder, @Value("${api.imdbApiKey}") String apiKey) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = apiKey;
	}
	
	public List<ImdbMovie> getMoviesPlainJSON(String name) throws InvalidImdbResponseException {
		String url = this.searchMoviesURL + this.apiKey + "/" + name;
		ResponseEntity<ImdbResponse> response = this.restTemplate.getForEntity(url, ImdbResponse.class);
		if(!response.getStatusCode().is2xxSuccessful() || response.getBody().getResults() == null) {
			throw new InvalidImdbResponseException("Invalid IMDB API Response");
		}
		return Arrays.asList(response.getBody().getResults());	 
	}
	
	public ImdbRatings getRatingsPlainJSON(String id) throws InvalidImdbResponseException {
		String url = this.getMovieRatingsURL + this.apiKey + "/" + id;
		ResponseEntity<ImdbRatings> response = this.restTemplate.getForEntity(url, ImdbRatings.class);
		if(!response.getStatusCode().is2xxSuccessful()) {
			throw new InvalidImdbResponseException("Invalid IMDB API Response");
		}
		return response.getBody();
	}
}

