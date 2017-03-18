package Troops;

public class Knight implements Troops {
	
	private int HP = 5;
	private final int ATK = 3, reward = 13, cost = 20;
	private final String player, ID = "K", name = "Knight";
	
	public Knight(String player){
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
		if (troop.getID() == "M"){
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
