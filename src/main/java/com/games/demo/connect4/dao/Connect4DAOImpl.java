package com.games.demo.connect4.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.games.demo.connect4.model.Connect4Model;

/**
 * Persistence class for Connect4
 * @author kvenkate
 *
 */
public class Connect4DAOImpl implements IConnect4DAO{
	
	private static final AtomicInteger nextGameId = new AtomicInteger(1);
	
	// Games are stored as static object.
	private static Map<String, Connect4Model> gridMap = new HashMap<String, Connect4Model>();
	
	public void addNewGame(Connect4Model connect4Model) {
		
		String gameId = String.valueOf(nextGameId.getAndIncrement());
		connect4Model.setGameId(gameId);
		gridMap.put(gameId, connect4Model);
		
	}
	
	public Connect4Model getGameTable(String gameId) {
		return gridMap.get(gameId);
	}

}
