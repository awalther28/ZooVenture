package ZooVenture;
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
	Tuple habitat;
	
	public Animal(String name, String hp, String strength, String dest, String liveImage, String sedatedImage) {
		super("animal", liveImage);
		this.name = name;
		this.hp = Integer.parseInt(hp);
		this.strength = Integer.parseInt(strength);
		String coords[] = dest.split(",");
		this.habitat = new Tuple(Integer.parseInt(coords[1]), Integer.parseInt(coords[0]));
		this.liveImage = liveImage;
		this.sedatedImage = sedatedImage;
	}

	@Override
	public String toString()
	{
		return this.name+" "+this.hp+" "+this.strength;
	}
	
}
