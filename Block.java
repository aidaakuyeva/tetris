
public class Block {
    private double x; //position on screen
    private double y;
    private int xArr; //position on gameboard
    private int yArr;
    private int rColor;
    private int gColor;
    private int bColor;
    
    //constructor
    public Block(int x, int y, int r, int g, int b) {
        this.xArr = x;
        this.yArr = y;
        this.x = findX();
        this.y = findY();
        this.rColor = r;
        this.gColor = g;
        this.bColor = b;
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draws the box with certain dimensions
    */
    public void draw() {
        PennDraw.setPenColor(rColor, gColor, bColor);
        PennDraw.filledRectangle(x, y, 0.025, 0.025);
        PennDraw.setPenColor();
        PennDraw.rectangle(x, y, 0.025, 0.025);
        PennDraw.rectangle(x, y, 0.015, 0.015);
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: setter. Changes the position of the block on
     *              the gameboard (xArr, yArr) & updates their 
     *              positions on the screen (x, y)
    */
    public void setArr(int x, int y) {
        this.xArr = x;
        this.yArr = y;
        this.x = findX();
        this.y = findY();
    }
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: getter. Retrieves the xArr, the x position
     *              of the block on the game board
    */
    public int getXArr() {
        return xArr;
    }
    
    /**
     * Inputs: none
     * Outputs: int
     * Description: getter. Retrieves the yArr, the y position
     *              of the block on the game board
    */
    public int getYArr() {
        return yArr;
    }
    
    /**
     * Inputs: none
     * Outputs: double
     * Description: Calculates the x position of the block on
     *              the screen based on its x position on the game board.
    */
    private double findX() {
        return ((double) xArr) * 0.05 + 0.275;
    }
    
    /**
     * Inputs: none
     * Outputs: double
     * Description: Calculates the y position of the block on
     *              the screen based on its y position on the game board.
    */
    private double findY() {
        return ((double) yArr) * 0.05 + 0.025;
    }
}