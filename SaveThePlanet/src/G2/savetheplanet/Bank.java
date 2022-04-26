/**
 * 
 */
package G2.savetheplanet;


/**
 * @author Matt
 *
 */
public class Bank {
	
private static double currentBalance, newBalance;
	
	/**
	 * Method to add an amount to the given players balance
	 * @param playerNumber (int)
	 * @param amount (double)
	 */
	public static void add(int playerNumber, double amount) {
		
		currentBalance = gameSetup.players.get(playerNumber).getplayerGB();
		newBalance = currentBalance + amount;
		
		gameSetup.players.get(playerNumber).setplayerGB(newBalance);
	}
	
	/**
	 * Method to subtract an amount from the given playres balance
	 * @param playerNumber (int)
	 * @param amount (double)
	 */
	public static void subtract(int playerNumber, double amount) {
		
		currentBalance = gameSetup.players.get(playerNumber).getplayerGB();
		newBalance = currentBalance - amount;
		
		gameSetup.players.get(playerNumber).setplayerGB(newBalance);
	}
	
	/**
	 * Method to check if the funds available in a given players balance are enough to perform an action
	 * @param playerNumber (int)
	 * @param amount (double)
	 * @return
	 */
	public static boolean checkFunds(int playerNumber, double amount) {
		boolean hasFunds;
		if(gameSetup.players.get(playerNumber).getplayerGB()>=amount) {
			hasFunds = true;
		}else {
			hasFunds=false;
		}
		return hasFunds;
	}
	
	/**
	 * Method to check if a player can afford to purchase battery
	 * @param playerNumber (int)
	 * @return (boolean)
	 */
	public static boolean canAffordToHire(int playerNumber) {
		
		boolean canAfford = false;
		
		for(Space s : gameSetup.spaces) {
			if (s instanceof InvestSpace) {
				if (checkFunds(playerNumber, ((InvestSpace) s).getPriceOfBattery())) {
					canAfford = true;
				}
			}
		}
		
		return canAfford;
		
	}

}
