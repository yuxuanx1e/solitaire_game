/**
 * CardView Prints card information to console
 * @version 1.00 28-09-22
 * @author Yuxuan Xie
 */
 
/**
 * A {@code CardView} class for handling outputs
 */ 
public class CardView {
	public static void displayCardInfo (Card card) {
		System.out.println("Card Symbol: " + card);
		System.out.println("Card Suit: " + card.getSuit());
		System.out.println("Card Rank: " + card.getRank());
	}
}