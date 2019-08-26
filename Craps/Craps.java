/*
 * Author: Charlie McDowell (charlie@cse)
 * TA Solution for Craps.java
 * 
 * This program allows the user to play the game of Craps. The rules of
 * craps are as follows:
 * if the first roll is a 7 or a 11, you win immediately
 * if the first roll is a 2 or a 12, you lose immediately
 * otherwise, you go into "point mode" and the first roll becomes the * point.
 * Now, you keep rolling the dice until you get the point again or a 7.
 * If you got the point, you win, if you get a 7, you lose.
 * 
 * For example, the following sequence of rolls wins
 * 5 (becomes point) 2 12 5
 * because 2 and 12 lose their special meaning in point mode.
 * 
 * The user bets some amount on each round of craps. If she wins, she gets
 * additional money equal to the bet. If she loses, she loses the money she
 * bet.
 *
 * This version has some intentional syntax errors to demo using eclipse to fix them.
 * 
 * Errors corrected by Yanwen Xu
 */

import java.util.Random;
import java.util.Scanner;

class Craps {
	static final boolean WIN = true;
	static final boolean LOSE = false;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int money = 100;

		System.out.println("Enter the seed.");
		int seed = input.nextInt();
		Random random = new Random(seed); 
		// Create a random number generator with the specified seed so that
		// we can duplicate our results.

		int bet;
		bet = getBet(input, money); // Get the bet from the user

		while (bet > 0 && money > 0) { // while user has wish and means to continue
			boolean won = rollDiceUntilWinOrLose(random); // play one round
			if (won) { // if she won, she gets wins <bet> money
				money = money + bet;
				System.out.println("You won, you now have " + money);
			} else { // if she lost, she loses <bet> money
				money = money - bet;
				System.out.println("You lost, you now have " + money);
			}
			if ( money > 0) {
				bet = getBet(input, money); // If user can bet, let her
			}
		}
	}

	/*
	 * This method plays one round of craps and tells us whether the user
	 * won or lost
	 * input: random number generator (because it uses rollDice)
	 * output: won or lost
	 */

	static boolean rollDiceUntilWinOrLose(Random random) {
		int point = rollDice(random);
		if ( point == 7 || point == 11 )
			return WIN;
		else if ( point == 2 || point == 12)
			return LOSE;

		// roll until point or 7
		boolean winLose = rollUntilPointOr7(random, point);

		return winLose;
	}

	static boolean rollUntilPointOr7(Random random, int point) {
		int nextRoll = rollDice(random);
		boolean winLose;
		while ( nextRoll != point && nextRoll != 7 ) {
			nextRoll = rollDice(random);
		}
		if ( nextRoll == 7 )
			winLose = false;
		else
			winLose = true;
		return winLose;
	}

	/*
	 * Simulates rolling of dice by generating random numbers from a random
	 * number generator.
	 * input: random number generator to use. We need this because we want
	 *         to use the *same* random number generator throughout and 
	 *         duplicate our results.
	 * output: total after rolling the dice.
	 */

	static int rollDice(Random random) {
		int die1 = random.nextInt(6) + 1;
		int die2 = random.nextInt(6) + 1;
		System.out.println("roll is " + die1 + ", " + die2);
		return die1 + die2;
	}

	/*
	 * Returns the amount bet by the user. Performs input checking to ensure
	 * that the user does not bet more than she has and she does not bet
	 * a negative value.
	 * input: amount user currently has. This is checked against the amount
	 *         she wants to bet to make sure the bet is valid.
	 * output: a valid bet
	 */

	static int getBet(Scanner input, int total) {
		System.out.println("Enter bet.");
		int bet = input.nextInt();
		while ( bet < 0 || bet > total) {
			System.out.println("Not an ok bet.");

			System.out.println("Enter bet.");
			bet = input.nextInt();

		}
		return bet;
	}
}
