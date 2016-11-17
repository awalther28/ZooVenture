package ZooVenture;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

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
	Hashtable<Tuple, ArrayList<Animal>> habitatDirectory;
	
	public Board(String boardText, String animalText, String itemText) throws IOException {
		try 
		{
			FileReader fileReader = new FileReader(boardText);		
			BufferedReader stream = new BufferedReader(fileReader);
			
			String[] coordinates = stream.readLine().split(",");
			this.x = Integer.parseInt(coordinates[0]);
			this.y = Integer.parseInt(coordinates[1]);
			
			this.habitatDirectory = new Hashtable<Tuple, ArrayList<Animal>>();
			
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
			Animal animal;
			Tuple habitat;
			while(s != null)
			{
				String[] coordinates = s.split(",");
				int xCoord = Integer.parseInt(coordinates[0]);
				int yCoord = Integer.parseInt(coordinates[1]);	
				animal = new Animal(stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine());
				this.board[yCoord][xCoord].setContents(animal);
				habitat = animal.getHabitat();
				//add animal to habitat
				if (this.habitatDirectory.containsKey(habitat))
				{
					ArrayList<Animal> list = this.habitatDirectory.get(habitat);
					list.add(animal);
					this.habitatDirectory.put(habitat, list);
				}
				//create new habitat if one did not already exist
				else
				{
					ArrayList<Animal> list = new ArrayList<Animal>();
					list.add(animal);
					this.habitatDirectory.put(habitat, list);
					this.board[habitat.getFirst()][habitat.getSecond()].isAHabitat = true;
				}
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


	/** 
	 * parameter: HashTable of room, item pair
	 * checks the rooms listed in the hashtable to see if they have all the animals
	 * returns true if all of the rooms contain all of the necessary items
	 * otherwise returns false 
	 */
	public Boolean checkHabitats() {
		Enumeration<Tuple> allHabitats = this.habitatDirectory.keys();
		boolean booleanArray[] = new boolean[this.habitatDirectory.size()]; 
		int i = 0;
		while(allHabitats.hasMoreElements())
		{
			Tuple room = allHabitats.nextElement();
			int y = room.getFirst();
			int x = room.getSecond();
			ArrayList<Animal> animals = this.habitatDirectory.get(room);
			booleanArray[i] = board[y][x].containsItems(animals);
			if(booleanArray[i])
			{
				animals.get(0).setSilhouetteImage(animals.get(0).getImage());
			}
			i++;
		}
		return this.evaluateBooleanArray(booleanArray);
	} 
	
	
	
	/**
	 * parameter: array of booleans
	 * returns true if the array is longer than 0 and all the booleans are true
	 * otherwise returns false
	 */
	private boolean evaluateBooleanArray(boolean array[])
	{
		boolean bool = true;
		for(boolean i : array)
		{
			bool = bool && i;
		}
		return bool && (array.length>0);
	}

}
