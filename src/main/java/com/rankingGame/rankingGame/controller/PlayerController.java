package com.rankingGame.rankingGame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rankingGame.rankingGame.model.Player;
import com.rankingGame.rankingGame.repository.PlayerRepository;

@RestController
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private PlayerRepository pr;
	
	@RequestMapping(value="/registerPlayer", method=RequestMethod.GET)
	public String form() {
		return "player/formPlayer";
	}
	
	@PostMapping
	public Player form(@Valid @RequestBody Player player) {
		return pr.save(player);
	}
	
	@GetMapping
	public List<Player> PlayersList() {
		List<Player> players = pr.findAllByOrderByVictoriesDesc();
		return players;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Player> search(@PathVariable long id){
		Player player = pr.findByCod(id);
		
		if(player == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(player);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Player> updatePlayer(@PathVariable long id, 
			@Valid @RequestBody Player newPlayer) {
		Player oldPlayer = pr.findByCod(id);
		
		if(oldPlayer == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(newPlayer, oldPlayer, "cod");
		oldPlayer = pr.save(oldPlayer);
		return ResponseEntity.ok(oldPlayer);
	}
	
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
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deletePlayer(long cod) {
		Player player = pr.getOne(cod);
		pr.delete(player);
		return "redirect:/players"; 
	}
	
	@RequestMapping(value="/addV", method=RequestMethod.GET)
	public String addVictory(long cod) {
		Player player = pr.getOne(cod);
		if(player.getVictories()+1 <= player.getNumberOfGames() ) {
			player.setVictories(player.getVictories() + 1);
			pr.save(player);
		}
		return "redirect:/players";
	}
	
	@RequestMapping(value="/addG", method=RequestMethod.GET)
	public String addGame(long cod) {
		Player player = pr.getOne(cod);
		player.setNumberOfGames(player.getNumberOfGames() + 1);
		pr.save(player);
		return "redirect:/players";
	}
	*/
	
	
}
