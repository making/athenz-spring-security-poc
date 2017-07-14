package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@GetMapping("/movie")
	@PreAuthorize("hasPermission('rec.movie', 'read')")
	public Movie getMovie() {
		Movie movie = new Movie();
		movie.name = "Slap Shot";
		movie.director = "George Roy Hill";
		return movie;
	}

	static class Movie {
		public String name;
		public String director;
	}
}