/**
 * 
 */
package G2.savetheplanet;

/**
 * @author Matt
 *
 */
public abstract class Space {

	//instance vars
	private String name;
	
	//constructors
	/**
	 * Default Constructor
	 */
	public Space() {
	}

	/**
	 * Constructor with args
	 * @param name (String)
	 */
	public Space(String name) {
		this.setName(name);
		
	}

	//getters & setters
	/**
	 * Get the name of the space
	 * @return the name (String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the space
	 * Validation: name cannot be null or an empty String
	 * @param name the name to set (String)
	 */
	public void setName(String name) {
		if(name != null && name != ""){
			this.name = name;
		}else {
			throw new IllegalArgumentException("Invalid or null String provided for name");
		}
	}
}
