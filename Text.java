import sum.kern.Buntstift;
import sum.kern.Muster;

import java.awt.*;

/**
 * A class to display and handle text on screen.
 */

class Text extends GameObject { //A class declaration extending Text

    String text; //A package-local variable of type String.
    boolean redrawBackground; //A package-default variable of type boolean.
    private int size; //A private variable of type int.

    /**
     * A constructor to pass through Text prameters.
     *
     * @param x      the x pos
     * @param y      the y pos
     * @param width  the width
     * @param height the height
     * @param text   the text displayed
     */
    Text(int x, int y, int width, int height, String text) {
        super(x, y, width, height); //A 'super-call' to constructor of GameObject.
        this.text = text; //Set object variable text to local variable.
        this.size = 75; //Set object variable size to 75.
    }//End of constructor.

    /**
     * A constrctor to pass through Text parameters with a custom size.
     *
     * @param x      the x pos
     * @param y      the y pos
     * @param width  the width
     * @param height the height
     * @param text   the text displayed
     * @param size   the size of the text
     */
    Text(int x, int y, int width, int height, String text, int size) {
        super(x, y, width, height); //A 'super-call' to constructor of GameObject.
        this.text = text; //Set object variable text to local variable text.
        this.size = size; //Set object variable size to local variable size.
    }

    /**
     * A method to render on screen.
     *
     * @param st The 'Buntstift' to use.
     */
    @Override
    //Override GameObject's render cycle.
    void render(Buntstift st) {
        st.setzeSchriftGroesse(size); //A method call to set 'Buntstift' size.

        if (redrawBackground) { //An if-statement to check if redrawBackground is true.
            st.setzeFuellmuster(Muster.GEFUELLT); //A method call to set the fill-padding to 'gefuellt'.
            st.setzeFarbe(Color.white); //A method call to set color to white.
            st.bewegeBis(bounds.getX(), bounds.getY()); //A method call to move to [X,Y].
            st.zeichneRechteck(bounds.getWidth(), bounds.getHeight()); //A method call to draw a Rectangle at current position by width and height.
            st.setzeFuellmuster(Muster.DURCHSICHTIG); //A method call to set the fill-padding to 'durchsichtig'.
            st.setzeFarbe(Color.black); //A method call to set the pencil color to black.
            redrawBackground = false; //Set redrawBackground to false.
        }//End of if-statement.

        st.bewegeBis(bounds.getX() + (bounds.getWidth() - st.textBreite(text)) / 2, bounds.getY() + size); //A method call to move the pencil to a position.

        st.setzeFarbe(Color.BLACK); //A method call to set color to black.
        st.schreibeText(text); //A method color to write a text.
    } //End of method declaration.
}//End of class declaration.