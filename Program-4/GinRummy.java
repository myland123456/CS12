import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


/*
 * Program 4
 * 
 * This program was completed with a programming partner. 
 * We will be submitting the same copy of this program.
 * 
 * @author Yanwen Xu
 * @author Junru Zhou
 */

class GinRummy {
	
	public static void main(String[] args) {
		ArrayList<Card> getCardList = new ArrayList<Card>();
		JFrame frame = new JFrame("GinRummy");
		Container display = frame.getContentPane();
		CardPicker picker = new CardPicker();
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand();
		
		// Initialize the game with seven cards in hand
		for(int i = 0; i < 7; i++) {
			hand.add(deck.draw());
		}
		HandDisplay handDisplay = new HandDisplay(hand, picker);
		display.add(handDisplay);
		
		// Three Panels
	    JPanel panelNorth = new JPanel();
	    JPanel panelCenter = new JPanel();
	    JPanel panelSouth = new JPanel();
	    
	    panelNorth.setPreferredSize(new Dimension(700, 150));
		
		// Discard Pile
	    JPanel pilePanel = new JPanel();
	    pilePanel.setPreferredSize(new Dimension(75, 110));
	    pilePanel.add(new ImagePainter("cards/b.gif"));
		
	    // Center Panel Displays Cards
	    panelCenter.setPreferredSize(new Dimension(575, 100));
	    panelCenter.add(pilePanel);
	    
	    handDisplay.setPreferredSize(new Dimension(500, 100));
	    panelCenter.add(handDisplay);
		
		// Strings used in the info labels
		String infoText = "Hints and warnings about the game will be displayed here.";
		
		// Labels

	    JLabel infoLable = new JLabel(infoText);// Displays rules about the game and player actions
	    infoLable.setPreferredSize(new Dimension(650, 40));
	    infoLable.setOpaque(true);
	    infoLable.setBackground(new Color(255, 255, 255));
	    infoLable.setForeground(new Color(100, 100, 100));
	    
	    panelNorth.add(infoLable);
		
		// Create 4 buttons for functions of the game
		
	    JButton drawButton = new JButton("Draw");
	    drawButton.setPreferredSize(new Dimension(150, 50));
	    panelSouth.add(drawButton);
	    
	    JButton discardButton = new JButton("Discard");
	    discardButton.setPreferredSize(new Dimension(150, 50));
	    panelSouth.add(discardButton);
	    
	    JButton flipButton = new JButton("flip");
	    flipButton.setPreferredSize(new Dimension(150, 50));
	    panelSouth.add(flipButton);
	    
	    JButton ginButton = new JButton("Gin");
	    ginButton.setPreferredSize(new Dimension(150, 50));
	    panelSouth.add(ginButton);
	    ginButton.addActionListener(new ActionListener() {
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ArrayList<Card>> list = hand.makesGin();
				if(list==null || list.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Keep trying!", "Message", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame, "Congratulations! You won. "+getCardList.size()+" cards", "Message", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// Adding all things to the layout
	    display.add(BorderLayout.NORTH, panelNorth);
	    display.add(BorderLayout.CENTER, panelCenter);
	    display.add(BorderLayout.SOUTH, panelSouth);
		
		// Adding action listeners to the buttons
		HandController drawCtrl = new HandController(handDisplay, deck);
		HandController discardCtrl = new HandController(handDisplay, deck);
		HandController flipCtrl = new HandController(handDisplay, deck);
		
		
		drawButton.addActionListener(drawCtrl);
		discardButton.addActionListener(discardCtrl);
		flipButton.addActionListener(flipCtrl);
		
		// Finalize the panels and set things to be visible
	    frame.setPreferredSize(new Dimension(800, 400)); 
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}