package com.games.demo.connect4.dao;

import com.games.demo.connect4.model.Connect4Model;

public interface IConnect4DAO {

	public void addNewGame(Connect4Model connect4Model);
	
	public Connect4Model getGameTable(String gameId);
}
