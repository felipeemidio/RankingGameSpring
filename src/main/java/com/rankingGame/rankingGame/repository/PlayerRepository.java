package com.rankingGame.rankingGame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rankingGame.rankingGame.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
	
	public Player findByCod(long cod);
	public List<Player> findAllByOrderByVictoriesDesc();
}
