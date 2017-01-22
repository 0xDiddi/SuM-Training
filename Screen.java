import sum.kern.Buntstift;

import java.util.ArrayList;
import java.util.List;

/**
 * A virtual representation of a screen.
 */
class Screen extends GameObject { // A class declaration extending GameObject.

    /**
     * A List of all children of this screen.
     */
    List<GameObject> children;

    /**
     * Whether or not to redraw every child regardless of its individual setting.
     */
    boolean redrawAll;
    /**
     * Whether or not to compute input events.
     */
    boolean inputActive = false;

    /**
     * Constructs a new Screen.
     */
    Screen() { // A constructor declaration without parameters.
        super(0, 0, -1, -1); // A call to the super constructor with the parameters 0, 0, -1 and -1.
        children = new ArrayList<>(); // Creation of a new ArrayList matching the type parameter of the variable.
    } // End of a constructor declaration.

    /**
     * Adds a game object to the list of children.
     *
     * @param go The game object to be added to the list of children.
     */
    void addChildren(GameObject go) { // A method declaration with one parameter of type GameObject and no return value.
        this.children.add(go); // Adding the parameter go to the list of children.
    } // End of a method declaration.

    /**
     * Renders all children of this screen.
     *
     * @param st The "Buntstift" to render with.
     */
    @Override
    // Overrides implementation in GameObject.
    void render(Buntstift st) { // A method declaration with one parameter of type "Buntstift" and no return value.
        if (redrawAll)
            BetterWurfbude.INSTANCE.bs.loescheAlles(); // Clears the bs of the current game instance if redrawAll is true.
        children.stream().filter(go -> go.dirty || redrawAll).forEach(go -> { // Beginning of a lambda block iterating through all dirty children.
            go.render(st); // Render the child using the given "Buntstift".
            go.dirty = false; // Resets the child to not be dirty.
        }); // End of a lambda block.
        redrawAll = false; // Resets redrawAll to false.
    } // End of a method declaration.

    /**
     * Updated all children of this screen.
     *
     * @param ms The mouse data to update with.
     */
    @Override
    // Overrides the implementation in GameObject.
    void update(MouseData ms) { // A method declaration with one parameter of type MouseData and no return value.
        List<GameObject> go_for_removal = new ArrayList<>(); // Creates a new ArrayList of GameObjects
        for (GameObject go : children) { // Iterates through all children.
            go.update(ms); // Updated the child with the given MouseData
            if (!go.alive) // Checks whether or not the child is alive.
                go_for_removal.add(go); // Adds the child to the go_for_removal list.
        } // End of a for loop.

        if (go_for_removal.size() > 0) { // Checks whether or not there are any game objects for removal.
            children.removeAll(go_for_removal); // Removes all marked game objects.
            redrawAll = true; // Forces this screen to redraw everything.
        } // End of an if condition.
        inputActive = !ms.clicked; // Sets inputActive
    } // End of a method declaration.

} // End of a class declaration.
