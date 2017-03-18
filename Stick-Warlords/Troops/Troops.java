package Troops;

public interface Troops {
	
	public int getCost();
	public int getATK();
	public void restHP(int num);
	public int getHp();
	public String getPlayer();
	public boolean counterKill(Troops troop);
	public String getID();
	public String getName();
	public int getReward();
	public boolean isDead();
}
