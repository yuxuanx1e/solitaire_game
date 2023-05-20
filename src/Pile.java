/**
 * Pile models the different piles of card used in a game of patience/ 
 * Solitaire. These are the stock pile, waste pile, foundation piles and
 * the 7 card lanes. 
 * @version 1.00 29-09-22
 * @author Yuxuan Xie
 */

import java.util.ArrayList; 
import java.util.Collections;

/**
 * A {@code Pile} object representing a Pile of Cards 
 */
public class Pile {
	
	private ArrayList<Card> cardsArray; // Arraylist storing cards in the pile
	private boolean stacked; 			// Are cards stacked on top of each other
	private int totalCards; 			// Total number of cards in the pile
	private String name;				// Name associated with the pile
	private String label; 				// Pile label


/**
 * Constructor for the class. 
 * @param name Pile name
 * @param label Pile label
 * @param stacked are cards stacked in this pile
 */
	Pile (String name, String label, boolean stacked) {
		this.cardsArray = new ArrayList<Card>();
		this.stacked = stacked;
		this.totalCards = cardsArray.size(); 
		this.name =name;
		this.label=label;
		
    }


/**
 * Return the cardsymbol of a card from the pile
 */
	public String showCard(int index) {
		return this.cardsArray.get(index).toString();
   }
   
/**
 * Returns boolean to indicate whether the pile is stacked or not
 */   
	public boolean isStacked() {
		return this.stacked; 
	}
	
/**
 * Returns the total number of cards in the pile
 */   
	public int getTotalCards() {
		return this.totalCards; 
	}

/**
 * Returns the name associated with this pile
 */   
	public String getName() {
		return this.name; 
	}	

/**
 * Returns the label associated with this pile of cards
 */  	
   public String getLabel () {
      return this.label;
   }
   
/**
 * Returns boolean to indicate whether a card at index in the pile is face up
 */  	
   public boolean isCardFaceUp (int index) {
      return this.cardsArray.get(index).isFaceUp();
   }


/**
 * Add a card to the pile
 */
	public void addCard(Card card) {
		this.cardsArray.add(card);
		this.totalCards++;	
   }
   

/**
 * Remove card(s) from a pile
 */
	public void removeCard(int numCards) {
		int start = this.totalCards-1; 
		int end = this.totalCards-numCards;
		for(int i=start;i>=end; i--){
			this.cardsArray.remove(i);
			this.totalCards--;
		}
   }
   
/**
 * Transfer card(s) from input parameter pile to this pile
 */
	public void transferCard(Pile otherPile, int numCards) {
		for(int i=otherPile.totalCards-numCards;i<otherPile.totalCards; i++){
			this.addCard(otherPile.cardsArray.get(i));
		}
		otherPile.removeCard(numCards); // Remove cards from input pile 
   }
   
   
/**
 * Shuffles the cards in the pile
 */  	
	public void shuffleCards () {
		Collections.shuffle(this.cardsArray);
	}
   
/**
 * Flip top card in the pile
 */  	
	public void flipTopCardInPile () {
		this.cardsArray.get(this.totalCards-1).flipCard();
	  
	}   
   
/**
 * Is Pile empty?
 */
	public boolean isEmpty() {
		return this.totalCards==0;
	}

/**
 * Check if the card be placed on a "Lane" pile  
 */   
	public boolean canPlaceOnLane(Pile fromPile, int numCardsToMove){
	   
		Card topCard = fromPile.cardsArray.get(fromPile.totalCards-numCardsToMove);
	   
		if(!this.isEmpty()){
			Card bottomCard = this.cardsArray.get(totalCards-1);
			
			// Check if alternating in color and if in descending rank order
			return bottomCard.isAlternateColor(topCard) && bottomCard.isConsecutive(topCard);  
		
		
		}else{ // Empty pile to place the card into
		
			// Must be King rank
			return topCard.getRank().equals(Rank.KING.name());
		}

		
	}
   
   
/**
 * Check if the card be placed on a "Foundation" pile  
 */   
   public boolean canPlaceOnFoundation(Pile fromPile){

		Card topCard = fromPile.cardsArray.get(fromPile.totalCards-1);
	   
		if(!this.isEmpty()){
			Card bottomCard = this.cardsArray.get(totalCards-1);
			
			// Must be same suit and in ascending rank order
			return bottomCard.isSameSuit(topCard) && topCard.isConsecutive(bottomCard);  
		}else{
			
			// Must be same suit as the pile label and must be ACE rank
			return topCard.getSuit().substring(0,1).equals(this.getLabel()) && topCard.getRank() == Rank.ACE.name(); 
		}
	   
   } 
   
/**
 * Check if foundation pile is complete
 */   
   public boolean isFoundationComplete(){
		if(!this.isEmpty()){
			Card topCard = this.cardsArray.get(this.totalCards-1);
			
			// Check is top card in pile is King Rank
			return topCard.getRank().equals(Rank.KING.name());
		}else{
			// Empty pile: Incomplete
			return false;
		}
	   
   }    
   
}
