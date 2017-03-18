package Troops;

/**
 * Factory Pattern Implementation:
 * A troop factory returns the corresponding 
 * troop object
 */
public class TroopsFactory {
	
	public Troops getTroop(String name, String player){ //Get requested troop
		Troops troop = null;
		
		if (name.equalsIgnoreCase("WARRIOR")){
			troop = new Warrior(player);
		}
		
		else if (name.equalsIgnoreCase("SPEARMAN")){
			troop = new Spearman(player);
		}
		
		else if (name.equalsIgnoreCase("KNIGHT")){
			troop = new Knight(player);
		}
		
		else if (name.equalsIgnoreCase("MAGE")){
			troop = new Mage(player);
		}
		
		else{
			troop = new Berserker(player);
		}
		
		return troop;
	}
	
}
