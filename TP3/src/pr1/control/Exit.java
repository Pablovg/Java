package pr1.control;
import pr1.logic.World;

public class Exit extends Command
{
	private static String help = "Type EXIT to finish the program";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("EXIT"))
		{
			return new Exit();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		world.setFinished(true);
	}
}
