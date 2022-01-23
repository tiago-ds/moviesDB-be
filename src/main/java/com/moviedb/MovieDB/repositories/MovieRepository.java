package com.moviedb.MovieDB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.moviedb.MovieDB.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM movie WHERE id=:id", nativeQuery=true)
	int deleteMovieById(@Param("id") int id);
	
	Movie findMovieById(int id);
	
	@Query(value="SELECT * FROM movie WHERE favorite=:isFavorite ORDER BY launch_year ASC", nativeQuery=true)
	public List<Movie> getMoviesWhereFavoriteIs(@Param("isFavorite") boolean isFavorite);
	
	@Query(value="SELECT * FROM movie ORDER BY launch_year ASC", nativeQuery=true)
	public List<Movie> getAllMoviesSorted();
	
	@Query(value="SELECT * FROM movie m WHERE m.name=:mName AND m.imdb_rate=:mRate AND m.launch_year=:mLaunch", nativeQuery=true)
	public List<Movie> findMovie(@Param("mName") String mName, @Param("mRate") double mRate, @Param("mLaunch") int mLaunch);
	
}
