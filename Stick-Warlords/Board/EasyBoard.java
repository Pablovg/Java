package Board;

import Troops.Troops;

public class EasyBoard implements Board{
	
	private int rows = 3, cols = 6;
	private Troops[][] board = new Troops[rows][cols];
	
	public int getCols(){
		return cols;
	}

	@Override
	public void init() {
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				board[i][j] = null;
			}
		}
	}

	@Override
	public Troops getTroop(int row, int col) {
		return board[row][col];
	}

	@Override
	public void setTroop(Troops troop, int row, int col) {
		board[row][col] = troop;
	}

	@Override
	public void deleteTroop(int row, int col) {
		board[row][col] = null;
	}
	
}
