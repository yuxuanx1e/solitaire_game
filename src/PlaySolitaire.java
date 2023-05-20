/**
 * Main Java program for playing a game of solitaire on the console
 * @version 1.00 30-09-22
 * @author Yuxuan Xie
 */

import java.util.Scanner;

public class PlaySolitaire {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		// An instance of the GameBoard class represents the game state/board,
		GameBoard game = new GameBoard();
		
		// Initialise the game board/ set up the game
		game.initialiseGame();
		
		// Display welcome message and rules of the game
		printWelcomeMsg();
		
		// Display the game board
		GameBoardView.displayBoard(game);
		
		// Check if game over condition is satisfied
		while(!game.isGameOver()){
			

			while(true){
				// Ask user for input
				System.out.print("Please Enter a move... ");
				String userInput = keyboard.next().toUpperCase(); 
				
				
				if(userInput.length()==1&& userInput.equals("Q")){
					System.out.println("Quit the game, exiting ....");
					return; // Exit the program
				}
				
				if(game.isValidInput(userInput)){ // User has entered a command in correct format
					if(game.isLegalMove()){ // Check if the specified command is legal according to the rules of solitaire
						break;
					}else{
						System.out.println("Illegal command, play cannot be made. Try again!");
					}
				}else{
					System.out.println("Invalid command, try again!");
				}
			}
			
			// Player has entered a valid command
			System.out.println("Valid repsonse!\n");
			
			// Make the requested move and increment counter
			game.makeMove();
			game.incrementMovesCount(); 
			
			// Display the game board
			GameBoardView.displayBoard(game);
		}
		
		// Game over message
		System.out.println("Congratulations! You Won the Game!");

	}
	
	// Prints welcome message
	public static void printWelcomeMsg(){
		String msg = "Welcome to Solitaire!\n\n" + 
					 "There are 4 types of card piles (pile labels shown in brackets): Stock/draw (P), Waste(W), Foundation(C,D,H,S) and Lanes (1-7).\n" + 
					 "The game is won when all cards are sorted in ascending order by rank in each of their respective suit/foundation deck.\n\n" +
					 "Use the following commands to play:\n\n" +
					 "\t<label1><label2> : Move one card from <label1> to <label2>. E.g. From Waste to lane 3 is W3\n" +
					 "\t<label1><label2><number> : Move <number> card(s) from <label1> to <label2>. E.g. Move 3 cards from lane 1 to 7 is 173\n" +
					 "\tNote: To re-populate the stock pile when it's empty, transfer all cards from waste back to stock using WP<number>\n" +
					 "\tD : To uncover a new card from the stock/upside down pile, equivalent to PW or PW1\n" +
					 "\tR : To re-stock the stock/draw pile, equivalent to WP<TotalNumberOfCardsInWaste> \n" +
					 "\tQ : Quit\n\n" +
					 "The game has been set up for you below, best of luck!\n"; 
		  
		System.out.println(msg);
	}
	
}