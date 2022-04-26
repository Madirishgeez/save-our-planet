/**
 * 
 */
package G2.savetheplanet;

import java.util.ArrayList;

/**
 * @author Matt
 *
 */
public class gameSetup {

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<String> playerNames = new ArrayList<String>();

	protected static ArrayList<Space> spaces = new ArrayList<Space>();

	protected static Board board = new Board(spaces);

	public static GameEngine game = new GameEngine();

	public static UserInputs UserInputs = new UserInputs();

	public static void main(String[] args){
		board.populateBoard();

		welcome();

	}

	/**
	 * 
	 * show welcome message and the start menu
	 * 
	 */
	public static void welcome() {
		
		/**
		 * Prints text art globe
		 * 
		 */
//		public static void printGlobe() {
			System.out.println("             ___.....___");
			System.out.println("       ,..-.=--.-.       ''.");
			System.out.println("     .{_..        `        ,. .");
			System.out.println("   '//       .           |      \\");
			System.out.println("  /        :   ;'          `____> \\");
			System.out.println(" :         `. (           /       :");
			System.out.println(" |           `>\\_         \\      r|");
			System.out.println("             /   \\         `._   \\");
			System.out.println(" |          |      `          ;   |");
			System.out.println("  :          \\     /          `   ;");
			System.out.println("   \\          \\.  '            ` /");
			System.out.println("     `.        | /             .'");
			System.out.println("        `      `/          . '");
			System.out.println("           `---'.....---''");
//		}


		System.out.println("Welcome to Save our Planet!\n");

		int menu = startMenu();
		if (menu == 1) {

			System.out.println("How many players will be playing?:");

			promptNamesOfPlayers(UserInputs.userInputPlayers());

			startGame();

		} else if (menu == 2) {

//			printGameRules();     get this added later
			System.out.println("\n\n\n\n\n");
			try {
				readyToStart();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 * start game or view rules options
	 * 
	 * @return (int)
	 * 
	 */
	public static int startMenu() {
		@SuppressWarnings("unused")
		int promptUserMenu;
		System.out.println("1: Start Game");
		System.out.println("     -OR-");
		System.out.println("2: View Rules");
		return promptUserMenu = UserInputs.userInputMenu(2);

	}

	/**
	 * start or end game
	 * 
	 * @throws Exception
	 */
	public static void readyToStart() throws Exception {
		System.out.println("         Ready to play?");

		String ready = UserInputs.userInputValidation();
		if (ready.equalsIgnoreCase("y")) {
			System.out.println("How many players will be playing?:");
			promptNamesOfPlayers(UserInputs.userInputPlayers());
			startGame();
		} else {
			System.out.println("Goodbye!");
		}
	}

	/**
	 * Method to initialise the GameEngine object if that name is ok
	 * 
	 */
	public static void startGame() {

		game = new GameEngine(spaces.size(), players.size());

		try {
			game.gameManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to prompt the user for the names of the players
	 * 
	 * @param number (int)
	 */
	public static void promptNamesOfPlayers(int number) {

		ArrayList<String> playerNames = new ArrayList<String>();

		int players = number;
		int counter = 1;
		do {
			System.out.println("Please enter a name for player " + counter);
			String givenname = UserInputs.userInputNames();

			if (playerNames.contains(givenname.toLowerCase())) {
				System.out.println("\nWoops! That name already exists. Please enter a unique name.");
			} else {
				playerNames.add(givenname.toLowerCase());
				gameSetup.players.add(new Player(givenname, 0, 150000));
				players--;
				counter++;
			}

		} while (players > 0);

		for (Player p : gameSetup.players) {
			System.out.println(p.getName());
		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}



}
