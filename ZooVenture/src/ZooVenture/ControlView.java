/**
 * 
 */
package ZooVenture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 * @author allisonwalther
 *
 */
@SuppressWarnings("serial")
public class ControlView extends JFrame implements ActionListener{
	private ZooView zoo;
	private GameModel model;
	private MiniMapView miniMapView;
	private JLabel health;
	private JLabel strength;
	private JList<String> inventory;
	private JButton rotateLeft;
	//move directions
	private JButton moveForward;
	private JButton rotateRight;
	private JButton moveLeft;
	private JButton moveBackward;
	private JButton moveRight;
	private JButton attack;
	private JScrollPane inventoryScrollPane;
	private JPanel statsPanel;
	private JButton miniMapButton;
	private ArrayList<JPanel> graphicsPanels;
	private JPanel buttons;
	static JTextArea console;
	
	public ControlView(GameModel model)
	{
		super("ZooVenture");
		this.model = model;
		this.miniMapView = new MiniMapView(model);
        this.zoo = new ZooView(model, this);
		
		//create control view panels
		//create all the directional buttons
		this.buttons = createButtonPanel();
        createStatsPanel(); 
        createInventoryPanel();
   
        setLayout(new GridLayout(3,3));
        
        this.graphicsPanels = this.zoo.getPanels();
        for (int i = 0; i < this.graphicsPanels.size(); i++)
        {
        	add(this.graphicsPanels.get(i));
        }
        add(this.buttons);
        add(this.createMiddlePanel());    
        add(this.statsPanel); 
        
		this.requestFocus();
	}
	
	public JPanel createMiddlePanel()
	{
		JPanel panel = new JPanel();
		this.createInventoryPanel();
		console = new JTextArea();
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(this.console);
		pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setPreferredSize(new Dimension(233, 133));
		console.append("Welcome to ZooVenture! \n The objective"
				+ " of the game is to sedate all of the wild zoo animals "
				+ "and return them to their rightful habitats. \n Open up the"
				+ " MiniMap by clicking the button in the bottom right corner."
				+ " You are the black arrow. The black silhouettes of animals "
				+ "symbolize which animal belongs in that habitat. Once you put "
				+ "the right animal(s) [there can more than one animal in a habitat!]"
				+ " in the right habitat, the silhouette will turn into an image of the "
				+ "animal. "
				+ "\n After all the animals are rightfully return, you win! \n "
				+ " If you run out of health while catching the animals, you die. \n"
				+ "Click on items in your inventory to use them or drop them in the room "
				+ "you are currently in. Click on items in a room to pick them all up. \n \n"
				+ " Good luck and have fun! \n\n\n");
		console.setCaretPosition(console.getDocument().getLength());

		panel.add(this.inventoryScrollPane);
		panel.add(pane);
		return panel;
		
	}
	public void createInventoryPanel()
	{
        //creates inventory scroll pane
        //	TODO: add scroll bar, limit number of items on the list
        this.inventory = new JList<String>(model.getInventory());
        this.inventory.setVisibleRowCount(4);
        this.inventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.inventoryScrollPane = new JScrollPane(this.inventory);
        this.inventoryScrollPane.setBackground(Color.WHITE);
        this.inventoryScrollPane.setPreferredSize(new Dimension(233, 100));
        this.inventoryScrollPane.createVerticalScrollBar();
        this.inventoryScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this.inventoryScrollPane.setViewportBorder(BorderFactory.createEmptyBorder(30,15,30, 15));
	}
	
