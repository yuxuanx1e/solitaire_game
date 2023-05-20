/**
 * GameBoard models the layout of a solitaire game
 * @version 1.00 29-09-22
 * @author Yuxuan Xie
 */
import java.util.ArrayList; 


/**
 * A {@code GameBoard} object representing a game board with 
 * piles of cards placed on top 
 */
public class GameBoard {
	

	private ArrayList<String> rows; 		// Rows in the gameboard
	private ArrayList<String> pileOptions;	// List of valid pile label options 
	private ArrayList<Pile> piles; 			// Arraylist of piles of cards
	
	private String fromPileLabel; 			// Label of pile to transfer cards from
	private String toPileLabel;				// Label of pile to transfer cards to
	
	private Pile fromPile; 					// Reference to one of piles in the list to transfer cards from
	private Pile toPile;					// Reference to one of piles in the list to transfer cards to
	private int numCardsToMove; 			// Number of cards to move
	
	private int score;						// Game score
	private int moves;						// Number of moves made by the player

	
	

/**
 * Constructor for the class. No input parameters
 */
	GameBoard () {
		// Declare new arraylist objects
		this.rows = new ArrayList<String>();
		this.pileOptions = new ArrayList<String>(); 
		this.piles = new ArrayList<Pile>();
		
		// Create piles for the game
		piles.add(new Pile("Stock","P", true)); // piles[0] = Stock Pile
		piles.add(new Pile("Waste","W", true)); // piles[1] = Waste Pile
		piles.add(new Pile("Clubs Foundation", "C", true)); // piles[2] = Clubs Foundation Pile
		piles.add(new Pile("Diamonds Foundation", "D", true)); // piles[3] = Diamonds Foundation Pile
		piles.add(new Pile("Hearts Foundation", "H", true)); // piles[4] = Hearts Foundation Pile
		piles.add(new Pile("Spades Foundation", "S", true)); // piles[5] = Spades Foundation Pile
		piles.add(new Pile("Lane 1", "1", false)); // piles[6-12] = Lanes 1-7
		piles.add(new Pile("Lane 2", "2", false));
		piles.add(new Pile("Lane 3", "3", false));
		piles.add(new Pile("Lane 4", "4", false));
		piles.add(new Pile("Lane 5", "5", false));
		piles.add(new Pile("Lane 6", "6", false));
		piles.add(new Pile("Lane 7", "7", false));


		// Create array of pile label strings
		for(int i=0; i<13; i++){
			this.pileOptions.add(piles.get(i).getLabel());
		}
		
		// Initialise int variables to zero
		this.numCardsToMove = 0; 
		this.score = 0;
		this.moves = 0; 

    }
	
/**
 * Return the pile as a formatted string
 */		
	public String getPile(int index){
		return PileView.formatPile(piles.get(index));
	}
	
/**
 * Return one row in the arraylist of rows
 */		
	public String getRow(int index){
		return rows.get(index);
	}
	
/**
 * Add a new row in the arraylist of rows, initialise with string
 */		
	public void addRow(String newStr){
		rows.add(newStr);
	}
	
/**
 * Set one row in the arraylist of rows
 */		
	public void setRow(int index, String newStr){
		rows.set(index, newStr);
	}
	
/**
 * Return the size of the rows arraylist object
 */		
	public int getRowSize(){
		return rows.size();
	}
	
/**
 * Clear the rows arraylist object
 */		
	public void clearRows(){
		rows.clear();
	}
	
/**
 * Return number of moves made so far
 */	
	public int getMovesCount(){
		return this.moves;
	}
	
/**
 * Return current score
 */	
	public int getScore(){
		return this.score;
	}
	
/**
 * Update/set the current score of the game. Scoring system as 
 * per assignment requirement. 
 */	
	public void updateScore(int change){
		this.score+=change;
	}

/**
 * Increment the number of moves made in the game
 */	
	public void incrementMovesCount(){
		this.moves++;
	}	
	

/**
 * Check if pile label corresponds to one of the foundation labels
 */
	public boolean isFoundation(String pileLabel){
		return this.pileOptions.subList(2,6).contains(pileLabel);
	}
	
/**
 * Check if pile label corresponds to one of the lane labels
 */
	public boolean isLane(String pileLabel){
		return this.pileOptions.subList(6,13).contains(pileLabel);
	}	

	
/**
 * Initialise the game board
 */
	public void initialiseGame() {
		
		// Create all 52 cards in a deck of cards, face down
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				piles.get(0).addCard(new Card(suit, rank, false));
			}
		}
		
		// Shuffle the cards 
		piles.get(0).shuffleCards();
		
		// Distribute cards into the seven lanes
		for(int i=0; i<7; i++){
			
			piles.get(i+6).transferCard(piles.get(0), i+1); 
		}

		// Flip the cards on the top of each lane
		for(int i=0; i<7; i++){
			piles.get(i+6).flipTopCardInPile(); 
		}
   }


   
   
