package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class LabelFactory {
	
	public static JLabel getLabel(int value) {
		
		JLabel label = new JLabel();
		
		if (value == 0) {
			label.setBackground(Color.WHITE);
		}
		
		if (value == 2) {
			label.setText("2");
			label.setBackground(new Color(255, 250, 0));
		}
		
		if (value == 4) {
			label.setText("4");
			label.setBackground(new Color(255, 200, 0));
		}
		
		if (value == 8) {
			label.setText("8");
			label.setBackground(new Color(255, 150, 0));
		}
		
		if (value == 16) {
			label.setText("16");
			label.setBackground(new Color(255, 100, 0));
		}
		
		if (value == 32) {
			label.setText("32");
			label.setBackground(new Color(255, 50, 0));
		}
		
		if (value == 64) {
			label.setText("64");
			label.setBackground(new Color(255, 0, 50));
		}
		
		if (value == 128) {
			label.setText("128");
			label.setBackground(new Color(255, 0, 100));
		}
		
		if (value == 256) {
			label.setText("256");
			label.setBackground(new Color(255, 0, 150));
		}
		
		if (value == 512) {
			label.setText("512");
			label.setBackground(new Color(255, 0, 200));
		}
		
		if (value == 1024) {
			label.setText("1024");
			label.setBackground(new Color(255, 0, 250));
		}
		
		if (value == 2048) {
			label.setText("2048");
			label.setBackground(new Color(255, 0, 200));
		}
		
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setOpaque(true);
		
		return label;
	}
}
