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
	String silhouetteImage;
	Tuple habitat;
	
	public Animal(String name, String hp, String strength, String dest, String liveImage, String silhouetteImage, String altImage) {
		super("animal", liveImage, altImage);
		this.name = name;
		this.hp = Integer.parseInt(hp);
		this.strength = Integer.parseInt(strength);
		String coords[] = dest.split(",");
		this.habitat = new Tuple(Integer.parseInt(coords[1]), Integer.parseInt(coords[0]));
		this.liveImage = liveImage;
		this.silhouetteImage = silhouetteImage;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
	public Tuple getHabitat()
	{
		return this.habitat;
	}

	public String getSilhouetteImage()
	{
		return this.silhouetteImage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (habitat == null) {
			if (other.habitat != null)
				return false;
		} else if (!habitat.equals(other.habitat))
			return false;
		if (liveImage == null) {
			if (other.liveImage != null)
				return false;
		} else if (!liveImage.equals(other.liveImage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @param image
	 */
	public void setSilhouetteImage(String image) {
		this.silhouetteImage = image;	
	}


	
}
