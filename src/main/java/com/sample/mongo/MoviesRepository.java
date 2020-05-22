package com.sample.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoviesRepository extends MongoRepository<Movie,String>{

	List<Movie> findByTitleContaining(String title);
	
	List<Movie> findByYear(int year);
}
