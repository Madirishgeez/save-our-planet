/**
 * 
 */
package G2.savetheplanet;

import java.util.ArrayList;


/**
 * This class represents the physical board
 * @author Matt
 *
 */
public class Board {
	
	private ArrayList<Space> spaces = new ArrayList<Space>();

	public Board(ArrayList<Space> spaces) {
		this.spaces = spaces;
	}

	public ArrayList<Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(ArrayList<Space> spaces) {
		this.spaces = spaces;
	}

	/**
	 * This method populates the spaces ArrayList with the spaces.
	 * Whatever is currently held in the InvestSpaceValues enum will be used first.
	 * the blank space and the 'GO' space added manually
	 * 
	 */  
	public void populateBoard() {

		for (InvestSpaceValues space : InvestSpaceValues.values()) {

			spaces.add(new InvestSpace(space.getSpaceName(), false, false, space.getPrice(), space.getSite_price(), 0, space.getSpaceField(), space.getSetRequired(), space.getBatt_1_price(), space.getBatt_2_price(), space.getBatt_3_price(), space.getAccum_price(), 0));
		}

		spaces.add(0, new Greta());
		spaces.add(6, new Tesla());

	}

}
