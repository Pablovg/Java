package Commands;

import Controller.Controller;

public class Stats implements Command{
private static String help = "Type STATS to see troop stats";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("STATS")) {
			return new Stats();
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
			control.stats();
		}
		
		else{
			System.err.println("You cannot see stats if the game did not start!");
		}
	}
}