/**
 * Check if user input is valid
 */   
	public boolean isValidInput(String userInput){
					
			
		// Basic User Input validation	
		
		// Check if shortcut command is used
		if(userInput.length()==1){
			
			// Uncover a new card from the stock pile shortcut
			if(userInput.equals("D")){
				this.fromPileLabel = "P"; // From stock
				this.toPileLabel = "W";	  // To waste
				this.numCardsToMove =1;	  // Move 1 card
			
				this.fromPile=piles.get(pileOptions.indexOf(fromPileLabel));
				this.toPile=piles.get(pileOptions.indexOf(toPileLabel));
			
				return !fromPile.isEmpty(); // Stock is not empty for new card to be drawn
			}
			
			// Re-stock the stock pile with all cards from the waste pile
			if(userInput.equals("R")){
				this.fromPileLabel = "W"; // From waste
				this.toPileLabel = "P";	  // To stock
			
				this.fromPile=piles.get(pileOptions.indexOf(fromPileLabel));
				this.toPile=piles.get(pileOptions.indexOf(toPileLabel));
				
				this.numCardsToMove =fromPile.getTotalCards(); // Move all card
			
				return toPile.isEmpty(); // Stock must be empty before it can be re-stocked
			}
				
			
		}
		
		// If not a shortcut, then input must be at least 2 characters long
		if(userInput.length() >=2){ 
			
			// Assign pile labels 
			this.fromPileLabel = userInput.substring(0,1);
			this.toPileLabel =  userInput.substring(1,2); 
			
			// Check if pile labels are correct
			if(!fromPileLabel.equals(toPileLabel) && pileOptions.contains(fromPileLabel) && pileOptions.contains(toPileLabel)){ 
				
				// Assign piles
				this.fromPile=piles.get(pileOptions.indexOf(fromPileLabel));
				this.toPile=piles.get(pileOptions.indexOf(toPileLabel));
				
				if(userInput.length()>2){ // Move Multiple cards
				
					try{ // Check if third argument is a number
						numCardsToMove=Integer.parseInt(userInput.substring(2)); // Number of cards to be moved
					}catch (NumberFormatException e){
						return false;
					}
				}else{ // Move only one card
					numCardsToMove = 1;		
				}
				// Check if numCardsToMove to not zero and
				// Check if pile has adequate cards to be moved from
				return numCardsToMove>0 && numCardsToMove<=fromPile.getTotalCards();
			}
		}
		
		return false; // Invalid input
   }
   
   
/**
 * Check if user input command is legal according to the rules of solitaire
 * There are 4 types of piles: Stock(P), Waste(W), Foundation(F) and Lane(1-7). 
 * Therefore there are 2^4 = 16 combinations of <label1><label2> inputs
 * Three has been eliminated by isValidMove(), namely PP, WW and FF
 * This leaves with 13 remaining combinations, 7 of which are legal. 
 */ 
	public boolean isLegalMove(){
		
		// Move 1 card from stock to waste pile only
		if(toPileLabel.equals("W")){ 
			return fromPileLabel.equals("P") && numCardsToMove==1; 
		}
		
		if(fromPileLabel.equals("P")){
			return toPileLabel.equals("W") && numCardsToMove ==1; 
		}
		
		// Move all cards from waste to stock pile only when stock is empty
		if(toPileLabel.equals("P")){
			return fromPileLabel.equals("W") && toPile.isEmpty() && numCardsToMove == fromPile.getTotalCards();
		}
				
		
		// A move to one of foundation piles 
		if(isFoundation(toPileLabel)){
			
			// Only move one card to foundation at a time
			return numCardsToMove==1 && toPile.canPlaceOnFoundation(fromPile);
		}
		
		// A move to one of "Lane" piles
		if(isLane(fromPileLabel)){ // From one of the other lane piles...
			
			return fromPile.isCardFaceUp(fromPile.getTotalCards()-numCardsToMove) && // Check if the card to be moved is face up
				   toPile.canPlaceOnLane(fromPile, numCardsToMove); // Can be placed in this lane
				   
		}else{ 
			// From the waste pile or one of the four foundation piles. 1 card only
			return numCardsToMove==1 && toPile.canPlaceOnLane(fromPile, numCardsToMove);
		}
		
	}



/**
 * Make a move on the game board and update the score as per assignment requirement
 */    
	public void makeMove(){

		
		// Stock (P) to Waste (W) pile
		if(toPileLabel.equals("W")){
			toPile.transferCard(fromPile,numCardsToMove); // Move Card
			toPile.flipTopCardInPile(); // Flip card face up
			return;
		}
		
		// Waste to stock pile
		if(toPileLabel.equals("P")){
			for(int i=0; i<numCardsToMove; i++){
				toPile.transferCard(fromPile,1); // Move all cards from waste to  stock one at a time
				toPile.flipTopCardInPile();  // Flip each card face down
			}
			return;
		}
		
		// A move to one of foundation piles 
		if(isFoundation(toPileLabel)){
			toPile.transferCard(fromPile,numCardsToMove); 
			
			// If transferring from a lane pile, flip the top card face up if pile not empty
			if(isLane(fromPileLabel)){
				this.updateScore(20); // Add 20Pt for card transfered from lane to foundation
				
				// Flip card if top card is facing down in the lane
				if(!fromPile.isEmpty()&& !fromPile.isCardFaceUp(fromPile.getTotalCards()-1)){
					fromPile.flipTopCardInPile();
				}
			}else{ // Transfer card from waste to foundation
				this.updateScore(10); // Add 10Pt
			}
			return;
		}
		
		// A move to one of "Lane" piles
		toPile.transferCard(fromPile,numCardsToMove);
		
		// From another "Lane" pile
		if(isLane(fromPileLabel)){
			this.updateScore(numCardsToMove*5); // Add 5Pt per card transferred between lanes
			if(!fromPile.isEmpty() && !fromPile.isCardFaceUp(fromPile.getTotalCards()-1)){
				fromPile.flipTopCardInPile();
			}
			return; 
		}
	}
   
/**
 * Return boolean to indicate if game is over
 */    
	public boolean isGameOver(){
		boolean gameOver=true;

		for(int i=2; i<6; i++){
			if(!piles.get(i).isFoundationComplete()){
				gameOver=false;
				break; 
			}
		}
		return gameOver;
	}
	
}