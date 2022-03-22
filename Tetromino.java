public interface Tetromino {
    
    /**
     * Inputs: none
     * Outputs: char
     * Description: Returns the char that represents the object's shape.
     *              For example, ShapeO will return 'o'.
    */
    char getShape();
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Resets the Tetromino's position and orientation to 
     *              starting values.
    */
    void reset();
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draw the Tetromino shape's blocks in the correct position.
    */
    void draw();
    
    /**
     * Inputs: Block[][]
     * Outputs: none
     * Description: Checks to see if the Tetromino shape is about to collide
     *              with the bottom of the screen or a block in the double array.
    */
    void update(Block[][] arr);
    
    /**
     * Inputs: none
     * Outputs: boolean
     * Description: Getter. Checks the status of the boolean variable moving.
    */
    boolean isMoving();
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: Getter. Gets the X position of the shape in the array.
    */
    int getXArr();
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: Getter. Gets the Y position of the shape in the array.
    */
    int getYArr();
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Sets the four blocks that make up the shape in the correct
     *              position based on rotation and shape position.
    */
    void setBlocks();

    /**
     * Inputs: int
     * Outputs: Block
     * Description: Getter. Returns one of the four blocks based 
     *              on the input integer
    */
    Block getBlock(int i);
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: If the shape is moving, this command will drop its
     *              position by one and re-update all of the blocks.
    */
    void dropOne();
    
    /**
     * Inputs: int, Block[][]
     * Outputs: none
     * Description: Will attempt to move the shape left or right, based on the
     *              sign of the integer. Prevents the shape from moving if
     *              there is a wall or if it will hit a block in the array.
    */
    void shift(int s, Block[][] arr);
    
    /**
     * Inputs: int, Block[][]
     * Outputs: none
     * Description: Will attempt to rotate the shape left or right, based on the
     *              sign of the integer. Prevents the shape from rotating if
     *              there is a wall or if it will hit a block in the array.
    */
    void rotate(int i, Block[][] arr);
      
}