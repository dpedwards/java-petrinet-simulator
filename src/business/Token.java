/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * Represents a Token in a network or system, with properties for its color, timestamp, and initial marking expression.
 * The `Token` class is an example of the Builder design pattern. 
 * This pattern is used to construct a complex object step by step and the final step will return the object. 
 * The process of constructing an object should be generic so that it can create different representations of the same configuration.
 * In this case, `Token` uses constructor overloading to build a `Token` object in steps. 
 * Each constructor calls the one before it, adding one more parameter each time. This is typical of the Builder pattern.
 */
public class Token {

    /** Represents the initial marking expression of the token. */
    private String initialMarkingExpression = "";

    /** Represents the color or nature of the token. */
    private Object object;

    /** Represents the timestamp of the token's creation or modification. */
    private long timestamp = 0;

    /**
     * Creates a new token with the given object representing its color or nature.
    * 
    * @param object The object representing the color or nature of the token.
    */
    public Token(Object object) {
        this.object = object;
    }

    /**
     * Creates a new token with the given object and timestamp.
    * 
    * @param object    The object representing the color or nature of the token.
    * @param timestamp The timestamp of the token's creation or modification.
    */
    public Token(Object object, long timestamp) {
        this(object);  // Call the other constructor to reduce code redundancy
        this.timestamp = timestamp;
    }

    /**
     * Creates a new token with the given object, timestamp, and initial marking expression.
    * 
    * @param object                  The object representing the color or nature of the token.
    * @param timestamp               The timestamp of the token's creation or modification.
    * @param initialMarkingExpression The initial marking expression of the token.
    */
    public Token(Object object, long timestamp, String initialMarkingExpression) {
        this(object, timestamp);  // Call the other constructor to reduce code redundancy
        this.initialMarkingExpression = initialMarkingExpression;
    }

    public Token() {
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Token objToken = (Token) obj;
        return timestamp == objToken.timestamp &&
            (object != null ? object.equals(objToken.object) : objToken.object == null);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * <code>java.util.Hashtable</code>.
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));

        return result;
    }

    /**
     * Returns a string representation of the Token object. 
     * If the initial marking expression is present, it will be used; 
     * otherwise, it will combine the object's string representation 
     * and, if present, the timestamp.
     * 
     * @return the string representation of the Token object
     */
    @Override
    public String toString() {
        if (!initialMarkingExpression.isEmpty()) {
            return initialMarkingExpression;
        }
        
        String s = object.toString();
        if (timestamp != 0) {
            s += " " + timestamp;
        }
        return s;
    }

    /**
     * Retrieves the object associated with this Token.
     * 
     * @return the object of this Token
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the object for this Token.
     * 
     * @param object the object to associate with this Token
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Retrieves the timestamp associated with this Token.
     * 
     * @return the timestamp of this Token
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for this Token.
     * 
     * @param timestamp the timestamp to associate with this Token
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Retrieves the initial marking expression associated with this Token.
     * 
     * @return the initial marking expression of this Token
     */
    public String getInitialMarkingExpression() {
        return initialMarkingExpression;
    }

    /**
     * Sets the initial marking expression for this Token.
     * 
     * @param initialMarkingExpression the initial marking expression to associate with this Token
     */
    public void setInitialMarkingExpression(String initialMarkingExpression) {
        this.initialMarkingExpression = initialMarkingExpression;
    }

}
