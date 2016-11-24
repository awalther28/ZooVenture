						ZooVenture
//////////////////////////////////////////////////////////////////
By: Allison Walther

ZooVenture is a first person maze game. The idea is that you are a 
zoo-keeper and quite possibly the worst thing just happened. PEETA
has broken into the zoo and released multiple animals from their 
cages. It is your job to sedate all of the animals and bring them 
back to their cages. When all of the animals are in their cages
you win the game.

To move, either use the buttons. To use a hot key/shortcut for movement
press alt+<up, down, right, or left arrow key>.

Do not place more than one animal in a room at a time, the movement 
won't work.

The scoreboard only reflects the last 15 games played.

Lastly, a special thank you goes out to Janie Ward for making
all of my animals and the theme song for ZooVenture.

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
<image for movement>



// how to write an animals.txt

x,y //where the animal is located
<name of the animal>
<int hp>
<int strength>
x,y //where the animal needs to be in order to win the game
<image file name of live animal>
<image file name of silhouette of animal for mini map>
<image file name of movement>