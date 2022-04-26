package G2.savetheplanet;

/**
 * @author Matt
 * ENUM to store the menu options   
 */
public enum MenuOptions {

		PURCHASE ("Purchase square"), 
		BATTERY ("Purchase battery"),
		TAKEOVER ("Takeover Investment"),
		END ("End turn"),
		TERMINATE ("Terminate game");
		
		
		private final String menuOptions;

		/**
		 * @param menuOptions
		 */
		private MenuOptions(String menuOptions) {
			this.menuOptions = menuOptions;
		}

		/**
		 * @return the menuOptions
		 */
		public String getMenuOptions() {
			return menuOptions;
		}

	}