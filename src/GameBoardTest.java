/**
 * GameBoardTest 
 * @version 1.00 28-09-22
 * @author Yuxuan Xie
 */

/**
 * A {@code GameBoardTest} 
 */
public class GameBoardTest {
   public static void main(String[] args) {
      GameBoard newGame = new GameBoard();
	  
	  newGame.initialiseGame();
	  GameBoardView.displayBoard(newGame);
   }
}
