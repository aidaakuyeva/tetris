
public class Tetris {
    
    private int t = 0; //time
    private int step = 40;
    private boolean lose;
    private int score = 0;
    private int scoreAdd = 0;
    private int rowCounter = 0;
    private boolean held = false;
    
    private Tetromino next;
    private Tetromino current;
    private Tetromino hold;
    private Icon nextIcon;
    private Icon holdIcon;
    private Block[][] arr = new Block[23][10];
    
    //constructor
    public Tetris() {
        this.current = randomShape();
        this.next = randomShape();
        this.nextIcon = new Icon("next", next.getShape());
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Updates the game with every animation frame.
     *              Controls pressing different keys during the game, score,
     *              changing to the next block, and locking the block into place.
    */
    public void update() {
        t++;
        if (PennDraw.hasNextKeyTyped()) {
            char c = PennDraw.nextKeyTyped();
            if (c == 'j') {
                current.shift(-1, arr);
            }
            if (c == 'l') {
                current.shift(1, arr);
            }
            if (c == 'i') {
                current.update(arr);
                current.dropOne();
            }
            if (c == 'k') {
                while (current.isMoving()) {
                    current.update(arr);
                    current.dropOne();
                }
            }
            if (c == 'u') { //rotate counterclockwise
                current.rotate(-1, arr);
            }
            if (c == 'o') { //rotate clockwise
                current.rotate(1, arr);
            }
            if (c == 'e') {
                lose = true;
            }
            if (c == 'h' && !held) {
                if (hold == null) {
                    hold = current;
                    current = next;
                    next = randomShape();
                    hold.reset();
                    current.setBlocks();
                    holdIcon = new Icon("hold", hold.getShape());
                    nextIcon = new Icon("next", next.getShape());
                    held = true;
                } else if (hold != null) {
                    Tetromino temp = hold;
                    hold = current;
                    current = temp;
                    hold.reset();
                    current.setBlocks();
                    holdIcon = new Icon("hold", hold.getShape());
                    held = true;
                }
            }
            
        }
        
        if (t == step && current.isMoving()) {
            current.update(arr);
            t = 0;
            current.dropOne();
        }
        if (!current.isMoving()) {
            held = false;
            addToArray(current);
            
            rowCounter = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                boolean fullRow = true;
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == null) {
                        fullRow = false;
                        break;
                    }
                }
                if (fullRow) {
                    rowCounter++;
                    destroyRow(i);
                }
            }
            scoreAdd = 0;
            if (rowCounter == 1) {
                scoreAdd = 40;
            } else if (rowCounter == 2) {
                scoreAdd = 100;
            } else if (rowCounter == 3) {
                scoreAdd = 300;
            } else if (rowCounter == 4) {
                scoreAdd = 1200;
            }
            score += scoreAdd;
            if (step > 11) {
                step = 40 - (int) score / 250;
            }
            if (step < 11) {
                step = 11;
            }
            
            
            this.current = this.next;
            this.next = randomShape();
            this.nextIcon = new Icon("next", this.next.getShape());
            t = 0;
        }
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draws the entire game, including background, text,
     *              game board, blocks, and score.
    */
    public void draw() {
        //draw background
        PennDraw.clear(186, 186, 186);
        
        //draw main box
        PennDraw.setPenColor(34, 34, 34);
        PennDraw.filledRectangle(0.5, 0.5, 0.25, 0.5);
        PennDraw.filledRectangle(0.875, 0.875, 0.125, 0.125);
        PennDraw.filledRectangle(0.125, 0.875, 0.125, 0.125);
        
        //draw (left) instructions
        PennDraw.setPenColor();
        PennDraw.text(0.125, 0.6, "Left: J");
        PennDraw.text(0.125, 0.55, "Right: L");
        PennDraw.text(0.125, 0.5, "Hard Drop: K");
        PennDraw.text(0.125, 0.45, "Soft Drop: I");
        PennDraw.text(0.125, 0.4, "Turn Right: O");
        PennDraw.text(0.125, 0.35, "Turn Left: U");
        PennDraw.text(0.125, 0.3, "Hold: H");
        
        //draw (right) stats
        PennDraw.setFontBold();
        PennDraw.text(0.875, 0.6, "Level:");
        PennDraw.setFontPlain();
        PennDraw.text(0.875, 0.55, " " + (41 - step) + " ");
        PennDraw.setFontBold();
        PennDraw.text(0.875, 0.5, "Score:");
        PennDraw.setFontPlain();
        PennDraw.text(0.875, 0.45, " " + score + " ");
        if (scoreAdd != 0) {
            PennDraw.setFontBold();
            if (rowCounter == 1) {
                PennDraw.text(0.875, 0.4, "SINGLE");
            } else if (rowCounter == 2) {
                PennDraw.text(0.875, 0.4, "DOUBLE");
            } else if (rowCounter == 3) {
                PennDraw.text(0.875, 0.4, "TRIPLE");
            } else if (rowCounter == 4) {
                PennDraw.text(0.875, 0.4, "TETRIS");
            }
            PennDraw.setFontPlain();
            PennDraw.text(0.875, 0.35, "+" + scoreAdd);
        }
        
        //Hold & Next
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.setFontBold();
        PennDraw.text(0.125, 0.95, "HOLD");
        PennDraw.text(0.875, 0.95, "NEXT");
        PennDraw.setFontPlain();
        PennDraw.setPenColor();
        if (nextIcon != null) {
            nextIcon.draw();
        }
        if (holdIcon != null) {
            holdIcon.draw();
        }
        
        //draw blocks
        current.draw();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == null) {
                    continue;
                }
                arr[i][j].draw();
            }
        }
        
        //draw rim
        PennDraw.setPenColor(237, 237, 237);
        PennDraw.setPenRadius(0.005);
        PennDraw.rectangle(0.5, 0.5, 0.25, 0.5);
        PennDraw.rectangle(0.875, 0.875, 0.125, 0.125);
        PennDraw.rectangle(0.125, 0.875, 0.125, 0.125);
        PennDraw.setPenRadius();
    }
    
    /**
     * Inputs: none
     * Outputs: boolean
     * Description: Returns true if a block reaches the top row or if
     *              game is forcefully shut with the 'e' key.
    */
    public boolean loseCondition() {
        for (int i = 0; i < arr[19].length; i++) {
            if (arr[19][i] != null) {
                return true;
            }
        }
        return lose;
    }
    
    /**
     * Inputs: Tetromino
     * Outputs: none
     * Description: Takes the blocks of a Tetromino shape and
     *              puts them into their correct positions in an array.puts
     *              Used when a moving shape finds its final spot.
    */
    public void addToArray(Tetromino t) {
        for (int i = 0; i < 4; i++) {
            Block b = t.getBlock(i);
            arr[b.getYArr()][b.getXArr()] = b;
        }
    }
    
    /**
     * Inputs: int, row number
     * Outputs: none
     * Description: Destroys the row given by the number and shifts
     *              all blocks above it down one.
    */
    public void destroyRow(int row) {
        for (int i = row; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = arr[i + 1][j];
                if (arr[i][j] != null) {
                    arr[i][j].setArr(j, i);
                }    
            }
           
        }
         for (int j = 0; j < arr[arr.length - 1].length; j++) {
             arr[arr.length - 1][j] = null;
         }
    }
    
    /**
     * Inputs: none
     * Outputs: Tetromino
     * Description: Generates a random Tetromino shape for current
     *              and next using Math.random()
    */
    public Tetromino randomShape() {
        double num = Math.random() * 7.0;
        Tetromino newShape = new ShapeI();
        if (num >= 0) {
            newShape = new ShapeO();
        }
        if (num >= 1) {
            newShape = new ShapeI();
        }
        if (num >= 2) {
            newShape = new ShapeS();
        }
        if (num >= 3) {
            newShape = new ShapeZ();
        }
        if (num >= 4) {
            newShape = new ShapeL();
        }
        if (num >= 5) {
            newShape = new ShapeJ();
        }
        if (num >= 6) {
            newShape = new ShapeT();
        }
        return newShape;
    }
    
    /**
     * Inputs: none
     * Outputs: int, score
     * Description: Score getter. Used for displaying the score on the end screen.
    */
    public int getScore() {
        return score;
    }

}