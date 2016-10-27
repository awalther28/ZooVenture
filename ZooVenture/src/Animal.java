/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class Animal extends MazeObject {

	String name;
	int hp;
	int strength;
	String liveImage;
	String sedatedImage;
	
	public Animal(String name, String hp, String strength, String liveImage, String sedatedImage) {
		super("animal");
		this.name = name;
		this.hp = Integer.parseInt(hp);
		this.strength = Integer.parseInt(strength);
		this.liveImage = liveImage;
		this.sedatedImage = sedatedImage;
	}

	@Override
	public String toString()
	{
		return this.name+" "+this.hp+" "+this.strength;
	}
	
}
