package Board;

import Troops.Troops;

public interface Board {
	
	public int getCols(); //Return columns
	public void init(); //Initialize the board with all null
	public Troops getTroop(int row, int col); //Get a position in the board
	public void setTroop(Troops troop, int row, int col); //Modify a position in the board 
	public void deleteTroop(int row, int col); //Delete a position in the board
}
