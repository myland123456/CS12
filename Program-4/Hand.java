import java.util.*;

/**
 * A hand of cards. This version includes methods to see if a Hand forms the
 * needed melds for Gin Rummy.
 * 
 * @author Charlie McDowell
 */
public class Hand {
	private TreeSet<Card> cards = new TreeSet<Card>();
	private int score = 0;

	/**
	 * Add a card to the hand. IF the Card parameter is null just ignore it and do nothing.
	 * @param - card to be added.
	 */
	public void add(Card c) {
		// allow calls with null, just ignore
		if (c != null) {
			cards.add(c);
		}
	}

	/**
	 * @return the cards (rank and suit) separated by spaces.
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();

		for (Card c:cards) {
			result.append(c + " " );
		}
		return result.toString();
	}

	/**
	 * @return the number of cards currently in the hand
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Cards are stored in the order specified by the Card class compareTo() method.
	 * @return the ith card in the hand where the cards are sorted in increasing order.
	 * If i is not a legal card number (i.e. less than 0 or greater than or equal to the
	 * number of cards in the hand, return null.
	 */
	public Card get(int i) {
		// TreeSet used for cards does not have a get(i) method. However, the iterator will
		// return the cards in order. We can then start from the beginning to find the ith card.
		for(Card card: cards) {
			if (i > 0) i--;
			else return card;
		}
		return null; // not a legal card number
	}

	/**
	 * Remove the card at a specified position in the hand.
	 * @param i the index of the card to remove
	 * @returns the card being removed
	 */
	public Card remove(int i) {
		Card card = get(i);
		cards.remove(card);
		return card;
	}

	/**
	 * Remove a specific card from the hand.
	 * @param card to be removed
	 * @return true if the card was in fact in the hand and false otherwise
	 */
	public boolean remove(Card card) {
		if (cards.contains(card)) {
			cards.remove(card);
			return true;
		}
		else return false;
	}

	/**
	 * @return true if the hand contains no cards false otherwise
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return The cards from the hand grouped into melds or null if the hand doesn't make gin.
	 */
	public ArrayList<ArrayList<Card>> makesGin() {
		// put all of the cards from the hand into an ArrayList
		ArrayList<Card> cardArray = new ArrayList<Card>(cards);
		// melds is used to accumulate the melds if successful
		// each meld will be one ArrayList<Card>
		ArrayList<ArrayList<Card>> melds = new ArrayList<ArrayList<Card>>();

		if (makesGin(melds, cardArray)) {
			return melds;
		}
		else { return null; }
	}

	/*
	 * The methods below here are all used to determine if a hand makes gin, ie
	 * does it contain 3 melds (sets or runs), one of which is 4 cards and the
	 * other two which are 3 cards.
	 */


	/*
	 * The comments in the following method were the original pseudo-code for this method.
	 * If you are studying this method you might want to just seperate out the
	 * comments and see if that is easier to follow.
	 * It is also an example of how you can start from pseudo-code and then fill in
	 * real code, leaving the pseudo-code as comments.
	 */
	private static boolean makesGin(ArrayList<ArrayList<Card>> melds, ArrayList<Card> hand) {
		// if there are cards left in the hand
		if (hand.size() > 1) {
			//   pick a card and try to complete a set
			//   if successful recurse on the rest of the hand
			ArrayList<Card> set = new ArrayList<Card>();
			ArrayList<Card> tempHand = new ArrayList<Card>(hand);
			if (makeAset(set, tempHand)) {
				System.out.println("Found set: " + set);
				melds.add(set);
				return makesGin(melds, tempHand);
			}
			//   else 
			else {
				//     try and make a run
				ArrayList<Card> run = new ArrayList<Card>();
				tempHand = new ArrayList<Card>(hand);
				//     if successful recurse on the rest of the hand
				if (makeArun(run, tempHand)) {
					System.out.println("Found run: " + run);
					melds.add(run);
					return makesGin(melds, tempHand);
				}
				//     else return false
				else {
					// couldn't make set or run with first card in hand
					return false;
				}
			}
		}
		else if (hand.size() == 1) {
			// check if last card works with sets found so far
			return make4card(melds,hand);
		}
		else {
			return false;
		}

	}

