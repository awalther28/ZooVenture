						ZooVenture
//////////////////////////////////////////////////////////////////
By: Allison Walther

ZooVenture is a first person maze game. The idea is that you are a 
zoo-keeper and quite possibly the worst thing just happened. PEETA
has broken into the zoo and released multiple animals from their 
cages. It is your job to sedate all of the animals and bring them 
back to their cages. When all of the animals are in their cages
you win the game.


Next implementations will include classes that will allow me to
utilize the Model View Controller design pattern. Those classes 
will include: ZooView and ZooPanel will make up the
view; ZooRePaintController (extends KeyAdapter and implements
MouseListener), AnimalController, ItemController, 
and MiniMapController are event handlers that implement 
ActionListener, they serve as event handlers; ZooModel 
will be a model (serving as an intermediary between game engine 
logic of the current classes).



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
Increase HP:<int value> or Increase Damage:<int value> or None
<image file name>



// how to write an animals.txt

x,y //where the animal is located
<name of the animal>
<int hp>
<int strength>
x,y //where the animal needs to be in order to win the game
<image file name of live animal>
<image file name of passed out animal>