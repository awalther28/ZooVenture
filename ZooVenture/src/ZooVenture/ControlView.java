/**
 * 
 */
package ZooVenture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class ControlView extends JFrame implements ActionListener{
	//private ZooView zoo;
	private GameModel model;
	private MiniMapView miniMapView;
	private JLabel health;
	private JList<String> inventory;
	private JButton rotateLeft;
	//move directions
	private JButton moveForward;
	private JButton rotateRight;
	private JButton moveLeft;
	private JButton moveBackward;
	private JButton moveRight;
	private JScrollPane inventoryScrollPane;
	private JPanel statsPanel;
	private JButton miniMapButton;
	
	public ControlView(GameModel model)
	{
		super("ZooVenture");
		this.model = model;
		this.miniMapView = new MiniMapView(model);
		
		//create control view panels
		//create all the directional buttons
		JPanel buttons = createButtonPanel();
        createStatsPanel(); 
        createInventoryPanel();
   
        setLayout(new GridLayout(3,3));
        //add to overall frame
        // TODO: create ZooView(model, this) which will create the panels for the ZooView
        // this will come in a later version
        add(new JPanel()); //top left graphic component (sky)
        add(new JPanel()); //top middle graphic component (sky)
        add(new JPanel()); //top right graphic component (sky)
        add(new JPanel()); //bottom left graphic component (ground)
        add(new JPanel()); //bottom middle graphic component (ground)
        add(new JPanel()); //bottom right graphic component (ground)
        add(buttons);
        add(this.inventoryScrollPane);    
        add(this.statsPanel); 
	
		this.requestFocus();
	}
	
	public void createInventoryPanel()
	{
        //creates inventory scroll pane
        //	TODO: add scroll bar, limit number of items on the list
        this.inventory = new JList<String>(model.getInventory());
        this.inventory.setVisibleRowCount(4);
        this.inventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.inventoryScrollPane = new JScrollPane(this.inventory);
        //this.inventoryScrollPane.createVerticalScrollBar();
        //this.inventoryScrollPane.setViewportBorder(BorderFactory.createEmptyBorder(30,15,30, 15));
	}
	
	public void createStatsPanel()
	{
        this.health = new JLabel("HEALTH:"+ model.getHealth());
		this.health.setOpaque(true);
		this.health.setBackground(Color.WHITE);
        
		//create miniMapButton
		this.miniMapButton = new JButton("MiniMap");
		this.miniMapButton.setEnabled(true);
		this.miniMapButton.setActionCommand("Open");
		this.miniMapButton.addActionListener(this);
		
        this.statsPanel = new JPanel();
        this.statsPanel.setLayout(new GridLayout(3,1));
        this.statsPanel.add(this.health);
        this.statsPanel.add(new JLabel());
        this.statsPanel.add(this.miniMapButton);
        
        this.statsPanel.setBackground(Color.WHITE);
	}
	
	public JPanel createButtonPanel()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(3,3));
		//buttons.setBounds(0, 700, 200, 200);
		
		//http://stackoverflow.com/questions/13503280/new-line-n-is-not-working-in-jbutton-settextfnord-nfoo
		// used to figure out how to wrap text on button
        this.rotateLeft = new JButton("<html><center>Rotate<br/>Left</center></html>");
        this.rotateRight = new JButton("<html><center>Rotate<br/>Right</center></html>");
        this.moveForward = new JButton("Forward");
        this.moveLeft = new JButton("Left");
        this.moveBackward = new JButton("Backward");
        this.moveRight = new JButton("Right"); 
        
        //enable all the buttons
        this.rotateLeft.setEnabled(true);
        this.rotateLeft.setActionCommand("Rotate Left");
        this.rotateLeft.addActionListener(this);
        
        this.rotateRight.setEnabled(true);
        this.rotateRight.setActionCommand("Rotate Right");
        this.rotateRight.addActionListener(this);
        
        this.moveLeft.setEnabled(true);
        this.moveLeft.setActionCommand("W");
        this.moveLeft.addActionListener(this);
        this.moveLeft.setMnemonic(KeyEvent.VK_LEFT);
        
        this.moveRight.setEnabled(true);
        this.moveRight.setActionCommand("E");
        this.moveRight.addActionListener(this);
        this.moveRight.setMnemonic(KeyEvent.VK_RIGHT);
        
        this.moveBackward.setEnabled(true);
        this.moveBackward.setActionCommand("S");
        this.moveBackward.addActionListener(this);
        this.moveBackward.setMnemonic(KeyEvent.VK_DOWN);
        
        this.moveForward.setEnabled(true); 
        this.moveForward.setActionCommand("N");
        this.moveForward.addActionListener(this);
        this.moveForward.setMnemonic(KeyEvent.VK_UP);
        
        buttons.add(this.rotateLeft);
        buttons.add(this.moveForward);
        buttons.add(this.rotateRight);
        buttons.add(this.moveLeft);
        buttons.add(new JLabel());
        buttons.add(this.moveRight);
        buttons.add(new JLabel());
        buttons.add(this.moveBackward);
        buttons.add(new JLabel());
        
        buttons.setOpaque(true);   
        buttons.setBackground(Color.WHITE);
        
        return buttons;
	}
	
	
	//updates the mini map
	public void updateGraphics()
	{
		this.miniMapView.updateMap();
		//also update zoo panel
	}
	
	//update control view
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.setColor(Color.DARK_GRAY);
		updateGraphics();
		
		
        try {
            Thread.sleep(20);
            this.repaint();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	
	//handle button clicked actions
	@Override 
	public void actionPerformed(ActionEvent ae)
	{
		String action = ae.getActionCommand();
		if(action.equals("Open"))
		{
			this.miniMapView.setVisible(true);
		}
		if(action.equals("S"))
		{
			this.miniMapView.prepMapForMove();
			this.model.move("S");
		}
		if(action.equals("N"))
		{
			this.miniMapView.prepMapForMove();
			this.model.move("N");
		}
		if(action.equals("W"))
		{
			this.miniMapView.prepMapForMove();
			this.model.move("W");
		}
		if(action.equals("E"))
		{
			this.miniMapView.prepMapForMove();
			this.model.move("E");
		}
		if(action.equals("Rotate Right"))
		{
			model.changeOrientation("R");
		}
		if(action.equals("Rotate Left"))
		{
			model.changeOrientation("L");
		}
				
		this.updateGraphics();
	}

}
