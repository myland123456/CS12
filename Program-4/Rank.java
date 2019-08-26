/**
 * The thirteen ranks ace through king of a standard 52 card deck of cards, plus joker.
 * Note that for ordering purposes based on the ordinal value, the joker comes first and ace is below two.
 */
public enum Rank {
  joker, ace, two, three, four, five, six, seven, 
  eight, nine, ten, jack, queen, king;
  
  /**
   * @return the next rank in sequence with king wrapping back to joker which is the start followed by
   * ace, two, etc.
   */
  public Rank next() {
    switch(this) {
      case joker: return ace;
      case ace: return two;
      case two: return three;
      case three: return four;
      case four: return five;
      case five: return six;
      case six: return seven;
      case seven: return eight;
      case eight: return nine;
      case nine: return ten;
      case ten: return jack;
      case jack: return queen;
      case queen: return king;
      default: return joker;
    }
  }
  
  /**
   * 
   * @return A one (or in the case of 10 - two) letter representation of the rank. 
   */
  public String toString() {
    switch(this) {
      case joker: return "X";
      case ace: return "A";
      case two: return "2";
      case three: return "3";
      case four: return "4";
      case five: return "5";
      case six: return "6";
      case seven: return "7";
      case eight: return "8";
      case nine: return "9";
      case ten: return "10";
      case jack: return "J";
      case queen: return "Q";
      default: return "K";
    }
  }
  
  /**
   * 
   * @return A one single character representation of each card. These are the same as those returned by
   * toString() except that they are lower case and ten is 't'.
   */
  public char toChar() {
    switch(this) {
      case joker: return 'x';
      case ace: return 'a';
      case two: return '2';
      case three: return '3';
      case four: return '4';
      case five: return '5';
      case six: return '6';
      case seven: return '7';
      case eight: return '8';
      case nine: return '9';
      case ten: return 't';
      case jack: return 'j';
      case queen: return 'q';
      default: return 'k';
    }
  }
  
  /**
   * A helper function to find the rank object that corresponds to a single character
   * representation of the rank. Note that 'a' is ACE and '1' is TEN. All others are either
   * the actual digit value or the first letter of the word for face cards.
   * @param a single char which maps to a Rank
   * @return the rank associated with the char r
   */
  public static Rank makeRank(char r) {
    switch(r) {
      case 'a': return ace;
      case '2': return two;
      case '3': return three;
      case '4': return four;
      case '5': return five;
      case '6': return six;
      case '7': return seven;
      case '8': return eight;
      case '9': return nine;
      case 't':
      case '1': return ten;
      case 'j': return jack;
      case 'q': return queen;
      case 'k': return king;
      default: return joker;
    }
  }
}
