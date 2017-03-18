package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import Commands.Command;
import Game.Game;


/**
 * Command Patter Implementation:
 * The controller asks for a command 
 * to the command parser, if it is found
 * uses the command's execute method
 */
public class Controller{
	
	private Game game = Game.getGame();
	boolean exit = false;
	Scanner scanner = new Scanner(System.in);
	
	public void start(Controller control){
		String input;
		
		while (exit == false){
			System.out.println("\nInsert command:");

			input = scanner.nextLine();
		
			Command command = CommandParser.parseCommand(input);
		
			if (command != null){
				command.execute(control, input);
				
				if (!exit){
					if (game.getPlaying()){
						printGame();
					}
				}
				
				else{
					System.out.print("See you soon :D!");
				}
			}
		
			else{
				System.err.println("Wrong command (Type HELP to see command list)");
			}
		}
		
		scanner.close();
	}
	
	public void printGame(){
		String turn1 = " ", turn2 = " ";
		if(game.playerTurn("player1")){turn1 = "*";}
		if(game.playerTurn("player2")){turn2 = "*";}
		
		if (game.getEasyMode()){
			
			System.out.println("");
			System.out.println("Player1" + turn1 + "                         " + turn2 + "Player2");
			System.out.println(game.getMoney("player1") + " $                                  " + game.getMoney("player2") + " $");
			System.out.println(game.getLifes("player1") + " <3                                 " + game.getLifes("player2") + " <3\n");
		}
		
		else{
			System.out.println("");
			System.out.println("Player1" + turn1 + "                                             " + turn2 + "Player2");
			System.out.println(game.getMoney("player1") + " $                                                      " + game.getMoney("player2") + " $");
			System.out.println(game.getLifes("player1") + " <3                                                     " + game.getLifes("player2") + " <3\n");
		}
		game.printBoard();
	}

	public void initGame(){
		game.setMode(chooseMode());
		game.setDefaultTurn();
		game.createBoard();
		game.createPlayers();
	}
	
	private int chooseMode(){
		int num = 1;
		
		try{
			do{
				System.out.println("Choose a valid game mode: ");
				System.out.println("1 - Easy");
				System.out.println("2 - Hard");
				num = scanner.nextInt();
			} while (num < 1 || num > 2);
		}
		
		catch (InputMismatchException e){
			System.err.println("Error: Input mismatch!");
		}
		
		return num;
	}
	
	public void setExit(boolean bool){
		exit = bool;
	}
	
	public void setPlaying(boolean bool){
		game.setPlaying(bool);
	}
	
	public boolean getPlaying(){
		return game.getPlaying();
	}
	
	public void turn(){
		game.findFight();
		game.moveForward();
		game.towerHit();
		if (game.getPlaying()){
			game.addPlayersMoney();
			game.changeTurn();
		}
	}
	
	public void troop(){
		String name;
		int row = -1;
		boolean nameOk = false, rowOk = false, exit = false;
		
		try{
			do{
				if (game.availableMove()){
					System.out.println("Type row: ");
					row = scanner.nextInt();
					
					if (row >= 0 && row <= 2 && game.validMove(row)){
						rowOk = true;
					}
					
					else{
						if (!game.validMove(row)){
							System.err.println("You cannot add a troop in that row. Try Again!");
						}
						
						else{
							System.err.println("Choose a valid row (0-2). Try again!");
						}
					}
				}
				
				else{
					System.err.println("You do not have space to add a troop!");
					exit = true;
				}
				
			} while(!rowOk && !exit);
			
			do{
				System.out.println("Type the name of the troop you want to add: ");
				name = scanner.nextLine();
				
				if ((name.equalsIgnoreCase("WARRIOR") || 
					name.equalsIgnoreCase("BERSERKER") ||
					name.equalsIgnoreCase("KNIGHT") ||
					name.equalsIgnoreCase("MAGE") ||
					name.equalsIgnoreCase("SPEARMAN"))) {
					
					if (game.checkEnoughMoney(name)){
						nameOk = true;
						game.purchaseTroop(name);
					}
					
					else{
						System.err.println("You do not have enough money to add that troop!");
						exit = true;
					}
				}
				
				else{
					System.err.println("Valid troops are Warrior, Berserker, " 
									+ "Knight, Mage and Spearman. Try again!");
				}
			} while(!nameOk && !exit);
			
			if (!exit){
				game.addTroop(name, row);
				System.out.println(name + " added to row " + row);
			}
		}
		
		catch (InputMismatchException e){
			System.err.println("Error: Input mismatch!");
		}
		
		
	}

	public void tower() {
		if (!game.upgrade()){
			System.err.println("You need 25 gold to upgrade your towers!");
		}
	}

	public void stats() {
		int row, col;
		
		try{
			System.out.println("Type valid row: ");
			row = scanner.nextInt();
			System.out.println("Type valid column: ");
			col = scanner.nextInt();
			game.printTroop(row, col);
		}
		
		catch (InputMismatchException e){
			System.err.println("Error: Input mismatch!");
		}
	}
}
