/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

// This is an interface named "Inscription" which defines a contract for its implementing classes.
public interface Inscription {

    /** 
     * Provides the ability to evaluate a condition.
     * Implementing classes must define how the evaluation is done.
     * @return true if the evaluation succeeds, false otherwise
     */
    public boolean evaluate();

    /** 
     * Provides the ability to execute an action.
     * Implementing classes must define the action to be executed.
     * @return a TokenSet representing the result of the execution
     */
    public TokenSet execute();

    /** 
     * Provides the ability to obtain a TokenSet.
     * Implementing classes must define how to get the TokenSet.
     * @return a TokenSet
     */
    public TokenSet getTokenSet();
}

