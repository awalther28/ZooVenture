/**
 * 
 */
package ZooVenture;

import java.io.IOException;
import java.util.ArrayList;


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
	}
	
	/**
	 * @return -1 if no live animals; else returns the index of where the animal 
	 * is in the room contents array
	 */
	public int checkForAnimal()
	{
		return board.board[getPlayerLocationY()][getPlayerLocationX()].containsLiveAnimal();	
	}
	
	/**
	 * turns normal animal into sedated animal; adds to player's inventory
	 * @param index of animal whose health is less than 0
	 * 
	 */
	public void sedateAnimal(int index)
	{
		Animal animal = (Animal) board.board[getPlayerLocationY()][getPlayerLocationX()].contents.get(index);
		board.board[getPlayerLocationY()][getPlayerLocationX()].contents.remove(index);
		animal.setType("sedated animal");
		addInventory(animal);
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
	
	/**
	 * * simulates one attack
	 * @param index of the animal that is to be attacked
	 * @return 1 if both player and animal are alive; 0 if player is dead; -1 if animal is dead
	 */
	public int animalEncounter(int index)
	{
		Animal animal = (Animal) board.board[getPlayerLocationY()][getPlayerLocationX()].contents.get(index);
		if(animal.strength > player.strength)
		{
			player.hp -= (animal.strength - player.strength);
			animal.hp -= 5;
		}
		else
		{
			animal.hp -= (player.strength - animal.strength);
			player.hp -= 5;
		}
		
		System.out.println("Animal HP: " + animal.hp);
		
		if (player.hp >= 0 & animal.hp >= 0)
		{
			return 1;
		}
		else if (player.hp < 0)
			return 0;
		else
			return -1;
	}


	/**
	 * @return
	 */
	public String[] getInventory() {
		return player.getInventory();
	}

	public void addAllInventory(ArrayList<MazeObject> stuff)
	{
		player.addAllInventory(stuff);
	}
	
	public void addInventory(MazeObject object)
	{
		player.addInventory(object);
	}
	
	/**
	 * @return
	 */
	public String getHealth() {
		return player.getHPString();
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

	/**
	 * @param stuff
	 */
	public void removeItemsFromRoom(ArrayList<MazeObject> stuff) {
		board.board[getPlayerLocationY()][getPlayerLocationX()].removeItemsFromRoom(stuff);	
	}

	public void removeItemFromInventory(MazeObject object)
	{
		player.removeInventoryItem(object);
	}

	
	public MazeObject getItemInInventory(int index) {
		return player.getItemInInventory(index);
	}


	public void increaseHP(int effectValue) {
		player.updateHP(effectValue);
	}


	public void addItemToRoom(MazeObject obj) {
		board.board[getPlayerLocationY()][getPlayerLocationX()].addItemToRoom(obj);
		
	}


	public void increaseStrength(int effectValue) {
		player.updateStrength(effectValue);
	}

	/**
	 * @return
	 */
	public String getStrength() {
		return player.getHPString();
	}

}
