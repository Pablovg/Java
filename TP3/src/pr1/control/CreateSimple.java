package pr1.control;
import pr1.logic.World;

public class CreateSimple extends Command
{
	private static String help = "Type CREATESIMPLE X Y to make a new cell";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("CREATESIMPLE"))
		{
			return new CreateSimple();
		}
		
		else
		{
			return null;
		}
	}
	
	public void execute(World world, String[] commandString)
	{
		String rowstring = commandString[1];
		String colstring = commandString[2];
		
		int row = Integer.parseInt(rowstring)-1; 
		int col = Integer.parseInt(colstring)-1;
		boolean success;
		
		if (row < World.INITIAL_ROWS && row >= 0 && col < World.INITIAL_COLS && col >= 0){
			
			success = world.createSimple(row, col);//CREATE A SIMPLE CELL
			
			if(success) 
			{			
				System.out.println("Simple cell created at (" + (row+1) + ", " + (col+1) + ")\n");
			}
		} 
		else 
		{
			System.err.println("Error. Parameters out of range!");
			success = true;
		}
		if(success == false)
		{
			System.err.println("Failed when creating a simple cell at position " + (row+1) + " " + (col+1) + ", (already occupied)\n");
		}
		
		
	}
}
