package com.rankingGame.rankingGame.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	// Return the page index.html
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
