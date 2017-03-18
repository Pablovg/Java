package Troops;

public class Spearman implements Troops {
	
	private int HP = 2;
	private final int ATK = 2, reward = 2, cost = 5;
	private final String player, ID = "S", name = "Spearman";
	
	public Spearman(String player){
		this.player = player;
	}
	
	@Override
	public int getCost() {
		return cost;
	}
	
	@Override
	public int getATK() {
		return ATK;
	}
	
	@Override
	public void restHP(int num) {
		HP = HP - num;
	}
	
	@Override
	public int getHp() {
		return HP;
	}
	
	@Override
	public String getPlayer() {
		return player;
	}
	
	@Override
	public boolean counterKill(Troops troop) {
		if (troop.getID() == "K"){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	@Override
	public String getID() {
		return ID;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public int getReward() {
		return reward;
	}
	
	@Override
	public boolean isDead() {
		if (HP <= 0){
			return true;
		}
		
		else{
			return false;
		}
	}
}
