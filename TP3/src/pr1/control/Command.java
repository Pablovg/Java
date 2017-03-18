package pr1.control;
import pr1.logic.World;

public abstract class Command
{
	public abstract void execute(World world, String[] commandString);
	public abstract Command parse(String[] commandString);
	public abstract String helpText();
}
