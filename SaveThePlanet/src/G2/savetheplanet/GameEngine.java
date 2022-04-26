/**
 * 
 */
package G2.savetheplanet;


/**
 * @author Matt
 *
 */
public class GameEngine {



	//Instance vars
	private boolean gameInPlay;
	private int currentPlayer;
	private int previousPlayer;
	private int playerSuccessiveTurnCounter;
	private int spacesOnBoard;
	private int turnCount;
	private int roundCount;
	private boolean roundCountMessage = true;
	private int numOfPlayers;
	private int doublesCounter = 0;
	private TurnEngine turn;
	private int playerWhoLost;
	
	//Constructors
	/**
	 * Empty Constructor
	 */
	public GameEngine() {
		this.roundCount =1;
	}
	
	/**
	 * Constructor with args for making custom games
	 * @param gameInPlay
	 * @param currentPlayer
	 * @param nextPlayer
	 * @param playerSuccessiveTurnCounter
	 * @param spacesOnBoard
	 */
	public GameEngine(boolean gameInPlay, int currentPlayer, int nextPlayer, int playerSuccessiveTurnCounter,
			int spacesOnBoard, int turnCount, int numOfPlayers) {
		this.gameInPlay = gameInPlay;
		this.currentPlayer = currentPlayer;
		this.playerSuccessiveTurnCounter = playerSuccessiveTurnCounter;
		this.spacesOnBoard = spacesOnBoard;
		this.turnCount = turnCount;
		this.numOfPlayers = numOfPlayers;
		this.roundCount =1;
	}

	/**
	 * Default Constructor with args used for starting the default game
	 * @param spacesOnBoard
	 * @param numOfPlayers
	 */
	public GameEngine(int spacesOnBoard, int numOfPlayers) {
		this.gameInPlay = true;
		this.currentPlayer = 0;
		this.playerSuccessiveTurnCounter = 0;
		this.spacesOnBoard = spacesOnBoard;
		this.turnCount =0;
		this.numOfPlayers = numOfPlayers;
		this.roundCount =1;
	}

	//Getters & Setters
	/**
	 * Get true/false whether the game is in play or not
	 * @return gameInPlay (boolean)
	 */
	public boolean isGameInPlay() {
		return gameInPlay;
	}

	/**
	 * Set whether the game is in play
	 * @param gameInPlay (boolean)
	 */
	public void setGameInPlay(boolean gameInPlay) {
		this.gameInPlay = gameInPlay;
	}

	/**
	 * Get the number of the current player
	 * @return currentPlayer (int)
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Set the number of the current player
	 * @param currentPlayer (int)
	 */
	public void setCurrentPlayer(int currentPlayer) {
		if(currentPlayer > (this.numOfPlayers-1)) {
			this.currentPlayer = 0;
		}else {
			this.currentPlayer = currentPlayer;
		}
	}
	
	/**
	 * Get the number of the player who lost
	 * @return playerWhoLost (int)
	 */
	public int getPlayerWhoLost() {
		return playerWhoLost;
	}

	/**
	 * Set the number of the player who lost
	 * @param playerWhoLost (int) 
	 */
	public void setPlayerWhoLost(int playerWhoLost) {
		this.playerWhoLost = playerWhoLost;
	}
	
	/**
	 * Get the space the current player is on
	 * @return currentPlayerSpace (int)
	 */
	public int getCurrentPlayerSpace() {
		return gameSetup.players.get(currentPlayer).getPositionInBoard();
	}

	/**
	 * Set the space number of the current player
	 * @param currentPlayerSpace (int)
	 */
	public void setCurrentPlayerSpace(int currentPlayerSpace) {
		gameSetup.players.get(currentPlayer).setPositionInBoard(currentPlayerSpace);
	}
	

	/**
	 * Get the number of back to back turns a player has taken
	 * @return playerSuccessiveTurnCounter (int)
	 */
	public int getPlayerSuccessiveTurnCounter() {
		return playerSuccessiveTurnCounter;
	}

