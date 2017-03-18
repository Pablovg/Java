package Commands;

import Controller.Controller;

public class Exit implements Command {
	
	private static String help = "Type EXIT to end the game";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("EXIT")) {
			return new Exit();
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
		control.setExit(true);
	}

}
