/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * Represents a Place in a Petri Net, which can contain tokens.
 * The Place class is an example of the Composite design pattern. 
 * This pattern is used when you need to treat a group of objects in the same way as a single instance of an object. 
 * The Composite pattern composes objects into tree structures to represent part-whole hierarchies.
 * In this case, Place is a component of the PetriNet composite object. 
 * It extends NetObject and contains a TokenSet, which could be considered as a composite of Token objects. 
 * The Place class provides methods for managing these tokens, which is typical of the Composite pattern.
 */
public class Place extends NetObject {

    /** List of tokens this place contains. */
    private TokenSet tokens = new TokenSet();

    /** Maximum number of tokens this place can hold (0 = no limit).*/
    private int capacity = 0;

    /**
     * Default constructor for creating a Place with a generated ID.
     */
    public Place() {
        this.id = "p" + this.id;
    }

    /**
     * Constructor for creating a Place with a specified ID.
     *
     * @param id The unique identifier for the Place.
     */
    public Place(String id) {
        this.id = id;
    }

    /**
     * Gets the tokens contained in this Place.
     *
     * @return The TokenSet representing the tokens in this Place.
     */
    public TokenSet getTokens() {
        return tokens;
    }

    /**
     * Sets the tokens in this Place.
     *
     * @param tokens The TokenSet to set as the tokens in this Place.
     */
    public void setTokens(TokenSet tokens) {
        this.tokens = tokens;
    }

    /**
     * Adds a TokenSet to the tokens in this Place.
     *
     * @param tokenSet The TokenSet to add to the current tokens in this Place.
     */
    public void addToken(TokenSet tokenSet) {
        tokens.addAll(tokenSet);
    }

    /**
     * Removes a TokenSet from the tokens in this Place.
     *
     * @param tokenSet The TokenSet to remove from the current tokens in this Place.
     */
    public void removeTokens(TokenSet tokenSet) {
        tokens.removeAll(tokenSet);
    }

    /**
     * Gets the capacity of this Place, which represents the maximum number of tokens it can hold.
     *
     * @return The capacity of this Place.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of this Place, which represents the maximum number of tokens it can hold.
     *
     * @param capacity The capacity to set for this Place.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}