package Game;

import Board.Board;
import Board.BoardFactory;
import Players.Player;
import Troops.Troops;
import Troops.TroopsFactory;

public class Game{
	
	private enum Mode {EASY, HARD};
	private enum Turn {Player1, Player2};
	private Mode mode;
	private Turn turn;
	private Player player1, player2;
	private TroopsFactory troopsF = new TroopsFactory();
	private BoardFactory boardF = new BoardFactory();
	private Board board;
	private static Game game = null;
	private boolean playing = false;;
	
	
	private Game(){}
	
	/**
	 * Singleton Pattern Implementation:
	 * We don't want to have 10 games in memory, we just 
	 * have 1 instance of a game and restart the game when
	 * it is finished 
	 * @return the only instance of the game that exists
	 */
	public static Game getGame(){
		if (game == null){
			game = new Game();
		}
		return game;
	}
	
	public void createBoard(){ //Get a board and initialize it
		if (mode == Mode.EASY){
			board = boardF.getBoard("EASY");
		}
		
		else{
			board = boardF.getBoard("HARD");
		}
		
		board.init();
	}
		
	public void setMode(int num){ 
		if (num == 1){
			mode = Mode.EASY; 
		}
		
		else{
			mode = Mode.HARD;
		}
	}
	
	public boolean getEasyMode(){
		if (mode == Mode.EASY){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	public void createPlayers(){
		player1 = new Player();
		player2 = new Player();
	}
	
	public void changeTurn(){
		if (turn == Turn.Player1){
			turn = Turn.Player2;
		}
		
		else{
			turn = Turn.Player1;
		}
	}
	
	public boolean validMove(int row){ //Check if there is a free space to add a troop
		boolean ok = false;
		
		if (turn == Turn.Player1 && board.getTroop(row, 0) == null){
			ok = true;
		}
		
		if (turn == Turn.Player2){
			if (mode == Mode.EASY && board.getTroop(row, 5) == null){
				ok = true;
			}
			
			if (mode == Mode.HARD && board.getTroop(row, 9) == null){
				ok = true;
			}
		}
		
		return ok;
	}
	
	public void addTroop(String name, int row){ //Add a troop int the requested position
		if (turn == Turn.Player1){
			board.setTroop(troopsF.getTroop(name, "player1"), row, 0);
		}
		
		else{
			if (mode == Mode.EASY){
				board.setTroop(troopsF.getTroop(name, "player2"), row, 5);
			}
			
			else{
				board.setTroop(troopsF.getTroop(name, "player2"), row, 9);
			}
		}
	}

	public boolean upgrade() { //Upgrade current player towers
		boolean ok = false;
		
		if (turn == Turn.Player1 && player1.getMoney() >= 25){
			player1.upgradeMPT();
			player1.addPlayerLifes();
			player1.restMoney(25);
			ok = true;
			
			System.out.println("Towers upgraded, current MPT = " + player1.getMPT());
		}
		
		if (turn == Turn.Player2 && player2.getMoney() >= 25){
			player2.upgradeMPT();
			player2.addPlayerLifes();
			player2.restMoney(25);
			ok = true;
			
			System.out.println("Towers upgraded, current MPT = " + player2.getMPT());
		}
		
		return ok;
	}

	public void findFight() { //j = player1, j+1 = player2
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < board.getCols()-1; j++){
				if (board.getTroop(i, j) != null && board.getTroop(i, j+1) != null){ //Check that the positions that we are gonna use aren't null
					if (board.getTroop(i, j).getPlayer() != board.getTroop(i, j+1).getPlayer()){ //Check that the 2 troops ar from different players
						if (checkCounterKill(board.getTroop(i, j), board.getTroop(i, j+1)) == 0){ //If there is no counter kill fight
							fight(board.getTroop(i, j), board.getTroop(i, j+1));
							
							if (board.getTroop(i, j).isDead()){ //If a troop dies fighting delete it and add reward to the other player
								System.out.println(board.getTroop(i, j).getName() + " in row " + i +
								" col " + j + " died in battle!");
								System.out.println("Player2 won " + board.getTroop(i, j).getReward() + "$");
								player2.addMoney(board.getTroop(i, j).getReward());
								board.deleteTroop(i, j);
							}
							
							if (board.getTroop(i, j+1).isDead()){ //If a troop dies fighting delete it and add reward to the other player
								System.out.println(board.getTroop(i, j+1).getName() + " in row " + i +
								" col " + (j+1) + " died in battle!");
								System.out.println("Player1 won " + board.getTroop(i, j+1).getReward() + "$");
								player1.addMoney(board.getTroop(i, j+1).getReward());
								board.deleteTroop(i, j+1);
							}
						}
						
						else{
							if (checkCounterKill(board.getTroop(i, j), board.getTroop(i, j+1)) == 1){ //If the is counterkill delete the looser and add money to the other player
								System.out.println(board.getTroop(i, j+1).getName() + " in row " + i +
								" col " + (j+1) + " counterkills a " + board.getTroop(i, j).getName() + "!");
								System.out.println("Player2 won " + board.getTroop(i, j).getReward() + "$");
								player2.addMoney(board.getTroop(i, j).getReward());
								board.deleteTroop(i, j);
							}
							
							else{ //If the is counterkill delete the looser and add money to the other player
								System.out.println(board.getTroop(i, j).getName() + " in row " + i +
								" col " + j + " counterkills a " + board.getTroop(i, j+1).getName() + "!");
								System.out.println("Player1 won " + board.getTroop(i, j+1).getReward() + "$");
								player1.addMoney(board.getTroop(i, j+1).getReward());
								board.deleteTroop(i, j+1);
							}
						}
					}
				}
			}
		}
	}
	
