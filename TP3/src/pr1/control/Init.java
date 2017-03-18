package pr1.control;
import pr1.logic.World;

public class Init extends Command
{
	private static String help = "Type INIT to restart the simulation";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("INIT"))
		{
			return new Init();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		world.init();
		System.out.println("\nSurface initialized:\n");
	}
}
