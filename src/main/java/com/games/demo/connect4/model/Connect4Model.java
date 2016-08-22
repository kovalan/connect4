package com.games.demo.connect4.model;

import java.io.Serializable;

public class Connect4Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1733188713978189232L;
	
	private String gameId;
	private boolean gameOver;
	private int winnerId;
	private int player1Id;
	private int player2Id;
	private int[][] gameGrid;
	private String gameLink;
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public int getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}
	public int getPlayer1Id() {
		return player1Id;
	}
	public void setPlayer1Id(int player1Id) {
		this.player1Id = player1Id;
	}
	public int getPlayer2Id() {
		return player2Id;
	}
	public void setPlayer2Id(int player2Id) {
		this.player2Id = player2Id;
	}
	public int[][] getGameGrid() {
		return gameGrid;
	}
	public void setGameGrid(int[][] gameGrid) {
		this.gameGrid = gameGrid;
	}
	public String getGameLink() {
		return gameLink;
	}
	public void setGameLink(String gameLink) {
		this.gameLink = gameLink;
	}
	
	public int getNextPlayerId(int currPlayerId) {
		if (player1Id == currPlayerId) {
			return player2Id;
		} else {
			return player1Id;
		}
	}

}
