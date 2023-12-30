/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * Represents a configuration or controller class for managing and interacting 
 * with a PetriNet model. The class provides various modes to define the 
 * current application state, such as selection, place addition, transition 
 * addition, etc.
 * 
 * The Global class is an example of the Singleton design pattern. 
 * This pattern ensures that a class has only one instance and provides a global point of access to it.
 */
public class Global {

/** 
     * Holds the current instance of the PetriNet model for the application.
     */
    public static PetriNet petriNet = new PetriNet();

    /** 
     * Indicates the current mode of the application, 
     * which determines the primary action or behavior.
     */
    public static int mode = 0;

    /** 
     * Mode constant to enable the selection of figures or components.
     */
    public static final int SELECTMODE = 0;

    /** 
     * Mode constant to enable the addition of places to the PetriNet.
     */
    public static final int PLACEMODE = 1;

    /** 
     * Mode constant to enable the addition of transitions to the PetriNet.
     */
    public static final int TRANSITIONMODE = 2;

    /** 
     * Mode constant to enable the addition of normal arcs to the PetriNet.
     */
    public static final int NORMALARCMODE = 3;

    /** 
     * Mode constant to indicate that the application is in simulation mode.
     */
    public static final int SIMULATIONMODE = 4;
}
