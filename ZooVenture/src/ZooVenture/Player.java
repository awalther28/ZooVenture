package ZooVenture;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class Player {
	int hp;
	int strength;
	ArrayList<MazeObject> inventory;
	
	public Player()
	{
		this.hp = 100;
		this.strength = 10;
		this.inventory = new ArrayList<MazeObject>();
	}
	
	public String[] getInventory()
	{
		String[] list = {"Tiger", "Lion", "Water", "PowerBar", "Water"};
		for(int i = 0; i < this.inventory.size(); i++)
		{
			list[i] = this.inventory.get(i).toString();
		}
		return list;
	}

	/**
	 * @return
	 */
	public String getHealth() {
		return String.valueOf(this.hp);
	}
	
}
