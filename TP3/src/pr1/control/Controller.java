package pr1.control;
import pr1.logic.*;

import java.util.Scanner;
import java.lang.String;

public class Controller
{
	//PRIVATE DATA
	private World world;
	/**
	 * Controller class constructor
	 */
	public Controller()
	{
		world = new World();
	}
	
	/**
	 * Performs the simulation following users commands
	 */
	
	
	public void executeSimulation()
	{
		Scanner scanner = new Scanner(System.in);
		
		while (World.getFinished() == false)
		{
			System.out.println("\nInsert command:");

			String input;
			input = scanner.nextLine();
		
			String[] inputWords = input.split(" ");
		
			Command command = CommandParser.parseCommand(inputWords);
		
			if (command != null)
			{
				command.execute(this.world, inputWords);
				if (World.getFinished() == false)
				{
					printSurface();
				}
				
				else
				{
					System.out.print("See you soon :D!");
				}
			}
		
			else
			{
				System.out.println("Wrong command (Type HELP to see command list)");
			}
		}
		scanner.close();
	}
	
	public static void printOutput(String output)
	{
		System.out.print("\n==================================");
		System.out.print(output);
		System.out.print("==================================\n\n");
	}
	
	/**
	 * Prints the surface only one time
	 */
	public void printSurface() //PRINTS THE GAME BOARD
	{
		for (int i=0 ; i < World.INITIAL_ROWS; i++)
		{	
			System.out.print("| ");
			
			for (int j=0 ; j < World.INITIAL_COLS; j++)
			{
				world.getSurface();
				
				if(Surface.isNull(i, j))
				{
					System.out.print(" - ");
				}
				
				else 
				{
					System.out.print(world.getSurface().getCellDie(i,j));
					
					if (world.getSurface().getCellIsEdible(i, j))
					{
						System.out.print("X");
						System.out.print(world.getSurface().getCellReproduce(i,j));
					}
					
					else 
					{
						System.out.print("*");
						System.out.print(world.getSurface().getCellEat(i,j));
					}
				}
				
				System.out.print(" | ");
			}
			
			System.out.print("\n");
		}
	}
}

