/* 

* Grey Kumar

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
package gkumar_p1;
import java.util.Scanner;
/**
 * This program simulates a tic tac toe game. It first displays the board and 
 * player X gets to choose which space they would like to mark by entering the 
 * coordinates. After checking to see if the space is open, the program updates
 * the board and switches players. This keeps going until one player has won, or
 * if there is a tie (board is full).
 * 
 *@author Grey Kumar
 *@version 1.0
 */
public class P1 {

	/**
	 * Initializes constants, creates the objects, switches player turns, 
	 * checks for winners and tie games
	 * 
	 * @param args A string array containing the command line arguments.
	 */
	public static void main(String[] args) {
		//constants for board size
		
		//variables for repeat
		String repeat;
		final String YES = "y";
		//counter for tie games
		int count = 0;
		int playerXCount = 0;
		int playerOCount = 0;
		int remainder;
		int number;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to TicTacToe!");
		System.out.println();
		do {
		System.out.print("Enter in an odd number greater than or equal to 3, "
				+ "no greater than 25.\n"
				+ "This will be the size of the board: ");
		number = keyboard.nextInt();
		
		remainder = number % 2;
		//check bounds
		if (remainder == 0) {
			System.out.println("Number must be odd! Try again..");
		} else if (number < 3) {
			System.out.println("Number must be greater than or equal to 3");
		} else if (number > 25) {
			System.out.println("Must be smaller than 25");
		}
		
		}while (remainder == 0 || number < 3 || number > 25);
		
		final int ROW = number;
		final int COL = number;
		//create game board object
		TicTacToe board = new TicTacToe(ROW, COL);
		
		do {
			//Set player
			board.changePlayer();
			//reset board
			board.resetBoard();
			//display board
			board.printBoard();
			boolean openPos = true;
			boolean end = false;
			do {
				//switch player again
				board.changePlayer();
				do {
					//check to see if position is open
					openPos = playerTurn(board, keyboard);
					System.out.println();
				}while (!openPos);
				//display updated board, check for winner, check for tie
				board.printBoard();
				board.checkWinner();
				board.isTie();
				//if winner
				if (board.checkWinner()) {
					System.out.println(board.getPlayer() + " has won this game!");
					//increment win counter
					if (board.getPlayer() == 'X') {
						playerXCount++;
					} else if (board.getPlayer() == 'O') {
						playerOCount++;
					}
					//print game stats
					board.gameStats(count, playerXCount, playerOCount);
					end = true;
				//if tie
				}else if(board.isTie()) {
					System.out.println("No winner - it was a tie!");
					count++;
					board.gameStats(count, playerXCount, playerOCount);
					end = true;
				}
			}while(!end);
			//prompt user to repeat
			System.out.print("Do you want to play again? (y/n): ");
			repeat = keyboard.nextLine();
		}while (repeat.equalsIgnoreCase(YES));
		System.out.println();
		//goodbye
		System.out.println("Thanks for playing!");
		keyboard.close();
	}
	
	/**
	 * Prompts each player to take their turn, collects coordinates, checks if 
	 * the space is open on the board, then updates the board
	 * 
	 * @param board the game board object
	 * @param keyboard the scanner object
	 * @return a true value if the space on the board is open and has been 
	 * updated
	 */
	public static boolean playerTurn(TicTacToe board, Scanner keyboard) {
		//variables to collect coordinates
		int inputRow;
		int inputCol;
		
		//prompt user for input
		System.out.println(board.getPlayer() + ", it is your turn.");
		System.out.print("Which row? ");
		inputRow = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("Which column? ");
		inputCol = keyboard.nextInt();
		keyboard.nextLine();
		boolean openPos = true; 
		//check to see if theres an open space
		openPos = board.checkBoard(inputRow, inputCol);
		if (openPos) {
			//if so update board with player mark on coordinates entered 
			board.updateBoard(inputRow, inputCol);
		} else {
			//otherwise return false and repeat
			board.printBoard();
			openPos = false;
		}	
		return openPos;
	}

}