	public void moveForward(){
		if (turn == Turn.Player1){
			for (int i = 0; i < 3; i++){
				for (int j = board.getCols()-2; j >= 0; j--){ //Get at the end of the row and decrease
					if (board.getTroop(i, j) != null){ //Check that the position isn't null
						if (board.getTroop(i, j).getPlayer() == "player1" && board.getTroop(i, j+1) == null){ //Check correct player and next position empty
							board.setTroop(board.getTroop(i, j), i, j+1); //Move the troop forward
							board.deleteTroop(i, j);
						}
					}
				}
			}
		}
		
		else{
			for (int i = 0; i < 3; i++){
				for (int j = 1; j <= board.getCols()-1; j++){ //Get at the start of the row and increase
					if (board.getTroop(i, j) != null){ //Check that the position isn't null
						if (board.getTroop(i, j).getPlayer() == "player2" && board.getTroop(i, j-1) == null){ //Check correct player and next position empty
							board.setTroop(board.getTroop(i, j), i, j-1);//Move the troop forward
							board.deleteTroop(i, j);
						}
					}
				}
			}
		}
	}
	
	private void fight(Troops troop1, Troops troop2){ //Decrease troop hp depending of the opposite troop atk
		troop1.restHP(troop2.getATK());
		troop2.restHP(troop1.getATK());
	}

	private int checkCounterKill(Troops troop1, Troops troop2){ //Check if the troop counters the opposite troop
		int looser = 0;
		
		if (troop2.counterKill(troop1)){
			looser = 1;
			
		}
		
		if (troop1.counterKill(troop2)){
			looser = 2;
		}
		
		return looser;
	}

	public void towerHit(){ //When a troop gets to the castle delete it and decrease opposite player hp
		for (int i = 0; i < 3; i++){
			checkTowerHit("player1", i, board.getCols()-1);
			checkTowerHit("player2", i, 0);
		}
		
		if (player1.getPlayerLifes() == 0){
			System.out.println("Player2 wins!");
			playing = false;
		}
		
		if (player2.getPlayerLifes() == 0){
			System.out.println("Player1 wins!");
			playing = false;
		}
	}
	
	public boolean getPlaying(){
		return playing;
	}
	
	public void setPlaying(boolean bool){
		playing = bool;
	}
	
	private void checkTowerHit(String player, int row, int col) {
		if (board.getTroop(row, col) != null && board.getTroop(row, col).getPlayer() == player){ //Check if there is a troop to enter the castle
			System.out.println(board.getTroop(row, col).getName() + 
			" at row " + row + " col " + col + " enters the castle!");
			board.deleteTroop(row, col);
			
			if (player == "player1"){
				player2.restPlayerLifes();
				player1.addMoney(20);
				System.out.println("Player2 loses 1 life!");
				System.out.println("Player1 wins 20$!");
			}
			
			else{
				player1.restPlayerLifes();
				player2.addMoney(20);
				System.out.println("Player1 loses 1 life!");
				System.out.println("Player2 wins 20$!");
			}
		}
	}

