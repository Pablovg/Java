package Commands;

import Controller.Controller;

public interface Command{
	public Command parse(String commandString); //If is the requested command return it, return null if not
	public String helpText(); //Return help string
	public void execute(Controller control, String inputWords); //Execute the command methods
}

