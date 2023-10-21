/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * Represents an OutputArc, a type of arc that connects a Place to a Transition in a Petri Net.
 */
public class OutputArc extends Arc implements Inscription {

    /** Text string that represents the expression this arc will execute by default */
    private String executeText = "1";

    /**
     * Constructor to create an output arc.
     *
     * @param place      The Place this arc is coming from.
     * @param transition The Transition this arc is going to.
     */
    public OutputArc(Place place, Transition transition) {
        // Generate a unique identifier for the arc.
        this.id = "o" + this.id;
        this.place = place;
        this.transition = transition;
    }

    /**
     * Constructor to create a new output arc with specific attributes.
     *
     * @param id     The unique identifier for the arc.
     * @param place  The Place this arc is coming from.
     * @param transition The Transition this arc is going to.
     * @param action The action or expression associated with this arc.
     */
    public OutputArc(String id, Place place, Transition transition, String action) {
        this.id = id;
        this.place = place;
        this.transition = transition;
        this.executeText = action;
    }

    /**
     * Evaluates this arc, but this method is not supported for OutputArcs.
     *
     * @return Always throws an UnsupportedOperationException.
     */
    public boolean evaluate() {
        throw new UnsupportedOperationException("Evaluating OutputArcs is not supported.");
    }

    /**
     * Executes this arc when a transition fires.
     *
     * @return A TokenSet representing the result of the execution.
     */
    public TokenSet execute() {
        return new TokenSet(executeText);
    }

    /**
     * Returns the TokenSet of the Place this arc is connected to.
     *
     * @return The TokenSet of the connected Place.
     */
    public TokenSet getTokenSet() {
        return this.getPlace().getTokens();
    }

    /**
     * Gets the expression associated with this arc's execution.
     *
     * @return The executeText representing the execution action.
     */
    public String getExecuteText() {
        return executeText;
    }

    /**
     * Sets the expression associated with this arc's execution.
     *
     * @param executeText The executeText to set.
     */
    public void setExecuteText(String executeText) {
        this.executeText = executeText;
    }
}