package com.moviedb.moviedb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviedb.moviedb.models.ImdbMovie;
import com.moviedb.moviedb.models.ImdbRatings;
import com.moviedb.moviedb.models.Movie;
import com.moviedb.moviedb.repositories.MovieRepository;

@Service
public class MovieService {
	private final MovieRepository movieRepository; 
	private final ImdbService imdbService;
	
	@Autowired
	public MovieService(MovieRepository movieRepository, ImdbService imdbService) {
		this.movieRepository = movieRepository;
		this.imdbService = imdbService; 
	}
	
	public Movie addMovie(Movie movie) {
		if(movie.getName().length() == 0 || movie.getImbdRate() == 0 || movie.getLaunchYear() == 0) {
			return null;
		}
		Movie res = this.movieRepository.save(movie);
		return res;
	}
	
	
	public List<Movie> addMovies(String name) {
		
		ImdbMovie[] imdbMovies = this.imdbService.getMoviesPlainJSON(name);
		
		List<Movie> newMovies = new ArrayList<Movie>();
		for(int x = 0; x < imdbMovies.length; x++) {
			// For each movie, there will be a IMDB rating that we need to get from ImdbService
			ImdbRatings ratings = this.imdbService.getRatingsPlainJSON(imdbMovies[x].getId());
			
			// Movie entity properties
			String movieName = imdbMovies[x].getTitle();
			boolean isFavorite = false;
			double movieImdbRate = ratings.getImDb();
			int movieLaunchYear = ratings.getYear();
			String movieImageURL = imdbMovies[x].getImage();
			String movieDescription = imdbMovies[x].getDescription();
			
			// If the movie does not have a name, an IMDBRate or a LaunchYear, it does not go
			// to the database
			if(movieName.length() == 0 || movieImdbRate == 0 || movieLaunchYear == 0) {
				continue;
			}
			
			// Avoid adding movies already inserted
			// This is considered the same movie if it has the same Name, IMDB Rate and Launch Year
			List<Movie> existingMovies = this.movieRepository.findMovie(movieName, movieImdbRate, movieLaunchYear);
			
			if(existingMovies != null && existingMovies.size() == 0) {
				newMovies.add(new Movie(movieName, isFavorite, movieImdbRate, movieLaunchYear, movieImageURL, movieDescription));
			}
			else {
				continue;
			}
		}
		if(newMovies.size() > 0) {
			List<Movie> result = this.movieRepository.saveAll(newMovies);
			// If this returns null, the controller will detect it and do the proper treatment
			return result;
		} else {
			// Returns a empty list, to assure that the system did not generate an error
			return newMovies;
		}
		
	}
	
	// Those does not need to be checked if the return is null, since the null return is checked in Controller
	
	public Movie findMovieById(int id) {
		Movie result = this.movieRepository.findMovieById(id);
		return result;
	}
	
	// Return a page of {limit} movies from the DB, based on the offset
	public List<Movie> getMoviesPage(int offset) {
		List<Movie> result = this.movieRepository.getMoviesPage(offset);
		return result;
	}

	public Movie updateMovie(Movie movie) {
		if(movie.getName().length() == 0 || movie.getImbdRate() == 0 || movie.getLaunchYear() == 0) {
			return null;
		}
		
		Movie oldMovie = this.movieRepository.findMovieById(movie.getMovieId());
		
		if(oldMovie != null) {
			return this.movieRepository.save(movie);
		} else {
			return null;
		}
	}
	
	
	public int deleteMovie(int id) {
		Movie movie = this.movieRepository.findMovieById(id);
		if (movie != null) {
			return this.movieRepository.deleteMovieById(id);
		}else {
			return 0;
		}
	}
	
	public List<Movie> getAllMoviesSorted() {
		List<Movie> result = movieRepository.getAllMoviesSorted();
		return result;
	}

}
