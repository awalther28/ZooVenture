						ZooVenture
//////////////////////////////////////////////////////////////////
By: Allison Walther

ZooVenture is a first person maze game. The idea is that you are a 
zoo-keeper and quite possibly the worst thing just happened. PEETA
has broken into the zoo and released multiple animals from their 
cages. It is your job to sedate all of the animals and bring them 
back to their cages. When all of the animals are in their cages
you win the game.


UPDATE 11/01/2016:
To move, either use the buttons. To use a hot key/shortcut for movement
press alt+<up, down, right, or left arrow key>.

The item indicator will disappear when you enter a room because you will
be able to see what is in the room. The information about how many items
in the room is not lost. When you exit the room, the item indicator will
reappear, reflecting the number of items currently within the room.
 
UPDATES TO COME:
update text file so the user can decide what orientation they want the 
player to start in
graphics for ZooView -- will use flyweight graphics factory
create scrollbar for inventory list & add ways to interact with inventory


// how to write a board.txt
x,y //dimensions of the board
WWWWW	//use W to indicate a wall, places that the player cannot go
WPW W	//use a blank space to indicate a room
W   W	//use P to indicate where the player will start
WW  W
WWWWW



// how to write an items.txt 

x,y //where the item is located
<name of the item>
Increase HP:<int value> or Increase Strength:<int value> or None
<image file name>



// how to write an animals.txt

x,y //where the animal is located
<name of the animal>
<int hp>
<int strength>
x,y //where the animal needs to be in order to win the game
<image file name of live animal>
<image file name of passed out animal>