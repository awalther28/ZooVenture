/**
 * 
 */
package ZooVenture;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author allisonwalther
 *
 */
public class InventoryListener implements ListSelectionListener {
	
	private GameModel model;
	private ControlView view;
	
	public InventoryListener(GameModel model, ControlView view)
	{
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!equals(e.getValueIsAdjusting()))
		{
			MazeObject obj = view.getSelectedObejct();
			
			if(obj == null)
			{
				return;
			}
			
			if(obj.getType().equals("animal"))
			{
				System.out.println(obj.getImage());
				this.model.addItemToRoom(obj);
				this.model.removeItemFromInventory(obj);
				this.view.updateGraphics();
			}
			
			if(obj.getType().equals("item"))
			{
				String effect = ((Item) obj).getEffect();
				int effectValue = ((Item) obj).getEffectValue();
				switch (effect)
				{
				case("Increase HP"):
					this.model.increaseHP(effectValue);
					this.model.removeItemFromInventory(obj);
					this.view.updateGraphics();
					break;
				}
				
			}
		}
	}

	
	//add in how to react to selected items
	
}
