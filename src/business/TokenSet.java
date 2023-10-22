/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class represents a set of tokens and provides various operations on tokens.
 */
public class TokenSet extends AbstractCollection<Token> {

    // ArrayList to store tokens in the TokenSet
    private ArrayList<Token> tokenList = new ArrayList<>();

    /**
     * Default constructor for TokenSet.
     */
    public TokenSet() {
    }

    /**
     * Constructor for TokenSet that accepts an object.
     *
     * @param object The object to be added as a token to the set.
     */
    public TokenSet(Object object) {
        if (object instanceof Token) {
            tokenList.add((Token) object);
        } else if (object instanceof TokenSet) {
            tokenList.addAll((TokenSet) object);
        } else {
            tokenList.add(new Token(object));
        }
    }

    /**
     * Constructor for TokenSet that accepts an object and a timestamp.
     *
     * @param object  The object to be added as a token to the set.
     * @param time    The timestamp associated with the token.
     */
    public TokenSet(Object object, long time) {
        if (object instanceof TokenSet) {
            this.addAll((TokenSet) object);
        } else {
            tokenList.add(new Token(object, time));
        }
    }

    /**
     * Constructor for TokenSet that accepts an object and an initial marking expression.
     *
     * @param object                   The object to be added as a token to the set.
     * @param initialMarkingExpression The initial marking expression associated with the token.
     */
    public TokenSet(Object object, String initialMarkingExpression) {
        tokenList.add(new Token(object, 0, initialMarkingExpression));
    }

    /**
     * Constructor for TokenSet that accepts an object, a timestamp, and an initial marking expression.
     *
     * @param object                   The object to be added as a token to the set.
     * @param timestamp                The timestamp associated with the token.
     * @param initialMarkingExpression The initial marking expression associated with the token.
     */
    public TokenSet(Object object, long timestamp, String initialMarkingExpression) {
        tokenList.add(new Token(object, timestamp, initialMarkingExpression));
    }

    /**
     * Returns an iterator over the tokens in this TokenSet.
     *
     * @return An iterator over the tokens.
     */
    @Override
    public Iterator<Token> iterator() {
        return tokenList.iterator();
    }

    /**
     * Returns the number of tokens in this TokenSet.
     *
     * @return The number of tokens.
     */
    @Override
    public int size() {
        return tokenList.size();
    }

    /**
     * Adds a token to this TokenSet.
     *
     * @param token The token to be added.
     * @return true if the token was added successfully, false otherwise.
     */
    @Override
    public boolean add(Token token) {
        return tokenList.add(token);
    }

    /**
     * Adds all tokens from a collection to this TokenSet.
     *
     * @param tokenSet The collection of tokens to be added.
     * @return true if the tokens were added successfully, false otherwise.
     */
    @Override
    public boolean addAll(Collection<? extends Token> tokenSet) {
        boolean added = tokenList.addAll(tokenSet);
        return added;
    }

    /**
     * Returns the ArrayList of tokens in this TokenSet.
     *
     * @return The ArrayList of tokens.
     */
    public ArrayList<Token> getTokenList() {
        return this.tokenList;
    }

    /**
     * Gets a token from the TokenSet by its index.
     *
     * @param id The index of the token to retrieve.
     * @return The token at the specified index.
     */
    public Token get(int id) {
        return tokenList.get(id);
    }

    /**
     * Removes a token from this TokenSet.
     *
     * @param o The token to be removed.
     * @return true if the token was removed successfully, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        return tokenList.remove(o);
    }

    /**
     * Checks if there exists at least one timed token with a time less than or equal to the given timestamp.
     *
     * @param timestamp The timestamp to compare with.
     * @return true if such a token exists, false otherwise.
     */
    public boolean containsTime(long timestamp) {
        boolean found = false;
        boolean allzero = true;
        Iterator<Token> it = tokenList.iterator();
        while (it.hasNext()) {
            Token token = it.next();
            if (token.getTimestamp() <= timestamp) {
                found = true;
            }
            if (token.getTimestamp() != 0) {
                allzero = false;
            }
        }

        return allzero || found;
    }

    /**
     * Increments the timestamp of timed tokens by a fixed amount.
     *
     * @param timestamp The amount to increment by.
     */
    public void incrementTime(long timestamp) {
        Iterator<Token> it = tokenList.iterator();
        while (it.hasNext()) {
            Token token = it.next();
            if (token.getTimestamp() != 0) {
                token.setTimestamp(token.getTimestamp() + timestamp);
            }
        }
    }

    /**
     * Removes all occurrences of tokens from this TokenSet that are also present in a given collection.
     *
     * @param c The collection containing tokens to be removed.
     * @return true if all tokens were removed successfully, false otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<Token> it = tokenList.iterator();
        while (it.hasNext()) {
            Token i = it.next();
            Iterator<?> it2 = c.iterator();
            while (it2.hasNext()) {
                Token j = (Token) it2.next();
                if (j.equals(i)) {
                    it.remove();
                    it2.remove();
                }
            }
        }
        return true;
    }

    /**
     * Removes all tokens from this TokenSet, resulting in an empty TokenSet.
     */
    @Override
    public void clear() {
        tokenList.clear();
    }
}