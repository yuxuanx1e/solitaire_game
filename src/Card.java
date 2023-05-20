/**
 * Card models standard playing cards information
 * @version 1.00 28-09-22
 * @author Yuxuan Xie
 */


/**
 * A {@code Card} object representing a card 
 */
public class Card {
	
	private Suit suit;		// Card Suit: Clubs, Diamonds, Hearts, Spades
	private Rank rank;		// Card Rank Ace to King
	private boolean faceUp; // IS card facing up or down

/**
 * Constructor for the class.
 * @param suit one of four suits: clubs, diamonds, hearts, spades
 * @param rank one of 13 ranks from ace to king
 * @param faceUp is card facing up
 */
	Card(Suit suit, Rank rank, boolean faceUp) {
		this.suit = suit;
		this.rank = rank;
		this.faceUp = faceUp;
    }

/**
 * Returns the suit of the card
 */
	public String getSuit() {
		return suit.name();
	}

/**
 * Returns the rank of the card
 */   
	public String getRank() {
		return rank.name();
	}
	
/**
 * Returns boolean to indicate if card is flipped up or down
 */   
	public boolean isFaceUp() {
		return this.faceUp;
	}
	
/**
 * Flips the card
 */   
	public void flipCard() {
		this.faceUp=!faceUp;
	}


/**
 * Returns the card symbol given as rank-suit e.g. 5-S
 */ 
	public String toString () {
		if(!this.isFaceUp()){
			return "[ ]";
		}
		
		String cardSymbol; 
		
		switch(this.getRank()) {
			case "ACE": cardSymbol = "A"; 
				 break;
			case "TWO": cardSymbol = "2"; 
				 break;
			case "THREE": cardSymbol = "3"; 
				 break;
			case "FOUR": cardSymbol = "4"; 
				 break;
			case "FIVE": cardSymbol = "5"; 
				 break;	 
			case "SIX": cardSymbol = "6"; 
				 break;
			case "SEVEN": cardSymbol = "7"; 
				 break;
			case "EIGHT": cardSymbol = "8"; 
				 break;
			case "NINE": cardSymbol = "9"; 
				 break;
			case "TEN": cardSymbol = "10"; 
				 break;
			case "JACK": cardSymbol = "J"; 
				 break;
			case "QUEEN": cardSymbol = "Q"; 
				 break;
			default: cardSymbol = "K"; 
				 break;
		}
		
		cardSymbol = cardSymbol +suit.name().charAt(0);
		return cardSymbol; 
			
	}

/**
 * Check if card belongs to a black suit
 */ 	
	public boolean isBlack(){
		return suit==Suit.CLUBS || suit==Suit.SPADES;
	}

/**
 * Check if card belongs to a red suit
 */ 	
	public boolean isRed(){
		return suit==Suit.HEARTS || suit==Suit.DIAMONDS;
	}	

/**
 * Check if two cards are different in colour
 */ 	
	public boolean isAlternateColor(Card otherCard){
		return this.isBlack() != otherCard.isBlack();
	}	
	
/**
 * Check if two cards are in sequence
 */ 	
	public boolean isConsecutive(Card lowerCard){
		return this.rank.ordinal() == lowerCard.rank.ordinal() +1;
	}	
	
/**
 * Check if two cards have the same suit
 */ 	
	public boolean isSameSuit(Card otherCard){
		return this.suit == otherCard.suit;
	}		

}
