

These are the additional challenges on canvas:
    *Block Types- All 7 block types are in the game.
    *Rotation- You can rotate the pieces (left with 'u' and right with 'o')
    *Soft Drop- Blocks can be soft dropped with 'i'
    
These are additional things I added for fun:
    *Hold- Blocks can be stored/held for later use with 'h'. Only can be
        used once per new block.
    *Next- The screen at the upper right tells you what block is coming next.
    *Level- As your score increases, the level will increase and the blocks
        will come down faster. Maximum level is 30. Level increases at
        every 250 score increment.
    *Force End- Pressing 'e' will force the game to come to an end and send 
        you to the end screen.
    *Score Increase- When you destroy a row, it will tell you how many points
        you earn and if you got a single, double, triple, or tetris.

/**********************************************************************
 *  File Descriptions                                          
 **********************************************************************/
-Game.java = Main file which controls the start screen, end screen, and 
activating the game

-Tetris.java = Main game file which controls the blocks, score, controls, etc.

-Tetromino.java = interface implemented by the 7 different shape types

-ShapeO.java
-ShapeZ.java
-ShapeS.java
-ShapeL.java
-ShapeJ.java
-ShapeT.java
-ShapeI.java = Objects which implement Tetromino and control the movement and 
drawing of each shape type.

-Icon.java = creates draws the icons for the "next" and "hold" boxes.

-Block.java = Creates, colors, and draws the individual boxes for the game board and
the tetromino pieces.
