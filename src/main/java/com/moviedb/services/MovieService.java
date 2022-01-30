package com.moviedb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviedb.exception.InvalidImdbResponseException;
import com.moviedb.exception.InvalidMovieException;
import com.moviedb.exception.MovieNotFoundException;
import com.moviedb.exception.ServiceException;
import com.moviedb.models.ImdbMovie;
import com.moviedb.models.ImdbRatings;
import com.moviedb.models.Movie;
import com.moviedb.repositories.MovieRepository;
import com.moviedb.vo.MovieVO;

@Service
public class MovieService {
	private final MovieRepository movieRepository; 
	private final ImdbService imdbService;
	
	@Autowired
	public MovieService(MovieRepository movieRepository, ImdbService imdbService) {
		this.movieRepository = movieRepository;
		this.imdbService = imdbService; 
	}
	
	public MovieVO addMovie(Movie movie) throws ServiceException, InvalidMovieException {
		if (this.isValidMovie(movie)) {
				Movie result = this.movieRepository.save(movie);
				if(result != null) {
					return new MovieVO(result.getMovieId(), result.getName(), result.isFavorite(), result.getImbdRate(), result.getLaunchYear(), result.getImage(), result.getDescription());
				}else {
					throw new ServiceException("An error ocurred in Movie Service");
				}
		} else {
			throw new InvalidMovieException("Invalid Movie Provided.");
		}

	}
	
	
	public List<MovieVO> addMovies(String name) throws InvalidImdbResponseException {
		
		List<ImdbMovie> imdbMovies = this.imdbService.getMoviesPlainJSON(name);
		
		List<Movie> newMovies = new ArrayList<Movie>();
		for(int x = 0; x < imdbMovies.size(); x++) {
			// For each movie, there will be a IMDB rating that we need to get from ImdbService
			ImdbRatings ratings = this.imdbService.getRatingsPlainJSON(imdbMovies.get(x).getId());
			
			Movie newMovie = new Movie(imdbMovies.get(x).getTitle(), false, ratings.getImDb(), ratings.getYear(), imdbMovies.get(x).getImage(), imdbMovies.get(x).getDescription());
			
			// If the movie does not have a name, an IMDBRate or a LaunchYear, it does not go
			// to the database
			if(!this.isValidMovie(newMovie)) {
				continue;
			}
			
			// Avoid adding movies already inserted
			// This is considered the same movie if it has the same Name, IMDB Rate and Launch Year
			List<Movie> existingMovies = this.movieRepository.findMovie(newMovie.getName(), newMovie.getImbdRate(), newMovie.getLaunchYear());
			
			if(existingMovies != null && existingMovies.size() == 0) {
				newMovies.add(newMovie);
			}
			else {
				continue;
			}
		}
		// Actual return results
		List<Movie> result = this.movieRepository.saveAll(newMovies);
		if(result != null) {
			List<MovieVO> vos = new ArrayList<MovieVO>();
			for (Movie movie : result) {
	            vos.add(new MovieVO(movie.getMovieId(), movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription()));
	        }
			return vos;
		} else {
			throw new ServiceException("An error ocurred in Movie Service");
		}
	}
	
	// Those does not need to be checked if the return is null, since the null return is checked in Controller
	
	public MovieVO findMovieById(int id) {
		Movie result = this.movieRepository.findMovieById(id);
		if(result != null) {
			return new MovieVO(result.getMovieId(), result.getName(), result.isFavorite(), result.getImbdRate(), result.getLaunchYear(), result.getImage(), result.getDescription());
		}
		else {
			throw new MovieNotFoundException(String.format("Could not find movie of id: %d", id));
		}
	}
	
	// Return a page of {limit} movies from the DB, based on the offset
	public List<MovieVO> getMoviesPage(int offset) {
		List<Movie> movies = this.movieRepository.getMoviesPage(offset);
		if(movies != null) {
			List<MovieVO> vos = new ArrayList<MovieVO>();
			for (Movie movie : movies) {
	            vos.add(new MovieVO(movie.getMovieId(), movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription()));
	        }
			return vos;
		} else {
			throw new ServiceException("Service could not find movies in DB");
		}
	}

	public MovieVO updateMovie(Movie movie) {
		if(this.isValidMovie(movie)) {
			Movie oldMovie = this.movieRepository.findMovieById(movie.getMovieId());
			if(oldMovie != null) {
				Movie result = this.movieRepository.save(movie);
				return new MovieVO(result.getMovieId(), result.getName(), result.isFavorite(), result.getImbdRate(), result.getLaunchYear(), result.getImage(), result.getDescription());
			} else {
				throw new MovieNotFoundException(String.format("Could not find movie of id: %d", movie.getMovieId()));
			}
		} else {
			throw new InvalidMovieException("Invalid Movie Provided.");
		}
	}
	
	
	public int deleteMovie(int id) {
		Movie movie = this.movieRepository.findMovieById(id);
		if(movie != null) {
			int result = this.movieRepository.deleteMovieById(id);
			if(result == 1) {
				return result;
			} else {
				throw new ServiceException("An error ocurred in Movie Service");
			}
		}
		else {
			throw new MovieNotFoundException(String.format("Could not find movie of id: %d", id));
		}
	}
	
	public List<MovieVO> getAllMovies() {
		List<Movie> movies = movieRepository.getAllMovies();
		if(movies != null) {
			List<MovieVO> vos = new ArrayList<MovieVO>();
			for (Movie movie : movies) {
	            vos.add(new MovieVO(movie.getMovieId(), movie.getName(), movie.isFavorite(), movie.getImbdRate(), movie.getLaunchYear(), movie.getImage(), movie.getDescription()));
	        }
			return vos;
		} else {
			throw new ServiceException("Service could not find movies in DB");
		}
	}
	
	public MovieVO toggleMovieFavorite(int id) {
		Movie movie = this.movieRepository.findMovieById(id);
		if (movie != null) {
			movie.setFavorite(!movie.isFavorite());
			MovieVO newMovie = this.updateMovie(movie);
			if(newMovie != null) {
				return newMovie;
			} else {
				throw new ServiceException("An error ocurred in Movie Service");
			}
		} else {
			throw new MovieNotFoundException(String.format("Could not find movie of id: %d", id));
		}
	}
	
	public boolean isValidMovie(Movie movie) {
		if(movie.getName() != null && !movie.getName().isEmpty() 
				&& movie.getImbdRate() != 0 && movie.getLaunchYear() != 0) {
			return true;
		}
		return false;
	}

}
