Feature: Player Managing
  
	Scenario: client makes call to GET /players
    	When the client calls get /players
    	Then the client receives status code of 200
    	And the client receives an array
    
#    Scenario: client makes call to POST /players
#    	When the client calls post /players
#    	Then the client receives status code of 200
#    	And the client receives player with a cod
	
#	Scenario: client makes call to DELETE /players	
#		When the client calls delete /players/43
#   	Then the client receives status code of 204
#    	And the player 43 do not exist
    	
    Scenario: client wants to find a player
    	When the client calls get /players/9
    	Then the client receives status code of 200
    	
    Scenario: client wants to add victory to a player
    	Given get the victories of player 26 
    	When the client calls put /players/addVictory/26
    	Then the client receives status code of 200 
    	And the player 26 has incremented the victories
    	
    Scenario: client wants to add numberOfGames to a player
    	Given get the numberOfGames of player 26 
    	When the client calls put /players/addGame/26
    	Then the client receives status code of 200 
    	And the player 26 has incremented the numberOfGames