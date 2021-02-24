/*

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
package gkumar_p1;

/**
 * This class creates the object gameBoard which runs the game
 *
 * @author  Grey Kumar
 * @version 1.0
 */
public class TicTacToe {
	
	//member variables
	private char [][] gameBoard; //character array to initialize board
	private char player = 'X'; //the players, starts with x
	private final int ROW; //size of the row 
	private final int COL; //size of the column
	private int playerXCount = 0; //counter variable
	private int playerOCount = 0; //counter variable
	
	/**
	 * The constructor initializes the game board
	 * 
	 * @param rows the size of the rows
	 * @param cols the size of the columns
	 */
	public TicTacToe(int rows, int cols) {
		ROW = rows;
		COL = cols;
		gameBoard = new char[ROW][COL];
		
	}
	
	/**
	 * This function returns the current player, either x or o
	 * 
	 * @return the current player
	 */
	public char getPlayer() {
		return player;
	}

	/**
	 * This function changes the player from X to O or vice versa 
	 * 
	 */
	public void changePlayer() {
		if (player == ('X')) {
			player = 'O';
		} else if (player == ('O')) {
			player = 'X';
		}
	}
	
	
	/**
	 * This function resets the board back to a blank board
	 * 
	 */
	public void resetBoard() {
		
		for(int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++){
				gameBoard [row][col] = ' ';
			}
		}
	}
	
	/**
	 * This function prints out the board, whether blank or updated
	 * 
	 */
	public void printBoard() {
		
		for (int i = 0; i < ROW; i++) {
			System.out.print("   " + i);
		}
		System.out.println();
		
		for (int row = 0; row < ROW; row++) {
			System.out.print(" " + row + ".");
			for (int col = 0; col < COL; col++) {
				if (col < 9) {
					System.out.print(gameBoard[row][col] + " | ");
				} else 
					System.out.print(gameBoard[row][col] + " |  ");
			}
			System.out.println();
			System.out.print(" ");
			for (int i = 0; i < ROW; i++) {
				if (i < 9) {
					System.out.print("----");
				} else 
					System.out.print("-----");
				
			}
			System.out.println();
		}
	}
	
	/**
	 * This function checks the board to make sure the space that is selected
	 * is available
	 * 
	 * @param inputRow the row coordinate the user selects
	 * @param inputCol the column coordinate the user selects
	 * @return true if the space is open, false if not
	 */
	public boolean checkBoard(int inputRow, int inputCol) {
		if (gameBoard[inputRow][inputCol] != ' ') {
			System.out.println("Bad location, try again...");
			System.out.println();
			return false;
		} 
		return true;
	}
	
	/**
	 * This function updates the board with the player mark at the positon they
	 * select
	 * 
	 * @param inputRow the row coordinate the user selects
	 * @param inputCol the column coordinate the user selects
	 */
	public void updateBoard(int inputRow, int inputCol) {
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if (gameBoard[inputRow][inputCol] == ' ');
					gameBoard[inputRow][inputCol] = player;
			}
		}
	}
	
	/**
	 * this function calls all the private functions to see if there is a winner
	 * if one function returns true, then someone has won
	 * 
	 * @return true if someone has won, false if not
	 */
	public boolean checkWinner() {
			if (checkRows() || checkDiags1() || checkDiags2() || checkCols()) {
				return true;
			}
		return false;
	}
	
	/**
	 * this private function checks each row to see if there is a winner
	 * 
	 * @return true if winner, false if not
	 */
	private boolean checkRows() {
		//check ascii value totals
		int xWinner = (88 * ROW);
		int oWinner	= (79 * ROW);
		int rowTotal;
		for (int row = 0; row < ROW; row++) {	
			rowTotal = 0;
			for (int col = 0; col < COL; col++) {
				//increment row total
				rowTotal += gameBoard[row][col];
			}
			if (rowTotal == xWinner || rowTotal == oWinner) {
				return true;
			}
		}
			
		return false;
	}
	
	/**
	 * this private function checks each column to see if there is a winner
	 * 
	 * 
	 * @return true if winner, false if not
	 */
	private boolean checkCols() {
		//create check for winner
		int xWinner = (88 * COL);
		int oWinner	= (79 * COL);
		int colTotal;
		for (int col = 0; col < COL; col++) {	
			colTotal = 0;
			for (int row = 0; row < ROW; row++) {
				//increment column total
				colTotal += gameBoard[row][col];				
			}

			if (colTotal == xWinner || colTotal == oWinner) {
				return true;
				
			}
		}
		
		return false;
	}
	
	/**
	 * this private function checks the first diagonal to see if there is a winner
	 * 
	 * @return true if winner, false if not
	 */
	private boolean checkDiags1() {
		int diagTotal1 = 0;
		//create check for winner
		int xWinner = (88 * COL);
		int oWinner	= (79 * COL);
		for (int row = 0; row < ROW; row++) {
			//increment diagonal total
			diagTotal1 += gameBoard[row][row];
		}
			
		if (diagTotal1 == xWinner || diagTotal1 == oWinner) {
			return true;
		}
		return false;
	}
	
	/**
	 * this private function checks the second diagonal to see if there is a winner
	 * 
	 * 
	 * @return true if winner, false if not
	 */
	private boolean checkDiags2() {
		int diagTotal2 = 0;
		//create check for winner
		int xWinner = (88 * COL);
		int oWinner	= (79 * COL);
		for (int row = 0; row < ROW; row++) {
			//increment diagonal total
			diagTotal2 += gameBoard[row][gameBoard.length - row - 1];
		}
			
		if (diagTotal2 == xWinner || diagTotal2 == oWinner) {
			return true;
		}
	
		return false;
	}
	
	/**
	 * this function prints out the stats of each round
	 * 
	 * @param tieCount the counter variable for each tie game
	 * @param playerXCount the counter variable for when x wins
	 * @param playerOCount the counter variable for when o wins
	 */
	public void gameStats(int tieCount, int playerXCount, int playerOCount) {
		System.out.println("Game Stats");
		System.out.println("X has won " + (playerXCount) + " games");
		System.out.println("O has won " + (playerOCount) + " games");
		System.out.println("There have been " + tieCount + " tie games");
	}
	
	/**
	 * this function checks to see if there is a tie by checking if the board 
	 * is full
	 * 
	 * @return true if the board is full, false if not
	 */
	public boolean isTie() {
		boolean checkTie = true;
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if (gameBoard[row][col] == ' ') {
					checkTie = false;
				}	
			}
		}
		return checkTie;
	}

}