	/*
	 * This method expects melds to contain 3, 3-card sets or runs and that
	 * hand contains just a single card.
	 * TODO: I should add asserts to check these.
	 */
	private static boolean make4card(ArrayList<ArrayList<Card>> melds, ArrayList<Card> hand) {
		for (ArrayList<Card> setOrRun : melds) {
			if (isRun(setOrRun)) {
				Card fourth = extendRun(setOrRun, hand);				
				if (fourth != null) {
					hand.remove(0);
					setOrRun.add(fourth);
					return true;
				}
			}
			else {
				// must be a set instead
				Card fourth = extendSet(setOrRun, hand);				
				if (fourth != null) {
					hand.remove(0);
					setOrRun.add(fourth);
					return true;
				}
			}
		}
		return false;

	}

	private static boolean isRun(ArrayList<Card> cards) {
		// assumes it is either a set or a run so just check if same suit
		return cards.get(0).suit == cards.get(1).suit;
	}

	/**
	 * 
	 * @param run - an output parameter where the run will be built up
	 * @param hand - the source of cards for the run
	 * @return true if a 3 card run was made, false otherwise
	 */
	private static boolean makeArun(ArrayList<Card> run, ArrayList<Card> hand ) {
		//	System.out.println("makeArun:" + run + ":" + hand);
		if (hand.size() == 0) return false;
		else if (run.size() == 0) {
			// pick a card and recurse
			run.add(hand.remove(0));

			boolean runWithFirstCard = makeArun(run, hand);
			if (runWithFirstCard) {
				return true;
			}
			else {
				Card next = hand.remove(0);
				while (run.size() > 0) {
					hand.add(run.remove(0));
				}
				run.add(next);
				return makeArun(run, hand);
			}
		}
		else {
			Card next = extendRun(run,hand);
			if (next == null) return false;
			else {
				hand.remove(next);
				run.add(next);
				if (run.size() == 3) return true;
				else return makeArun(run, hand);
			}
		}
	}

	/** 
	 * @param set - an output parameter where the set will be built up
	 * @param hand - the source of cards for the run
	 * @return true if a 3 card run was made, false otherwise
	 */
	private static boolean makeAset(ArrayList<Card> set, ArrayList<Card> hand ) {
	//	System.out.println("makeAset:" + set + ":" + hand);
		if (hand.size() == 0) return false;
		else if (set.size() == 0) {
			// pick a card and recurse
			set.add(hand.remove(0));
			boolean setWithFirstCard = makeAset(set, hand);
			if (setWithFirstCard) {
				return true;
			}
			else {
				Card next = hand.remove(0);
				while (set.size() > 0) {
					hand.add(set.remove(0));
				}
				set.add(next);
				return makeAset(set, hand);
			}
		}
		else {
			Card next = extendSet(set,hand);
			if (next == null) return false;
			else {
				hand.remove(next);
				set.add(next);
				if (set.size() == 3) return true;
				else return makeAset(set, hand);
			}
		}
	}
	
	// try to add one more card to the set
	private static Card extendSet(ArrayList<Card> set, ArrayList<Card> hand) {
		//System.out.println("extendSet:" + set + ":" + hand);
		for (Card card : hand) {
			if (card.rank == set.get(0).rank) {
				return card;
			}
		}
		return null;
	}
	// try to add one more card to the run
	private static Card extendRun(ArrayList<Card> run, ArrayList<Card> hand) {
		run.sort(run.get(0)); // sort just needs any Card which includes a Comparator
		Card first = run.get(0);
		Card last = run.get(run.size()-1);
		for (Card card : hand) {
			if (card.suit == run.get(0).suit) {
				if (card.rank.ordinal() == first.rank.ordinal() - 1) {
					return card;
				}
				else if (card.rank.ordinal() == last.rank.ordinal() + 1) {
					return card;
				}
			}
		}
		return null;
	}

