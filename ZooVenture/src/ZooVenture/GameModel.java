/**
 * 
 */
package ZooVenture;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ListModel;

/**
 * @author allisonwalther
 *
 */
public class GameModel {
	public static Board board;
	public static Player player;
	public static String orientation;
	
	public GameModel(String boardFile, String animalFile, String itemFile) throws IOException
	{
		player = new Player();
		board = new Board(boardFile, animalFile, itemFile);
		orientation = "N";
	}
	
	public void move(String direction)
	{
		String adjDirection = adjustMoveDirection(direction);
		board.move(adjDirection);
		System.out.println(board.toString());
	}
	
	/**
	 * @param direction to move based on N being orientation
	 * @return direction to move based on current orientation
	 */
	private String adjustMoveDirection(String dir) {
		switch(orientation)
		{
			case("N"):
				return dir;
			case("S"):
				if (dir.equals("N"))
					return "S";
				else if (dir.equals("S"))
					return "N";
				else if (dir.equals("W"))
					return "E";
				else
					return "W";
			case("E"):
				if (dir.equals("N"))
					return "E";
				else if (dir.equals("S"))
					return "W";
				else if (dir.equals("W"))
					return "N";
				else
					return "S";
			case("W"):
				if (dir.equals("N"))
					return "W";
				else if (dir.equals("S"))
					return "E";
				else if (dir.equals("W"))
					return "S";
				else
					return "N";
		}			
		return null;
	}

	public void rotate(String direction)
	{
		//TODO tonight if time
	}
	
	public void removeItemFromRoom(String item) 
	{
		//TODO
	}
	
	public void animalEncounter()
	{
		//TODO
	}
	
	public void addItemToInventory()
	{
		//TODO
	}

	/**
	 * @return
	 */
	public String[] getInventory() {
		return player.getInventory();
	}

	/**
	 * @return
	 */
	public String getHealth() {
		return player.getHealth();
	}
	
	public int getX()
	{
		return board.x;
	}
	
	public int getY()
	{
		return board.y;
	}
	
	public Room getRoom(int x, int y)
	{
		return board.getRoom(x, y);
	}
	
	public int getPlayerLocationX()
	{
		return board.playerLocation.x;
	}
	
	public int getPlayerLocationY()
	{
		return board.playerLocation.y;
	}
	
	public String getOrientation()
	{
		return orientation;
	}
	
	public void changeOrientation(String dir)
	{
		switch(orientation)
		{
			case("N"):
				if (dir.equals("R"))
					orientation = "E";
				else
					orientation = "W";
				break;
			case("S"):
				if (dir.equals("R"))
					orientation = "W";
				else
					orientation = "E";
				break;
			case("E"):
				if (dir.equals("R"))
					orientation = "S";
				else
					orientation = "N";
				break;
			case("W"):
				if (dir.equals("R"))
					orientation = "N";
				else
					orientation = "S";
				break;
		}	
	}
}
