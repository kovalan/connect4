package com.games.demo.connect4.util;

public class Connect4Util {

	/**
	 * Check if the player has won after the current placing. Returns true if win else return false;
	 * @param gridTable is the game matrix(can be any number of rows and columns between 4 and 40)
	 * @param lastPlacedRowIndex is the column index where the last token was placed
	 * @param lastPlacedColIndex is the row index where the last token was placed
	 * @return
	 */
	public static boolean isWinMove(int[][] gridTable, int lastPlacedRowIndex, int lastPlacedColIndex) {
		
	int maxRowIndex = gridTable.length-1;
	int maxColIndex = gridTable[0].length-1;

	int playerId = gridTable[lastPlacedRowIndex][lastPlacedColIndex]; //player ID
	
	return isHorizontalConnect(gridTable, lastPlacedRowIndex, lastPlacedColIndex, maxRowIndex, maxColIndex, playerId)
			|| isVerticalConnect(gridTable, lastPlacedRowIndex, lastPlacedColIndex, maxRowIndex, maxColIndex, playerId)
			|| isLeftToRightDiagonalConnect(gridTable, lastPlacedRowIndex, lastPlacedColIndex, maxRowIndex, maxColIndex, playerId)
			|| isRightToLeftDiagonalConnect(gridTable, lastPlacedRowIndex, lastPlacedColIndex, maxRowIndex, maxColIndex, playerId);
			
	
	}
	
	// Horizontal check
	private static boolean isHorizontalConnect(int[][] gridTable,
			int lastPlacedRowIndex, 
			int lastPlacedColIndex,
			int maxRowIndex,
			int maxColIndex, 
			int playerId) {
		
		int count = 0;
		// Horizontal check
		int startIndex = Math.max(0, lastPlacedColIndex-3);
		int endIndex = Math.min(lastPlacedColIndex+3, maxColIndex);
		for (int i=startIndex;i<=endIndex;i++){
			
			if (gridTable[lastPlacedRowIndex][i]==playerId){
		        count++;
		        if (count>=4) {
			        return true;
			    }
			}
		    else {
		        count=0;
		    }
		}
		
		return false;
	}
	
	//Vertical check
	private static boolean isVerticalConnect(int[][] gridTable,
			int lastPlacedRowIndex, 
			int lastPlacedColIndex,
			int maxRowIndex,
			int maxColIndex, 
			int playerId) {
		
		int count = 0;
		int startIndex = Math.max(0, lastPlacedRowIndex-3);
		int endIndex = Math.min(lastPlacedRowIndex+3, maxRowIndex);
		for (int i=startIndex;i<=endIndex;i++){
			
		    if (gridTable[i][lastPlacedColIndex]==playerId) {
		        count++;
		        if (count>=4) {
			        return true;
			    }
		    }
		    else {
		        count=0;
		    }
		} 
		
		return false;
	}
	
	// diagonal check (top-left to bottom-right)
	private static boolean isLeftToRightDiagonalConnect(int[][] gridTable,
			int lastPlacedRowIndex, 
			int lastPlacedColIndex,
			int maxRowIndex,
			int maxColIndex, 
			int playerId) {
		
		int count = 0;
		int tempRowStartIndex = Math.max(0, lastPlacedRowIndex-3);
		int tempRowEndIndex = Math.min(lastPlacedRowIndex+3, maxRowIndex);
		int tempColStartIndex = Math.max(0, lastPlacedColIndex-3);
		int tempColEndIndex = Math.min(lastPlacedColIndex+3, maxColIndex);
		
		int startIndexLimit = Math.min(lastPlacedRowIndex-tempRowStartIndex, lastPlacedColIndex-tempColStartIndex);
		int endIndexLimit = Math.min(tempRowEndIndex-lastPlacedRowIndex, tempColEndIndex-lastPlacedColIndex);
		
		int rowStartIndex = lastPlacedRowIndex - startIndexLimit;
		int colStartIndex = lastPlacedColIndex - startIndexLimit;
		int rowEndIndex = lastPlacedRowIndex + endIndexLimit;
		int colEndIndex = lastPlacedColIndex + endIndexLimit;
		
		if((rowEndIndex-rowStartIndex) >=3 && (colEndIndex-colStartIndex) >=3) {
			
			for (int row = rowStartIndex, col = colStartIndex; row <= rowEndIndex && col <= colEndIndex; row++, col++) {
				if(gridTable[row][col] == playerId) {
					count++;
					if(count >= 4) {
						return true;
					}
				}else {
		            count = 0;
		        }
			}
			
		}
		
		return false;
	}
	
	// diagonal check (top-right to bottom-left)
	private static boolean isRightToLeftDiagonalConnect(int[][] gridTable,
			int lastPlacedRowIndex, 
			int lastPlacedColIndex,
			int maxRowIndex,
			int maxColIndex, 
			int playerId) {
		
		int count = 0;
		int tempRowStartIndex = Math.max(0, lastPlacedRowIndex-3);
		int tempRowEndIndex = Math.min(lastPlacedRowIndex+3, maxRowIndex);
	    int	tempColStartIndex = Math.min(lastPlacedColIndex+3, maxColIndex);
		int tempColEndIndex = Math.max(0, lastPlacedColIndex-3);
		
		int startIndexLimit = Math.min(lastPlacedRowIndex-tempRowStartIndex, tempColStartIndex-lastPlacedColIndex);
		int endIndexLimit = Math.min(tempRowEndIndex-lastPlacedRowIndex, lastPlacedColIndex-tempColEndIndex);
		
		int rowStartIndex = lastPlacedRowIndex - startIndexLimit;
		int colStartIndex = lastPlacedColIndex + startIndexLimit;
		int rowEndIndex = lastPlacedRowIndex + endIndexLimit;
		int colEndIndex = lastPlacedColIndex - endIndexLimit;
		
		if((rowEndIndex-rowStartIndex) >=3 && (colStartIndex-colEndIndex) >=3) {
			
			for (int row = rowStartIndex, col = colStartIndex; row <= rowEndIndex && col >= colEndIndex; row++, col--) {
				if(gridTable[row][col] == playerId) {
					count++;
					if(count >= 4) {
						return true;
					 }
				}else {
		            count = 0;
		        }
			}
			
		}
		
		return false;
	}
}
