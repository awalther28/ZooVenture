/**
 * 
 */
package ZooVenture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.Timer;


/**
 * @author allisonwalther
 *
 */
public class ZooTimer implements ActionListener{
	private Timer timer;
	public int second;
	public int minute;
	public int hour;
	public ControlView view;
	public GameModel model;
	
	public ZooTimer(ControlView view, GameModel gameModel)
	{
		this.view = view;
		this.model = gameModel;
		this.second = 0;
		this.minute = 0;
		this.hour = 0;
		this.timer = new Timer(1000, this);
		this.timer.start();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.second == 60)
		{
			this.second = 0;
			this.minute++;
			if(this.minute == 60)
			{
				this.hour++;
				this.minute = 0;
			}
		}
		else
			this.second++;
		view.time.setText("TIME: "+this.hour+":"+this.minute+":"+this.second);
	}
	
	public void stop()
	{
		this.timer.stop();
	}
	
	public String getTime()
	{
		return this.hour+":"+this.minute+":"+this.second;
	}
	
	public void updateTimeFile(String name)
	{
		ArrayList<String> contents = new ArrayList();
		try {
			FileReader fileReader = new FileReader("./time.txt");		
			BufferedReader stream = new BufferedReader(fileReader);
			String s = stream.readLine();
			String[] line;
			String[] times;
			Boolean used = false;
			while(s != null)
			{
//					line = s.split(" ");
//					times = line[1].split(":");
//					if(Integer.parseInt(times[0]) < this.hour & Integer.parseInt(times[1]) < this.minute & Integer.parseInt(times[2]) < this.second)
//						contents.add(s);
//					else if (!used)
//					{
//						contents.add(s);
//						contents.add(name+" "+this.hour+":"+this.minute+":"+this.second);
//						used = true;
//					}
				contents.add(s);
					s = stream.readLine();
			}
			stream.close();
			fileReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("./time.txt"), "utf-8")))
		{
			for(int i = 0; i < contents.size(); i++)
			{
				writer.write(contents.get(i) +"\n");
			}
			writer.write(name+" "+this.hour+":"+this.minute+":"+this.second);

			writer.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


