package pr1.logic;
import java.util.Random;

public class ComplexCell extends Cell
{

	public ComplexCell()
	{
		eat = MAX_EAT;
		moved = false;
	}
	
	public Position movePos(int num, int row, int col, Cell[][] surface)
	{
		Position movePos = null; 
 		
		if ((((row-1 != -1) && (col-1 != -1)) && ((row-1 != World.INITIAL_ROWS) && (col-1 != World.INITIAL_COLS))) && (num == 0)) 
		{
			movePos = new Position(row-1, col-1);
		}
		
		if ((((row-1 != -1) && (col != -1)) && ((row-1 != World.INITIAL_ROWS) && (col != World.INITIAL_COLS))) && (num == 1)) 
		{
			movePos = new Position(row-1, col);
		}
		
		if ((((row-1 != -1) && (col+1 != -1)) && ((row-1 != World.INITIAL_ROWS) && (col+1 != World.INITIAL_COLS))) && (num == 2)) 
		{
			movePos = new Position(row-1, col+1);
		}
		
		if ((((row != -1) && (col-1 != -1)) && ((row != World.INITIAL_ROWS) && (col-1 != World.INITIAL_COLS))) && (num == 3)) 
		{
			movePos = new Position(row, col-1);
		}
		
		if ((((row != -1) && (col+1 != -1)) && ((row != World.INITIAL_ROWS) && (col+1 != World.INITIAL_COLS))) && (num == 4)) 
		{
			movePos = new Position(row, col+1);
		}
		
		if ((((row+1 != -1) && (col-1 != -1)) && ((row+1 != World.INITIAL_ROWS) && (col-1 != World.INITIAL_COLS))) && (num == 5)) 
		{
			movePos = new Position(row+1, col-1);
		}
		
		if ((((row+1 != -1) && (col != -1)) && ((row+1 != World.INITIAL_ROWS) && (col != World.INITIAL_COLS))) && (num == 6)) 
		{
			movePos = new Position(row+1, col);
		}
		
		if ((((row+1 != -1) && (col+1 != -1)) && ((row+1 != World.INITIAL_ROWS) && (col+1 != World.INITIAL_COLS))) && (num == 7)) 
		{
			movePos = new Position(row+1, col+1);
		}
		
		return movePos;
	}
	
	public String executeMovement(Position pos, Cell[][] surface)
	{
		Random randpos = new Random(); //CHOOSE WHERE TO MOVE
		boolean ok = false;
		int fail = 0;
		Position newPos = null;
		int[] randArray = {0, 0, 0, 0, 0, 0, 0, 0};
		String output = "";
		
		if (moved == false)
		{
			while (ok == false && fail < 8)
			{
				int random = randpos.nextInt(8); //RANDOMLY GET A POSTION BETWEEN 0 AND 7
				
				//|0|1|2|
				//|3|x|4|
				//|5|6|7|
				
				if (randArray[random] == 0)
				{
					randArray[random] = 1;
					
					newPos = movePos(random, pos.getRow(), pos.getCol(), surface); //GET THE POSITION WHERE WE THE CELL IS MOVING
					
					if (newPos == null) //CHECK IF THERE IS AN AVAILABLE POSITION TO MOVE
					{
						fail++;
					}
					
					else
					{
						if (surface[newPos.getRow()][newPos.getCol()] == null || surface[newPos.getRow()][newPos.getCol()].isEdible() == true) //CHECK IF THE CELL CAN BE MOVED THERE
						{
							ok = true;
						}
						
						else
						{
							fail++;
						}
					}
				}
			}
			
			if (fail < 8) //CELL CAN BE MOVED TO THE NEW POSITION
			{
				if (eat != 0) //THE CELL CAN EAT
				{
					if (surface[newPos.getRow()][newPos.getCol()] == null) //MOVES TO A FREE POSITION
					{
						surface[newPos.getRow()][newPos.getCol()] = surface[pos.getRow()][pos.getCol()];
						surface[pos.getRow()][pos.getCol()] = null;
						output = output + "Complex cell at (" + pos.getRow() + ", " + pos.getCol() + ") moved to (" + newPos.getRow() + ", " + newPos.getCol() + ")";
					}
					
					else //EATS A SIMPLE CELL
					{
						surface[newPos.getRow()][newPos.getCol()] = surface[pos.getRow()][pos.getCol()];
						surface[pos.getRow()][pos.getCol()] = null;
						eat--;
						output = output + "Complex cell at (" + pos.getRow() + ", " + pos.getCol() + ") ate simple cell at (" + newPos.getRow() + ", " + newPos.getCol() + ")";
					}
				}
				
				else //THE CELL CAN'T EAT ANYMORE
				{
					surface[newPos.getRow()][newPos.getCol()] = null;
					surface[pos.getRow()][pos.getCol()] = null;
					output = output + "Complex cell at (" + pos.getRow() + ", " + pos.getCol() + ") ate simple cell at (" + newPos.getRow() + ", " + newPos.getCol() + ") and EXPLOTED!!!";
				}
				
				moved = true;
			}
			
			else //CELL COULD NOT MOVE
			{
				output = output + "Complex cell at (" + pos.getRow() + ", " + pos.getCol() + ") can't move";
			}
			
			return output;
		}
		
		else
		{
			return output;
		}		
	}

	public boolean isEdible() 
	{
		return false;
	}
	
	public void setMoved(boolean value)
	{
		moved = value;
	}
}