package ZooVenture;
/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class MazeObject {
	String type;
	String image;
	String altImage;
	
	public MazeObject(String type, String image, String altImage)
	{
		this.type = type;
		this.image = image;
		this.altImage = altImage;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof MazeObject)
		{
			MazeObject obj = (MazeObject) other;
			if (this.type.equals(obj.type))
				return true;
			else
				return false;
		}
		else
			return false;		
	}
	
	public int compareTo(Object other)
	{
		if (other instanceof MazeObject)
		{
			MazeObject obj = (MazeObject) other;
			if (this.type.equals(obj.type))
				return 0;
			else
				return 1;
		}
		else
			return 1;		
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.type);
	}

	/**
	 * @return
	 */
	public String getAltImage() {
		return this.altImage;
	}

	/**
	 * @param image2
	 */
	public void setAltImage(String image2) {
		this.altImage = image2;
	}
}
