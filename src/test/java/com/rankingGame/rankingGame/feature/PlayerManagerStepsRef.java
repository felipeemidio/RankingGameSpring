package com.rankingGame.rankingGame.feature;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PlayerManagerStepsRef extends MyHttpRequester {
	int holder;
	
	@Given("^the client calls get /players$")
	public void call_players_get() throws Throwable {
		executeGet("http://localhost:8080/players");
	}
	
	@Given("^the client calls post /players$")
	public void call_players_post() throws Throwable {
		executePost("http://localhost:8080/players");
	}
	
	@Given("^the client calls delete /players/(\\d+)$")
	public void call_players_delete(int cod) throws Throwable {
		executeDelete("http://localhost:8080/players/" + cod);
	}
	
	@Given("^the client calls get /players/(\\d+)$")
	public void call_players_get_player(int cod) throws Throwable {
		executeGet("http://localhost:8080/players/" + cod);
	}
	
	@Given("^get the (.+) of player (\\d+)$")
	public void get_variable_from_player_with_cod(String field, int codPlayer) throws Throwable {
		executeGet("http://localhost:8080/players/"+ codPlayer);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		holder = root.get(field).intValue();
		
	}
	
	@When("^the client calls put /players/addVictory/(\\d+)$")
	public void call_players_get_player_victory(int cod) throws Throwable {
		executePut("http://localhost:8080/players/addVictory/" + cod);
	}
	
	@When("^the client calls put /players/addGame/(\\d+)$")
	public void call_players_get_player_number_of_games(int cod) throws Throwable {
		executePut("http://localhost:8080/players/addGame/" + cod);
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
		executeGet("http://localhost:8080/players/"+ playerCod);
		assertThat("Status code Incorrect", 
				this.response.getStatusCode().value(), IsEqual.equalTo(204));
	}
	
	@And("^the player (\\d+) has incremented the (.+)$")
	public void has_player_incremented_victory(int playerCod, String field) throws Throwable {
		executeGet("http://localhost:8080/players/"+ playerCod);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		int fieldValue = root.get(field).intValue();
		System.out.println("HOLDER: " + holder);
		assertThat("Number of "+ field + " is wrong", 
				fieldValue, IsEqual.equalTo( holder+1 ));
	}
}
