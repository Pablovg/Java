package Commands;

import Controller.Controller;

public class Play implements Command {

	private static String help = "Type PLAY to start a game";

	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("PLAY")) {
			return new Play();
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
		control.setPlaying(true);
		control.initGame();
		System.out.println("Starting new game...");
	}

}
