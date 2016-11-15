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

	private static final Hashtable<String, ImageIcon> images = new Hashtable<String, ImageIcon>();

	/**
	 * @param string
	 * @return
	 */
	public static ImageIcon getGraphic(String string) {
		if(!images.containsKey(string))
		{
			images.put(string, getPanelGraphic(string));
		}
		return images.get(string);
	}

	/**
	 * @param string
	 * @return null if no image was found in system else returns ImageIcon 
	 */
	private static ImageIcon getPanelGraphic(String string) {
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+string));
			Image rescaledImage = null;
			rescaledImage = img.getScaledInstance(233, 233, Image.SCALE_SMOOTH);
			return new ImageIcon(rescaledImage);
		} catch (IOException e) {
			System.out.println("Image was not found.");
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
	
	public static ImageIcon getOriginalImage(String image) {
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+image));
			return new ImageIcon(img);
		} catch (IOException e) {
			System.out.println("Image was not found.");
			return null;
		}
	}

}
