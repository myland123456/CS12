/**
 * The four suits of a standard deck of 52 playing cards.
 * Their ordinal order is clubs, diamonds, hearts, then spades.
 */
public enum Suit { 
  clubs, diamonds, hearts, spades;
  
  /**
   * @return the next suit in ordinal order with spades wrapping back
   * around to clubs.
   */
  public Suit next() {
    switch(this) {
      case clubs: return diamonds;
      case diamonds: return hearts;
      case hearts: return spades;
      default: return clubs;
    }
  }
  /**
   * @return a one character representation of the suit (first letter of the name)
   */
  public String toString() {
    switch(this) {
      case clubs: return "C";
      case diamonds: return "D";
      case hearts: return "H";
      default: return "S";
    }
  }
}
