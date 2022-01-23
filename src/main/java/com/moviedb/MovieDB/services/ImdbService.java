package com.moviedb.MovieDB.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviedb.MovieDB.models.ImdbMovie;
import com.moviedb.MovieDB.models.ImdbRatings;
import com.moviedb.MovieDB.models.ImdbResponse;


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
	
	// Both methods have to return an Array, since it's the format that we get from the API
	// TODO: Perguntar se deveria ser criado um método de conversão de Array pra lista
	public ImdbMovie[] getMoviesPlainJSON(String name) {
		String url = this.searchMoviesURL + this.apiKey + "/" + name;
		ResponseEntity<ImdbResponse> response = this.restTemplate.getForEntity(url, ImdbResponse.class);
		if(response != null) {
			return response.getBody().getResults();
		}else {
			return null;
		}
	}
	
	public ImdbRatings getRatingsPlainJSON(String id) {
		String url = this.getMovieRatingsURL + this.apiKey + "/" + id;
		ResponseEntity<ImdbRatings> response = this.restTemplate.getForEntity(url, ImdbRatings.class);	
		if(response != null) {
			return response.getBody();
		}else {
			return null;
		}
	}
}

