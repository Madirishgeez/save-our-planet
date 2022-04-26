package G2.savetheplanet;

public class diceRoll {
	
	public static int diceRoll() {
		
			int roll = (int) (Math.random() * 6) + 1; // +1 fixes the 0 to exclusive top end
			
			//print result
			//System.out.println(roll);
			
			return roll;
	}

}


