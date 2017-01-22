import sum.kern.Buntstift;

import java.awt.*;

/**
 * The Base Class for all GameObjects {@link gescheit.Can}{@link gescheit.Button}{@link gescheit.Crosshair}{@link gescheit.Text}
 */

abstract class GameObject { //Class declaration.

    Rectangle bounds; //The position for the object on screen.
    ICallback cb; //An implement of ICallback.
    boolean dirty = true; //A package local boolean called dirty; set by default to true.
    boolean alive = true; //A package local boolean called alive; set by default to true.

    private boolean lastClicked; //A private boolean called lastClicked... > Describes if the object was clicked in the last tick.

    /**
     * A constructor creating a new rectangle based on the passed int's.
     *
     * @param x      The x position.
     * @param y      The y position.
     * @param width  The width.
     * @param height The height.
     */
    GameObject(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    abstract void render(Buntstift st); //A abstract method for the rendering cycle.

    /**
     * A method to check if clicked by Mouse.
     *
     * @param ms The MouseData to use.
     */
    void update(MouseData ms) {
        if (cb != null) { //An if-statement to check if cb is not equal to null.
            if (ms.clicked && !lastClicked && BetterWurfbude.INSTANCE.currentScreen.inputActive) { //An if-statement to check if mouse is clicked and if mouse wasn't clicked last tick and if there is an active input to the screen.
                if (bounds.contains(ms.xy())) { //An if-statement to check if mouse is inside the GameObject.
                    lastClicked = true; //Set lastClicked to true.
                    cb.callback(); //Call the callback method.
                }//End of if-statement
            } else if (!ms.clicked && lastClicked) { //An else if-statement to check if mouse isn't clicked and if the mouse was clicked in the last tick.
                BetterWurfbude.INSTANCE.currentScreen.inputActive = false; //Set inputActive to false
                lastClicked = false; //Set lastClicked to false
            }//End of if-statement
        }//End of if-statement
    }//End of method declaration

} //End of Class declaration
