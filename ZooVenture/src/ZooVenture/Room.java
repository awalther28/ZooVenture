package ZooVenture;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class Room {

	boolean isAWall;
	ArrayList<MazeObject> contents;
	
	public Room(char identifier) {
		if (identifier == 'W')
		{
			this.isAWall = true;
		}
		else
			this.isAWall = false;
		this.contents = new ArrayList<MazeObject>();
	}
	
	public void setContents(MazeObject stuff)
	{
		this.contents.add(stuff);
	}
	
	public ArrayList<MazeObject> getContents()
	{
		return this.contents;
	}
	
	@Override
	public String toString()
	{
		if (this.isAWall)
			return "W";
		else if (this.contents.size() > 0)
			return "X";
		else
			return " ";
	}

	/**
	 * @param arraylist removes items from room
	 */
	public void removeItemsFromRoom(ArrayList<MazeObject> stuff) {
		this.contents.removeAll(stuff);
		
	}

	/**
	 * @param mazeobject to be added to room
	 */
	public void addItemToRoom(MazeObject obj) {
		this.contents.add(obj);	
	}
}
