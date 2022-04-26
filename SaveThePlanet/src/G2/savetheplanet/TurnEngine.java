/**
 * 
 */
package G2.savetheplanet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Matt
 *
 */
public class TurnEngine {

	// Instance vars
	protected int currentPlayer;
	private int boardSpaces;
	private int currentPlayerSpace;
	private static final double TAX = 1000;
	//// array list for menu
	static ArrayList<Integer> menuList = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1));

	// Constructors
	/**
	 * Empty Constructor
	 */
	public TurnEngine() {
	}

	/**
	 * Constructor with args for generating a turn1
	 * 
	 * @param currentPlayer      (int)
	 * @param boardSpaces        (int)
	 * @param currentPlayerSpace (int)
	 */
	public TurnEngine(int currentPlayer, int boardSpaces, int currentPlayerSpace) {
		this.currentPlayer = currentPlayer;
		this.boardSpaces = boardSpaces;
		this.currentPlayerSpace = currentPlayerSpace;
	}

	// Getters and setters
	/**
	 * Get the number of the current player
	 * 
	 * @return currentPlayer (int)
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Set the current player number
	 * 
	 * @param currentPlayer (int)
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Get the number of spaces on the board
	 * 
	 * @return boardSpaces (int)
	 */
	public int getBoardSpaces() {
		return boardSpaces;
	}

	/**
	 * Set the number of spaces on the board
	 * 
	 * @param boardSpaces (int)
	 */
	public void setBoardSpaces(int boardSpaces) {
		this.boardSpaces = boardSpaces;
	}

	/**
	 * Get the position of the current player on the board
	 * 
	 * @return currentPlayerSpace (int)
	 */
	public int getCurrentPlayerSpace() {
		return currentPlayerSpace;
	}

	/**
	 * Set the players position on the board
	 * 
	 * @param currentPlayerSpace (int)
	 */
	public void setCurrentPlayerSpace(int currentPlayerSpace) {
		this.currentPlayerSpace = currentPlayerSpace;
	}

	// Methods
	/**
	 * 
	 * Method to roll the dice and move the player check if doubles have been
	 * rolled. Rolling doubles gives the current player another go. However rolling
	 * doubles three times in a row results in a fine for the player.
	 * 
	 * @throws Exception
	 */
	public void rollDice() throws Exception {

		int dice1 = diceRoll.diceRoll();
		int dice2 = diceRoll.diceRoll();
		int moveAmount = dice1 + dice2;
		boolean playerHasInvestment = false;

		// if current player owns any, call listOwned();
		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				if (((InvestSpace) s).getPlayerOwner() == getCurrentPlayer()) {
					playerHasInvestment = true;
				}
			}
		}

		MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(), getCurrentPlayer(),
				gameSetup.players.get(getCurrentPlayer()).getName(),
				gameSetup.players.get(getCurrentPlayer()).getplayerGB());
		if (playerHasInvestment) {
			System.out.println();
			listOwned();
		}
		System.out.print("You are on " + gameSetup.board.getSpaces().get(currentPlayerSpace).getName() + ". ");

		System.out.println("You rolled a " + dice1 + " and a " + dice2 + ": moving you " + moveAmount + " spaces.\n");

		if (dice1 == dice2 && gameSetup.game.getDoublesCounter() != 2) {
			System.out.println("Because you rolled doubles, you get another turn after this one!");
			System.out.println();
			gameSetup.game.setDoublesCounter(true);

			movePlayer(moveAmount);
		} else if (dice1 == dice2 && gameSetup.game.getDoublesCounter() == 2) {
			System.out.println("Three doubles in a row! Well the good news is wealthy capitalists don't go to jail, they just get a slap on the wrist!");
			System.out.printf("You've been fined £%,.0f\n", TAX);
			if (Bank.checkFunds(this.currentPlayer, TAX)) {
				Bank.subtract(this.currentPlayer, TAX);
				gameSetup.game.setDoublesCounter(false);
				movePlayer(moveAmount);
			} else {
				// NEEDS A METHOD TO TERMINATE THE GAME!!!!!!!!!!!!!!!!!!!!!!!!
				System.out.println("YOU IS BROKE!!! GAME OVER");
				gameSetup.game.setGameInPlay(false);
			}

		} else {
			gameSetup.game.setDoublesCounter(false);
			movePlayer(moveAmount);
		}

	}

	/**
	 * Method to move the player the amount of spaces required by passing an amount.
	 * Check if the player has passed the Greta space and work out the new space
	 * that the player has landed on.
	 * 
	 * @param moveAmount (int)
	 */
	public void movePlayer(int moveAmount) {
		int lapBoardBy;
		String spaceField = "";
		if ((this.currentPlayerSpace + moveAmount) < boardSpaces) {
			this.currentPlayerSpace += moveAmount;
			gameSetup.players.get(this.currentPlayer).setPositionInBoard(this.getCurrentPlayerSpace());
			if (gameSetup.board.getSpaces().get(getCurrentPlayerSpace()) instanceof InvestSpace) {
				spaceField = "- "+ ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getSpaceField();
			}
			System.out.println("You landed on " + gameSetup.board.getSpaces().get(currentPlayerSpace).getName() + "! "
					+ spaceField + "\n");
			landedInvestSpace();
		} else {
			lapBoardBy = (this.currentPlayerSpace + moveAmount) - (boardSpaces);
			this.currentPlayerSpace = lapBoardBy;

			System.out.println("You landed on " + gameSetup.board.getSpaces().get(currentPlayerSpace).getName() + "! "
					+ spaceField + "\n");

			if (this.currentPlayerSpace == 0) {
				System.out.printf("You get £%,.0f\n", Greta.getGretaAmount());
				((Greta) gameSetup.board.getSpaces().get(0)).addInvestment(this.currentPlayer);

				System.out.printf("New Balance: £%,.0f\n\n", gameSetup.players.get(currentPlayer).getplayerGB());
			} else {
				System.out.printf("Because you passed Greta, you get £%,.0f\n", Greta.getGretaAmount());
				((Greta) gameSetup.board.getSpaces().get(0)).addInvestment(this.currentPlayer);
				System.out.printf("New Balance: £%,.0f\n\n", gameSetup.players.get(currentPlayer).getplayerGB());
			}

			gameSetup.players.get(this.currentPlayer).setPositionInBoard(this.getCurrentPlayerSpace());
			landedInvestSpace();

		}
	}

	/**
	 * Tells you what space you landed on (name) checks if investment is owned? if
	 * true - calls pay fee else if not owned calls menu to give option to buy
	 */

	public void landedInvestSpace() {

		// Check if player has landed on Tesla or Greta and ignore
		if (gameSetup.board.getSpaces().get(currentPlayerSpace).getName() == "Tesla Charging Station"|| gameSetup.board.getSpaces().get(currentPlayerSpace).getName() == "Greta Points") {
			menuList.set(0, 0);
			viewsMenu();
		} else {

			if (((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).isOwned() && ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPlayerOwner() == currentPlayer) {

				System.out.println("You already own this investment");
				System.out.println("Would you like to do anything else?");
				menuList.set(0, 0);
				viewsMenu();

			} else if (((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).isOwned() && ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPlayerOwner() != currentPlayer) {

				System.out.println((gameSetup.players.get(((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPlayerOwner()).getName()) + " owns this space\n\n");

				// call paysLicenceFee and pass the amount of rent to be paid
				paysLicenceFee(((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getRent());

			} else if (!((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).isOwned()) {

				System.out.printf("%s is not owned. It costs £%,.0f\n\n",((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getName(),((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPrice());
				if (Bank.checkFunds(getCurrentPlayer(),((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPrice())) {
					
					//ask to buy the square here
					
					menuList.set(0, 1);
					viewsMenu();
				} else {
					
					
					
					menuList.set(0, 0);
					viewsMenu();
				}
			}
		}

	}

	/**
	 * Purchase investment method: Get the the space landed on get the price Get the
	 * player balance New balance = balance price add the player as player owner of
	 * that space Print out "you now own array list of players spaces" and player
	 * balance
	 */

	public void purchaseStartup() {

		// need an temporary variable to store the price and pass into Bank
		double propertyPrice;

		// check player wants to purchase
		System.out.println("Are you sure you want to purchase "
				+ ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getName() + "?");

		// call scanner and validation
		String uInput = UserInputs.userInputValidation();

		if (uInput.equalsIgnoreCase("Y")) {
			propertyPrice = ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPrice();

			// calculate the new balance
			Bank.subtract(currentPlayer, propertyPrice);
			MessagePrinter.pushScreenContent();
			MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
					getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
					gameSetup.players.get(getCurrentPlayer()).getplayerGB());
			// print out new balance and array list of players owned spaces
			System.out.printf("New Balance: £%,.0f\n\n", gameSetup.players.get(getCurrentPlayer()).getplayerGB());

			// add the start up to the players array list of Investment
			((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).setPlayerOwner(currentPlayer);

			// display all that players' Investment
			listOwned();
			// add line for spacing
			System.out.println();
			menuList.set(0, 0);
			System.out.println("What else would you like to do?");
			viewsMenu();

			// if player selects N return to the menu
		} else if (uInput.equalsIgnoreCase("N")) {
			MessagePrinter.pushScreenContent();
			MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
					getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
					gameSetup.players.get(getCurrentPlayer()).getplayerGB());
			viewsMenu();

		}
	}

	/**
	 * method will provide a list of owned Investment for the player whose turn it
	 * is and print this to screen when called for
	 */

	public void listOwned() {
		System.out.println("You own the following Investment: ");
		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				if (((InvestSpace) s).getPlayerOwner() == getCurrentPlayer()) {

					System.out.println("- " + s.getName());
				}
			}
		}
		System.out.println();
	}

	/**
	 * 
	 * This method lists all spaces owned which can be developed.
	 * 
	 */
	public void listOwnedAndCanDevelop() {
		checkIfPlayerCanDevelop(getCurrentPlayer());
		ArrayList<Integer> startupIndex = new ArrayList<Integer>();
		int menuNumbers = 1;
		MessagePrinter.pushScreenContent();
		MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(), getCurrentPlayer(),
				gameSetup.players.get(getCurrentPlayer()).getName(),
				gameSetup.players.get(getCurrentPlayer()).getplayerGB());
		System.out.println("Select a investment to develop: \n");
		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				if (((InvestSpace) s).getPlayerOwner() == getCurrentPlayer()
						&& ((InvestSpace) s).getCanBeDeveloped() == true && (((InvestSpace) s).getBattery() < 4)
						&& (((InvestSpace) s).getPriceOfBattery() <= gameSetup.players.get(getCurrentPlayer())
								.getplayerGB())) {

					int startupPosition = gameSetup.spaces.indexOf(s);
					startupIndex.add(startupPosition);

					System.out.print(menuNumbers + ". ");
					MessagePrinter.printInvestmentWithBatterys(s.getName(), ((InvestSpace) s).getPriceOfBattery(),
							((InvestSpace) s).getSpaceField(), ((InvestSpace) s).getBattery());
//					System.out.printf(menuNumbers + ". " + s.getName() + " - " + ((InvestSpace) s).getSpaceField() + "%2s (Current batterys:" + ((InvestSpace) s).getBattery() + "/4)", " ");
					System.out.println();
					menuNumbers++;
				}
			}
		}

		int userInput = UserInputs.userInputMenu(menuNumbers - 1);

		System.out.println("Are you sure you would like to develop: "
				+ gameSetup.board.getSpaces().get(startupIndex.get(userInput - 1)).getName());
		String userChoice = UserInputs.userInputValidation();

		if (userChoice.equalsIgnoreCase("Y")) {

			// are you sure you want?
			double fieldCost = ((InvestSpace) gameSetup.board.getSpaces().get(startupIndex.get(userInput - 1)))
					.getPriceOfBattery();
			MessagePrinter.pushScreenContent();
			MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
					getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
					gameSetup.players.get(getCurrentPlayer()).getplayerGB());
			buyBattery(startupIndex.get(userInput - 1), getCurrentPlayer(), fieldCost);
			// add line for spacing
			System.out.println();
			System.out.println("What else would you like to do?");
			menuList.set(1, 0);
			viewsMenu();
		} else {
			MessagePrinter.pushScreenContent();
			MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
					getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
					gameSetup.players.get(getCurrentPlayer()).getplayerGB());
			listOwned();
			viewsMenu();
		}
	}

	/**
	 * 
	 * Method that checks whether a player can develop any properties. If the player
	 * can, the Space's canBeDeveloped is set to true. Otherwise it remains as
	 * false.
	 * 
	 * @param playerOwner
	 * @return
	 *
	 */
	public boolean checkIfPlayerCanDevelop(int playerOwner) {
		boolean canDevelop = false;

		// Populate Map with fields (can't be duplicate, nice!) with available fields.
		Map<String, Integer> uniqueFields = new HashMap<>();

		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				if (((InvestSpace) s).getPlayerOwner() == getCurrentPlayer()) {
					if (Bank.checkFunds(getCurrentPlayer(), ((InvestSpace) s).getPriceOfBattery())) {

					}
				}
			}
		}

		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				uniqueFields.put(((InvestSpace) s).getSpaceField(), ((InvestSpace) s).getFieldSetRequired());
			}
		}

		// Keep track of how many of a particular set are owned
		int requiredCounter = 0;

		// Loop through every unique field entry
		for (Map.Entry<String, Integer> entry : uniqueFields.entrySet()) {

			// loop through InvestSpaces that match a field
			for (Space s : gameSetup.spaces) {
				if (s instanceof InvestSpace) {
					// if they are owned by the same player
					if ((((InvestSpace) s).getSpaceField().equals(entry.getKey()))
							&& (((InvestSpace) s).getPlayerOwner() == playerOwner) && ((InvestSpace) s)
									.getPriceOfBattery() <= gameSetup.players.get(currentPlayer).getplayerGB()) {
						requiredCounter++;
					}
				}
			}

			// if the counter found the same amount of owned properties as is needed
			if (requiredCounter == entry.getValue()) {
				for (Space s : gameSetup.spaces) {
					if (s instanceof InvestSpace) {
						if (((InvestSpace) s).getSpaceField().equals(entry.getKey())) {
							((InvestSpace) s).setCanBeDeveloped(true);
						}
					}

				}
				canDevelop = true;
			} else {
				for (Space s : gameSetup.spaces) {
					if (s instanceof InvestSpace) {
						if (((InvestSpace) s).getSpaceField().equals(entry.getKey())) {
							((InvestSpace) s).setCanBeDeveloped(false);
						}
					}
				}
			}
			requiredCounter = 0;
		}

		// loop through all owned properties and if all of them have battery < 4 not
		// true,
		boolean foundone = false;
		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				// if you don't find one, set found none to false
				if (((InvestSpace) s).getPlayerOwner() == playerOwner && ((InvestSpace) s).getBattery() < 4
						&& ((InvestSpace) s).getCanBeDeveloped() == true) {
					foundone = true;
				}
			}
		}

		if (!foundone) {
			canDevelop = false;
			menuList.set(1, 0);
		}

		return canDevelop;
	}

	/**
	 * 
	 * method to check if battery can be bought in a space
	 * 
	 */
	public void buyBattery(int startUpPosition, int playerNumber, double fieldCost) {

		int batteryOnSpace = ((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).getBattery();
		String spaceName = gameSetup.board.getSpaces().get(startUpPosition).getName();
		switch (batteryOnSpace) {

		case 0:
			((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).increaseBattery();
			batteryOnSpace = ((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).getBattery();
			System.out.println(
					"You have bought a battery. You now have " + batteryOnSpace + " battery.(" + spaceName + ")");
			Bank.subtract(playerNumber, fieldCost);
			System.out.printf("£%,.0f has been deducted from your account\n", fieldCost);
			System.out.printf("New Balance: £%,.0f", gameSetup.players.get(currentPlayer).getplayerGB());
			menuList.set(1, 0);
			break;
		case 1:
			((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).increaseBattery();
			batteryOnSpace = ((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).getBattery();
			System.out.println(
					"You have bought a battery. You now have " + batteryOnSpace + " batterys.(" + spaceName + ")");
			Bank.subtract(playerNumber, fieldCost);
			System.out.printf("£%,.0f has been deducted from your account\n", fieldCost);
			System.out.printf("New Balance: £%,.0f", gameSetup.players.get(currentPlayer).getplayerGB());
			menuList.set(1, 0);
			break;
		case 2:
			((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).increaseBattery();
			batteryOnSpace = ((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).getBattery();
			System.out.println(
					"You have bought a battery. You now have " + batteryOnSpace + " batterys.(" + spaceName + ")");
			Bank.subtract(playerNumber, fieldCost);
			System.out.printf("£%,.0f has been deducted from your account\n", fieldCost);
			System.out.printf("New Balance: £%,.0f", gameSetup.players.get(currentPlayer).getplayerGB());
			menuList.set(1, 0);
			break;
		case 3:
			((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).increaseBattery();
			batteryOnSpace = ((InvestSpace) gameSetup.board.getSpaces().get(startUpPosition)).getBattery();
			System.out.println("You have bought an accumulator. You now have the maximum number of batterys("+ batteryOnSpace + ").(" + spaceName + ")");
			Bank.subtract(playerNumber, fieldCost);
			System.out.printf("£%,.0f has been deducted from your account\n", fieldCost);
			System.out.printf("New Balance: £%,.0f", gameSetup.players.get(currentPlayer).getplayerGB());
			menuList.set(1, 0);
			break;

		default:
			System.out.println("You already have the maximum number of batterys");

		}

	}

	/**
	 * Method to display the menu to the player. It should only ever show options
	 * available.
	 */
	public void viewsMenu() {

		checkForTakeOver();
		if (checkIfPlayerCanDevelop(currentPlayer) && Bank.canAffordToHire(getCurrentPlayer())) {
			menuList.set(1, 1);
		} else {
			menuList.set(1, 0);
		}

		System.out.println("Please select one of the following options. ");

		if ((menuList.get(0) == 1) && (menuList.get(1) == 1) && (menuList.get(2) == 1)) {
			MessagePrinter.printMenuTitle();
			System.out.printf("\n 1. " + MenuOptions.PURCHASE.getMenuOptions() + "\n 2. "
					+ MenuOptions.BATTERY.getMenuOptions() + "\n 3. " + MenuOptions.TAKEOVER.getMenuOptions() + "\n 4. "
					+ MenuOptions.END.getMenuOptions() + "\n 5. " + MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(5);

			switch (returnedInput) {

			case 1:
				purchaseStartup();
				break;
			case 2:
				listOwnedAndCanDevelop();
				break;
			case 3:
				takesOverInvestment();
				break;
			case 4:
				endTurn();

			case 5:
				terminatesGame();
				break;
			}

		} else if ((menuList.get(0) == 1) && (menuList.get(1) == 1) && (menuList.get(2) == 0)) {
			MessagePrinter.printMenuTitle();
			System.out.printf("\n 1. " + MenuOptions.PURCHASE.getMenuOptions() + "\n 2. "
					+ MenuOptions.BATTERY.getMenuOptions() + "\n 3. " + MenuOptions.END.getMenuOptions() + "\n 4. "
					+ MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(4);

			switch (returnedInput) {

			case 1:
				purchaseStartup();
				break;
			case 2:
				listOwnedAndCanDevelop();
				break;
			case 3:
				endTurn();
				break;
			case 4:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 1) && (menuList.get(1) == 0) && (menuList.get(2) == 1)) {
			MessagePrinter.printMenuTitle();

			System.out.printf("\n 1. " + MenuOptions.PURCHASE.getMenuOptions() + "\n 2. "
					+ MenuOptions.TAKEOVER.getMenuOptions() + "\n 3. " + MenuOptions.END.getMenuOptions() + "\n 4. "
					+ MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(4);

			switch (returnedInput) {

			case 1:
				purchaseStartup();
				break;
			case 2:
				takesOverInvestment();
				break;
			case 3:
				endTurn();
				break;
			case 4:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 1) && (menuList.get(1) == 0) && (menuList.get(2) == 0)) {
			MessagePrinter.printMenuTitle();

			System.out.printf("\n 1. " + MenuOptions.PURCHASE.getMenuOptions() + "\n 2. "
					+ MenuOptions.END.getMenuOptions() + "\n 3. " + MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(3);

			switch (returnedInput) {

			case 1:
				purchaseStartup();
				menuList.set(0, 0);
				break;
			case 2:
				endTurn();
				break;
			case 3:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 0) && (menuList.get(1) == 1) && (menuList.get(2) == 1)) {
			MessagePrinter.printMenuTitle();

			System.out.printf("\n 1. " + MenuOptions.BATTERY.getMenuOptions() + "\n 2. "
					+ MenuOptions.TAKEOVER.getMenuOptions() + "\n 3. " + MenuOptions.END.getMenuOptions() + "\n 4. "
					+ MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(4);

			switch (returnedInput) {

			case 1:
				listOwnedAndCanDevelop();
				break;
			case 2:
				takesOverInvestment();
				break;
			case 3:
				endTurn();
				break;
			case 4:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 0) && (menuList.get(1) == 1) && (menuList.get(2) == 0)) {
			MessagePrinter.printMenuTitle();

			System.out.printf("\n 1. " + MenuOptions.BATTERY.getMenuOptions() + "\n 2. "
					+ MenuOptions.END.getMenuOptions() + "\n 3. " + MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(3);

			switch (returnedInput) {

			case 1:
				listOwnedAndCanDevelop();
				break;
			case 2:
				endTurn();
				break;
			case 3:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 0) && (menuList.get(1) == 0) && (menuList.get(2) == 1)) {
			MessagePrinter.printMenuTitle();

			System.out.printf("\n 1. " + MenuOptions.TAKEOVER.getMenuOptions() + "\n 2. "
					+ MenuOptions.END.getMenuOptions() + "\n 3. " + MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(3);

			switch (returnedInput) {

			case 1:
				takesOverInvestment();
				break;
			case 2:
				endTurn();
				break;
			case 3:
				terminatesGame();
				break;
			}
		} else if ((menuList.get(0) == 0) && (menuList.get(1) == 0) && (menuList.get(2) == 0)) {
			MessagePrinter.printMenuTitle();

			System.out.printf(
					"\n 1. " + MenuOptions.END.getMenuOptions() + "\n 2. " + MenuOptions.TERMINATE.getMenuOptions());

			int returnedInput = UserInputs.userInputMenu(2);

			switch (returnedInput) {

			case 1:
				endTurn();
				break;
			case 2:
				terminatesGame();
				break;
			}
		}
	}

	/**
	 * Method to end the current players turn
	 * 
	 */
	public void endTurn() {
		System.out.println("Are you sure you want to end your turn?");
		if (UserInputs.userInputValidation().equalsIgnoreCase("y")) {

			MessagePrinter.pushScreenContent();
		} else {
			viewsMenu();
		}

	}

	/**
	 * Method to end the game and call the declare Winner method
	 * 
	 */
	public void terminatesGame() {
		System.out.println("Are you sure you want to end the game?");
		System.out.println("Your score will be ignored and you cannot win the game! ");
		if (UserInputs.userInputValidation().equalsIgnoreCase("y")) {
			System.out.println("\nCome back soon, Greta needs you!");
			gameSetup.game.setGameInPlay(false);
			declareWinner();
		} else {
			viewsMenu();
		}
	}

	/**
	 * Lists all the available investment spaces for take over.
	 * 
	 */
	public void takesOverInvestment() {

		ArrayList<Integer> availableInvestment = new ArrayList<Integer>();

		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {

				if (((InvestSpace) s).isOwned() && !(((InvestSpace) s).getPlayerOwner() == getCurrentPlayer())
						&& ((InvestSpace) s).getPrice() <= gameSetup.players.get(currentPlayer).getplayerGB()) {
					availableInvestment.add(gameSetup.spaces.indexOf(s));
				}
			}
		}
		MessagePrinter.pushScreenContent();
		MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(), getCurrentPlayer(),
				gameSetup.players.get(getCurrentPlayer()).getName(),
				gameSetup.players.get(getCurrentPlayer()).getplayerGB());
		System.out.println("Takeover: Select one of the following Investment:\n");

		// loop through the available takeover Investment
		int list = 1;
		for (Integer index : availableInvestment) {
			String spaceName = gameSetup.spaces.get(index).getName();
			int startupOwnerIndex = ((InvestSpace) gameSetup.spaces.get(index)).getPlayerOwner();
			String startupOwnerName = gameSetup.players.get(startupOwnerIndex).getName();

			System.out.printf("%d. %s %,.0f (Area: %s - Owner: %s).\n", list, spaceName,
					((InvestSpace) gameSetup.spaces.get(index)).getPrice(),
					((InvestSpace) gameSetup.spaces.get(index)).getSpaceField(), startupOwnerName);
			list++;
		}

		// ask for user input in number
		int userInput = UserInputs.userInputMenu(availableInvestment.size());

		// verify

		// access content in array of given userinput

		int indexAccesor = userInput - 1;

		int indexOfInvestSpace = availableInvestment.get(indexAccesor);

		String propertyName = gameSetup.board.getSpaces().get(indexOfInvestSpace).getName();

		System.out.println("Are you sure you would like to takeover: " + propertyName + "?");
		String userChoice = UserInputs.userInputValidation();

		if (userChoice.equalsIgnoreCase("y")) {

			int startupOwnerIndex = ((InvestSpace) gameSetup.spaces.get(indexOfInvestSpace)).getPlayerOwner();
			String startupOwnerName = gameSetup.players.get(startupOwnerIndex).getName();
			double startupPrice = ((InvestSpace) gameSetup.spaces.get(indexOfInvestSpace)).getPrice();

			// the owner of investment is sent a message to confirm he will allow the
			// takeover
			System.out.println(
					"TAKEOVER! " + startupOwnerName + ", someone is attempting to take over " + propertyName + "!");
			System.out.printf("If you accept the deal, you would gain £%,.0f\n", startupPrice);
			System.out.printf("Do you accept the offer? - ");

			// owner response
			String response = UserInputs.userInputValidation();

			// if true, set owner to current player.
			if (response.equalsIgnoreCase("Y")) {

				((InvestSpace) gameSetup.spaces.get(indexOfInvestSpace)).setPlayerOwner(getCurrentPlayer());
				// update balances
				Bank.subtract(getCurrentPlayer(), startupPrice);
				Bank.add(startupOwnerIndex, startupPrice);
				MessagePrinter.pushScreenContent();
				MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
						getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
						gameSetup.players.get(getCurrentPlayer()).getplayerGB());

				System.out.printf("%s, your new balance is: £%,.0f\n",
						gameSetup.players.get(getCurrentPlayer()).getName(),
						gameSetup.players.get(getCurrentPlayer()).getplayerGB());
				System.out.println();

				System.out.printf("%s, your new balance is: £%,.0f\n",
						gameSetup.players.get(startupOwnerIndex).getName(),
						gameSetup.players.get(startupOwnerIndex).getplayerGB());
				System.out.println();
				listOwned();

			} else {
				System.out.println(startupOwnerName + " decided to not proceed. Your take over was rejected.");
			}
			viewsMenu();
		} else {
			boolean playerHasInvestment = false;

			// if current player owns any, call listOwned();
			for (Space s : gameSetup.spaces) {
				if (s instanceof InvestSpace) {
					if (((InvestSpace) s).getPlayerOwner() == getCurrentPlayer()) {
						playerHasInvestment = true;
					}
				}
			}
			MessagePrinter.pushScreenContent();
			MessagePrinter.printName(gameSetup.board.getSpaces().get(getCurrentPlayerSpace()).getName(),
					getCurrentPlayer(), gameSetup.players.get(getCurrentPlayer()).getName(),
					gameSetup.players.get(getCurrentPlayer()).getplayerGB());
			if (playerHasInvestment) {
				listOwned();
			}
			viewsMenu();
		}

	}

	/**
	 * Check if any Investment are available for 'takeover'. investment cannot be
	 * owned by the current player.
	 * 
	 * @return boolean
	 */
	public void checkForTakeOver() {
		menuList.set(2, 0);
		for (Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				// if investment is owned && investment owner is not the current player
				if (((InvestSpace) s).isOwned() && !(((InvestSpace) s).getPlayerOwner() == getCurrentPlayer())
						&& ((InvestSpace) s).getPrice() <= gameSetup.players.get(currentPlayer).getplayerGB()) {
					menuList.set(2, 1);
				}

			}
		}
	}

	/**
	 * 
	 * Method to subtract the fee from currentPlayer add to playerOwner
	 *
	 * @param rent
	 */
	public void paysLicenceFee(double rent) {

		double licenceFee = rent;
		int currentPlayer = getCurrentPlayer();
		int playerOwner = ((InvestSpace) gameSetup.board.getSpaces().get(getCurrentPlayerSpace())).getPlayerOwner();

		// Check if current player has the balance to pay licence fee

		if (Bank.checkFunds(currentPlayer, licenceFee)) {

			System.out.printf("The licence fee £%,.0f has been debited from your account.\n\n", licenceFee);
			Bank.subtract(this.currentPlayer, licenceFee);

			Bank.add(playerOwner, licenceFee);
			System.out.printf("Current Balance: £%,.0f\n\n", gameSetup.players.get(getCurrentPlayer()).getplayerGB());

			menuList.set(0, 0);
			System.out.println("Would you like to do anything else?");
			viewsMenu();
		} else {
			System.out.println("You have insufficient funds to continue playing.  You've been declared bankrupt!");
			gameSetup.game.setGameInPlay(false);
			declareWinner();
		}

	}

	/**
	 * 
	 * Method to declare a winner. Winner is the player with the biggest cash
	 * balance + total property value. The player that terminates the game cannot be
	 * declared the winner
	 * 
	 */
	public void declareWinner() {
		ArrayList<Double> playersVal = new ArrayList<Double>();
		double playerBal;
		double playerPropVal = 0;
		double totalPlayerValue;

		for (int outter = 0; outter < gameSetup.players.size(); outter++) {
			playerBal = gameSetup.players.get(outter).getplayerGB();
			for (int inner = 0; inner < gameSetup.board.getSpaces().size(); inner++) {
				// if a investment has a non-bank owner
				if (gameSetup.spaces.get(inner) instanceof InvestSpace
						&& ((InvestSpace) gameSetup.spaces.get(inner)).getPlayerOwner() != -1) {
					// calculate value of all Investment owned by players
					if (((InvestSpace) gameSetup.board.getSpaces().get(inner)).getPlayerOwner() == outter) {
						playerPropVal += ((InvestSpace) gameSetup.board.getSpaces().get(inner)).getPrice();
					}

				}

			}
			totalPlayerValue = playerBal + playerPropVal;
			playersVal.add(outter, totalPlayerValue);
			gameSetup.players.get(outter).setplayerNet(totalPlayerValue);
			playerPropVal = 0;
		}

		Collections.sort(playersVal);
		Collections.reverse(playersVal);

		System.out.println("\n______________Winner/s______________");

		// Declare winner
		if (playersVal.get(0) != gameSetup.players.get(currentPlayer).getplayerNet()) {
			for (int loop = 0; loop < gameSetup.players.size(); loop++) {
				if (playersVal.get(0) == gameSetup.players.get(loop).getplayerNet()
						&& gameSetup.players.get(loop) != gameSetup.players.get(currentPlayer)) {
					System.out.printf("%s with a total worth of £%,.0f\n", gameSetup.players.get(loop).getName(),
							playersVal.get(0));
				}
			}

		} else {
			for (int loop = 0; loop < gameSetup.players.size(); loop++) {
				if (playersVal.get(1) == gameSetup.players.get(loop).getplayerNet()
						&& gameSetup.players.get(loop) != gameSetup.players.get(currentPlayer)) {
					System.out.printf("%s with a total worth of £%,.0f\n", gameSetup.players.get(loop).getName(),
							playersVal.get(1));
				}
			}
		}
	}

}
