/**
 * PileView Displays pile of cards neatly on console
 * @version 1.00 28-09-22
 * @author Yuxuan Xie
 */
 
/**
 * A {@code PileView} class for formatting and displaying pile of cards
 */ 
public class PileView {
	public static String formatPile (Pile pile) {
		
		String pileString;
		int totalCards = pile.getTotalCards();
		
		pileString = pile.getName() + "\n"; // Attach pile name
		pileString += "(" + totalCards + " cards)\n"; // Total number of cards
		
		if(!pile.isEmpty()){ 
			if(pile.isStacked()){ 
				pileString+=pile.showCard(totalCards-1)+"\n"; // Show only top card
				
			}else{
				
				for (int i= 0; i<totalCards; i++){
					pileString+=pile.showCard(i)+"\n"; // Add card symbol(s)
				}
			}
		}else{
			pileString+="   \n"; // Space to indicate empty pile
		}
		
		return pileString;
	}
	
	public static void displayPile(Pile pile){
		String pileString = formatPile(pile);
		System.out.println(pileString);
	}
}