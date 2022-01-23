package com.moviedb.MovieDB.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviedb.MovieDB.models.Movie;
import com.moviedb.MovieDB.services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	private final MovieService movieService;
	
	public MovieResource(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		Movie newMovie = this.movieService.addMovie(movie);
		if(newMovie != null) {
			return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("ERROR: COULD NOT ADD MOVIE", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") int id) {
		Movie movie = this.movieService.findMovieById(id);
		if(movie != null) {
			return new ResponseEntity<>(movie, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("ERROR: COULD NOT FIND MOVIE", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllMovies(@RequestParam(required = false) boolean sorted) {
		List<Movie> movies;
		
		// Sorted Param
		if(sorted) {
			movies = this.movieService.getAllMoviesSorted();
		}
		else {
			movies = this.movieService.getAllMovies();
		}
		// Return statements
		if(movies != null) {
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("ERROR: COULD NOT FIND MOVIES", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> getIMDBMovie(@PathVariable("name") String name) {
		List<Movie> createdMovies = this.movieService.addMovies(name);
		
		if(createdMovies != null) {
			return new ResponseEntity<List<Movie>>(createdMovies, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("ERROR: IMDB API DID NOT RESPOND", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/favorites")
	public ResponseEntity<?> getFavoriteMovies() {
		List<Movie> favoritedMovies = this.movieService.getMoviesWhereFavoriteIs(true);
		
		if(favoritedMovies != null) {
			return new ResponseEntity<>(favoritedMovies, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("ERROR: COULD NOT FIND MOVIES", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		Movie updatedMovie = this.movieService.updateMovie(movie);
		if (updatedMovie != null) {
			return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ERROR: COULD NOT ADD MOVIE", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/toggle-favorite/{id}")
	public ResponseEntity<?> toggleFavorite(@PathVariable("id") int id) {
		Movie oldMovie = this.movieService.findMovieById(id);
		if (oldMovie != null) {
			oldMovie.setFavorite(!oldMovie.isFavorite()); 
			Movie updatedMovie = this.movieService.updateMovie(oldMovie);
			if (updatedMovie != null) {
				return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("ERROR: COULD NOT FAVORITE MOVIE", HttpStatus.BAD_REQUEST); 
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") int id) {
		int result = this.movieService.deleteMovie(id);
		if(result == 1) {
			return new ResponseEntity<>("MOVIE SUCESSFULLY DELETED", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("ERROR: MOVIE NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

}
