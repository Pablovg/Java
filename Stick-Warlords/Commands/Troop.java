package Commands;

import Controller.Controller;

public class Troop implements Command {

	private static String help = "Type TROOP to add a new troop";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("TROOP")) {
			return new Troop();
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
		if (control.getPlaying()){
			control.troop();
		}
		
		else{
			System.err.println("You cannot add troops if the game did not start!");
		}
	}

}
