/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A collection of tokens.
 */
public class TokenSet extends AbstractCollection<Token> {

    private ArrayList<Token> tokenList = new ArrayList<>();

    public TokenSet() {
    }

    public TokenSet(Object object) {
        if (object instanceof Token) {
            tokenList.add((Token) object);
        } else if (object instanceof TokenSet) {
            tokenList.addAll((TokenSet) object);
        } else {
            tokenList.add(new Token(object));
        }
    }

    public TokenSet(Object object, long time) {
        if (object instanceof TokenSet) {
            this.addAll((TokenSet) object);
        } else {
            tokenList.add(new Token(object, time));
        }
    }

    public TokenSet(Object object, String initialMarkingExpression) {
        tokenList.add(new Token(object, 0, initialMarkingExpression));
    }

    public TokenSet(Object object, long timestamp, String initialMarkingExpression) {
        tokenList.add(new Token(object, timestamp, initialMarkingExpression));
    }

    @Override
    public Iterator<Token> iterator() {
        return tokenList.iterator();
    }

    @Override
    public int size() {
        return tokenList.size();
    }

    @Override
    public boolean add(Token token) {
        return tokenList.add(token);
    }

    @Override
    public boolean addAll(Collection<? extends Token> tokenSet) {
        boolean b = tokenList.addAll(tokenSet);
        return b;
    }

    public ArrayList<Token> getTokenList() {
        return this.tokenList;
    }

    public Token get(int id) {
        return tokenList.get(id);
    }

    @Override
    public boolean remove(Object o) {
        return tokenList.remove(o);
    }

    /**
     * Checks if there exists at least one timed token with a time less than or equal to the given timestamp.
     *
     * @param timestamp the timestamp to compare with
     * @return true if such a token exists, false otherwise
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
     * @param timestamp the amount to increment by
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

    @Override
    public void clear() {
        tokenList.clear();
    }
}
