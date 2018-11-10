package com.rankingGame.rankingGame.repository;

import org.springframework.data.repository.CrudRepository;

import com.rankingGame.rankingGame.model.Player;

public interface PlayerRepository extends CrudRepository<Player, String>{

	public Player findByCod(long cod);
	
	public Iterable<Player> findAllByOrderByVictoriesDesc();
}
