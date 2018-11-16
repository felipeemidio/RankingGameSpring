package com.rankingGame.rankingGame.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlayerServiceTest {
	@Autowired
	PlayerService service = new PlayerService();
	
	// Try to use canIncrementVictory() in a valid situation. 
	@Test
	public void testIncrementVictoryForTrue() {
		
		Player p = mock(Player.class);
		when(p.getVictories()).thenReturn(1);
		when(p.getNumberOfGames()).thenReturn(2);
		
		assertTrue(service.canIncrementVictory(p));
	}
	
	// Try to use canIncrementVictory() in a invalid situation. 
	@Test
	public void testIncrementVictoryForFalse() {
		
		Player p = mock(Player.class);
		when(p.getVictories()).thenReturn(2);
		when(p.getNumberOfGames()).thenReturn(2);
		
		assertFalse(service.canIncrementVictory(p));
	}
	
	// Try to use canIncrementVictory() for a null player. 
	@Test
	public void testIncrementVictoryForNull() {
		Player p = null;
		assertFalse(service.canIncrementVictory(p));
	}
}
