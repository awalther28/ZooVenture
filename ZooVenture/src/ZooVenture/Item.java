package ZooVenture;
/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class Item extends MazeObject{

	String name;
	String effect;
	int effectValue;
	String image;
	
	
	public Item(String name, String effect, String image, String altImage) {
		super("item", image, altImage);
		
		this.name = name;
		
		if (!effect.equals("None"))
		{
			String info[] = effect.split(":");
			this.effect = info[0];
			this.effectValue = Integer.parseInt(info[1]);
		}
		else
		{
			this.effect = "None";
			this.effectValue = 0;
		}
		
		this.image = image;
	}

	public String getEffect()
	{
		return this.effect;
	}
	
	@Override
	public String toString()
	{
		return this.name+" "+this.effect+" "+this.effectValue;
	}

	/**
	 * @return
	 */
	public int getEffectValue() {
		return this.effectValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (effect == null) {
			if (other.effect != null)
				return false;
		} else if (!effect.equals(other.effect))
			return false;
		if (effectValue != other.effectValue)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
