/**
 * 
 */
package G2.savetheplanet;

/**
 * @author Matt
 *
 */
public class InvestSpace extends Space {

	// constants
	@SuppressWarnings("unused")
	private final int minBattery = 0;
	private final int maxBattery = 4;
	private final double minRent = 0;
	private final double minPrice = 0;
	private static final int BANK_OWNER = -1;

	// instance vars
	private boolean canBeDeveloped, isOwned;
	private double price, rent, priceOfBattery, licenceFee1Battery, licenceFee2Battery, licenceFee3Battery,	licenceFee4Battery;
	private int playerOwner, Battery;
	private String spaceField;
	private int fieldSetRequired;

	//constructors
		/**
		 * Default Constructor
		 */


		/**
		 * Constructor to be used when starting a quick game where spaces 
		 * are owned by the bank.
		 * @param name
		 * @param playerOwner
		 * @param spaceNumber
		 * @param canBeDeveloped
		 * @param isOwned
		 * @param price
		 * @param rent
		 * @param Battery
		 * @param spaceField
		 * @param fieldRequired
		 * @param priceOfBattery
		 * @param licenceFee1Battery
		 * @param licenceFee2Battery
		 * @param licenceFee3Battery
		 * @param licenceFee4Battery
		 */
		public InvestSpace(String name,  boolean canBeDeveloped, boolean isOwned, double price, double rent, int Battery, String spaceField, int fieldSetRequired, int priceOfBattery, int licenceFee1Battery, int licenceFee2Battery, int licenceFee3Battery, int licenceFee4Battery) {
			super(name);
			this.setCanBeDeveloped(canBeDeveloped);
			this.setOwned(isOwned);
			this.setPrice(price);
			this.setRent(rent);
			this.playerOwner = BANK_OWNER;
			this.setBattery(Battery);
			this.setSpaceField(spaceField);
			this.setFieldSpaceRequired(fieldSetRequired);
			this.priceOfBattery = priceOfBattery;
			this.licenceFee1Battery = licenceFee1Battery;
			this.licenceFee2Battery = licenceFee2Battery;
			this.licenceFee3Battery = licenceFee3Battery;
			this.licenceFee4Battery = licenceFee4Battery;
		}


		//getters and setters
		/**
		 * Get the current Development status
		 * @return the canBeDeveloped (boolean)
		 */
		public boolean getCanBeDeveloped() {
			return canBeDeveloped;
		}

		/**
		 * Set the current development status
		 * @param set the canBeDeveloped (boolean)
		 */
		public void setCanBeDeveloped(boolean canBeDeveloped) {
			this.canBeDeveloped = canBeDeveloped;
		}

		/**
		 * Get the current ownership status
		 * @return the isOwned (boolean)
		 */
		public boolean isOwned() {
			return isOwned;
		}

		/**
		 * Set the current ownership status
		 * @param set the isOwned (boolean)
		 */
		public void setOwned(boolean isOwned) {
			this.isOwned = isOwned;
		}

		/**
		 * Get the price to buy of the InvestSpace
		 * @return the price (double)
		 */
		public double getPrice() {
			return price;
		}

		/**
		 * Set the price to buy of the InvestSpace
		 * price cannot be set below the minPrice constant var 
		 * @param set the price (double)
		 */
		public void setPrice(double price) {
			if(price >= minPrice) {
				this.price = price;
			}else {
				throw new IllegalArgumentException("Invalid price set for Space");
			}
		}

		/**
		 * Get the price of rent on this InvestSpace
		 * @return the rent (double)
		 */
		public double getRent() {
			return rent;
		}

		/**
		 * Set the price of rent on this InvestSpace
		 * Minimum amount for rent should not be below the set minRent
		 * @param set the rent (double)
		 */
		public void setRent(double rent) {
			if(rent >= minRent) {
				this.rent = rent;
			}else {
				throw new IllegalArgumentException("Invalid amount indicated for rent");
			}
			
		}

		/**
		 * Get the player number of the current owner
		 * @return the playerOwner (int)
		 */
		public int getPlayerOwner() {
			return playerOwner;
		}

		/**
		 * Set the current owner to a player number
		 * Checks if the square has been previously owned and if not increases the value of the property to reflect business good will of the new owner
		 * @param set the playerOwner (int)
		 */
		public void setPlayerOwner(int playerOwner) {
			if(this.playerOwner == BANK_OWNER) {
				this.playerOwner = playerOwner;
				this.isOwned = true;
				increasePrice();
			}else {
				this.playerOwner = playerOwner;
			}
		}

