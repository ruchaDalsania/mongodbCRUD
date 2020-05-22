package com.sample.mongo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v2")
@Log4j2
public class MovieController {

	private MoviesRepository moviesRepository;

	@Autowired
	public MovieController(MoviesRepository moviesRepository) {
		this.moviesRepository = moviesRepository;
	}

	@Value("${spring.data.mongodb.uri}")
	private String cloudUri;

	@GetMapping("/cloud/movies")
	public ResponseEntity<List<Document>> getMoviesFromCloud(@RequestParam(required = false) String title) {
		com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI(cloudUri));
		MongoDatabase database = mongoClient.getDatabase("video");
		MongoCollection<Document> collection = database.getCollection("movies");
		try {
			List<Document> movies = collection.find(eq("title", title == null ? "Titanic" : title))
					.sort(descending("year")).into(new ArrayList<>());

			movies.forEach(d -> log.info(d.toJson()));

			if (movies.isEmpty()) {
				log.warn("No Movies for title: " + title);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			log.debug("list size: " + movies.size());
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("/movies")
//	public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
//		System.out.println(".....hi..... i am here......5");
//		com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI(
//				"mongodb+srv://m001-student:m001-mongodb-basics@cluster0-jxeqq.mongodb.net/test?authSource=admin&replicaSet=Cluster0-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true"));
//		MongoDatabase database = mongoClient.getDatabase("video");
//		MongoCollection<org.bson.Document> collection = database.getCollection("movies");
//		Movie m = new Movie("rucha", 10, 1992);
//		Document document = new Document().append("title", "rucha").append("viewerRating", 9.9).append("year", 1992);
//		// collection.insertOne(document);
//		System.out.println(collection.find().first().toJson());
//		System.out.println("*****");
//		try {
//			List<Movie> movies = new ArrayList<Movie>();
//			if (title == null)
//				moviesRepository.findAll().forEach(movies::add);
//			else
//				moviesRepository.findByTitleContaining(title).forEach(movies::add);
//			System.out.println("*****" + movies.size() + "*****");
//			if (movies.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(movies, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
