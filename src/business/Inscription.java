/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 *
 * @author Davain Pablo Edwards
 */
public interface Inscription {

    /** Gives the hability to evaluate */
    public boolean evaluate();

    /** Gives the hability to execute */
    public TokenSet execute();

    /** Gives the hability to obtain a TokenSet */
    public TokenSet getTokenSet();
}
