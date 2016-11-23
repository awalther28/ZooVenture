/**
 * 
 */
package ZooVenture;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author allisonwalther
 *
 */
public class GraphicsFactory {

	private static final Hashtable<String, ImageIcon> ImageIcons = new Hashtable<String, ImageIcon>();
	private static final Hashtable<String, Image> Images = new Hashtable<String, Image>();

	
	
	
	public static Image getImage(String image) {
		if(!Images.containsKey(image))
		{
			Images.put(image, getImagePrivate(image));
		}
		return Images.get(image);
	}
	
	private static Image getImagePrivate(String image)
	{
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+image));
			return img;
		} catch (IOException e) {
			System.out.println("Image was not found.");
			return null;
		}	
	}


	/**
	 * @param string
	 * @return
	 */
	public static ImageIcon getImageIcon(String string) {
		if(!ImageIcons.containsKey(string))
		{
			ImageIcons.put(string, getImageIconPrivate(string));
		}
		return ImageIcons.get(string);
	}
	

	/**
	 * @param string
	 * @return null if no image was found in system else returns ImageIcon 
	 */
	private static ImageIcon getImageIconPrivate(String string) {
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+string));
			Image rescaledImage = null;
			rescaledImage = img.getScaledInstance(233, 466, Image.SCALE_SMOOTH);
			return new ImageIcon(rescaledImage);
		} catch (IOException e) {
			System.out.println("Image was not found.");
			return null;
		}
	}
	
	public static ImageIcon getMiniMapTile(String string, int x, int y)
	{
		if(!ImageIcons.containsKey(string))
		{
			ImageIcons.put(string, processMiniMapTile(string, x, y));
		}
		return ImageIcons.get(string);
	}
	
	public static ImageIcon processMiniMapTile(String string, int x, int y)
	{
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+string));
			Image rescaledImage = null;
			rescaledImage = img.getScaledInstance(y, x, Image.SCALE_SMOOTH);
			return new ImageIcon(rescaledImage);
		} catch (IOException e) {
			System.out.println("Image was not found: "+string);
			return null;
		}
	}

	/**
	 * @param image
	 * @return
	 */
	public static ImageIcon getMazeObjectGraphic(String image) {
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+image));
			Image rescaledImage = null;
			rescaledImage = img.getScaledInstance(img.getWidth()/5, img.getHeight()/5, Image.SCALE_SMOOTH);
			return new ImageIcon(rescaledImage);
		} catch (IOException e) {
			System.out.println("Image was not found.");
			return null;
		}
	}

}
