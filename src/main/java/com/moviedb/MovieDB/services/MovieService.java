package com.moviedb.MovieDB.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviedb.MovieDB.models.Movie;
import com.moviedb.MovieDB.repository.MovieRepository;

@Service
public class MovieService {
	private final MovieRepository movieRepository; 
	
	@Autowired
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public Movie addMovie(Movie movie) {
		Movie res = this.movieRepository.save(movie);
		return res;
	}
	
	public Movie[] addMovies(Movie[] movies) {
		Movie[] result = new Movie[movies.length];
		for(int x = 0; x < movies.length; x++) {
			result[x] = this.movieRepository.save(movies[x]);
		}
		return result;
	}
	
	public Movie findMovieById(int id) {
		return this.movieRepository.findMovieById(id);
	}
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public Movie updateMovie(Movie movie) {
		return this.movieRepository.save(movie);
	}
	
	public void deleteMovie(int id) {
		this.movieRepository.deleteMovieById(id);
	}
	
	public List<Movie> getMoviesWhereFavoriteIs(boolean isFavorite) {
		return this.movieRepository.getMoviesWhereFavoriteIs(isFavorite);
	}
	
	public List<Movie> getAllMoviesSorted() {
		return movieRepository.getAllMoviesSorted();
	}

}
