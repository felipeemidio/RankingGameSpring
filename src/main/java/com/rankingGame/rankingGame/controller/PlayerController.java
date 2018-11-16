package com.rankingGame.rankingGame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rankingGame.rankingGame.model.Player;
import com.rankingGame.rankingGame.model.PlayerService;
import com.rankingGame.rankingGame.repository.PlayerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private PlayerRepository pr;
	private PlayerService service = new PlayerService();
	
	/*
	 * Save a new player in the database.
	 * 
	 * @param player the data of the new player.
	 * @return       A ResponseEntity with the new player.
	 */
	@PostMapping
	public ResponseEntity<Player> save(@Valid @RequestBody Player player) {
		System.out.println("Saving");
		pr.save(player);
		return ResponseEntity.ok(player);
	}
	
	/*
	 * Get a list of all player sorted by victories.
	 * 
	 * @return   A List with all players.
	 */
	@GetMapping
	public List<Player> playersList() {
		List<Player> players = pr.findAllByOrderByVictoriesDesc();
		return players;
	}
	
	/*
	 * Delete a player from the database.
	 * 
	 * @param id the primary key of the player.
	 * @return   A ResponseEntity w/ code 204 - No Content or 404 - File not Found
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Player> deletePlayer(@PathVariable long id) {
		Player player = pr.findByCod(id);
		
		if(player == null) {
			return ResponseEntity.notFound().build();
		}
		pr.delete(player);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * Increment the victory variable.
	 * 
	 * @param id the primary key of the player.
	 * @return   A ResponseEntity with badRequest or the new Player.
	 */
	@PutMapping("/addVictory/{id}")
	public ResponseEntity<Player> addVictory(@PathVariable long id) {
		Player player = pr.findByCod(id);
		if(player == null) {
			System.out.println("ERROR - Player " + id + " not found");
			return ResponseEntity.badRequest().build();
		}
		if( service.canIncrementVictory(player) ) {
			player.setVictories(player.getVictories() + 1);
			pr.save(player);
		}
		return ResponseEntity.ok(player);
	}
	
	/*
	 * Increment the numberOfGames variable.
	 * 
	 * @param id the primary key of the player.
	 * @return   the updated player.
	 */
	@PutMapping("/addGame/{id}")
	public ResponseEntity<Player> addGame(@PathVariable long id) {
		Player player = pr.findByCod(id);
		player.setNumberOfGames(player.getNumberOfGames() + 1);
		pr.save(player);
		return ResponseEntity.ok(player);
	}
	
	/*
	 * Search for a player given the id.
	 * this function is only used in test.
	 * @param id the primary key of the player.
	 * @return   the asked player.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Player> search(@PathVariable long id){
		Player player = pr.findByCod(id);
		
		if(player == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(player);
	}
}