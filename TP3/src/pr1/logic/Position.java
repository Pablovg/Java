package pr1.logic;

public class Position 
{
	private int rowPos;
	private int colPos;
	
	public Position(int row, int col)
	{
		rowPos = row;
		colPos = col;
	}
	
	public int getRow()
	{
		return rowPos;
	}

	public int getCol()
	{
		return colPos;
	}
}