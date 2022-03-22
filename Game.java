
public class Game {

    public static void main(String[] args) {

        boolean startScreen = true;
        boolean endScreen = false;
        int score = 0;
        
        
        PennDraw.enableAnimation(60);
        
        while (true) {
            while (startScreen) {
                PennDraw.clear();
                drawControls();
                if (PennDraw.hasNextKeyTyped()) {
                    PennDraw.nextKeyTyped();
                    startScreen = false;
                }
                PennDraw.advance();
            }
            
            Tetris tetris = new Tetris();
            while (!startScreen && !endScreen) {
                tetris.update();
                
                
                if (tetris.loseCondition()) {
                    endScreen = true;
                }
                
                tetris.draw();
                
                PennDraw.advance();
            }
            
            score = tetris.getScore();
            
            while (endScreen) {
                PennDraw.clear();
                PennDraw.setPenColor();
                PennDraw.text(0.5, 0.5, "Play again? Y or N?");
                PennDraw.text(0.5, 0.45, "Score: " + score);
                if (PennDraw.hasNextKeyTyped()) {
                    char c = PennDraw.nextKeyTyped();
                    if (c == 'y') {
                        endScreen = false;
                    }
                    if (c == 'n') {
                        endScreen = false;
                        startScreen = true;
                    }
                }
                PennDraw.advance();
            }
        }

    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draws the instructions and controls on the start screen
    */
    public static void drawControls() {
        PennDraw.setPenColor();
        
        PennDraw.setFontBold();
        PennDraw.text(0.5, 0.65, "Press any key to start");
        PennDraw.setFontPlain();
        
        PennDraw.text(0.5, 0.6, "Left: J");
        PennDraw.text(0.5, 0.55, "Right: L");
        PennDraw.text(0.5, 0.5, "Hard Drop: K");
        PennDraw.text(0.5, 0.45, "Soft Drop: I");
        PennDraw.text(0.5, 0.4, "Turn Right: O");
        PennDraw.text(0.5, 0.35, "Turn Left: U");
        PennDraw.text(0.5, 0.3, "Hold: H");
    }

}