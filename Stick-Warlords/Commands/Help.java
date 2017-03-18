package Commands;

import Controller.CommandParser;
import Controller.Controller;

public class Help implements Command {

	private static String help = "Type HELP to see available commands";
	
	@Override
	public Command parse(String commandString) {
		if (commandString.equalsIgnoreCase("HELP")) {
			return new Help();
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
		String help = CommandParser.helpTextCommands();
		System.out.println(help);
	}

}
