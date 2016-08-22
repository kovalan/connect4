package com.games.demo.connect4.model;

import java.io.Serializable;

public class NewGameModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491996393397383956L;
	
	public NewGameModel(int p1Id, int p2Id, int rowSize, int colSize) {
		this.player1Id = p1Id;
		this.player2Id = p2Id;
		this.colSize = colSize;
		this.rowSize = rowSize;
	}
	
	public NewGameModel() {
		
	}
	
	private int player1Id;
	private int player2Id;
	private int colSize;
	private int rowSize;

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
	public int getColSize() {
		return colSize;
	}
	public void setColSize(int colSize) {
		this.colSize = colSize;
	}
	public int getRowSize() {
		return rowSize;
	}
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	
}
