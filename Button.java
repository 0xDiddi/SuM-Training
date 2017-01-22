import sum.kern.Buntstift;
import sum.kern.Muster;

import java.awt.*;

/**
 * A class that can handle and draw a button on the screen. {@link gescheit.Text}
 */
class Button extends Text {
    /**
     * Class declaration of Button which extends Text
     */

    private Color clr;//A private variable of type Color

    /**
     * A constructor to create a Button.
     *
     * @param x      the x pos
     * @param y      the y pos
     * @param width  the width
     * @param height the height
     * @param text   the text displayed
     * @param clr    the color of the background
     * @param cb     the callback
     */
    Button(int x, int y, int width, int height, String text, Color clr, ICallback cb) {
        super(x, y, width, height, text); //A super call to Text's constructor to initialize super object.
        this.clr = clr; //object variable clr is set to local variable clr.
        this.cb = cb; //object variable cb is set to locale variable cb.
    }//End of constructor

    /**
     * A method declaration with Buntstift as parameter. > To render the Button.
     *
     * @param st the 'Butnstift' to use
     */
    @Override //Override the render cycle from Text-Class.
    public void render(Buntstift st) {
        st.bewegeBis(bounds.getX(), bounds.getY()); //A mehtod call to move the Button to [X,Y]
        st.runter(); //A method call to 'put down the pen'.
        st.setzeFuellMuster(Muster.GEFUELLT); //A method call to set the fill pattern to 'gefuellt'.
        st.setzeFarbe(clr); //A method call to set the color to the color of clr.
        st.zeichneRechteck(bounds.getWidth(), bounds.getHeight()); //A method call to draw a rectangle at current position with width and hight as parameter.
        st.hoch(); //A mehtod call to 'move the pencil up'.

        super.render(st); //A 'super-method' call to render the image.
    }//End of method declarartion

}//End of class declarartion
