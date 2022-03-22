
public class Icon {
    private char shape;
    private String placement;
    private double xPos;
    private double yPos;
    private int r;
    private int g;
    private int b;
    
    //constructor
    public Icon(String s, char c) {
        this.placement = s;
        this.shape = c;
        setPos();
        
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Change the position of the icon based on whether
     *              it is a "hold" or "next" icon.
    */
    private void setPos() {
        if (placement.equals("hold")) {
            yPos = 0.85;
            xPos = 0.125;
        }
        if (placement.equals("next")) {
            yPos = 0.85;
            xPos = 0.875;
        }
    }
    
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draw the icon differently based on the shape.
    */
    public void draw() {
        if (shape == 'o') {
            r = 255;
            g = 255;
            b = 0;
            boxDraw(xPos + 0.015, yPos + 0.015);
            boxDraw(xPos - 0.015, yPos + 0.015);
            boxDraw(xPos + 0.015, yPos - 0.015);
            boxDraw(xPos - 0.015, yPos - 0.015);
        }
        if (shape == 'z') {
            r = 255;
            g = 0;
            b = 0;
            boxDraw(xPos, yPos + 0.015);
            boxDraw(xPos, yPos - 0.015);
            boxDraw(xPos - 0.03, yPos + 0.015);
            boxDraw(xPos + 0.03, yPos - 0.015);
        }
        if (shape == 's') {
            r = 0;
            g = 255;
            b = 0;
            boxDraw(xPos, yPos + 0.015);
            boxDraw(xPos, yPos - 0.015);
            boxDraw(xPos + 0.03, yPos + 0.015);
            boxDraw(xPos - 0.03, yPos - 0.015);
        }
        if (shape == 'l') {
            r = 255;
            g = 145;
            b = 0;
            boxDraw(xPos, yPos - 0.015);
            boxDraw(xPos + 0.03, yPos - 0.015);
            boxDraw(xPos - 0.03, yPos - 0.015);
            boxDraw(xPos + 0.03, yPos + 0.015);
        }
        if (shape == 'j') {
            r = 255;
            g = 0;
            b = 255;
            boxDraw(xPos, yPos - 0.015);
            boxDraw(xPos - 0.03, yPos - 0.015);
            boxDraw(xPos + 0.03, yPos - 0.015);
            boxDraw(xPos - 0.03, yPos + 0.015);
        }
        if (shape == 't') {
            r = 120;
            g = 0;
            b = 230;
            boxDraw(xPos, yPos - 0.015);
            boxDraw(xPos, yPos + 0.015);
            boxDraw(xPos + 0.03, yPos - 0.015);
            boxDraw(xPos - 0.03, yPos - 0.015);
        }
        if (shape == 'i') {
            r = 0;
            g = 255;
            b = 255;
            boxDraw(xPos + 0.015, yPos);
            boxDraw(xPos + 0.045, yPos);
            boxDraw(xPos - 0.015, yPos);
            boxDraw(xPos - 0.045, yPos);
        }
    }
    
    /**
     * Inputs: double x, double y
     * Outputs: none
     * Description: Draw the individual boxes of the icon, using the x and y
     *              positions
    */
    private void boxDraw(double x, double y) {
        PennDraw.setPenColor(r, g, b);
        PennDraw.filledRectangle(x, y, 0.015, 0.015);
        PennDraw.setPenColor();
        PennDraw.rectangle(x, y, 0.015, 0.015);
        PennDraw.rectangle(x, y, 0.009, 0.009);
    }
}