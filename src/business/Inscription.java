/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * This is an interface named "Inscription" which defines a contract for its implementing classes.
 * It can be part of the Strategy design pattern. 
 * The Strategy pattern is characterized by the definition of a common public interface for a family of algorithms or behaviors. 
 * In this case, Inscription defines a common interface for a family of behaviors (evaluate, execute, getTokenSet), 
 * which can be implemented in many different ways by classes that implement the Inscription interface. 
 * This allows the algorithm to be selected at runtime.
 */
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
     * TODO: 
     * Provides the ability to obtain a TokenSet.
     * Implementing classes must define how to get the TokenSet.
     * @return a TokenSet
     */
    //public TokenSet getTokenSet();
}

