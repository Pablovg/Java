package Model;

public class Cell {
	
	private boolean move, merge;
	private int value; 
	
	public Cell() {
		this.move = false;
		this.merge = false;
		this.value = 0;
	}
	
	public void setMove(boolean bool) {
		this.move = bool;
	}
	
	public boolean getMove() {
		return this.move;
	}
	
	public void setMerge(boolean bool) {
		this.merge = bool;
	}
	
	public boolean getMerge() {
		return this.merge;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void duplicateValue() {
		this.value = this.value * 2;
	}
}
