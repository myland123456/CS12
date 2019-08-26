/**
 * This is minor modification of the Deck.java class from Java by Dissection chapter 6.
 * 
 */
import java.util.Random;

class Deck {
  private Card[] deck;
  private int next = 0;
  private Random rand;
  
  /**
   * Construct a deck of 52 cards.
   */
  Deck() {
    deck = new Card[52];
    Suit suit = Suit.spades;
    Rank rank = Rank.ace;
    for (int i = 0; i < deck.length; i++) {
      if (i % 13 == 0) {
        suit = suit.next();
        rank = Rank.ace;
      }
      deck[i] = new Card(suit, rank);
      rank = rank.next();
    }
    rand = new Random();
  }
  
  /**
   * Construct a deck of 52 cards using the specified seed to build the pseudo-random number generator
   * that will control the shuffling of the deck.
   * @param seed - for the random number generator
   */
  Deck(long seed) {
    this();
    rand = new Random(seed);
  }
  
  /**
   * Remove one card from the deck and return it.
   * @return the next card from the deck or null of no more cards
   */
  Card draw() {
    if (next < deck.length) {
      Card card = deck[next];
      next++;
      return card;
    }
    else {
      return null;
    }
  }
  
  /**
   * @return true if there are no more cards left in the deck, false otherwise
   */
  boolean isEmpty() {
    return next >= deck.length;
  }
  
  /**
   * Shuffle the cards of the deck returning all cards to the deck if any have been removed.
   */
  void shuffle() {  
    next = 0; // reset count of cards already drawn from the deck
    for (int i = 0; i < deck.length - 1; i++){
      int k = rand.nextInt(deck.length-i);
      Card t = deck[i];
      deck[i] = deck[i+k];
      deck[i+k] = t;    
    } 
  } 
  
  /**
   * A string representation of the entire deck including cards that have been drawn.
   * This shows the ordering of the cards that will be returned (or have been returned) from draw().
   */
  public  String toString()  { 
    String t = ""; 
    for (int i = 0; i < deck.length; i++)
      if ( (i + 1) % 4 == 0)
        t = t + deck[i] + "\n";
      else     
        t = t + deck[i] + " ";
      return t;
  }
}
