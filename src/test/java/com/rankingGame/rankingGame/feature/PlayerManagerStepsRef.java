package com.rankingGame.rankingGame.feature;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.hamcrest.core.IsEqual;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PlayerManagerStepsRef extends MyHttpRequester {
	
	
	@Given("^the client calls get /players$")
	public void call_players_get() throws Throwable {
		executeGet("http://localhost:8080/players");
	}
	
	@Given("^the client calls post /players$")
	public void call_players_post() throws Throwable {
		executePost("http://localhost:8080/players");
	}
	
	@Given("^the client calls delete /players/(\\d+)")
	public void call_players_delete(int cod) throws Throwable {
		executeDelete("http://localhost:8080/players/" + cod);
	}
	
	@Then("^the client receives status code of (\\d+)$")
	public void receive_status_code_of(int status) throws Throwable {
		assertThat("Status code Incorrect", 
				this.response.getStatusCode().value(), IsEqual.equalTo(status));
	}
	
	@And("^the client receives an array$")
	public void receive_array() throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		assertThat(root.isArray(), IsEqual.equalTo(true));
	}
	
	@And("^the client receives player with a cod$")
	public void receive_player() throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		int cod = root.get("cod").intValue();
		assertThat("Response without code", cod, notNullValue());
	}
	
	@And("^the player (\\d+) do not exist$")
	public void player_no_exists(int playerCod) throws Throwable {
		executeGet("http://localhost:8080/players");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		Iterator<JsonNode> it = root.iterator();
		boolean hasThatPlayer = false;
		while(it.hasNext()) {
			JsonNode element = it.next();
			if (element.findValue("cod").intValue() == playerCod){
				hasThatPlayer = true;
			}
		}
		
		assertThat("Player with code " + playerCod + " exits" , hasThatPlayer, IsEqual.equalTo(false));
	}
}
