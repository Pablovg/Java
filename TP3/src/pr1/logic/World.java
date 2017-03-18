package pr1.logic;

import java.util.Random;

import pr1.control.Controller;

public class World
{	
	//PRIVATE DATA
	private Surface surface;
	private static boolean finished;
	
	//CONSTANTS
	public static final int INITIAL_SIMPLE_CELLS = 2;
	public static final int INITIAL_COMPLEX_CELLS = 1;
	public static final int INITIAL_ROWS = 5;
	public static final int INITIAL_COLS = 5;
	
	/**
	 * World class constructor
	 */
	public World() //WORLD CONTRUCTOR
	{	
		surface = new Surface(INITIAL_ROWS, INITIAL_COLS);
		finished = false;
	}
	
	/**
	 * Cleans the surface and creates the random initial cells
	 */
	public void init() //INITIALIZES A WORLD, FIRST CLEANS THE SURFACE AND THEN CREATES NEW (INITIAL) CELLS
	{
		surface.clean(); 
		
		for (int i = 0; i < INITIAL_SIMPLE_CELLS; i++)
		{
			boolean success = false;
			
			while (success == false)
			{
				Random rand = new Random();
				int row = rand.nextInt(INITIAL_ROWS);
				int col = rand.nextInt(INITIAL_COLS);
				
				if (createSimple(row, col) == true)
				{
					success = true;
				}
			}
		}
		
		for (int j = 0; j < INITIAL_COMPLEX_CELLS; j++)
		{
			boolean success = false;
			
			while (success == false)
			{
				Random rand = new Random();
				int row = rand.nextInt(INITIAL_ROWS);
				int col = rand.nextInt(INITIAL_COLS);
				
				if (createComplex(row, col) == true)
				{
					success = true;
				}
			}
		}
	}
	
	/**
	 * Performs a simulation step (move, reproduce or die)
	 */
	public String evolve() //PERFORMS A SIMULATION STEP (MOVE, REPRODUCE OR DIE)
	{
		String output = "\n", string;
		
		for (int i = 0; i < INITIAL_ROWS; i++)
		{
			for (int j = 0; j < INITIAL_COLS; j++)
			{
				Position pos = new Position(i ,j);
				
				if (Surface.isNull(i, j) == false)
				{
					string = surface.executeMovement(pos);
					
					if (string != "")
					{
						output = output + string + "\n";
					}
				}
			}
		}
		
		Controller.printOutput(output);
		
		for (int a = 0; a < INITIAL_ROWS; a++)
		{
			for (int b = 0; b < INITIAL_COLS; b++)
			{
				if (Surface.isNull(a, b) == false)
				{
					surface.setCellMoved(a, b, false);
				}
			}
		}
		
		return output;
	}
  
	public Surface getSurface() //RETURNS SURFACE
	{
		return surface;
	}
	
	/**
	 * @param row The row where the new cell will be created
	 * @param col The row where the new cell will be created
	 * @return
	 */
	public boolean createSimple(int row, int col) //CREATE A NEW CELL IN POS X Y
	{
		boolean ok = false;
				
		if (Surface.isNull(row, col) == true)
		{
			surface.setSimpleCell(row, col);
			ok = true;
		}
		
		return ok;
	}
	
	public boolean createComplex(int row, int col) //CREATE A NEW CELL IN POS X Y
	{
		boolean ok = false;
				
		if (Surface.isNull(row, col) == true)
		{
			surface.setComplexCell(row, col);
			ok = true;
		}
		
		return ok;
	}
	
	
	/**
	 * @param row The row where the cell will be deleted
	 * @param col The row where the cell will be deleted
	 * @return
	 */
	public boolean delete(int row, int col) //DELETE CELL IN POS X Y
	{
		boolean ok = false;
				
		if (Surface.isNull(row, col) == false)
		{
			surface.setNull(row, col);
			ok = true;
		}
    
		return ok;
	}
	
	public void setFinished(boolean value)
	{
		finished = value;
	}
	
	public static boolean getFinished()
	{
		return finished;
	}
}