package pr1.control;

public class CommandParser 
{
	static Command[] availableCommands = {new Help(), new Init(), new Step(), new CreateSimple(), new CreateComplex(), new Delete(), new Clean(), new Exit()} ; 
	
	
	static public String helpTextCommands()
	{
		String help = "\n";
		
		for (int i = 0; i < availableCommands.length; i++)
		{
			help = help + availableCommands[i].helpText() + "\n";
		}
		
		return help;
	}
	
	static public Command parseCommand(String[] commandString)
	{
		Command command = null;
		
		for (int i = 0; i < availableCommands.length; i++)
		{
			Command test = availableCommands[i];
			
			if (test.parse(commandString) != null)
			{
				command = test;
			}
		}
		
		return command;
	}
}
