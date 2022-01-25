package com.moviedb.moviedb.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.moviedb.moviedb.models.Movie;
import com.moviedb.moviedb.services.MovieService;
import com.moviedb.moviedb.vo.MovieVo;

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
			MovieVo vo = new MovieVo(newMovie.getMovieId(),newMovie.getName(), newMovie.isFavorite(), newMovie.getImbdRate(), newMovie.getLaunchYear(), newMovie.getImage(), newMovie.getDescription());
			return new ResponseEntity<>(vo, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("ERROR: COULD NOT ADD MOVIE", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllMovies() {

		List<Movie> movies = this.movieService.getAllMovies();
		
		// Return statements
		if(movies != null) {
			List<MovieVo> vos = new ArrayList<MovieVo>();
			movies.forEach((movie) -> {
				MovieVo vo = new MovieVo(movie.getMovieId(),movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription());
				vos.add(vo);
			});
			return new ResponseEntity<List<MovieVo>>(vos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("ERROR: COULD NOT FIND MOVIES", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") int id) {
		Movie movie = this.movieService.findMovieById(id);
		if(movie != null) {
			MovieVo vo = new MovieVo(movie.getMovieId(),movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription());
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("ERROR: COULD NOT FIND MOVIE", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/batch/{offset}")
	public ResponseEntity<?> getAllMoviesBatch(@PathVariable int offset) {

		List<Movie> movies = this.movieService.getMoviesPage(offset);
		
		// Return statements
		if(movies != null) {
			List<MovieVo> vos = new ArrayList<MovieVo>();
			movies.forEach((movie) -> {
				MovieVo vo = new MovieVo(movie.getMovieId(),movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription());
				vos.add(vo);
			});
			return new ResponseEntity<List<MovieVo>>(vos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("ERROR: COULD NOT FIND MOVIES", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> getIMDBMovie(@PathVariable("name") String name) {
		List<Movie> createdMovies = this.movieService.addMovies(name);
		if(createdMovies != null) {
			List<MovieVo> vos = new ArrayList<MovieVo>();
			createdMovies.forEach((movie) -> {
				MovieVo vo = new MovieVo(movie.getMovieId(),movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription());
				vos.add(vo);
			});
			return new ResponseEntity<List<MovieVo>>(vos, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("ERROR: IMDB API DID NOT RESPOND", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		Movie updatedMovie = this.movieService.updateMovie(movie);
		if (updatedMovie != null) {
			MovieVo vo = new MovieVo(updatedMovie.getMovieId(),updatedMovie.getName(), updatedMovie.isFavorite(), updatedMovie.getImbdRate(), updatedMovie.getLaunchYear(), updatedMovie.getImage(), updatedMovie.getDescription());
			return new ResponseEntity<MovieVo>(vo, HttpStatus.OK);
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
				MovieVo vo = new MovieVo(updatedMovie.getMovieId(),updatedMovie.getName(), updatedMovie.isFavorite(), updatedMovie.getImbdRate(), updatedMovie.getLaunchYear(), updatedMovie.getImage(), updatedMovie.getDescription());
				return new ResponseEntity<MovieVo>(vo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("ERROR: COULD NOT FAVORITE MOVIE", HttpStatus.BAD_REQUEST); 
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") int id) {
		int result = this.movieService.deleteMovie(id);
		if(result == 1) {
			return new ResponseEntity<String>("MOVIE SUCESSFULLY DELETED", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("ERROR: MOVIE NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

}
