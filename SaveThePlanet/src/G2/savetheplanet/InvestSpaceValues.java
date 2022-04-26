/**
 * 
 */
package G2.savetheplanet;

/**
 * @author Matt
 *
 */
public enum InvestSpaceValues {
	
	// Properties for each Value.

		WIND1("Slieve Donard", "Wind Power", 2, 300, 20, 100, 300, 900, 2500, 150),
		WIND2("Black Mountain", "Wind Power", 2, 600, 40, 200, 600, 1800, 4500, 300),
		SOLAR1("Benone Beach", "Solar Power", 3, 1400, 100, 500, 1500, 4500, 7500, 700),
		SOLAR2("Tyrella Beach", "Solar Power", 3, 1600, 120, 600, 1800, 5000, 9000, 800),
		SOLAR3("White Park Bay", "Solar Power", 3, 1800, 140, 700, 2000, 5500, 9500, 900),
		HYDRO1("River Bann", "Hydro Power", 3, 2400, 200, 1000, 3000, 7500, 11000, 1200),
		HYDRO2("River Foyle", "Hydro Power", 3, 2600, 220, 1100, 3300, 8000, 11500, 1300),
		HYDRO3("River Lagan", "Hydro Power", 3, 2800, 220, 1200, 3600, 8500, 12000, 1400),
		BIO1("Tollymore Forest Park", "Bio Mass", 2, 3500, 350, 1750, 5000, 11000, 15000, 1750),
		BIO2("Glenariff Forest Park", "Bio Mass", 2, 4000, 500, 2000, 6000, 14000, 20000, 2000);
	
	private final String spaceName;
	private final String spaceField;
	private final int setRequired;
	private final int price;
	private final int site_price;
	private final int batt_1_price;
	private final int batt_2_price;
	private final int batt_3_price;
	private final int accum_price;
	
	private final double batteryPrice;
	
	private InvestSpaceValues(String spaceName, String spaceField, int setRequired, int price, int site_price, int batt_1_price, int batt_2_price, int batt_3_price, int accum_price, double batteryPrice) {
		this.spaceName = spaceName;
		this.spaceField = spaceField;
		this.setRequired = setRequired; 
		this.price = price;
		this.site_price = site_price;
		this.batt_1_price = batt_1_price;
		this.batt_2_price = batt_2_price;
		this.batt_3_price = batt_3_price;
		this.accum_price = accum_price;
		this.batteryPrice = batteryPrice;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public String getSpaceField() {
		return spaceField;
	}

	public int getSetRequired() {
		return setRequired;
	}

	public int getPrice() {
		return price;
	}

	public int getSite_price() {
		return site_price;
	}

	public int getBatt_1_price() {
		return batt_1_price;
	}

	public int getBatt_2_price() {
		return batt_2_price;
	}

	public int getBatt_3_price() {
		return batt_3_price;
	}

	public int getAccum_price() {
		return accum_price;
	}

	public double getBatteryPrice() {
		return batteryPrice;
	}
	
	

}
