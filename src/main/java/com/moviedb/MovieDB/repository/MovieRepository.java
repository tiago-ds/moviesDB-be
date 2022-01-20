package com.moviedb.MovieDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviedb.MovieDB.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{

	void deleteMovieById(int id);
	Movie findMovieById(int id);
}
