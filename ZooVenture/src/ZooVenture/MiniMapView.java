/**
 * 
 */
package ZooVenture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author allisonwalther
 *
 */
public class MiniMapView extends JFrame{
	private GameModel model;
	private JPanel[][] graphicBoard;
	private JLabel[][] labelBoard;
	
	public MiniMapView(GameModel model)
	{ 
		this.model = model;
		setSize(400,400);
		setLocation(900,300);
		setLayout(new GridLayout(model.getY(), model.getX()));
		
		JPanel roomGraphic = null;
		JLabel roomLabel = null;
		Room room = null;
		this.graphicBoard = new JPanel[model.getY()][model.getX()];
		this.labelBoard = new JLabel[model.getY()][model.getX()];
		for(int y = 0; y < model.getY(); y++)
		{
			for(int x = 0; x < model.getX(); x++)
			{
				roomGraphic = new JPanel();
				roomLabel = new JLabel();
				roomGraphic.add(roomLabel);
				room = model.getRoom(x, y);
				if (room.isAWall)
				{
					addImage(roomGraphic, roomLabel, "foliage.jpg", Color.BLACK);
				}
				else if(room.getContents().size() > 0)
				{
					if (room.getContents().size() > 2)
					{
						roomGraphic.setBackground(Color.RED);
					}
					else if (room.getContents().size() == 2)
					{
						roomGraphic.setBackground(Color.GREEN);
					}
					else if (room.getContents().size() == 1)
					{
						roomGraphic.setBackground(Color.DARK_GRAY);
					}
				}
				else
					roomGraphic.setBackground(Color.WHITE);

				this.graphicBoard[y][x] = roomGraphic;
				this.labelBoard[y][x] = roomLabel;
				add(roomGraphic);
			}
		}
		
	
		
	}
	
	public void updateMap()
	{	
		int x = model.getPlayerLocationX();
		int y = model.getPlayerLocationY();
		
		switch(model.getOrientation()){
			case("S"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "S.png", Color.YELLOW);
				break;
			case("N"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "N.png", Color.YELLOW);
				break;
			case("W"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "W.png", Color.YELLOW);
				break;
			case("E"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "E.png", Color.YELLOW);
				break;
		}
	}
	
	public void addImage(JPanel panel, JLabel label, String image, Color altColor)
	{
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+image));
			Image rescaledImage = null;
			if (panel.getWidth() == 0 & panel.getHeight() == 0)
			{
				rescaledImage = img.getScaledInstance(50, 70, Image.SCALE_SMOOTH);
			}
			else
			{
				// http://stackoverflow.com/questions/10634417/image-resize-to-fit-on-jpanel
				rescaledImage = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
			}
			label.setIcon(new ImageIcon(rescaledImage));
			panel.setBackground(Color.WHITE);
		} catch (IOException e) {
			System.out.println("Image was not found.");
			label.setBackground(altColor);	
		}
	}
	
	public void prepMapForMove()
	{
		this.labelBoard[model.getPlayerLocationY()][model.getPlayerLocationX()].setIcon(new ImageIcon("nothing"));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.setColor(Color.GREEN);
		//updateMap();
        try {
            Thread.sleep(20);
            this.repaint();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @return the graphicBoard
	 */
	public JPanel[][] getGraphicBoard() {
		return graphicBoard;
	}

	/**
	 * @param graphicBoard the graphicBoard to set
	 */
	public void setGraphicBoard(JPanel[][] graphicBoard) {
		this.graphicBoard = graphicBoard;
	}

	/**
	 * @param b
	 */

	
	
}
