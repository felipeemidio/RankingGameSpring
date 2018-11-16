package com.rankingGame.rankingGame.feature;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyHttpRequester {
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response;
	
	public void executeGet(String url) {
		response = restTemplate.getForEntity(url, String.class);
	}
	
	public void executePost(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"name\": \"Jose\", \"victories\": \"2\", \"numberOfGames\": \"3\" }";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
	    response = restTemplate.postForEntity(url, entity, String.class);
		
	}
	
	public void executeDelete(String url) {
	    //restTemplate.delete(url);
	    response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
	}
}
