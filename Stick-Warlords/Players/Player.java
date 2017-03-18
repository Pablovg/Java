package Players;

public class Player{ //MPT = Money Per Turn
	
	private int money = 5, MPT = 5, lifes = 5; //Default values
	
	public int getMoney(){
		return money;
	}
	
	public void addMoney(int num){
		money = money + num;
	}

	public void restMoney(int num){
		money = money - num;
	}
	
	public void upgradeMPT(){
		MPT = MPT*2;
	}
	
	public int getMPT(){
		return MPT;
	}
	
	public int getPlayerLifes(){
		return lifes;
	}
	
	public void addPlayerLifes(){
		lifes++;
	}
	
	public void restPlayerLifes(){
		lifes--;
	}
}