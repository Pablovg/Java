package Commands;

import Controller.Controller;

public class Rules implements Command{

	private static String help = "Type RULES to see game rules";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("RULES")) {
			return new Rules();
		}
		
		else {
			return null;
		}
	}

	@Override
	public String helpText() {
		return help;
	}

	@Override
	public void execute(Controller control, String inputWords) {
		System.out.println("Game rules:");	
		System.out.println("-Add troops to the board, make them fight, win and get into the opponent's castle");
		System.out.println("-When  troop gets inside the opponent's castle the opponent loses a life");
		System.out.println("-If your opponent's lifes decrease to 0 you win\n");
		System.out.println("Troop Counters:");
		System.out.println("-Warrior counters Spearman");
		System.out.println("-Spearman counters Knight");
		System.out.println("-Knight counters Mage");
		System.out.println("-Mage counters Berserker");
		System.out.println("-Berserker counters Warrior\n");
		System.out.println("Troop Stats: ");
		System.out.println("-Warrior: 2 HP, 1 ATK");
		System.out.println("-Spearman: 2 HP, 2 ATK");
		System.out.println("-Knight: 5 HP, 3 ATK");
		System.out.println("-Mage: 2 HP, 5 ATK");
		System.out.println("-Berserker: 3 HP, 4 ATK");
	}

}
