import java.awt.event.*;

/**
 * This class demonstrates how you can respond to a card being clicked on.
 * In this case it removes it from the hand.
 * This class assumes that CardHolders are stored in a HandDisplay.
 */
class CardPicker extends MouseAdapter {
  /**
   * Removed the selected card from it's containing HandDisplay.
   */
  public void mouseClicked(MouseEvent e) {
    CardHolder holder = (CardHolder)(e.getSource());
    System.out.println("Card clicked was " + holder.getCard());
    HandDisplay hd = (HandDisplay)holder.getParent();
    hd.remove(holder);
  }
}