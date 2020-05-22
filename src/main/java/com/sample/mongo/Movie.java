package com.sample.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class Movie {

	@Id
	private String Id;

	private String title;

	private float viewerRating;

	private int year;

	public Movie(String title, float viewerRating, int year) {
		this.title = title;
		this.viewerRating = viewerRating;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movies >> [id:" + Id + ",title:+" + title + ",viewerRating:" + viewerRating + ",year:+" + year + "]";
	}

}
