/**
 * 
 */
package ZooVenture;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author allisonwalther
 *
 */
public class ZooVenture {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		GameModel model = new GameModel("board.txt", "animals.txt", "items.txt");
		ControlView view = new ControlView(model);
		//RepaintController repaintController = new RepaintController(model, view);
		
		
		//new Timer(25, repaintController).start();
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(700, 700);
		view.setVisible(true);

	}

}
