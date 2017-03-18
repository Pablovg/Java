package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Model2048;
import View.View2048;

public class Controller2048 {

	private View2048 view;
	private Model2048 model;
	
	public Controller2048(View2048 view, Model2048 model) {
		
		this.view = view;
		this.model = model;
		
		this.view.addKeyListener(new GameListener());
		this.view.createView(model.getBoard());
	}
	
	class GameListener implements KeyListener {
		
		boolean firstMove = false;
		
		public void keyPressed(KeyEvent e){}
		public void keyTyped(KeyEvent e){}

		public void keyReleased(KeyEvent e) {
			
			model.setLastBoard(model.getBoard());
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("UP");
				model.moveUp();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
				model.moveDown();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
				model.moveLeft();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				model.moveRight();
			}
			
			if (model.checkWin()) {
				System.out.println("Congrats! you won :D");
				System.exit(0);
			}
			
			if (model.isFull() && model.checkLose()) {
				System.out.println("Sorry, you lost :S");
				System.exit(0);
			}
			
			if (firstMove) {
				if (model.getMove()) {
					model.addCell();
				}
			}
			
			else {
				firstMove = true;
				model.addCell();
			}
			
			//model.setLastBoard(model.getBoard());
			view.createView(model.getBoard());
			view.revalidate();
			model.restartAttributes();
		}
	}
}