	public void createStatsPanel()
	{
        this.health = new JLabel("HEALTH:"+ model.getHealth());
		this.health.setOpaque(true);
		this.health.setBackground(Color.WHITE);
        
		this.strength = new JLabel("STRENGTH:" + model.getStrength());
		this.strength.setOpaque(true);
		this.strength.setBackground(Color.WHITE);
		
		//create miniMapButton
		this.miniMapButton = new JButton("MiniMap");
		this.miniMapButton.setEnabled(true);
		this.miniMapButton.setActionCommand("Open");
		this.miniMapButton.addActionListener(this);
		
        this.statsPanel = new JPanel();
        this.statsPanel.setLayout(new GridLayout(3,1));
        this.statsPanel.add(this.health);
        this.statsPanel.add(this.strength);
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
        this.attack = new JButton("<html><center>Sedate<br/>Animal</center></html>");
        
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
        
        this.attack.setEnabled(true);
        this.attack.setActionCommand("attack");
        this.attack.addActionListener(this);
        this.attack.setMnemonic(KeyEvent.VK_SPACE);
        
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
        buttons.add(this.attack);
        buttons.add(this.moveRight);
        buttons.add(new JLabel());
        buttons.add(this.moveBackward);
        buttons.add(new JLabel());
        
        buttons.setOpaque(true);   
        buttons.setBackground(Color.WHITE);
        
        return buttons;
	}
	
	
	//updates the mini map and sets the graphics panels
	public void updateGraphics()
	{
		this.miniMapView.updateMap();
		this.zoo.setPanels();
		this.updateInventory();
		this.updateHealth();
	}
	
	public void updateHealth()
	{
		this.health.setText(("HEALTH:"+ this.model.getHealth()));	
	}
	
	public void updateInventory()
	{
		this.inventory.setListData(this.model.getInventory());
	}
	
	public void checkRoom()
	{
		int animalIndex = model.checkForAnimal();
		if(animalIndex > -1)
		{
			this.animalEncounter(animalIndex);
		}
	}

	public void animalEncounter(int animalIndex)
	{
		int keepGoing = model.animalEncounter(animalIndex);
		this.updateHealth();
		if (keepGoing == 0)
		{
			//you lost the game
			//remove items from frame and inform player that they lost
			remove(this.buttons);
	        remove(this.inventoryScrollPane);    
	        remove(this.statsPanel); 
	        
	        for (int i = 0; i < this.graphicsPanels.size(); i++)
	        {
	        	remove(this.graphicsPanels.get(i));
	        }
	
	        getContentPane().setBackground(Color.BLACK);
			setLayout(new BorderLayout());
			JLabel lost = new JLabel(); //"You lost...");
			lost.setIcon(GraphicsFactory.getOriginalImage("lost.png"));
			lost.setHorizontalAlignment(JLabel.CENTER);
			lost.setVerticalAlignment(JLabel.CENTER);
			add(lost, BorderLayout.CENTER);
		}
		else if (keepGoing == -1)
		{
			model.sedateAnimal(animalIndex);
		}
		
		this.updateGraphics();
		
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
		if(action.equals("attack"))
		{
			this.checkRoom();
		}
				
		this.updateGraphics();
	}
	
	public void registerListener(InventoryListener listener)
	{
		this.inventory.addListSelectionListener(listener);
	}

	/**
	 * @returns null if no object is selected from the inventory list otherwise returns object
	 * of item in inventory list
	 */
	public MazeObject getSelectedObejct() {
		int index = this.inventory.getSelectedIndex();
		if(index != -1)
		{
			return model.getItemInInventory(index);
		}
		return null;
	}

	//update control view
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		updateGraphics();
//		g.setColor(Color.WHITE);
//		g.drawLine(0, 0, 700, 700);
//		g.draw3DRect(233, 300, 100, 100, true);
	
	    try {
	        Thread.sleep(20);
	        this.repaint();
	    }
	    catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * 
	 */
	public void win() {
		remove(this.buttons);
        remove(this.inventoryScrollPane);    
        remove(this.statsPanel); 
        
        for (int i = 0; i < this.graphicsPanels.size(); i++)
        {
        	remove(this.graphicsPanels.get(i));
        }

        getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		JLabel won = new JLabel("You WON!");
		//lost.setIcon(GraphicsFactory.getOriginalImage("lost.png"));
		won.setHorizontalAlignment(JLabel.CENTER);
		won.setVerticalAlignment(JLabel.CENTER);
		add(won, BorderLayout.CENTER);
	}

}
