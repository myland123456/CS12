import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")

/**
 A component that can display a Card. The card images must be in the local directory in a subdirectory named "cards".
 The names of the cards are two letters, rank-suit, followed by ".gif". 
 */
class CardHolder extends ImagePainter {
	/**
   @param card - the card that this holder displays
   @param display - the component that contains this card
	 */
	CardHolder(Card card, Component display) {
		this.display = display;
		this.card = card;
		if (card.rank == Rank.joker) {
			image = new GIFImage("cards/joker.gif", display).image;
		}
		else {
			image = new GIFImage("cards/"+card.rank.toChar() + card.suit.toString().toLowerCase() + ".gif", display).image;
		}
	}

	/**
   @param card- the new card to display in this CardHolder
	 */
	void setCard(Card card) {
		if (card.rank == Rank.joker) {
			image = new GIFImage("cards/joker.gif", display).image;
		}
		else {
			image = new GIFImage("cards/"+card.rank.toChar() + card.suit.toString().toLowerCase() + ".gif", display).image;
		}
		this.card = card;
		super.repaint();
	}

	Card getCard() { return card;}

	private Card card;
	private Component display;
}
