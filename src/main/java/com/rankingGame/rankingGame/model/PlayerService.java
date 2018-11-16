package com.rankingGame.rankingGame.model;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	
	public boolean canIncrementVictory(Player p) {
		if(p!=null && p.getVictories() < p.getNumberOfGames()) {
			return true;
		}
		return false;
	}
	
	public int getVictories(Player p) {
		return p.getVictories();
	}
}
