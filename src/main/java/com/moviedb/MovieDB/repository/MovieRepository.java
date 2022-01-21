package com.moviedb.MovieDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moviedb.MovieDB.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{

	void deleteMovieById(int id);
	
	Movie findMovieById(int id);
	
	@Query(value="SELECT * FROM movie WHERE favorite=:isFavorite", nativeQuery=true)
	public List<Movie> getMoviesWhereFavoriteIs(@Param("isFavorite") boolean isFavorite);
	
	@Query(value="SELECT * FROM movie ORDER BY launch_year ASC", nativeQuery=true)
	public List<Movie> getAllMoviesSorted();
	
}