	public void addPlayersMoney(){ //At the end of the turn add MPT to the player
		if (turn == Turn.Player1){
			player1.addMoney(player1.getMPT());
		}
		
		else{
			player2.addMoney(player2.getMPT());
		}
		
	}

	public boolean checkEnoughMoney(String name){ //Check if a player can add the requested troop
		Troops troop;
		boolean ok = false;
		
		troop = troopsF.getTroop(name, "null");
		
		if (turn == Turn.Player1){
			if (player1.getMoney() >= troop.getCost()){
				ok = true;
			}
		}
		
		else{
			if (player2.getMoney() >= troop.getCost()){
				ok = true;
			}
		}
		
		return ok;
	}

	public boolean availableMove(){ //Check if the players ahs at least 1 available move
		boolean ok = false;
		
		if (turn == Turn.Player1){
			for (int i = 0; i < 3; i++){
				if (board.getTroop(i, 0) == null){
					ok =  true;
				}
			}
		}
		
		else{
			if (mode == Mode.EASY){
				for (int i = 0; i < 3; i++){
					if (board.getTroop(i, 5) == null){
						ok =  true;
					}
				}
			}
			
			else{
				for (int i = 0; i < 3; i++){
					if (board.getTroop(i, 9) == null){
						ok =  true;
					}
				}
			}
		}
		
		return ok;
	}

	public void purchaseTroop(String name){ //Decrease player's money depending on the bought troop
		Troops troop;
		
		troop = troopsF.getTroop(name, "null");
		
		if (turn == Turn.Player1){
			player1.restMoney(troop.getCost());
		}
		
		else{
			player2.restMoney(troop.getCost());
		}
	}

	public void setDefaultTurn() {
		turn = Turn.Player1;
	}

	public boolean playerTurn(String string) { //Check if is the correct player turn
		boolean ok = false;
		
		if (string == "player1" && turn == Turn.Player1){
			ok = true;
		}
		
		if (string == "player2" && turn == Turn.Player2){
			ok = true;
		}
		
		return ok;
	}

	public int getMoney(String string){ //Get the current player money
		int money = -1;
		
		if (string == "player1"){
			money =  player1.getMoney();
		}
		
		else{
			money =  player2.getMoney();
		}
		
		return money;
	}

	public int getLifes(String string){ //Get the current player lifes
		int lifes = -1;
		
		if (string == "player1"){
			lifes =  player1.getPlayerLifes();
		}
		
		else{
			lifes =  player2.getPlayerLifes();
		}
		
		return lifes;
	}

	public void printBoard(){
		for (int i = 0; i < 3; i++){
			if (mode == Mode.EASY){
				System.out.println(" ___              Row " + i + "              ___");
				System.out.println("|   |                               |   |");
				System.out.println("| O |                               | O |");
				System.out.println("| _ |  0    1    2    3    4    5   | _ |");
				System.out.println("|(_)|" + printCols(i) +            "|(_)|\n");
		
			}
			
			else{
				System.out.println(" ___                        Row " + i + "                        ___");
				System.out.println("|   |                                                   |   |");
				System.out.println("| O |                                                   | O |");
				System.out.println("| _ |  0    1    2    3    4    5    6    7    8    9   | _ |");
				System.out.println("|(_)|" + printCols(i) +                      "|(_)|\n");
			}
		}
	}
	
	public String printCols(int row){
		String string = "|";
		
		for (int j = 0; j < board.getCols(); j++){
			if (board.getTroop(row, j) != null){
				if (board.getTroop(row, j).getPlayer() == "player1"){
					string = string + " " + board.getTroop(row, j).getID() + "1 |";
				}
				
				else{
					string = string + " " + board.getTroop(row, j).getID() + "2 |";
				}
				
			}
			
			else{
				string = string + "    |";
			}
		}
		
		return string;
	}

	public void printTroop(int row, int col) {
		
		if (row >= 0 && row <= 3 && col >= 0 && col <= board.getCols() && board.getTroop(row, col) != null){
			System.out.println("HP = " + board.getTroop(row, col).getHp());
			System.out.println("ATK = " + board.getTroop(row, col).getATK());
		}
		
		else{
			System.err.println("Select a valid troop!");
		}
	}
}
