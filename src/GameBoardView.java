/**
 * GameBoardView Displays the current state of the game board
 * @version 1.00 30-09-22
 * @author Yuxuan Xie
 */
 
/**
 * A {@code GameBoardView} class for displaying the current game board
 */ 
public class GameBoardView {
	public static void displayBoard (GameBoard board) {

		String boardStr=""; // Initialise empty string
		String[][] pileStrArray = new String[13][]; // Total of 13 piles
		
		// Split piles into string arrays
		for(int i =0; i<13;i++){
			pileStrArray[i]=board.getPile(i).split("\n");
		}
		
		
		int[] laneLengths = new int[7]; // Integer array storing length of each lane
		int maxLaneLength =0; 
		
		// Find the longest lane
		for(int i =0; i<7; i++){
			laneLengths[i]=pileStrArray[i+6].length;
			if(laneLengths[i]>maxLaneLength){
				maxLaneLength = laneLengths[i];
			}
		}

		board.clearRows(); // Initialise row arraylist object
		
		// The stock, waste and foundation piles 
		for(int i=0; i<3; i++){ // 3 lines/rows
			board.addRow("");
			
			// Stock and Waste piles
			for(int j=0; j<2; j++){
				
				// Formatted string with 25 character spaces, left justify the text
				board.setRow(i, board.getRow(i)+String.format("%-25s", pileStrArray[j][i]));
			}
			
			// Insert empty space so that Stock + waste + Foundations (6 piles) aligns with the 7 lanes below
			board.setRow(i, board.getRow(i)+String.format("%-25s", ""));
			
			// Foundation piles
			for(int j=2; j<6; j++){
				board.setRow(i, board.getRow(i)+String.format("%-25s", pileStrArray[j][i]));
			}
			// End of line/row
			board.setRow(i,board.getRow(i)+"\n");
		}			
			
		board.setRow(2, board.getRow(2)+ "\n"); // Add newline or row
		
		// The seven lanes
		for(int i=0; i<maxLaneLength; i++){
			board.addRow("");
			for(int j=6; j<13; j++){
				if(pileStrArray[j].length>i){
					board.setRow(i+3, board.getRow(i+3)+String.format("%-25s", pileStrArray[j][i]));
				}else{
					// Pad shorter lanes with space
					board.setRow(i+3, board.getRow(i+3)+String.format("%-25s", ""));
				}
			}
			// End of line/row
			board.setRow(i+3,board.getRow(i+3)+"\n");
		}
		
		board.setRow(maxLaneLength+2, board.getRow(maxLaneLength+2)+ "\n"); // Add newline
		board.addRow(String.format("%-15s","Score:")+board.getScore()+"\n"); 		// Display the current score
		board.addRow(String.format("%-15s","Moves Count:")+board.getMovesCount()+"\n"); // Display the number of moves made so far


		// Concatenate all rows together into one string
		for(int i =0; i<board.getRowSize(); i++){
			boardStr+=board.getRow(i);
		}
			
		System.out.println(boardStr);
	}
}