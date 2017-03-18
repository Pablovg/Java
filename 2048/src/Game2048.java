
import javax.swing.SwingUtilities;

import Control.Controller2048;
import Model.Model2048;
import View.View2048;

public class Game2048 {

	private static int size = 4;
	
	public static void main(String[] args) {
		
		
		
		View2048 view = new View2048(size);
		Model2048 model = new Model2048(size);
		
		Controller2048 control = new Controller2048(view, model);
		
		view.setVisible(true);
	}

}
