import sum.kern.Maus;

import java.awt.*;

/**
 * A class holding mouse data.
 */
class MouseData { // A class declaration.

    /**
     * Whether or not the "Maus" is clicked.
     */
    boolean clicked; // A variable of type boolean.
    private Maus ms; // A reference to the "Maus" object whose data is being held.

    /**
     * Constructs a MouseData object with the given "Maus".
     *
     * @param ms The "Maus" reference.
     */
    MouseData(Maus ms) { // A constructor declaration with a parameter of type "Maus".
        this.ms = ms; // An assignment of the parameter ms to the private variable ms.
        this.clicked = ms.istGedrueckt(); // An assignment of the parameter "ms.istGedrueckt()" to the public variable clicked.
    } // End of a constructor declaration.

    /**
     * Gets the current position of the "Maus" object.
     *
     * @return The current position.
     */
    Point xy() { // A method declaration without parameters and a return value of type Point.
        return new Point(ms.hPosition(), ms.vPosition()); // A return statement returning a new Point.
    } // End of a method declaration.

} // End of a class declaration.
