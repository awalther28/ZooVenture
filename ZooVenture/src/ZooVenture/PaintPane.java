/**
 * 
 */
package ZooVenture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class PaintPane extends JPanel{
	public JPanel panel;
	private String IMAGE;
	private ArrayList<String> ANIMAL_IMAGES;
	private ArrayList<String> ANIMAL_ALT_IMAGES; 
	private Boolean change;
	
	public PaintPane(String Image)
	{
		panel = new JPanel();
		panel.setVisible(true);
		panel.requestFocus();
		panel.setPreferredSize(new Dimension(233, 466));
		
		this.ANIMAL_IMAGES = new ArrayList<String>();
		this.ANIMAL_ALT_IMAGES = new ArrayList<String>();
		this.IMAGE = Image;
		this.change = false;
	}
	
	public void setImage(String image)
	{
		this.IMAGE = image;
	}
	
	public void setAnimalImages(ArrayList<String> images)
	{
		this.ANIMAL_IMAGES = images;
	}
	
	public void setAltAnimalImages(ArrayList<String> altImages) {
		this.ANIMAL_ALT_IMAGES = altImages;	
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(233,466);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		setOpaque(true);
		
		g.drawImage(GraphicsFactory.getImage(this.IMAGE), 0, 0, null);
		int x = 0;
		int y = 210;
		if(!this.IMAGE.equals("center_wall_in_face.png"))
		{
			for(int i = 0; i < this.ANIMAL_IMAGES.size(); i++)
			{
				if(!this.change)
				{
					g.drawImage(GraphicsFactory.getImage(this.ANIMAL_IMAGES.get(i)), x, y, null);
					this.change = true;
				}
				else
				{
					g.drawImage(GraphicsFactory.getImage(this.ANIMAL_ALT_IMAGES.get(i)), x, y, null);
					this.change = false;
				}
				x+=30;
			}
		}
		else
		{
			if(this.ANIMAL_IMAGES.size() > 0)
			{
				g.setFont(new Font("TimesRoman", Font.BOLD, 25));
				g.setColor(Color.WHITE);
				g.drawString("Rotate around!", 30, 460);
			}
		}
	
	    try {
	        Thread.sleep(100);
	        this.repaint();
	    }
	    catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
