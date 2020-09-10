package com.challenge.source.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

	@GetMapping(value="/source")
	public String source () {
		return "https://github.com/leandrow3b/challenge";
	}
}
