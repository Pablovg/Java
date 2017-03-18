package Commands;

import Controller.Controller;

public class Turn implements Command {

	private static String help = "Type TURN to finish your turn";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("TURN")) {
			return new Turn();
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
			control.turn();
		}
		
		else{
			System.err.println("You cannot end a turn if the game did not start!");
		}
	}
}
