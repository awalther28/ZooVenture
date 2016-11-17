package ZooVenture;

import java.util.ArrayList;

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
		this.hp = 1000;
		this.strength = 50;
		this.inventory = new ArrayList<MazeObject>();
	}
	
	//returns string array of inventory
	//used for setting the JList
	public String[] getInventory()
	{
		String[] list = new String[this.inventory.size()];
		for(int i = 0; i < this.inventory.size(); i++)
		{
			list[i] = this.inventory.get(i).toString();
		}
		return list;
	}

	/**
	 * @returns string of hp
	 */
	public String getHPString() {
		return String.valueOf(this.hp);
	}
	
	public String getStrengthString()
	{
		return String.valueOf(this.strength);
	}

	/**
	 * @param array list of MazeObjects to be added to inventory
	 */
	public void addAllInventory(ArrayList<MazeObject> stuff) {
		this.inventory.addAll(stuff);
	}
	
	public void removeInventoryItem(MazeObject object)
	{
		this.inventory.remove(object);
	}
	
	public MazeObject getItemInInventory(int index)
	{
		return this.inventory.get(index);
	}

	/**
	 * @param int
	 */
	public void updateHP(int num) {
		this.hp = this.hp + num;
	}
	
	//returns int
	public int getHP()
	{
		return this.hp;
	}

	/**
	 * @param effectValue
	 */
	public void updateStrength(int effectValue) {
		this.strength += effectValue;
	}

	/**
	 * @param object
	 */
	public void addInventory(MazeObject object) {
		this.inventory.add(object);		
	}
	
}
