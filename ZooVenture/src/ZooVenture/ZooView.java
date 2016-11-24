/**
 * 
 */
package ZooVenture;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class ZooView extends JComponent {

	JLabel left;
	JLabel right;
	PaintPane paintPane;
 	GameModel model;
	ControlView view;
	
	
	public ZooView(GameModel gm, ControlView cv)
	{
		this.model = gm;
		this.view = cv;

		this.left = new JLabel();
		this.right = new JLabel();
		this.paintPane = new PaintPane("center_no_wall_back.png");
		
		this.setPanels();
	}
	
	public JLabel getLeft()
	{
		return this.left;
	}
	
	public JLabel getRight()
	{
		return this.right;
	}
	
	public PaintPane getMiddle()
	{
		return this.paintPane;
	}
	
	public void setPanels()
	{
		int x = model.getPlayerLocationX();
		int y = model.getPlayerLocationY();
		String orientation = model.getOrientation();
		Room[][] board = model.board.board;
		
		ArrayList<MazeObject> stuff = board[y][x].getContents();
		ArrayList<String> images = new ArrayList<String>();
		ArrayList<String> altImages = new ArrayList<String>();
		for(int i = 0; i < stuff.size(); i++)
		{
			images.add(stuff.get(i).getImage());
			altImages.add(stuff.get(i).getAltImage());
		}
		this.paintPane.setAnimalImages(images);
		this.paintPane.setAltAnimalImages(altImages);
		
		this.paintPane.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent me)
			{
				if(stuff.size() > 0)
				{	
					int x = model.getPlayerLocationX();
					int y = model.getPlayerLocationY();
					ArrayList<MazeObject> stuff = model.getRoom(x,y).getContents();
					ArrayList<MazeObject> validContents = new ArrayList<MazeObject>();
					for(int i = 0; i < stuff.size(); i ++)
					{
						if(! stuff.get(i).type.equals("animal"))
						{
							validContents.add(stuff.get(i));
						}
					}
					model.addAllInventory(validContents);
					view.updateInventory();
					model.removeItemsFromRoom(validContents);
				}
				
			}		
		});
		
		switch(orientation)
		{
			case("N"):
				//set right view
				if(board[y][x+1].isAWall)
				{
					if(board[y-1][x+1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_wall_both.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_front.png"));
				}
				else
				{
					if(board[y-1][x+1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_back.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_both.png"));
				}
			
				//set middle view
				if(board[y-1][x].isAWall)
					this.paintPane.setImage("center_wall_in_face.png");
				else if(board[y-2][x].isAWall)
					this.paintPane.setImage("center_wall.png");
				else
					this.paintPane.setImage("center_no_wall_back.png");
			
				//set left view
				if(board[y][x-1].isAWall)
				{
					if(board[y-1][x-1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_wall_both.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_front.png"));
				}
				else
				{
					if(board[y-1][x-1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_back.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_both.png"));
				}
				
				break;
				
			case("S"):
				//set right view
				if(board[y][x-1].isAWall)
				{
					if(board[y+1][x-1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_wall_both.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_front.png"));
				}
				else
				{
					if(board[y+1][x-1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_back.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_both.png"));
				}
			
				//set middle view
				if(board[y+1][x].isAWall)
					this.paintPane.setImage("center_wall_in_face.png");
				else if(board[y+2][x].isAWall)
					this.paintPane.setImage("center_wall.png");
				else
					this.paintPane.setImage("center_no_wall_back.png");
			
				//set left view
				if(board[y][x+1].isAWall)
				{
					if(board[y+1][x+1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_wall_both.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_front.png"));
				}
				else
				{
					if(board[y+1][x+1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_back.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_both.png"));
				}
				
				break;
				
			case("E"):
				//set right view
				if(board[y+1][x].isAWall)
				{
					if(board[y+1][x+1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_wall_both.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_front.png"));
				}
				else
				{
					if(board[y+1][x+1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_back.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_both.png"));
				}
			
				//set middle view
				if(board[y][x+1].isAWall)
					this.paintPane.setImage("center_wall_in_face.png");
				else if(board[y][x+2].isAWall)
					this.paintPane.setImage("center_wall.png");
				else
					this.paintPane.setImage("center_no_wall_back.png");
			
				//set left view
				if(board[y-1][x].isAWall)
				{
					if(board[y-1][x+1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_wall_both.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_front.png"));
				}
				else
				{
					if(board[y-1][x+1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_back.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_both.png"));
				}
				
				break;
				
			case("W"):
				//set right view
				if(board[y-1][x].isAWall)
				{
					if(board[y-1][x-1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_wall_both.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_front.png"));
				}
				else
				{
					if(board[y-1][x-1].isAWall)
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_back.png"));
					else
						this.right.setIcon(GraphicsFactory.getImageIcon("right_no_wall_both.png"));
				}
			
				//set middle view
				if(board[y][x-1].isAWall)
					this.paintPane.setImage("center_wall_in_face.png");
				else if(board[y][x-2].isAWall)
					this.paintPane.setImage("center_wall.png");
				else
					this.paintPane.setImage("center_no_wall_back.png");
			
				//set left view
				if(board[y+1][x].isAWall)
				{
					if(board[y+1][x-1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_wall_both.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_front.png"));
				}
				else
				{
					if(board[y+1][x-1].isAWall)
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_back.png"));
					else
						this.left.setIcon(GraphicsFactory.getImageIcon("left_no_wall_both.png"));
				}
				
				break;
		}
		
		
	}	
}
	

