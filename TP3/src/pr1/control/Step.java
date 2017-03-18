package pr1.control;
import pr1.logic.*;

public class Step extends Command
{
	private static String help = "Type STEP to perform a simulation step";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("Step"))
		{
			return new Step();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		world.evolve();
	}
}
