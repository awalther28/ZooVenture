import java.io.IOException;

/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class GameFacade {

	static Board board;
	static Player player;
	static boolean running;
	
	public GameFacade () throws IOException
	{
		player = new Player();
		board = new Board("board.txt", "animals.txt", "items.txt");
		running = true;
	}
	
	public void run()
	{
		
		
		
		
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	
	public static void main(String[] args) throws IOException {
		GameFacade game = new GameFacade();
//		while (game.isRunning())
//		{
//			game.run();
//		}
		
		System.out.println(game.board);

	}

}
