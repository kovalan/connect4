# connect4
Connect4 game web service using dropwizard

#1. To start a new game - 

REQUEST
 REQUEST URI : http://<host>/api/games/new
 HTTP Method  : POST
 Content-Type : application/json
 Data payload : {
   "player1Id" : 1, 
	"player2Id" : 2, 
	"colSize" : 7,  //column size of the grid
	"rowSize" :6   //row size of the grid
 }

RESPONSE
  Content-Type : application/json
  Data : 
  {
  "gameId": gameId
  "gameOver": false
  "winnerId": 
  "player1Id": player1Id
  "player2Id": player2Id
  "gameGrid": 2Dgrid array
  "gameLink": "http://<host>/api/games/<gameId>/player/<playerId>"
  }

#2. To continue play the game - 

REQUEST
  REQUEST URI : http://<host>/api/games/<gameId>/player/<playerId>?insert_in_column=3
  HTTP Method  : PUT
  Content-Type : application/json
   
  RESPONSE
    Content-Type : application/json
    Data : 
    if the game is not over
    {
    "gameId": gameId
    "gameOver": false
    "winnerId": 
    "player1Id": player1Id
    "player2Id": player2Id
    "gameGrid": 2Dgrid array
    "gameLink": "http://<host>/api/games/<gameId>/player/<nextplayerId>"
    }
    if the game is over
    {
    "gameId": gameId
    "gameOver": false
    "winnerId": winnerId
    "player1Id": player1Id
    "player2Id": player2Id
    "gameGrid": 2Dgrid array
    "gameLink":  "http://<host>/api/games/<gameId>"
    }
    
 
#3. To view game status - 

REQUEST
  REQUEST URI : http://<host>/api/games/<gameId>
  HTTP Method  : PUT
  Content-Type : application/json
   
  RESPONSE
    Content-Type : application/json
    Data : 
    if the game is not over
    {
    "gameId": gameId
    "gameOver": false
    "winnerId": 
    "player1Id": player1Id
    "player2Id": player2Id
    "gameGrid": 2Dgrid array
    "gameLink": "http://<host>/api/games/<gameId>/player/<nextplayerId>"
    }
    if the game is over
    {
    "gameId": gameId
    "gameOver": false
    "winnerId": winnerId
    "player1Id": player1Id
    "player2Id": player2Id
    "gameGrid": 2Dgrid array
    "gameLink":  "http://<host>/api/games/<gameId>"
    }


