import java.util.EventListener;

/**
 * The interface for event callbacks.
 */
interface ICallback extends EventListener { // An interface declaration extending EventListener.

    /**
     * The method to be called upon the event.
     */
    void callback(); // A method pattern without parameters and no return type.

} // End of an interface declaration.
