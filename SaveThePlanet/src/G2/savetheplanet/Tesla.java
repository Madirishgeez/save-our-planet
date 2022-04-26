/**
 * 
 */
package G2.savetheplanet;

/**
 * @author Matt
 *
 */
public class Tesla extends Space {
	
	//constants
		private static final String name = "Tesla Charging Station";
		
		//constructors
		/**
		 * Default constructor
		 * Names the space Tesla Charging Station and gives the default space number
		 */
		public Tesla() {
			super(name);
		}

		/**
		 * Constructor with args
		 * @param name (String)
		 * @param spaceNumber (int)
		 */
		public Tesla(String name, int spaceNumber) {
			super(name);
		}

}
