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
	
	
	public Item(String name, String effect, String image) {
		super("item");
		
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

	@Override
	public String toString()
	{
		return this.name+" "+this.effect+" "+this.effectValue;
	}
}
