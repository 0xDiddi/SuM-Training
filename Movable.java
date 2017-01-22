import sum.kern.Buntstift;

import java.awt.*;

/**
 * A class which implements movable GameObject's {@link gescheit.GameObject}
 */

abstract class Movable extends GameObject { //A class declaration which extends GameObject

    private Rectangle last_bounds; //A private variable of type Rectangle to represent position on screen.

    /**
     * A 'super-calling' constructor to pass through paramenters.
     */
    Movable(int x, int y, int width, int height) {
        super(x, y, width, height); // A super call to GameObject's constructor to initialize object.
    } //End of constructor.

    /**
     * A method declaration with a 'Buntstift' as parameter.
     *
     * @param st The 'Buntstift' to use.
     */
    @Override
    //Override GameObject's render cycle.
    void render(Buntstift st) {
        if (last_bounds != null) { //An if-statement to check if last_bound isn't equl to null.
            st.radiere(); //A method call to switch the 'Butnstift' to rubber mode.
            render_internal(st, last_bounds); //Call the render_internal method which will be declared in a sub class based on Movable.
            st.normal(); //A method call to switch the 'Buntstift' to normal mode.
        }//End of if-statement.
        last_bounds = new Rectangle(this.bounds); //Set the last_bounds to the current bounds (reinitializing it to avoid only copying object pointer...) for use in next frame.

        render_internal(st, bounds); //Call the render_internal method which will be declared in a sub class based on Movable.
    }//End of if-statement.

    protected abstract void render_internal(Buntstift st, Rectangle bounds); //An abstract method declaration of the render_internal method.

}//End of class declaration
