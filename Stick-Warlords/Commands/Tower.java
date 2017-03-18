package Commands;

import Controller.Controller;

public class Tower implements Command {

	private static String help = "Type TOWER to upgrade your towers";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("TOWER")) {
			return new Tower();
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
			control.tower();
		}
		
		else{
			System.err.println("You cannot upgrade towers if the game did not start!");
		}
	}

}
