import sum.kern.Buntstift;
import sum.kern.Muster;

import java.awt.*;

/**
 * The crosshair for aiming.
 */
class Crosshair extends Movable { // A class declaration extending Movable.

    /**
     * Creates a new Crosshair at the given position with the given size.
     *
     * @param x      The x position.
     * @param y      The y position.
     * @param width  The width.
     * @param height The height.
     */
    Crosshair(int x, int y, int width, int height) { // A constructor declaration with four parameters.
        super(x, y, width, height); // A call to the super constructor with the parameters x, y, width and height.
    } // End of a constructor declaration.

    /**
     * Draws the crosshair.
     *
     * @param st     The "Buntstift" to render with.
     * @param bounds The bounds of this crosshair.
     */
    @Override // Overrides the implementation in Movable.
    protected void render_internal(Buntstift st, Rectangle bounds) { // A protected method declaration with two parameters of type "Buntstift" and Rectangle and no return value.
        st.setzeLinienBreite(1); // Calls "setzeLinienBreite" on the "Buntstift".
        st.setzeFuellmuster(Muster.DURCHSICHTIG); // Calls "setzeLinienBreite" on the "Buntstift".
        st.setzeFarbe(Color.BLACK); // Calls "setzeLinienBreite" on the "Buntstift".

        st.bewegeBis(bounds.getX(), bounds.getY()); // Calls "bewegeBis" on the "Buntstift".
        st.runter(); // Calls "runter" on the "Buntstift".
        st.zeichneRechteck(bounds.getWidth(), bounds.getHeight()); // Calls "zeichneRechteck" on the "Buntstift".
        st.hoch(); // Calls "hoch" on the "Buntstift".
        st.bewegeBis(bounds.getX() + bounds.getWidth() / 4, bounds.getY() + bounds.getHeight() / 4); // Calls "bewegeBis" on the "Buntstift".
        st.runter(); // Calls "runter" on the "Buntstift".
        st.zeichneRechteck(bounds.getWidth() / 2, bounds.getHeight() / 2); // Calls "zeichneRechteck" on the "Buntstift".
        st.hoch(); // Calls "hoch" on the "Buntstift".
    } // End of a method declaration.

    /**
     * Updates and moves the crosshair.
     *
     * @param ms The MouseData to use.
     */
    @Override
    // Overrides the implementation in GameObject
    void update(MouseData ms) { // A method declaration with one parameter of type MouseData and no return value.
        this.bounds.x = (int) (ms.xy().getX() - bounds.getWidth() / 2); // Recalculates the x position of the crossair.
        this.bounds.y = (int) (ms.xy().getY() - bounds.getHeight() / 2); // Recalculates the y position of the crossair.
        this.dirty = true; // Sets the variable dirty to true.

        BetterWurfbude.INSTANCE.currentScreen.children.stream().filter(go -> go.bounds.intersects(this.bounds)).forEach(go -> go.dirty = true); // Sets all overlapping children dirty.
    } // End of a method declaration.
}
