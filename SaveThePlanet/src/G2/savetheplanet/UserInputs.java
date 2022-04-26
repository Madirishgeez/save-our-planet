/**
 * 
 */
package G2.savetheplanet;

import java.util.InputMismatchException;

/**
 * @author Matt
 *
 */

import java.util.Scanner;

public class UserInputs {

	// constants
	private static final String YES = "Y";
	private static final String NO = "N";
	private static final int MIN_PLAYERS = 2;
	private static final int MAX_PLAYERS = 4;
	private static final String EMPTY_STRING = "";
	private static final int MENU_LOWER = 1;

	static Scanner sc1 = new Scanner(System.in);

	// constructor
	/**
	 * default constructor
	 */

	public UserInputs() {

	}

	/**
	 * method to return user input as int for number of players The number of
	 * players must be between 2 and 4
	 */
	public static int userInputPlayers() {
		boolean valid = false;
		int players = 0;
		do {
			try {
			System.out.println("Hint: Please choose between 2 - 4 players.");
			players = sc1.nextInt();
		} catch (InputMismatchException e) {
			sc1.next();
		}
			// now validate - TEST
			valid = validateUserPlayers(players);
			// other go again
		} while (!valid);
		return players;
	}

	/**
	 * method to check the validation rules
	 * 
	 * @return boolean
	 */
	protected static boolean validateUserPlayers(int userInput) {

		if ((userInput >= MIN_PLAYERS) && (userInput <= MAX_PLAYERS)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get user input as String for user name Names cannot be empty strings or null
	 * values
	 */
	public static String userInputNames() {
		boolean valid = false;
		String userName = EMPTY_STRING;

		do {
			System.out.println("Hint: Empty names are not allowed.");
			userName = sc1.nextLine();
			valid = validateUserNames(userName);
		} while (!valid);
		return userName;

	}

	/**
	 * check the validation rules of the userInputNames method Names cannot be empty
	 * strings or null values
	 * 
	 * @return boolean
	 */
	protected static boolean validateUserNames(String userInput) {

		if ((userInput.trim().equals(EMPTY_STRING)) || (userInput.trim().equals(null))) {
			return false;
		}

		else {
			return true;
		}
	}

	/**
	 * verify user input as String for choice verification Input cannot be empty
	 * strings or null values.
	 */
	public static String userInputValidation() {
		boolean valid = false;
		String userInput = EMPTY_STRING;

		do {
			System.out.println("Please choose Yes(Y) or No (N)");
			userInput = sc1.nextLine();

			valid = validateUserValidation(userInput);

		} while (!valid);

		return userInput;

	}

	/**
	 * check the validation rules Input cannot be empty strings or null values.
	 * 
	 * @return boolean
	 */
	protected static boolean validateUserValidation(String userInput) {

		if ((userInput.trim().equalsIgnoreCase(YES)) || (userInput.trim().equalsIgnoreCase(NO))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * method to get user input as int for the menu User must input a number
	 * depending on how many options are valid
	 * 
	 * @return
	 */
	public static int userInputMenu(int numberOfChoices) {
		boolean valid = false;
		int userInput = 0;

		do {

			System.out.println("\n\nHint: Choose one of the options using the numbers on your keyboard.");
			userInput = sc1.nextInt();
			sc1.nextLine();

			valid = validateUserMenu(userInput, numberOfChoices);

		} while (!valid);
		return userInput;

	}

	/**
	 * User must input a number between 1 and the number of choices passed in to the
	 * method.
	 * 
	 * @param userInput
	 * @param numberOfChoices
	 * @return
	 */
	protected static boolean validateUserMenu(int userInput, int numberOfChoices) {

		if ((userInput >= MENU_LOWER) && (userInput <= numberOfChoices)) {
			return true;
		} else {
			return false;

		}
	}

}