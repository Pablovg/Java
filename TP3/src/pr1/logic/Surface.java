package pr1.logic;

public class Surface
{
	//PRIVATE DATA
	private static Cell[][] surface;
	private int rows = World.INITIAL_ROWS;
	private int columns = World.INITIAL_COLS;
	
	/**
	 * @param numRows The number of the row
	 * @param numColumns The number of the column
	 */
	public Surface(int numRows, int numColumns) //CONSTRUCTOR
	{
		surface = new Cell[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++)
	    {
			for (int j = 0; j < numColumns; j++)
			{
				surface[i][j] = null;
	    	}
	    }
	}
	
	public String executeMovement(Position pos)
	{
		return surface[pos.getRow()][pos.getCol()].executeMovement(pos, surface);
	}
	
	/**
	 * Sets all cells to empty
	 */
	public void clean() //MODIFIES ALL CELLS TO EMPTY
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				surface[i][j] = null;
	    	}
	    }
	}
	
	public static boolean isNull(int row, int col)
	{
		boolean isnull = false;
		
		if (surface[row][col] == null)
		{
			isnull = true;
		}
		
		return isnull;
	}
	
	public void setNull (int row, int col)
	{
		surface[row][col] = null;
	}
	

	public void setSimpleCell(int row,int col)
	{
		surface[row][col] = new SimpleCell();
	}
	
	public void setComplexCell(int row,int col)
	{
		surface[row][col] = new ComplexCell();
	}
	
	public int getCellDie(int row,int col) //ACCESS TO SETDIE()
	{
		return surface[row][col].die;
	}
	
	public int getCellReproduce(int row, int col) //ACCESS TO GETREPRODUCE()
	{
		return surface[row][col].reproduce;
	}
	
	public int getCellEat(int row, int col) //ACCESS TO GETREPRODUCE()
	{
		return surface[row][col].eat;
	}
	
	public boolean getCellIsEdible(int row, int col)
	{
		return surface[row][col].isEdible();
	}
	
	public void setCellMoved(int row, int col, boolean value)
	{
		surface[row][col].setMoved(value);
	}
}