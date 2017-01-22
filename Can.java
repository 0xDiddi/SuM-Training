import sum.kern.Buntstift;
import sum.kern.Muster;

import java.awt.*;

/**
 * A class to display movable cans.
 */

class Can extends Movable { //A class declaration which extends Movable

    int speed; //A package-local variable of type int to define speed.

    /**
     * A package-local constructor to pass through parameters.
     *
     * @param x      the x pos
     * @param y      the y pos
     * @param width  the width
     * @param height the height
     * @param cb     the callback
     */
    Can(int x, int y, int width, int height, ICallback cb) {
        super(x, y, width, height); //A 'super-call' to Movable's constructor.
        this.cb = cb; //Set objects ICallback to local ICallback.
    } //End of constructor

    /**
     * A method declaration. > set the to dead (disapear and remove from render).
     */
    void setDead() {
        BetterWurfbude.INSTANCE.points++; //Increase points by one.
        BetterWurfbude.INSTANCE.textPoints.text = "Points: " + BetterWurfbude.INSTANCE.points; //Set display text to 'Points: [amount of points]'
        BetterWurfbude.INSTANCE.textPoints.dirty = true; //Set dirty to true.
        BetterWurfbude.INSTANCE.textPoints.redrawBackground = true; //Set redrawBackground to true.
        alive = false; //Set alive to false.

        if (BetterWurfbude.INSTANCE.points >= 4) //An if-statement to check if points are greater than 4.
            BetterWurfbude.INSTANCE.gameWin();//A method call to display the win-screen.
    }

    @Override //Override Movable's render_internal method.
    protected void render_internal(Buntstift st, Rectangle bounds) { //A method declaration with parameters of type 'Buntstift' and Rectangle.
        st.bewegeBis(bounds.getX(), bounds.getY()); //A method call to move to [X,Y].
        st.runter(); //A method call to 'nove down the pencil'.
        st.setzeFuellMuster(Muster.DURCHSICHTIG); //A method call to set the fill-padding to 'durchsichtig' > transparent
        st.setzeFarbe(Color.BLACK); //A method call to set the pencil color to black.
        st.zeichneRechteck(bounds.getWidth(), bounds.getHeight()); //A method call to draw a Rectangle with width and height.
        st.hoch(); //A method call to move up the pen.

        double y_start = bounds.getY(); //A package-default variable of type double to represent y-start-bounds.
        double y_adder = bounds.getHeight() / 4.0; //A package-default variable of type double to represent y-space between the boxes lines.
        for (int i = 0; i < 4; i++) { //A for loop to interate through i.
            y_start += y_adder; //Add the y_adder amount to y_start.
            st.bewegeBis(bounds.getX(), y_start); //A method call to move to [X,Y].
            st.runter(); //A method to 'move down the pen'.
            st.bewegeBis(bounds.getX() + bounds.getWidth(), y_start); //A method call to move to [X,Y]
            st.hoch(); //A method call to 'move up the pen'.
        }//End of for loop.
    }//End of method declaration.

    /**
     * A method declaration with MouseData as parameter.
     *
     * @param ms the MouseData to deal with
     */
    @Override
    //Override Movable's update method.
    void update(MouseData ms) {
        super.update(ms); //A 'super-call' to Movable's constructor passing through MouseData

        bounds.x += speed; //Add the value of speed to the value of the x coordinate of the can. > Move the can.
        if (bounds.getX() < 0 || bounds.getX() > BetterWurfbude.INSTANCE.bs.getWidth() - bounds.getWidth()) //An if-statement to check if x is smaller than 0 or x is greater than screen-width minus can-with
            speed = -speed; //Inverse the speed. > Let the can move in the opposite direction.
        this.dirty = true; //Set dirty to true.
    }//End of method declaration.
}//End of class declaration.
