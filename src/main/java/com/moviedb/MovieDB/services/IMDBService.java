package com.moviedb.MovieDB.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviedb.MovieDB.models.IMDBMovie;
import com.moviedb.MovieDB.models.IMDBRatings;
import com.moviedb.MovieDB.models.IMDBResponse;

@Service
public class IMDBService {
	private final RestTemplate restTemplate;
	private final String APIKey;
	
	public IMDBService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.APIKey = "k_3dyenf6z";
	}

	public IMDBMovie[] getMoviesPlainJSON(String name) {
		String url = "https://imdb-api.com/en/API/SearchMovie/" + this.APIKey+"/" + name;
		ResponseEntity<IMDBResponse> response = this.restTemplate.getForEntity(url, IMDBResponse.class);		
		return response.getBody().getResults();
	}
	
	public IMDBRatings getRatingsPlainJSON(String id) {
		String url = "https://imdb-api.com/en/API/Ratings/" + this.APIKey + "/" + id;
		ResponseEntity<IMDBRatings> response = this.restTemplate.getForEntity(url, IMDBRatings.class);	
		return response.getBody();
	}
}

