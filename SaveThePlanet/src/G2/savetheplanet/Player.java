/**
 * 
 */
package G2.savetheplanet;

/**
 * @author Matt
 *
 */
public class Player {

	/**
	 * not sure if we need these as constants but we can so i did
	 */
	
	private final int minBoardPosition = 0;
	private final int maxBoardPosition = 11;
//	private final int minplayerNumber = 1;
//	private final int maxplayerNumber = 4;

	/**
	 * Declaration of all instance variables
	 */
	
	private int playerNumber;
	private String name;
	private int positionInBoard;
	private double playerGB;
	private double playerNet;

	
	public Player() {

	}

	/**
	 * no players constructor
	 */
	public Player(String name, int positionInBoard, double playerGB) {
		this.setName(name);
		this.setPositionInBoard(positionInBoard);
		this.setplayerGB(playerGB);

	}

	/**
	 * Player number
	 * @return player number between 2 and 4 inclusive
	 */

	public int getPlayerNumber() {
		return playerNumber;
	}

	

	/**
	 * getter for player name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for player name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for player worth
	 * 
	 * @return
	 */
	public double getplayerNet() {
		return playerNet;
	}

	/**
	 * setter for player name
	 * 
	 * @param name
	 */
	public void setplayerNet(double playerNet) {
		this.playerNet = playerNet;
	}

	/**
	 * getter for position on board
	 * 
	 * @return
	 */
	public int getPositionInBoard() {
		return positionInBoard;
	}

	/**
	 * setter for position on board
	 * int greater than or = 1 and less than or = to 12
	 * @param positionInBoard     
	 */
	public void setPositionInBoard(int positionInBoard) throws IllegalArgumentException {

		if ((positionInBoard >= minBoardPosition) && (positionInBoard <= maxBoardPosition)) {
			this.positionInBoard = positionInBoard;
		} else {
			throw new IllegalArgumentException("ERROR: Invalid position on the board");
		}

	}

	/**
	 * getter for balance
	 * 
	 * @return
	 */
	public double getplayerGB() {
		return playerGB;
	}

	/**
	 * Setter for balance
	 * 
	 * @param playerGB
	 */
	public void setplayerGB(double playerGB) {
		this.playerGB = playerGB;
	}

	/**
	 * display all method will run at start of each players turn and display the
	 * info from this class
	 */
	public void displayAll() {
		System.err.println("***Player " + playerNumber + " - " + name + "***");
		System.out.println("Current Position " + positionInBoard + " Balance: " + playerGB);

	}

	
	

}
