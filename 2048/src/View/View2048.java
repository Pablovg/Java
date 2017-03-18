package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

import Model.Cell;

public class View2048 extends JFrame {
	
	private int size;
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;

	public View2048(int size) {
		
		this.size = size;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		gridPanel = new JPanel(new GridLayout(size, size));
		gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(gridPanel);
		
		
	}
	
	public void createView(Cell[][] Board) {
		createBoard(Board);
		
	}
	
	public void createBoard(Cell[][] Board) {
		//this.remove(gridPanel);
		gridPanel.removeAll();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board[i][j] != null) {
					gridPanel.add(LabelFactory.getLabel(Board[i][j].getValue()));
				}
				
				else {
					gridPanel.add(LabelFactory.getLabel(0));
				}
			}
		}
		
		//this.add(gridPanel);
	}
}
