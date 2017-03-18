package Controller;

import Commands.Command;
import Commands.Exit;
import Commands.Help;
import Commands.Play;
import Commands.Rules;
import Commands.Stats;
import Commands.Tower;
import Commands.Troop;
import Commands.Turn;


/**
 * Command Patter Implementation:
 * The parser has a list with all the available 
 * commands and returns the selected one if it is 
 * in the list 
 */

public class CommandParser{
	static Command[] availableCommands = {new Stats(), new Help(), new Rules(), new Play(), new Turn(), new Troop(), new Tower(), new Exit()}; 
	
	static public String helpTextCommands(){ //Print all the help strings from each command
		String help = "\n";
		
		for (int i = 0; i < availableCommands.length; i++){
			help = help + availableCommands[i].helpText() + "\n";
		}
		
		return help;
	}
	
	static public Command parseCommand(String commandString){ //Find the requested command
		Command command = null;
		
		for (int i = 0; i < availableCommands.length; i++){
			Command test = availableCommands[i];
			
			if (test.parse(commandString) != null){
				command = test;
			}
		}
		
		return command;
	}
}