	/*
	 * This is a unit test for the makesGin() and helper methods.
	 */

	public static void main(String[] args) {
		Deck deck = new Deck();
		//System.out.println(deck);

		// trivial test all same suit
		Hand hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.two));
		hand.add(new Card(Suit.hearts,Rank.two));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.spades,Rank.four));
		assert makeAset(new ArrayList<Card>(), new ArrayList<Card>(hand.cards)) : "test all same rank";

		// test no set
		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.three));
		hand.add(new Card(Suit.hearts,Rank.four));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.spades,Rank.four));
		assert !makeAset(new ArrayList<Card>(), new ArrayList<Card>(hand.cards)) : "test no set";

		// test finds set
		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.three));
		hand.add(new Card(Suit.hearts,Rank.two));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.spades,Rank.four));
		hand.add(new Card(Suit.spades,Rank.two));

		ArrayList<Card> theSet = new ArrayList<Card>();
		ArrayList<Card> handSet = new ArrayList<Card>(hand.cards);
		assert makeAset(theSet, handSet) : "test find set in mixed hand";
		System.out.println("set:" + theSet);
		System.out.println("hand:" + handSet);
		System.out.println("passed basic find a set tests");

		// test basic run
		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.clubs,Rank.four));
		hand.add(new Card(Suit.diamonds,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.four));
		hand.add(new Card(Suit.hearts,Rank.two));
		hand.add(new Card(Suit.hearts,Rank.four));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.clubs,Rank.three));
		ArrayList<Card> theRun = new ArrayList<Card>();
		handSet = new ArrayList<Card>(hand.cards);
		assert makeArun(theRun, handSet) : "test find a run";

		System.out.println("run:" + theRun);
		System.out.println("hand:" + handSet);
		System.out.println("passed basic find a run tests");

		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.clubs,Rank.three));
		hand.add(new Card(Suit.clubs,Rank.four));
		hand.add(new Card(Suit.diamonds,Rank.five));
		hand.add(new Card(Suit.spades,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.six));
		hand.add(new Card(Suit.hearts,Rank.seven));
		hand.add(new Card(Suit.hearts,Rank.eight));
		hand.add(new Card(Suit.hearts,Rank.nine));;

		//	handSet = new ArrayList<Card>(hand.cards);
		ArrayList<ArrayList<Card>> melds = hand.makesGin();
		assert melds != null : "test makesGin";
		if (melds != null) {
			System.out.println("Melds:" + melds);
		}

		// test for 3 sets which broke earlier version
		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.two));
		hand.add(new Card(Suit.hearts,Rank.two));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.spades,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.five));
		hand.add(new Card(Suit.diamonds,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.seven));
		hand.add(new Card(Suit.clubs,Rank.seven));
		hand.add(new Card(Suit.spades,Rank.seven));;

		//	handSet = new ArrayList<Card>(hand.cards);
		melds = hand.makesGin();
		assert melds != null : "test makesGin";
		if (melds != null) {
			System.out.println("Melds:" + melds);
		}


		// test for 3 sets but randomized hand order
		hand = new Hand();
		hand.add(new Card(Suit.clubs,Rank.two));
		hand.add(new Card(Suit.clubs,Rank.seven));
		hand.add(new Card(Suit.spades,Rank.two));
		hand.add(new Card(Suit.spades,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.two));
		hand.add(new Card(Suit.diamonds,Rank.five));
		hand.add(new Card(Suit.hearts,Rank.seven));
		hand.add(new Card(Suit.spades,Rank.seven));
		hand.add(new Card(Suit.diamonds,Rank.two));
		//	handSet = new ArrayList<Card>(hand.cards);
		melds = hand.makesGin();
		assert melds != null : "test makesGin";
		if (melds != null) {
			System.out.println("Melds:" + melds);
		}



		System.out.println("passed all tests");


	}


}