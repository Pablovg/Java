package Board;

/**
 * Factory Pattern Implementation:
 * A board factory returns the corresponding 
 * board object
 */
public class BoardFactory{
	
	public Board getBoard(String type){ //Return the requesed board
		Board board = null;
		
		if (type.equalsIgnoreCase("EASY")){
			board = new EasyBoard();
		}
		
		else{
			board = new HardBoard();
		}
		
		return board;
	}
}
