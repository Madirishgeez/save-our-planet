/**
 * 
 */
package G2.savetheplanet;


/**
 * @author Matt
 *
 */
public class Greta extends Space{
	
	//constants
		private static final String NAME = "Greta Points";
		private static final double INVESTMENTAMOUNT = 2000;

		// constructors
		/**
		 * Default Constructor
		 */
		public Greta() {
			super(NAME);
		}

		/**
		 * Constructor with args
		 * @param name (String)
		 */
		public Greta(String name) {
			super(name);
		}
		
		/**
		 * Get the amount of investment
		 * @return (double)
		 */
		public static double getGretaAmount() {
			return INVESTMENTAMOUNT;
		}
		
		public void addInvestment(int playerNumber) {
			Bank.add(playerNumber, INVESTMENTAMOUNT);
		}


}
