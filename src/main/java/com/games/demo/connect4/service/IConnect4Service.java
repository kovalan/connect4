package com.games.demo.connect4.service;

import com.games.demo.connect4.exception.Connect4NotFoundException;
import com.games.demo.connect4.model.Connect4Model;
import com.games.demo.connect4.model.NewGameModel;

public interface IConnect4Service {

	public Connect4Model createNewGame(NewGameModel newGameModel);
	
	public Connect4Model viewGame(String gameId)  throws Connect4NotFoundException;
	
	public Connect4Model playGame(String gameId, int colNum, int playerId)  throws Connect4NotFoundException;
}
