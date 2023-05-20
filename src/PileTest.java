/**
 * PileTest tests the various methods of Pile and PileView classes
 * @version 1.00 29-09-22
 * @author Yuxuan Xie
 */

/**
 * A {@code PileTest} class for testing the Pile and PileView classes
 */
public class PileTest {
   public static void main(String[] args) {
	   
		Card  c5S= new Card(Suit.SPADES, Rank.FIVE, false);
		Card  c6H= new Card(Suit.HEARTS, Rank.SIX, false);
		Card  c3C= new Card(Suit.CLUBS, Rank.THREE, true);
		Card  cQD= new Card(Suit.DIAMONDS, Rank.QUEEN, true);
		Card  cAS= new Card(Suit.SPADES, Rank.ACE, true);
		
		Card  c8C= new Card(Suit.CLUBS, Rank.EIGHT, false);
		Card  c7D= new Card(Suit.DIAMONDS, Rank.SEVEN, true);
		Card  c4S= new Card(Suit.SPADES, Rank.FOUR, true);
		
		Pile  pile1 = new Pile("Pile 1","1", false);
		pile1.addCard(c5S);
		pile1.addCard(c6H);
		pile1.addCard(c3C);
		pile1.addCard(cQD);
		pile1.addCard(cAS);
		
		Pile  pile2 = new Pile("Pile 2","2", true);
		pile2.addCard(c8C);
		pile2.addCard(c7D);
		pile2.addCard(c4S);

		PileView.displayPile(pile1); // Display all cards
		PileView.displayPile(pile2); // Display all cards
		

		
		pile1.removeCard(2); 
		PileView.displayPile(pile1); // Display pile after removing 2 cards
		
		
		// Transfer two cards from pile 2 to pile 1
		pile1.transferCard(pile2, 2); 

		
		PileView.displayPile(pile1); // Display pile 1 after transfer
		PileView.displayPile(pile2); // Display pile 1 after transfer
		
		
   }
}
