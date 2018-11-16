Feature: Player Managing
  
	Scenario: client makes call to GET /players
    	When the client calls get /players
    	Then the client receives status code of 200
    	And the client receives an array
    
    Scenario: client makes call to POST /players
    	When the client calls post /players
    	Then the client receives status code of 200
    	And the client receives player with a cod
	
	Scenario: client makes call to DELETE /players	
		When the client calls delete /players/42
    	Then the client receives status code of 204
    	And the player 42 do not exist