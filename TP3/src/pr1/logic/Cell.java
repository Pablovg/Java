package pr1.logic;

public abstract class Cell
{
	public static final int DIE_STEPS = 5;
	public static final int REPRODUCTION_STEPS = 5;
	public static final int MAX_EAT = 2;
	
	protected int die;
	protected int eat;
	protected int reproduce;
	protected boolean moved;
	
	public abstract String executeMovement(Position pos, Cell[][] surface);
	public abstract boolean isEdible();
	public abstract void setMoved(boolean value);
}