		/**
		 * Get the number of Battery at the site
		 * @return the Battery (int)
		 */
		public int getBattery() {
			return Battery;
		}

		/**
		 * Set the number of Battery at the site
		 * There cannot be more than the maxBattery at any one site
		 * @param set the Battery (int)
		 */
		public void setBattery(int Battery) {
			this.Battery = Battery;
			
		}
		
		/**
		 * Gets the amount of spaces in the field the player is required to own
		 * before being allowed to develop the space
		 * @return fieldSetRequired (int)
		 */
		public int getFieldSetRequired() {
			return fieldSetRequired;
		}

		/**
		 * Set the amount of spaces in the space field, the player is required is own
		 * before being allowed to develop the space
		 * @param fieldSetRequired(int);
		 */
		public void setFieldSpaceRequired(int fieldSetRequired) {
			this.fieldSetRequired = fieldSetRequired;	
		}
		
		
		/**
		 * Get the field type of this space
		 * @return (String)
		 */
		public String getSpaceField() {
			return spaceField;
		}

		/**
		 * Set the price to buy of the InvestSpace
		 * price cannot be set below the minPrice constant var 
		 * @param set the price (double)
		 */
		public void setSpaceField(String spaceField) {
			this.spaceField = spaceField;
		}
		
		/**
		 * Get the price of the first Battery member for this space
		 * @return priceOfBattery1 (double)
		 */
		public double getPriceOfBattery() {
			return this.priceOfBattery;
		}

		/**
		 * Set the price of a Battery member for this space
		 * @param priceOfBattery1 (double)
		 */
		public void setPriceOfBattery(double priceOfBattery) {
			this.priceOfBattery = priceOfBattery;
		}
		
		/**
		 * Get the licence fee for the space with 1 member of Battery
		 * @return licenceFee1Battery (double)
		 */
		public double getLicenceFee1Battery() {
			return this.licenceFee1Battery;
		}

		/**
		 * Set the licence fee for the space with 1 member of Battery
		 * @param licenceFee1Battery (double)
		 */
		public void setLicenceFee1Battery(double licenceFee1Battery) {
			this.licenceFee1Battery = licenceFee1Battery;
		}
		
		/**
		 * Get the licence fee for the space with 2 member of Battery
		 * @return licenceFee2Battery (double)
		 */
		public double getLicenceFee2Battery() {
			return this.licenceFee2Battery;
		}

		/**
		 * Set the licence fee for the space with 2 member of Battery
		 * @param licenceFee2Battery (double)
		 */
		public void setLicenceFee2Battery(double licenceFee2Battery) {
			this.licenceFee2Battery = licenceFee2Battery;
		}
		
		/**
		 * Get the licence fee for the space with 3 member of Battery
		 * @return licenceFee3Battery (double)
		 */
		public double getLicenceFee3Battery() {
			return this.licenceFee3Battery;
		}

		/**
		 * Set the licence fee for the space with 3 member of Battery
		 * @param licenceFee3Battery (double)
		 */
		public void setLicenceFee3Battery(double licenceFee3Battery) {
			this.licenceFee3Battery = licenceFee3Battery;
		}
		
		/**
		 * Get the licence fee for the space with 4 member of Battery
		 * @return licenceFee4Battery (double)
		 */
		public double getLicenceFee4Battery() {
			return this.licenceFee4Battery;
		}

		/**
		 * Set the licence fee for the space with 4 member of Battery
		 * @param licenceFee4Battery (double)
		 */
		public void setLicenceFee4Battery(double licenceFee4Battery) {
			this.licenceFee4Battery = licenceFee4Battery;
		}
		
		
		
		/**
		 * Add 20% to the value of the space to reflect business cost increase
		 */
		public void increasePrice() {
			this.price += (price/100) * 20;
		}
		
		/**
		 * Method to increase the number of Battery working at the space
		 * No more than the maxBattery constant var is allowed
		 */
		public void increaseBattery() {
			if(this.Battery < this.maxBattery ) {
				this.Battery++;
				switch(this.Battery) {
				case 1:
					setRent(licenceFee1Battery);
				break;
				case 2:
					setRent(licenceFee2Battery);
				break;
				case 3:
					setRent(licenceFee3Battery);
				break;
				case 4:
					setRent(licenceFee4Battery);
				break; 
				}
			}else { 
				throw new AssertionError("Max limit of Battery reached");
			}
		}
		
		
		

	
	



	

}
