package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TvShowController {

	@GetMapping("/tvshow")
	@PreAuthorize("hasPermission('rec.tvshow', 'read')")
	public TvShow getTvShow() {
		TvShow tvShow = new TvShow();
		tvShow.name = "Middle";
		tvShow.channel = "ABC";
		return tvShow;
	}

	static class TvShow {
		public String name;
		public String channel;
	}
}