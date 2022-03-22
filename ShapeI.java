
public class ShapeI implements Tetromino {
    
    private boolean moving;
    private int xArr;
    private int yArr;
    private int rot = 1;
    private Block[] blocks = new Block[4];
    private static int r = 0;
    private static int g = 255;
    private static int b = 255;
    private char shape = 'i';
    
    //constructor
    public ShapeI() {
        this.xArr = 4;
        this.yArr = 19;
        this.moving = true;
        blocks[0] = new Block(xArr + 2, yArr, r, g, b);
        blocks[1] = new Block(xArr + 1, yArr, r, g, b);
        blocks[2] = new Block(xArr, yArr, r, g, b);
        blocks[3] = new Block(xArr - 1, yArr, r, g, b);
    }
    
    /**
     * Inputs: none
     * Outputs: char
     * Description: Returns the char that represents the object's shape.
    */
    public char getShape() {
        return shape;
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Resets the Tetromino's position and orientation to 
     *              starting values.
    */
    public void reset() {
        this.xArr = 4;
        this.yArr = 19;
        this.rot = 1;
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draw the Tetromino shape's blocks in the correct position.
    */
    public void draw() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].draw();
        }
    }
    
    /**
     * Inputs: Block[][]
     * Outputs: none
     * Description: Checks to see if the Tetromino shape is about to collide
     *              with the bottom of the screen or a block in the double array.
    */
    public void update(Block[][] arr) {
        for (int k = 0; k < blocks.length; k++) {
            if (blocks[k].getYArr() == 0) {
                moving = false;
                return;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == null) {
                    continue;
                }
                for (int k = 0; k < blocks.length; k++) {
                    if (j == blocks[k].getXArr() &&
                        i + 1 == blocks[k].getYArr()) {
                        moving = false;
                    }
                }
            }
        }
    }
    
    /**
     * Inputs: none
     * Outputs: boolean
     * Description: Getter. Checks the status of the boolean variable moving.
    */
    public boolean isMoving() {
        if (moving) {
            return true;
        }
        return false;
    }
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: Getter. Gets the X position of the shape in the array.
    */
    public int getXArr() {
        return xArr;
    }
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: Getter. Gets the Y position of the shape in the array.
    */
    public int getYArr() {
        return yArr;
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Sets the four blocks that make up the shape in the correct
     *              position based on rotation and shape position.
    */
    public void setBlocks() {
        if (rot == 0) {
            blocks[0].setArr(xArr, yArr + 1);
            blocks[1].setArr(xArr, yArr);
            blocks[2].setArr(xArr, yArr - 1);
            blocks[3].setArr(xArr, yArr - 2);
        } else if (rot == 1) {
            blocks[0].setArr(xArr + 2, yArr);
            blocks[1].setArr(xArr + 1, yArr);
            blocks[2].setArr(xArr, yArr);
            blocks[3].setArr(xArr - 1, yArr);
        } else if (rot == 2) {
            blocks[0].setArr(xArr + 1, yArr + 1);
            blocks[1].setArr(xArr + 1, yArr);
            blocks[2].setArr(xArr + 1, yArr - 1);
            blocks[3].setArr(xArr + 1, yArr - 2);
        } else if (rot == 3) {
            blocks[0].setArr(xArr + 2, yArr - 1);
            blocks[1].setArr(xArr + 1, yArr - 1);
            blocks[2].setArr(xArr, yArr - 1);
            blocks[3].setArr(xArr - 1, yArr - 1);
        }
    }
    
    /**
     * Inputs: int
     * Outputs: Block
     * Description: Getter. Returns one of the four blocks based 
     *              on the input integer
    */
    public Block getBlock(int i) {
        return blocks[i];
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: If the shape is moving, this command will drop its
     *              position by one and re-update all of the blocks.
    */
    public void dropOne() {
        if (moving) {
            yArr -= 1;
            setBlocks();
        }
    }
    
    /**
     * Inputs: int, Block[][]
     * Outputs: none
     * Description: Will attempt to move the shape left or right, based on the
     *              sign of the integer. Prevents the shape from moving if
     *              there is a wall or if it will hit a block in the array.
    */
    public void shift(int s, Block[][] arr) {
        if (s > 0) { //go right
            for (int k = 0; k < blocks.length; k++) {
                if (blocks[k].getXArr() == 9) {
                    return;
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == null) {
                        continue;
                    }
                    for (int k = 0; k < blocks.length; k++) {
                        if (blocks[k].getXArr() + 1 == j && 
                            blocks[k].getYArr() == i) {
                            return;
                        }
                    }
                }
            }
            
            xArr += 1;
            setBlocks();
        }
        
        if (s < 0) {
            for (int k = 0; k < blocks.length; k++) {
                if (blocks[k].getXArr() == 0) {
                    return;
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == null) {
                        continue;
                    }
                    for (int k = 0; k < blocks.length; k++) {
                        if (blocks[k].getXArr() - 1 == j && 
                            blocks[k].getYArr() == i) {
                            return;
                        }
                    }
                }
            }
            xArr -= 1;
            setBlocks();
        }  
    }
    
    /**
     * Inputs: int, Block[][]
     * Outputs: none
     * Description: Will attempt to rotate the shape left or right, based on the
     *              sign of the integer. Prevents the shape from rotating if
     *              there is a wall or if it will hit a block in the array.
    */
    public void rotate(int i, Block[][] arr) {
        if (i > 0) {
            rot = (rot + 1) % 4;
        }
        if (i < 0) {
            rot = (rot + 3) % 4;
        }
        setBlocks();
        if (rotationError(arr)) {
            rotate(-1 * i, arr);
        }
    }
    
    /**
     * Inputs: Block[][]
     * Outputs: boolean
     * Description: Returns true if the shape cannot rotate in a certain direction
     *              because of the walls or a block in the array.
    */
    public boolean rotationError(Block[][] arr) {
        for (int k = 0; k < blocks.length; k++) {
            if (blocks[k].getXArr() > 9 || blocks[k].getXArr() < 0 ||
               blocks[k].getYArr() < 0) {
                return true;
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == null) {
                        continue;
                    }
                    if (blocks[k].getXArr() == j && blocks[k].getYArr() == i) {
                        return true;
                    }
                
                }
            }    
        }
        return false;
    }  
    
}