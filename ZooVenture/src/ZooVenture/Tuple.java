package ZooVenture;
/**
 * 
 */

/**
 * @author allisonwalther
 *
 */
public class Tuple {
	int y;
	int x;
	
	public Tuple(int first, int second)
	{
		this.y = first;
		this.x = second;
	}
	
	public void print()
	{
		System.out.println(this.y + ", " + this.x);
	}
	
	public int getFirst()
	{
		return this.y;
	}
	
	public int getSecond()
	{
		return this.x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (y != other.y)
			return false;
		if (x != other.x)
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return this.y+","+this.x;
	}
}
