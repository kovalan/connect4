package com.games.demo.connect4.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.games.demo.connect4.util.Connect4Util;

public class Connect4UtilTest {

	@Test
	public void testVerticalConnectWin() {
		
		int[][] grid = {
				
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0},
				{0,1,0,0,0,0,0},
				{0,1,2,0,0,0,0},
				{2,1,2,0,0,0,0}				
		};
		
		int lastRowIndex = 3;
		int lastColIndex = 1;
		
		assertTrue(Connect4Util.isWinMove(grid, lastRowIndex, lastColIndex));
	}
	
	@Test
	public void testHorizontalConnectWin() {
		
		int[][] grid = {
				
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{2,2,2,0,0,0,0},
				{2,1,1,1,1,0,0}				
		};
		
		int lastRowIndex = 5;
		int lastColIndex = 3;
		
		assertTrue(Connect4Util.isWinMove(grid, lastRowIndex, lastColIndex));
	}
	
	@Test
	public void testLeftToRightDiagonalConnectWin() {
		
		int[][] grid = {
				
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,1,0,0,0,0},
				{0,0,2,1,0,0,0},
				{0,0,2,2,1,0,0},
				{0,0,1,2,2,1,1}				
		};
		
		int lastRowIndex = 3;
		int lastColIndex = 4;
		
		assertTrue(Connect4Util.isWinMove(grid, lastRowIndex, lastColIndex));
	}
	
	@Test
	public void testRightToLeftDiagonalConnectWin() {
		
		int[][] grid = {
				
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,2},
				{0,0,0,0,0,2,1},
				{0,0,0,0,2,1,2},
				{0,0,1,2,1,1,1}				
		};
		
		int lastRowIndex = 5;
		int lastColIndex = 3;
		
		assertTrue(Connect4Util.isWinMove(grid, lastRowIndex, lastColIndex));
	}
	
	@Test
	public void testNotWin() {
		
		int[][] grid = {
				
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,2},
				{0,0,0,0,0,1,2},
				{0,0,0,0,2,1,2},
				{0,0,1,2,1,1,1}				
		};
		
		int lastRowIndex = 5;
		int lastColIndex = 3;
		
		assertTrue(!Connect4Util.isWinMove(grid, lastRowIndex, lastColIndex));
	}
}
