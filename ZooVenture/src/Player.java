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
		this.hp = 100;
		this.strength = 10;
		this.inventory = new ArrayList<MazeObject>();
	}
	
}
