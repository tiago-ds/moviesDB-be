package com.moviedb.MovieDB;

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

import com.moviedb.MovieDB.models.IMDBMovie;
import com.moviedb.MovieDB.models.IMDBRatings;
import com.moviedb.MovieDB.models.Movie;
import com.moviedb.MovieDB.services.MovieService;
import com.moviedb.MovieDB.services.IMDBService;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	private final MovieService movieService;
	private final IMDBService imdbService;
	
	public MovieResource(MovieService movieService, IMDBService tmdbService) {
		this.movieService = movieService;
		this.imdbService = tmdbService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		Movie newMovie = this.movieService.addMovie(movie);
		System.out.println(newMovie.toString());
		return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = this.movieService.getAllMovies();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	
	@PutMapping("/update")
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
		Movie updatedMovie = this.movieService.updateMovie(movie);
		return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
	}
	
	@PutMapping("/update/toggle-favorite/{id}")
	public ResponseEntity<Movie> toggleFavorite(@PathVariable("id") int id) {
		Movie oldMovie = this.movieService.findMovieById(id);
		oldMovie.setFavorite(!oldMovie.isFavorite());
		Movie updatedMovie = this.movieService.updateMovie(oldMovie);
		return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") int id) {
		this.movieService.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<Movie[]> getIMDBMovie(@PathVariable("name") String name) {
		IMDBMovie[] movies = this.imdbService.getMoviesPlainJSON(name);
		
		Movie[] newMovies = new Movie[movies.length];
		
		for(int x = 0; x < movies.length; x++) {
			IMDBRatings ratings = this.imdbService.getRatingsPlainJSON(movies[x].getId());
			
			// Movie entity properties
			String movieName = movies[x].getTitle();
			boolean isFavorite = false;
			double movieIMDBNote = ratings.getImDb();
			int movieLaunchYear = ratings.getYear();
			String movieImageURL = movies[x].getImage();
			
			newMovies[x] = new Movie(movieName, isFavorite, movieIMDBNote, movieLaunchYear, movieImageURL);
		}
		
		Movie[] createdMovies = this.movieService.addMovies(newMovies);
		
		return new ResponseEntity<>(createdMovies, HttpStatus.OK);
	}

}
