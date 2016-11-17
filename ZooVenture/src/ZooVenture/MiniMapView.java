/**
 * 
 */
package ZooVenture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class MiniMapView extends JFrame{
	private GameModel model;
	private JPanel[][] graphicBoard;
	private JLabel[][] labelBoard;
	private JLabel[][] indicatorBoard;
	private final int X_DIMENSION = 400;
	private final int Y_DIMENSION = 400;
	
	public MiniMapView(GameModel model)
	{ 
		this.model = model;
		setSize(X_DIMENSION,Y_DIMENSION);
		setLocation(900,300);
		GridLayout layout = new GridLayout(model.getY(), model.getX());
		layout.setHgap(1);
		layout.setVgap(1);
		setLayout(layout);
		setBackground(Color.LIGHT_GRAY);
		
		
		JPanel roomGraphic = null;
		JLabel roomLabel = null;
		Room room = null;
		this.graphicBoard = new JPanel[model.getY()][model.getX()];
		this.labelBoard = new JLabel[model.getY()][model.getX()];
		this.indicatorBoard = new JLabel[model.getY()][model.getX()];
		for(int y = 0; y < model.getY(); y++)
		{
			for(int x = 0; x < model.getX(); x++)
			{
				roomGraphic = new JPanel();
				roomLabel = new JLabel();
				JLabel indicator = new JLabel();
				roomLabel.setHorizontalAlignment(JLabel.CENTER);
		        roomLabel.setVerticalAlignment(JLabel.CENTER);
				roomGraphic.setLayout(new BorderLayout());
				roomGraphic.add(roomLabel);
				room = model.getRoom(x, y);
				if (room.isAWall)
				{
					addImage(roomGraphic, roomLabel, "foliage.jpg", new Color(9, 109, 14));
				}
				if (room.isAHabitat)
				{
					//roomGraphic.add(indicator);
					//figure out how to structure visual of habitat indication on minimap
					String animalImage = this.model.getInhabitantImage(y,x);
					addImage(roomGraphic, roomLabel, animalImage, new Color(9, 109, 14));
				}
				else if(room.getContents().size() > 0)
				{
					if (room.getContents().size() > 2)
					{
						roomGraphic.add(indicator, BorderLayout.NORTH);
						addImage(roomGraphic, indicator, "redSquare.png", Color.RED);
					}
					else if (room.getContents().size() == 2)
					{
						roomGraphic.add(indicator, BorderLayout.NORTH);
						addImage(roomGraphic, indicator, "greenSquare.png", Color.GREEN);
					}
					else if (room.getContents().size() == 1)
					{
						roomGraphic.add(indicator, BorderLayout.NORTH);
						addImage(roomGraphic, indicator, "blackSquare.png", Color.BLACK);
					}
				}
				else
					roomGraphic.setBackground(Color.WHITE);

				this.indicatorBoard[y][x] = indicator;
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
		
		updateIndicatorBoard();
		this.indicatorBoard[y][x].setIcon(new ImageIcon("nothing"));
		
		switch(model.getOrientation()){
			case("S"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "S.png", Color.WHITE);
				break;
			case("N"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "N.png", Color.WHITE);
				break;
			case("W"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "W.png", Color.WHITE);
				break;
			case("E"):
				addImage(this.graphicBoard[y][x], this.labelBoard[y][x], "E.png", Color.WHITE);
				break;
		}
	}
	
	public void updateIndicatorBoard()
	{
		for(int y = 0; y < model.getY(); y++)
		{
			for(int x = 0; x < model.getX(); x++)
			{
				Room room = model.getRoom(x, y);
				if(!room.isAWall & room.getContents().size() > 0)
				{
					if (room.getContents().size() > 2)
					{
						addImage(this.graphicBoard[y][x], this.indicatorBoard[y][x], "redSquare.png", Color.WHITE, Color.WHITE);
					}
					else if (room.getContents().size() == 2)
					{
						addImage(this.graphicBoard[y][x], this.indicatorBoard[y][x], "greenSquare.png", Color.WHITE, Color.WHITE);
					}
					else if (room.getContents().size() == 1)
					{
						addImage(this.graphicBoard[y][x], this.indicatorBoard[y][x], "blackSquare.png", Color.WHITE,Color.WHITE);
					}
				}
				if(room.isAHabitat)
				{
					String animalImage = this.model.getInhabitantImage(y,x);
					addImage(this.graphicBoard[y][x], this.labelBoard[y][x], animalImage, new Color(9, 109, 14));
				}
				else
					this.graphicBoard[y][x].setBackground(Color.WHITE);
			}
		}
	}
	
			
	public void addImage(JPanel panel, JLabel label, String image, Color altColor)
	{
			ImageIcon imageIcon = null;
			if (panel.getWidth() == 0 & panel.getHeight() == 0)
			{
				int x = this.X_DIMENSION/model.getX();
				int y = this.Y_DIMENSION/model.getY();
				imageIcon = GraphicsFactory.getMiniMapTile(image, x, y);
			}
			else
			{
				// http://stackoverflow.com/questions/10634417/image-resize-to-fit-on-jpanel
				imageIcon = GraphicsFactory.getMiniMapTile(image, panel.getWidth(), panel.getHeight());
			}
			label.setIcon(imageIcon);
			panel.setBackground(altColor);
			label.setBackground(altColor);	
	}
	
	public void addImage(JPanel panel, JLabel label, String image, Color altColor, Color backgroundColor)
	{
		BufferedImage img;
		try {		
			img = ImageIO.read(new File("src/Images/"+image));
			Image rescaledImage = null;
			if (panel.getWidth() == 0 & panel.getHeight() == 0)
			{
				rescaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			}
			else
			{
				// http://stackoverflow.com/questions/10634417/image-resize-to-fit-on-jpanel
				rescaledImage = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
			}
			label.setIcon(new ImageIcon(rescaledImage));
			panel.setBackground(backgroundColor);
		} catch (IOException e) {
			System.out.println("Image was not found: " + image);
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
}
