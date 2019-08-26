import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 This class shows how to use the HandDisplay component.
 */
class HandDisplayTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Card Table");
		Container display = frame.getContentPane();
		CardPicker picker = new CardPicker();
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand();
		// hard coded test for 7 cards in the initial hand
		for(int i = 0; i < 7; i++) {
			hand.add(deck.draw());
		}
		HandDisplay handDisplay = new HandDisplay(hand, picker);
		display.add(handDisplay);
		
		JButton remove = new JButton("remove");
		JButton add = new JButton("add");
		display.add(BorderLayout.NORTH, remove);
		display.add(BorderLayout.SOUTH, add);
		HandController controller = new HandController(handDisplay, deck);
		remove.addActionListener(controller);
		add.addActionListener(controller);
		frame.pack();
		frame.setVisible(true);
	}
}