package pr1.control;
import pr1.logic.World;

public class Clean extends Command
{
	private static String help = "Type CLEAN to delete all cells";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("CLEAN"))
		{
			return new Clean();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		world.getSurface().clean();
		System.out.println("Surface cleaned.\n");
	}
}
