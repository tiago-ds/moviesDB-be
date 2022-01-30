package com.moviedb.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.moviedb.models.Movie;
import com.moviedb.vo.MovieVO;
import com.moviedb.services.MovieService;
import com.moviedb.exception.InvalidImdbResponseException;
import com.moviedb.exception.ServiceException;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	private final MovieService movieService;
	
	public MovieResource(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@PostMapping("/")
	public ResponseEntity<MovieVO> addMovie(@RequestBody Movie movie) throws ServiceException {
		MovieVO newMovie = this.movieService.addMovie(movie);
		return new ResponseEntity<MovieVO>(newMovie, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieVO>> getAllMovies() {
		List<MovieVO> movies = this.movieService.getAllMovies();
		return new ResponseEntity<List<MovieVO>>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieVO> getMovieById(@PathVariable("id") int id) {
		MovieVO movie = this.movieService.findMovieById(id);
		return new ResponseEntity<MovieVO>(movie, HttpStatus.OK);
	}
	
	@GetMapping("/batch/{offset}")
	public ResponseEntity<List<MovieVO>> getAllMoviesBatch(@PathVariable int offset) {
		List<MovieVO> movies = this.movieService.getMoviesPage(offset);
		return new ResponseEntity<List<MovieVO>>(movies, HttpStatus.OK);
	}
	
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<MovieVO>> getIMDBMovie(@PathVariable("name") String name) throws InvalidImdbResponseException, ServiceException {
		List<MovieVO> createdMovies = this.movieService.addMovies(name);
		return new ResponseEntity<List<MovieVO>>(createdMovies, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<MovieVO> updateMovie(@RequestBody Movie movie) throws Exception {
		MovieVO updatedMovie = this.movieService.updateMovie(movie);
		return new ResponseEntity<MovieVO>(updatedMovie, HttpStatus.OK);
	}
	
	@PutMapping("/toggle-favorite/{id}")
	public ResponseEntity<MovieVO> toggleFavorite(@PathVariable("id") int id) {
		MovieVO oldMovie = this.movieService.toggleMovieFavorite(id);
		return new ResponseEntity<MovieVO>(oldMovie, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable("id") int id) {
		this.movieService.deleteMovie(id);
		return new ResponseEntity<String>(String.format("Movie of id %d successfuly deleted.", id), HttpStatus.OK);
	}

}
