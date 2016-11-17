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
	boolean isAHabitat;
	ArrayList<MazeObject> contents;
	MazeObject sedatedAnimal;
	
	public Room(char identifier) {
		if (identifier == 'W')
		{
			this.isAWall = true;
		}
		else
		{
			this.isAWall = false;
			this.isAHabitat = false;
		}
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

	/**
	 * @return
	 */
	public int containsLiveAnimal() {
		for (int i = 0; i < this.contents.size(); i++)
		{
			if(this.contents.get(i).type.equals("animal"))
				return i;
		}
		return -1;
	}

	/**
	 * @param animals
	 * @return
	 */
	public boolean containsItems(ArrayList<Animal> animals) {
		return this.contents.containsAll(animals);
	}
}
