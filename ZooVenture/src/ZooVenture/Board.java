package ZooVenture;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author allisonwalther
 * ZooVenture
 */
public class Board {

	int x;
	int y;
	Room[][] board;
	Tuple playerLocation;
	
	public Board(String boardText, String animalText, String itemText) throws IOException {
		try 
		{
			FileReader fileReader = new FileReader(boardText);		
			BufferedReader stream = new BufferedReader(fileReader);
			
			String[] coordinates = stream.readLine().split(",");
			this.x = Integer.parseInt(coordinates[0]);
			this.y = Integer.parseInt(coordinates[1]);
			
		    this.board = new Room[this.y][this.x];
			
			//creates board with empty rooms
			String line = null;
			for (int i = 0; i < this.y; i ++)
			{	
				line = stream.readLine();
				for (int l = 0; l < this.x; l++)
				{
					if(line.charAt(l) == 'P')
					{
						this.playerLocation = new Tuple(i, l);
						board[i][l] = new Room(' ');
					}
					board[i][l] = new Room(line.charAt(l));
				}
			}
			fileReader.close();
			stream.close();
			
			//add Animals to the room
			this.addAnimals(animalText);
			this.addItems(itemText);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("The board text file was not found.");
		}
	}

	
	private void addItems(String itemText) throws IOException {
		try {
			FileReader fileReader;
			fileReader = new FileReader(itemText);		
			BufferedReader stream = new BufferedReader(fileReader);
					
			String s = stream.readLine();
			while(s != null)
			{
				String[] coordinates = s.split(",");
				int xCoord = Integer.parseInt(coordinates[0]);
				int yCoord = Integer.parseInt(coordinates[1]);			
				this.board[yCoord][xCoord].setContents(new Item(stream.readLine(), stream.readLine(), stream.readLine()));
				s = stream.readLine();
			}
				
			fileReader.close();
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println("The items text file was not found.");
		}
	}

	
	private void addAnimals(String animalText) throws IOException {
		try {
			FileReader fileReader;
			fileReader = new FileReader(animalText);		
			BufferedReader stream = new BufferedReader(fileReader);
		
			String s = stream.readLine();
			while(s != null)
			{
				String[] coordinates = s.split(",");
				int xCoord = Integer.parseInt(coordinates[0]);
				int yCoord = Integer.parseInt(coordinates[1]);			
				this.board[yCoord][xCoord].setContents(new Animal(stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine()));
				s = stream.readLine();
			}
				
			fileReader.close();
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println("The animals text file was not found.");
		}
	}
	
	public Room getRoom(int x, int y)
	{
		return this.board[y][x];
	}
	
	//prints out maze FOR DEV USE ONLY
	@Override
	public String toString()
	{
		int py = this.playerLocation.y;
		int px = this.playerLocation.x;
		String devLines = "";
		String line = "";
		for (int l = 0; l < this.y; l ++)
		{
			for (int i = 0; i < this.x; i++)
			{
				if (l == py & i == px)
				{
					devLines+= "U";
				}
				else
					devLines+= this.board[l][i].toString();	
			}
			line = line + devLines + "\n";
			devLines = "";
		}
		return line;
	}

	public void move(String direction) {
		int py = this.playerLocation.y;
		int px = this.playerLocation.x;
		switch (direction)
		{
		case "N":
			if (py -1 > -1)
			{
				if (!board[(py-1)][px].isAWall)
				{
					this.playerLocation = new Tuple((py-1), px);		
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the zoo. \n\n");
			break;
		case "S":
			if (py +1 < this.y)
			{
				if (!board[(py+1)][px].isAWall)
				{
					this.playerLocation = new Tuple((py+1), px);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the zoo. \n\n");
			break;
		case "E":
			if (px+1 < this.x)
			{
				if (!board[py][px+1].isAWall)
				{
					this.playerLocation = new Tuple(py, px+1);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the zoo. \n\n");
			break;
		case "W":
			if (px-1 > -1)
			{
				if (!board[py][px-1].isAWall)
				{
					this.playerLocation = new Tuple(py, px-1);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the zoo. \n\n");
					break;
				}
				
			} 

}
