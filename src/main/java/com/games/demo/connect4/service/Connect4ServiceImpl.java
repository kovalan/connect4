package com.games.demo.connect4.service;

import com.games.demo.connect4.dao.Connect4DAOImpl;
import com.games.demo.connect4.dao.IConnect4DAO;
import com.games.demo.connect4.exception.Connect4NotFoundException;
import com.games.demo.connect4.model.Connect4Model;
import com.games.demo.connect4.model.NewGameModel;
import com.games.demo.connect4.util.Connect4Util;

/**
 * Service class for connect4 game
 * @author kvenkate
 *
 */
public class Connect4ServiceImpl implements IConnect4Service{
	
	private IConnect4DAO connect4dao = new Connect4DAOImpl();
	
	public Connect4Model createNewGame(NewGameModel newGameModel) {
		
		Connect4Model connect4Model = new Connect4Model();
		
		connect4Model.setPlayer1Id(newGameModel.getPlayer1Id());
		connect4Model.setPlayer2Id(newGameModel.getPlayer2Id());
		connect4Model.setGameGrid(new int[newGameModel.getRowSize()][newGameModel.getColSize()]);
		
		connect4dao.addNewGame(connect4Model);
		
		return connect4Model;
	}
	
	public Connect4Model viewGame(String gameId)  throws Connect4NotFoundException{
		Connect4Model connect4Model = connect4dao.getGameTable(gameId);
		checkIfNotFound(connect4Model);
		return connect4Model;
	}
	
	public Connect4Model playGame(String gameId, int colNum, int playerId)  throws Connect4NotFoundException{
		
		Connect4Model connect4Model = viewGame(gameId);

		if(connect4Model.isGameOver()) {
			return connect4Model;
		}
		
		int[][] gameGrid = connect4Model.getGameGrid();
		
		int maxRow = gameGrid.length;
		
		if (gameGrid.length < colNum) {
			throw new Connect4NotFoundException("Request to insert in invalid column");
		}
		
		int currColIndex = colNum-1; // -1 as array is zero indexed.
		int currRowIndex = 0;
		
		for(int i=maxRow-1; i>=0; i--) {
			if(gameGrid[i][currColIndex] == 0) { // not occupied
				gameGrid[i][currColIndex] = playerId;
				currRowIndex = i;
				break;
			}
		}
		
		// Check if win
		if(Connect4Util.isWinMove(gameGrid, currRowIndex, currColIndex)) {
			connect4Model.setWinnerId(playerId);
			connect4Model.setGameOver(true);
		} 
		
		return connect4Model;
		
	}
	
	private static void checkIfNotFound(Connect4Model connect4Model) throws Connect4NotFoundException{
		if (connect4Model == null) {
			throw new Connect4NotFoundException("Game not found");
		}
	}

}
