package Model;

import java.util.Random;

public class Model2048 {
	
	private Random rand = new Random();
	private int size;
	private Cell[][] Board, LastBoard;
	private boolean move;
	
	public Model2048(int size) {
		this.size = size;
		this.move = false;
		Board = new Cell[size][size];
		setLastBoard(new Cell[size][size]);
		initializeGrid();
		//print();
	}
	
	public boolean isFull() {
		boolean full = true;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] == null) {
					full = false;
				}
			}
		}
		
		return full;
	}
	
	public boolean checkLose() {
		boolean noMovesLeft = true;
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (Board[row][col] != null) {
					if (row-1 >= 0 && Board[row-1][col] != null) { //check up
						if (Board[row][col].getValue() == Board[row-1][col].getValue()) {
							noMovesLeft = false;
						}
					}
					
					if (row+1 < size && Board[row+1][col] != null) { //check down
						if (Board[row][col].getValue() == Board[row+1][col].getValue()) {
							noMovesLeft = false;
						}
					}
					
					if (col+1 < size && Board[row][col+1] != null) { //check right
						if (Board[row][col].getValue() == Board[row][col+1].getValue()) {
							noMovesLeft = false;
						}
					}
					
					if (col-1 >= 0 && Board[row][col-1] != null) { //check left
						if (Board[row][col].getValue() == Board[row][col-1].getValue()) {
							noMovesLeft = false;
						}
					}
				}
			}
		} 
		
		return noMovesLeft;
	}
	
	public boolean checkWin() {
		boolean found2048 = false;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] != null && Board[i][j].getValue() == 2048) {
					found2048 = true;
				}
			}
		}
		
		return found2048;
	}
	
	public void initializeGrid() {
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Board[i][j] = null;
			}
		}
		
		addCell();
		addCell();
	}
	
	public void addCell() {
		boolean occupied = false;
		
		while (occupied == false && !isFull()) {
			int row = randomPos(), col = randomPos();	
			
			if (Board[row][col] == null) {
				Board[row][col] = new Cell();
				Board[row][col].setValue(randomValue());
				occupied = true;
			}
		}
	}
	
	public int randomPos() {
		return rand.nextInt(size);
	}
	
	public int randomValue() {
		
		int random = rand.nextInt(10);
		
		if (random == 0) {
			return 4;
		}
		
		else {
			return 2;
		}
	}
	
	public void restartAttributes() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] != null) {
					Board[i][j].setMerge(false);
				}
			}
		}
		
		move = false;
	}
	
	public boolean checkEqual() {
		boolean equal = true;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] != null && LastBoard[i][j] != null) {
					if (Board[i][j].getValue() != LastBoard[i][j].getValue()) {
						equal = false;
					}
				}
				
				if ((Board[i][j] == null && LastBoard[i][j] != null) || (Board[i][j] != null && LastBoard[i][j] == null)) {
					equal = false;
				}
			}
		}
		
		return equal;
	}
	
	public void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] != null) {
					System.out.print(Board[i][j].getValue() + " ");
				}
				
				else {
					System.out.print("· ");
				}
			}
			System.out.println();
		}
	}
	
	public void moveUp() {
		
		for (int col = 0; col < size; col++) {
			for (int row = 0; row < size; row++) {
				for (int aux = 1; aux < size; aux++) {
					if (row+aux < size && Board[row][col] == null && Board[row+aux][col] != null){
						Board[row][col] = Board[row+aux][col];
						Board[row+aux][col] = null;
						move = true;
					}
				}
			}
			
			for (int row = 0; row < size; row++) {
				if (row+1 < size && Board[row][col] != null && Board[row+1][col] != null) {
					if (Board[row][col].getMerge() == false && Board[row][col].getValue() == Board[row+1][col].getValue()) {
						Board[row][col].duplicateValue();
						Board[row][col].setMerge(true);
						Board[row+1][col] = null;
						move = true;
					}
				}
			}
			
			for (int row = 0; row < size; row++) {
				for (int aux = 1; aux < size; aux++) {
					if (row+aux < size && Board[row][col] == null && Board[row+aux][col] != null){
						Board[row][col] = Board[row+aux][col];
						Board[row+aux][col] = null;
						move = true;
					}
				}
			}
		}
	}
	
	
	
	public void moveDown() {
		for (int col = 0; col < size; col++) {
			for (int row = size-1; row >= 0; row--) {
				for (int aux = 1; aux < size; aux++) {
					if (row-aux >= 0 && Board[row][col] == null && Board[row-aux][col] != null){
						Board[row][col] = Board[row-aux][col];
						Board[row-aux][col] = null;
						move = true;
					}
				}
			}
			
			for (int row = size-1; row >= 0; row--) {
				if (row-1 >= 0 && Board[row][col] != null && Board[row-1][col] != null) {
					if (Board[row][col].getMerge() == false && Board[row][col].getValue() == Board[row-1][col].getValue()) {
						Board[row][col].duplicateValue();
						Board[row][col].setMerge(true);
						Board[row-1][col] = null;
						move = true;
					}
				}
			}
			
			for (int row = size-1; row >= 0; row--) {
				for (int aux = 1; aux < size; aux++) {
					if (row-aux >= 0 && Board[row][col] == null && Board[row-aux][col] != null){
						Board[row][col] = Board[row-aux][col];
						Board[row-aux][col] = null;
						move = true;
					}
				}
			}
		}
	}
	
	
	public void moveLeft() {
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				for (int aux = 1; aux < size; aux++) {
					if (col+aux < size && Board[row][col] == null && Board[row][col+aux] != null) {
						Board[row][col] = Board[row][col+aux];
						Board[row][col+aux] = null;
						move = true;
					}
				}
			}
			
			for (int col = 0; col < size; col++){
				if (col+1 < size && Board[row][col] != null && Board[row][col+1] != null){
					if (Board[row][col].getMerge() == false && Board[row][col].getValue() == Board[row][col+1].getValue()) {
						Board[row][col].duplicateValue();
						Board[row][col].setMerge(true);
						Board[row][col+1] = null;
						move = true;
					}
				}
			}
			
			for (int col = 0; col < size; col++) {
				for (int aux = 1; aux < size; aux++) {
					if (col+aux < size && Board[row][col] == null && Board[row][col+aux] != null) {
						Board[row][col] = Board[row][col+aux];
						Board[row][col+aux] = null;
						move = true;
					}
				}
			}
		}
	}
	
	public void moveRight() {
		
		for (int row = 0; row < size; row++) {
			for (int col = size-1; col >= 0; col--) {
				for (int aux = 1; aux < size; aux++) {
					if (col-aux >= 0 && Board[row][col] == null && Board[row][col-aux] != null) {
						Board[row][col] = Board[row][col-aux];
						Board[row][col-aux] = null;
						move = true;
					}
				}
			}
			
			for (int col = size-1; col >= 0; col--) {
				if (col-1 >= 0 && Board[row][col] != null && Board[row][col-1] != null){
					if (Board[row][col].getMerge() == false && Board[row][col].getValue() == Board[row][col-1].getValue()) {
						Board[row][col].duplicateValue();
						Board[row][col].setMerge(true);
						Board[row][col-1] = null;
						move = true;
					}
				}
			}
			
			for (int col = size-1; col >= 0; col--) {
				for (int aux = 1; aux < size; aux++) {
					if (col-aux >= 0 && Board[row][col] == null && Board[row][col-aux] != null) {
						Board[row][col] = Board[row][col-aux];
						Board[row][col-aux] = null;
						move = true;
					}
				}
			}
		}
	}
	
	public Cell[][] getBoard() {
		return this.Board;
	}

	public Cell[][] getLastBoard() {
		return LastBoard;
	}

	public void setLastBoard(Cell[][] lastBoard) {
		LastBoard = lastBoard;
	}

	public boolean getMove() {
		return move;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

