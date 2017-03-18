package pr1.control;
import pr1.logic.World;

public class Delete extends Command
{
	private static String help = "Type DELETE X Y to delete a cell";
	
	public String helpText()
	{
		return help;
	}
	
	public Command parse(String[] commandString)
	{
		if (commandString[0].equalsIgnoreCase("DELETE"))
		{
			return new Delete();
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
			
			success = world.delete(row, col);//DELETE
			
			if(success) 
			{			
				System.out.println("Cell deleted at (" + (row+1) + ", " + (col+1) + ")\n");
			}
		} 
		else 
		{
			System.out.println("Error. Parameters out of range!");
			success = true;
		}
		if(success == false)
		{
			System.err.println("Failed when deleting a cell at position " + (row+1) + " " + (col+1) + ", (empty cell)\n");
		}
		
		
	}
	

}
