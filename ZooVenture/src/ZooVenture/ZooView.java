/**
 * 
 */
package ZooVenture;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class ZooView extends JFrame {
	/**
	 * 
	 */
	private static final String L_OPEN_FLOOR = "lOpenFloor.png";
	private static final String L_OPEN = "lOpen.png";
	private static final String LFLOOR_WALL = "lfloorWall.png";
	private static final String L_WALL = "lWall.png";
	private static final String R_OPEN_FLOOR = "rOpenFloor.png";
	private static final String R_OPEN = "rOpen.png";
	private static final String RFLOOR_WALL = "rfloorWall.png";
	private static final String R_WALL = "rWall.png";
	private static final String C_OPEN = "cOpen.png";
	private static final String C_WALL = "cWall.png";
	ArrayList<JPanel> panels;
	ArrayList<JLabel> labels;
 	GameModel model;
	ControlView view;
	
	
	public ZooView(GameModel gm, ControlView cv)
	{
		this.model = gm;
		this.view = cv;
		this.panels = new ArrayList<JPanel>();
		this.labels = new ArrayList<JLabel>();
		
		for(int i = 0; i < 6; i++)
		{
			JPanel p = new JPanel();
			JLabel l = new JLabel();
			p.add(l);
			this.panels.add(p);
			this.labels.add(l);
		}
		
		this.setPanels();
	}

	public ArrayList<JPanel> getPanels()
	{
		return this.panels;
	}
	
	
	@SuppressWarnings("static-access")
	public void setPanels()
	{
		int x = model.getPlayerLocationX();
		int y = model.getPlayerLocationY();
		String orientation = model.getOrientation();
		JLabel temp = new JLabel();
		
		JPanel p = this.panels.get(4);
		Room room = this.model.getRoom(x, y);
		p.removeAll();
		p.setBackground(new Color(247, 225, 138));
		ArrayList<MazeObject> stuff = room.getContents();
		for(int i = 0; i < stuff.size(); i++)
		{
			JLabel obj = new JLabel(GraphicsFactory.getMazeObjectGraphic(stuff.get(i).getImage()));
			p.add(obj);
		}
		p.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent me)
			{
				if(stuff.size() > 0)
				{
					p.removeAll();
					panels.remove(4);
					panels.add(4, p);
					
					int x = model.getPlayerLocationX();
					int y = model.getPlayerLocationY();
					ArrayList<MazeObject> stuff = model.getRoom(x,y).getContents();
					model.addInventory(stuff);
					view.updateInventory();
					model.removeItemsFromRoom(stuff);
				}
				
			}		
		});
		this.panels.remove(4);
		this.panels.add(4, p);
		
			switch(orientation)
			{
				case("N"):
					if (model.board.board[y-1][x].isAWall)
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_WALL));
						temp.setBackground(Color.black);
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
					else
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_OPEN));
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
				
				
					if (model.board.board[y][x+1].isAWall)
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_WALL));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(RFLOOR_WALL));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					else
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN_FLOOR));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					
					
					if (model.board.board[y][x-1].isAWall)
					{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_WALL));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(LFLOOR_WALL));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
					else{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN_FLOOR));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
				break;
						
				case("S"):
					if (model.board.board[y+1][x].isAWall)
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_WALL));
						temp.setBackground(Color.black);
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
					else
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_OPEN));
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
					
					if (model.board.board[y][x-1].isAWall)
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_WALL));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(RFLOOR_WALL));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					else
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN_FLOOR));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					
					
					if (model.board.board[y][x+1].isAWall)
					{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_WALL));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(LFLOOR_WALL));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
					else{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN_FLOOR));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
					break;
				
					
					
				case("E"):
					if (model.board.board[y][x+1].isAWall)
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_WALL));
						temp.setBackground(Color.black);
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
					else
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_OPEN));
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
				
					if (model.board.board[y+1][x].isAWall)
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_WALL));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(RFLOOR_WALL));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					else
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN_FLOOR));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					
					
					if (model.board.board[y-1][x].isAWall)
					{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_WALL));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(LFLOOR_WALL));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
					else{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN_FLOOR));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
						
					break;
				
				case("W"):
					if (model.board.board[y][x-1].isAWall)
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_WALL));
						temp.setBackground(Color.black);
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
					else
					{
						temp = this.labels.get(1);
						temp.setIcon( GraphicsFactory.getGraphic(C_OPEN));
						this.labels.remove(1);
						this.labels.add(1, temp);
					}
				
					if (model.board.board[y-1][x].isAWall)
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_WALL));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(RFLOOR_WALL));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					else
					{
						temp = this.labels.get(2);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN));
						this.labels.remove(2);
						this.labels.add(2, temp);
						
						temp = this.labels.get(5);
						temp.setIcon( GraphicsFactory.getGraphic(R_OPEN_FLOOR));
						this.labels.remove(5);
						this.labels.add(5, temp);
					}
					
					
					if (model.board.board[y+1][x].isAWall)
					{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_WALL));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(LFLOOR_WALL));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
					else{
						temp = this.labels.get(0);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN));
						this.labels.remove(0);
						this.labels.add(0, temp);
						
						temp = this.labels.get(3);
						temp.setIcon( GraphicsFactory.getGraphic(L_OPEN_FLOOR));
						this.labels.remove(3);
						this.labels.add(3, temp);
					}
						
					break;
			}			
		}

	}
	