	/**
	 * Set the number of back to back turns a player has to take
	 * @param playerSuccessiveTurnCounter (int)
	 */
	public void setPlayerSuccessiveTurnCounter(int playerSuccessiveTurnCounter) {
		this.playerSuccessiveTurnCounter = playerSuccessiveTurnCounter;
	}

	/**
	 * Get the number of spaces on the board
	 * @return spacesOnBoard (int)
	 */
	public int getSpacesOnBoard() {
		return spacesOnBoard;
	}

	/**
	 * Set the number of spaces on the board
	 * @param spacesOnBoard (int)
	 */
	public void setSpacesOnBoard(int spacesOnBoard) {
		this.spacesOnBoard = spacesOnBoard;
	}

	/**
	 * Get the number of turns taken in the game
	 * @return turnCount (int)
	 */
	public int getTurnCount() {
		return turnCount;
	}

	/**
	 * Set the current number of turns taken
	 * @param turnCount (int)
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	/**
	 * Get the number of players playing
	 * @return numOfPlayers (int)
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	/**
	 * Set the number of player playing
	 * @param numOfPlayers (int)
	 */
	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	
	/**
	 * Get the number of doubles rolled by this player
	 * @return doublesCounter (int)
	 */
	public int getDoublesCounter() {
		return doublesCounter;
	}
	
	/**
	 * Get the number of the previous player. This can on 
	 * occasion be the same as the current player
	 * @return nextPlayer (int)
	 */
	public int getPreviousPlayer() {
		return previousPlayer;
	}

	/**
	 * Set the number of the previous player
	 * @param nextPlayer (int)
	 */
	public void setPreviousPlayer(int previousPlayer) {
		this.previousPlayer = previousPlayer;
	}
	
	/**
	 * Get the game round
	 * @return roundCount(int)
	 */
	public int getRoundCount() {
		return roundCount;
	}
	
	/**
	 * Set the game round
	 * @return roundCount(int)
	 */
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	
	/**
	 * Set the game round
	 * @return roundCount(int)
	 */
	public void setRoundCountMessage(boolean message) {
		this.roundCountMessage = message;
	}
	

	/**
	 * Increase the doubles roll counter by 1 if doubles rolled
	 * @param doubleRolled (boolean)
	 */
	public void setDoublesCounter(boolean doubleRolled) {
		if(doubleRolled) {
			this.doublesCounter += 1;
		}else {
			this.doublesCounter = 0;
		}
		
	}
	
	/**
	 * Increases the round counter for every time play returns to the first player
	 */
	public void increaseRoundCount() {
		if(this.previousPlayer == gameSetup.players.size()-1 && this.currentPlayer == gameSetup.players.get(0).getPlayerNumber()) {
			roundCount++;
			this.setRoundCountMessage(true);
		}
	}
	
	/**
	 * 
	 * keep count of how many turns have happened, and keep calling the TurnEngine 
	 * while the gameInPlay boolean is set to true.
	 * @throws Exception
	 */
	public void gameManager() throws Exception {
		do {
			if(this.roundCountMessage) {
				System.out.println("Round: " + getRoundCount());
				this.setRoundCountMessage(false);
			}
			
			System.out.printf("It's " + gameSetup.players.get(this.currentPlayer).getName() + "'s turn.\n");
			turn = new TurnEngine(getCurrentPlayer(), getSpacesOnBoard(), getCurrentPlayerSpace());
			turn.rollDice();
			
			if(this.doublesCounter>0 && this.gameInPlay == true) {
				System.out.println("IN CASE YOU FORGOT, YOU GET ANOTHER TURN!!!!!\n");
				setPreviousPlayer(this.currentPlayer -1);
			}else {
				setPreviousPlayer(this.currentPlayer);
				setCurrentPlayer(this.currentPlayer + 1);
			}
		
			increaseRoundCount();
			this.turnCount++;
			
			

		}while(this.gameInPlay);
		
		
	}
	

}
