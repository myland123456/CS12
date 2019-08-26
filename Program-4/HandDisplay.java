import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")

/**
 * A container (JPanel) that displays the cards in a Hand.
 */
class HandDisplay extends JPanel {
  Hand hand;
  CardHolder[] cardViews;
  MouseListener listener;
  
  /**
   * The cards are displayed in a row using a GridLayout.
   * The initial size is set to be just big enough to hold the cards in the initial hand.
   * @param hand to be displayed
   */
  HandDisplay(Hand hand, MouseListener listener) {
    this.hand = hand;
    this.listener = listener;
    setLayout(new GridLayout(1,hand.size()));
    cardViews = new CardHolder[hand.size()];
    for(int i = 0; i < hand.size(); i++) {
      add(cardViews[i] = new CardHolder(hand.get(i), this));
      cardViews[i].addMouseListener(listener);
    }
  }
  
  /**
   * Remove the specifed card from this hand.
   * @param card the CardHolder holding the card to be removed
   */
  void remove(CardHolder card) {
    hand.remove(card.getCard());
    update();
  }
  
  
  /**
   * Redisplay the cards in the hand. This method should be called whenever the underlying hand has changed.
   */
  void update() {
    removeAll();
    cardViews = new CardHolder[hand.size()];
    for(int i = 0; i < hand.size(); i++) {
      add(cardViews[i] = new CardHolder(hand.get(i), this));
      cardViews[i].addMouseListener(listener);
    }
    validate();
  }
}