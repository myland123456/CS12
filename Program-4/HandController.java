import java.awt.*;
import java.awt.event.*;
/**
 * Test controller for a HandDisplay.
 * Shows how cards can be added and removed from a HandDisplay.
 */
class HandController implements ActionListener {
  private Deck deck;
  private HandDisplay handDisplay;
  
  /**
   * @param hd the hand being controlled
   * @param d a deck used for getting new cards to add to the hand
   */
  HandController(HandDisplay hd, Deck d) {
    deck = d;
    handDisplay = hd;
  }
  
  /**
   * add or remove a card from the hand
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("remove")) {
      handDisplay.hand.remove(0);
      handDisplay.update();
    }
    else {
      Card c;
      System.out.println("drew:" + (c = deck.draw()));
      handDisplay.hand.add(c);
      handDisplay.update();
    }
  }
}
