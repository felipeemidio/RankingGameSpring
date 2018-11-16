Feature: Player Managing
  
  Scenario: client makes call to POST /players
    When the client calls /players
    Then the client receives status code of 200
    And the client receives a player with a cod variable