import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.kern.Maus;
import sum.kern.Tastatur;

import java.awt.*;
import java.util.Random;

/**
 * The main class of the better game.
 */
public class BetterWurfbude { // A class declaration.

    /**
     * A reference to the current instance.
     */
    static BetterWurfbude INSTANCE; // A package-local, static variable of type BetterWurfbude.

    /**
     * A reference to the current "Bildschirm" object.
     */
    Bildschirm bs; // A package-local variable of type "Bildschirm".
    /**
     * A reference to the current displayed screen.
     */
    volatile Screen currentScreen; // A package-local, volatile variable of type Screen
    /**
     * The current score of the game.
     */
    int points = 0; // A package-local variable of type int.
    /**
     * A reference to the Text object displaying the current score.
     */
    Text textPoints; // A package-local variable of type Text.
    private Buntstift st; // A private variable of type "Buntstift".
    private Maus ms; // A private variable of type "Maus".
    private Tastatur ts; // A private variable of type "Tastatur".
    private Text textTime; // A private variable of type Text.
    private double timeStep; // A private variable of type double.
    private double timeRemaining; // A private variable of type double.

    private BetterWurfbude() { // A private constructor declaration.
        bs = new Bildschirm(10, 10, 800, 600); // Creation of a "Bildschirm" at (10|10) with the measures (800|600).
        bs.setResizable(false); // Setting the bs to not be resizable.
        ms = new Maus(); // Creation of a "Maus".
        ts = new Tastatur(); // Creation of a "Tastatur".
        st = new Buntstift(); // Creation of a "Buntstift".
    } // End of a constructor declaration.

    /**
     * The main function of this program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) { // A public, static method declaration without parameters and no return value.
        INSTANCE = new BetterWurfbude();
        INSTANCE.mainLoop();
    } // End of a method declaration.

    private void mainLoop() { // A private method declaration without parameters and no return value.
        currentScreen = new Screen(); // Creates a new virtual screen.
        currentScreen.addChildren(new Button(100, 225, 600, 100, "Easy game", Color.green, () -> mainGame(1))); // Adds a Button object to the current screen.
        currentScreen.addChildren(new Button(100, 350, 600, 100, "Hard game", Color.orange, () -> mainGame(2))); // Adds a Button object to the current screen.
        currentScreen.addChildren(new Button(100, 475, 600, 100, "Impossible game", Color.red, () -> mainGame(10))); // Adds a Button object to the current screen.
        currentScreen.addChildren(new Text(100, 100, 600, 100, "Welcome!")); // Adds a Text object to the current screen.

        Rectangle lastWindowBounds = bs.getBounds(); // Saves the last window bounds.
        double lastTickTime = System.currentTimeMillis(); // Sets the last tick time to the current time in milliseconds.

        while (!ts.wurdeGedrueckt()) { // A while loop that runs until a key is pushed.
            currentScreen.render(st); // Renders the current screen.
            currentScreen.update(new MouseData(ms)); // Updates the current screen with the current mouse data.

            if (textTime != null) { // Checks if the time display is not null.
                double delta = System.currentTimeMillis() - timeStep; // Calculates the delta time since last tick.
                timeStep = System.currentTimeMillis(); // Refreshed the time step to be the current time in milliseconds.
                timeRemaining = Math.max(0, timeRemaining - delta); // Recalculates the remaining time.
                textTime.text = "Time remaining: " + timeRemaining / 1000 + " s"; // Displays the remaining time.

                textTime.redrawBackground = true; // Forces the text field to redraw its background.
                textTime.dirty = true; // Forces the text field to be redrawn.
                if (timeRemaining <= 0) { // Checks if there is time left.
                    gameLose(); // End the game in the "You Lost." screen.
                } // End of an if condition.
            } // End of an if condition.

            if (!bs.getBounds().equals(lastWindowBounds)) { // Checks if the window moved.
                currentScreen.redrawAll = true; // Forces the screen to redraw all its children.
                lastWindowBounds = bs.getBounds(); // Refreshed the last window bounds.
            } // End of an if condition.

            //noinspection StatementWithEmptyBody
            while (System.currentTimeMillis() - lastTickTime < 24) {
            } // Waits until the tick time  is reached.

            lastTickTime = System.currentTimeMillis(); // Sets the last tick time.
        } // End of a while loop.

        this.dispose(); // Disposes this object.
    } // End of a method declaration.

    private void mainGame(int difficulty) { // A private method declaration without parameters and no return value.
        bs.loescheAlles(); // Clears the "Bildschirm".
        currentScreen = new Screen(); // Creates a new virtual screen.

        timeRemaining = 30000 / difficulty; // Calculates the remaining time.

        currentScreen.addChildren(textPoints = new Text(10, 0, 780, 100, "Points: 0")); // Adds a Text object to the current screen.
        currentScreen.addChildren(textTime = new Text(10, 80, 780, 25, "Time remaining: " + timeRemaining / 1000 + " s", 20)); // Adds a Text object to the current screen.

        Random rng = new Random(); // Creates a new object of type random.
        for (int i = 0; i < 4; i++) { // Iterates over i = 0 while i < 4, increasing it by 1 each iteration
            Can c = new Can(350, 125 + (i * 110), 100, 100, () -> {
            }); // Creates a new Can object.
            c.cb = c::setDead; // Sets the callback of the Can object to its setDead method.
            c.speed = (int) ((rng.nextGaussian() * 10) + 20) * difficulty; // Sets the speed of the can according to the difficulty.
            currentScreen.addChildren(c); // Adds the Can ocject to the current screen.
        } // End of a for loop.
        currentScreen.addChildren(new Crosshair(0, 0, 100, 100)); // Adds a Crosshair object to the current screen.

        timeStep = System.currentTimeMillis(); // Sets the timeStep to the current time in milliseconds.
    } // End of a method declaration.

    /**
     * Displays the "You won." screen.
     */
    void gameWin() { // A package-local method declaration without parameters and no return value.
        bs.loescheAlles(); // Clears the "Bildschirm".

        bs.setzeFarbe(Color.green); // Fills the "Bildschirm" green.
        currentScreen = new Screen(); // Creates a new virtual screen.

        currentScreen.addChildren(new Text(10, 200, 780, 100, "You Won.", 100)); // Adds a Text object to the current screen.

        currentScreen.render(st); // Renders the current screen.

        this.dispose(); // Disposes this object.
    } // End of a method declaration.

    private void gameLose() { // A private method declaration without parameters and no return value.
        bs.loescheAlles(); // Clears the "Bildschirm".

        bs.setzeFarbe(Color.red); // Fills the "Bildschirm" red.
        currentScreen = new Screen(); // Creates a new virtual screen.

        currentScreen.addChildren(new Text(10, 200, 780, 100, "You Lost.", 100)); // Adds a Text object to the current screen.

        currentScreen.render(st); // Renders the current screen.

        this.dispose(); // Disposes this object.
    } // End of a method declaration.

    private void dispose() { // A private method declaration without parameters and no return value.
        bs.gibFrei(); // Disposal of the "Bildschirm" object.
        ms.gibFrei(); // Disposal of the "Maus" object.
        ts.gibFrei(); // Disposal of the "Tastatur" object.
        st.gibFrei(); // Disposal of the "Buntstift" object.
    } // End of a method declaration.

} // End of a class declaration.
