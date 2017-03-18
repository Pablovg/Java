package pr1.control;
import pr1.logic.World;

public class Help extends Command
{
	private static String help = "List of commands: ";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("HELP"))
		{
			return new Help();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		String help = CommandParser.helpTextCommands();
		System.out.println(help);
	}
}
