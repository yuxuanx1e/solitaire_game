/**
 * CardTest creates a few sample cards
 * @version 1.00 28-09-22
 * @author Yuxuan Xie
 */

/**
 * A {@code CardTest} class for testing the Card class
 */
public class CardTest {
   public static void main(String[] args) {
      Card  c5S= new Card(Suit.SPADES, Rank.FIVE, false);
	  Card  c7H= new Card(Suit.HEARTS, Rank.SEVEN, false);

      CardView.displayCardInfo(c5S);
	  CardView.displayCardInfo(c7H);
	  
	  if(c5S.isConsecutive(c7H)){
		  System.out.println("Two cards in sequence!");
	  }else{
		  System.out.println("Two cards NOT in sequence!");
	  }
   }
}
