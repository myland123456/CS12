import java.util.Comparator;
/**
 * A card representation that can be compared. The order is first by rank (any 3 is higher than any 2)
 * and then by suit if the rank is the same. The lowest rank is the Joker then the Ace. King is the highest rank.
 * The rank order is actually specified by the Rank class.
 * 
 */
class Card implements Comparable<Card>, Comparator<Card> {
  Suit suit;   
  Rank rank;
  
  /**
   * A card can be constructed from a suit and a rank.
   */
  Card(Suit s, Rank r) { suit = s; rank = r; }
  
  /**
   * A card can be constructed from another card, essentially making a copy.
   */
  Card(Card c) { suit = c.suit; rank = c.rank; }
  
  /**
   * A card displays as the rank followed by the suit.
   * @return RankSuit
   */
  public  String toString() {
    return rank +""+ suit;  
  }   

  /**
   * Cards are compared first by rank and if the rank is the same, then by
   * suit. See Suit and Rank to determine ordering within these classes.
   * @return the difference in rank or if that is zero the difference in suit
   */
  public int compareTo(Card other) {
	  return compare(this, other);
  }
  
  public int compare(Card c1, Card c2) {
	    int rankDiff = c1.rank.ordinal() - c2.rank.ordinal();
	    if (rankDiff != 0) {
	      return rankDiff;
	    }
	    else {
	      return c1.suit.ordinal() - c2.suit.ordinal();
	    }
  }
}
