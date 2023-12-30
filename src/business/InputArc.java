/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.Iterator;

/**
 * Represents an input arc in a petri net or a related data structure.
 * <p>
 * An input arc is typically used to connect a place to a transition, signifying
 * that tokens from the associated place are required for the transition to occur.
 * </p>
 * As an extension of the {@link Arc} class, this class inherits properties like
 * source, target, and any behaviors defined in the parent class.
 * 
 * Implementing the {@link Inscription} interface suggests that this class 
 * provides mechanisms to annotate or describe the behavior and properties 
 * of the arc, like the number of tokens needed for a transition.
 * 
 * The InputArc class is an example of the Decorator design pattern. 
 * This pattern involves a set of decorator classes that are used to wrap concrete classes. 
 * Decorators mirror the type of the objects they are decorating, they aren't just used to add, 
 * or "decorate", the object they wrap with new behavior.
 * In this case, InputArc extends Arc and implements Inscription, which suggests that it adds
 * additional behavior (like handling inscriptions) to the base Arc functionality. 
 * This is typical of the Decorator pattern.
 */
public class InputArc extends Arc implements Inscription {

    /** 
     * A text string representing the default expression that this arc evaluates.
     * By default, it checks if the size of the token set is greater than 0.
     */
    private String evaluateText = "getTokenSet().size()>0";

    /** 
     * A text string representing the default expression that this arc executes.
     * By default, it has a value of "1", which might indicate the default number
     * of tokens to be transferred or processed.
     */
    private String executeText = "1";

    /**
     * Constructs a new instance of InputArc, initializing its place, transition, and unique id.
     *
     * @param place      The place associated with this arc.
     * @param transition The transition associated with this arc.
     */
    public InputArc(Place place, Transition transition) {
        this.id = generateId();  // Call a method to generate a unique ID for this arc.
        this.place = place;
        this.transition = transition;
    }
    
    /**
     * Generates a unique identifier for the InputArc.
     *
     * @return A unique identifier string.
     */
    private String generateId() {
        return "i" + System.currentTimeMillis();
    }

    /**
     * Constructs a new instance of InputArc, initializing its attributes with the given parameters.
     *
     * @param id         The unique identifier for this InputArc.
     * @param place      The place associated with this arc.
     * @param transition The transition associated with this arc.
     * @param action     The action text to be executed when this arc is triggered.
     */
    public InputArc(String id, Place place, Transition transition, String action) {
        this.id = id;
        this.place = place;
        this.transition = transition;
        this.executeText = action;
    }

    /**
     * Evaluates whether the arc is enabled based on the number of tokens in its associated place.
     *
     * @return true if the associated place has more than 0 tokens, false otherwise.
     */
    public boolean evaluate() {
        return getTokenSet().size() > 0;
    }

    /**
     * Executes the specified action for this arc, resulting in a new TokenSet.
     * 
     * @return A new TokenSet created with the executeText of this arc.
     */
    public TokenSet execute() {
        return new TokenSet(executeText);
    }

    /**
     * Retrieves the set of tokens associated with the place to which this arc is connected.
     *
     * @return A TokenSet representing the tokens in the associated place.
     */
    public TokenSet getTokenSet() {
        return this.getPlace().getTokens();
    }

    /**
     * Retrieves and removes a token from the given token set, where the token's timestamp 
     * is less than or equal to the current simulation global clock.
     *
     * @param tokenSet The set of tokens to search through.
     * @return A Token with timestamp less than or equal to the global clock, or null if none found.
     */
    public Token removeTimedToken(TokenSet tokenSet) {
        Iterator<Token> it = tokenSet.iterator();
        while (it.hasNext()) {
            Token token = it.next();
            if (token.getTimestamp() <= transition.getGlobalClock()) {
                it.remove();  // Removing the token from the set.
                return token;
            }
        }
        return null;
    }

    /**
     * Retrieves the text used for evaluation purposes.
     *
     * @return The current evaluation text.
     */
    public String getEvaluateText() {
        return evaluateText;
    }

    /**
     * Sets a new text for evaluation purposes.
     *
     * @param evaluateText The new text to be used for evaluation.
     */
    public void setEvaluateText(String evaluateText) {
        this.evaluateText = evaluateText;
    }

    /**
     * Retrieves the text used for execution purposes.
     *
     * @return The current execution text.
     */
    public String getExecuteText() {
        return executeText;
    }

    /**
     * Sets a new text for execution purposes.
     *
     * @param executeText The new text to be used for execution.
     */
    public void setExecuteText(String executeText) {
        this.executeText = executeText;
    }
}