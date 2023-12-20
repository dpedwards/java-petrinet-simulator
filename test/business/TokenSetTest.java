package test.business;

import org.junit.Before;
import org.junit.Test;
import business.TokenSet;
import business.Token;
import static org.junit.Assert.*;

/**
 * Test class for TokenSet.
 * This class conducts unit tests on the TokenSet class, ensuring the correct functionality
 * of its constructors and the ability to handle tokens as expected. It includes tests for the
 * default constructor, constructors with tokens, and constructor with an object and time.
 */
public class TokenSetTest {

    private TokenSet tokenSet;

    /**
     * Sets up the test environment before each test.
     * Initializes a new instance of TokenSet for testing.
     */
    @Before
    public void setUp() {
        tokenSet = new TokenSet();
    }

    /**
     * Tests the default constructor of TokenSet.
     * Verifies that a newly created TokenSet is not null and is initially empty.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(tokenSet);
        assertEquals(0, tokenSet.size());
    }

    /**
     * Tests the constructor of TokenSet with a single token.
     * Verifies that the TokenSet correctly contains the provided token and has the correct size.
     */
    @Test
    public void testConstructorWithToken() {
        Token token = new Token("Test");
        TokenSet tokenSetWithToken = new TokenSet(token);
        assertNotNull(tokenSetWithToken);
        assertEquals(1, tokenSetWithToken.size());
        assertTrue(tokenSetWithToken.contains(token));
    }

    /**
     * Tests the constructor of TokenSet with another TokenSet.
     * Verifies that the new TokenSet correctly replicates the tokens of the provided TokenSet.
     */
    @Test
    public void testConstructorWithTokenSet() {
        Token token = new Token("Test");
        TokenSet anotherTokenSet = new TokenSet(token);
        TokenSet tokenSetWithTokenSet = new TokenSet(anotherTokenSet);
        assertNotNull(tokenSetWithTokenSet);
        assertEquals(1, tokenSetWithTokenSet.size());
        assertTrue(tokenSetWithTokenSet.contains(token));
    }

    /**
     * Tests the constructor of TokenSet with an object and a timestamp.
     * Verifies that the TokenSet is correctly initialized with a token having the specified timestamp.
     */
    @Test
    public void testConstructorWithObjectAndTime() {
        long time = System.currentTimeMillis();
        TokenSet tokenSetWithObjectAndTime = new TokenSet("Test", time);
        assertNotNull(tokenSetWithObjectAndTime);
        assertEquals(1, tokenSetWithObjectAndTime.size());
        assertEquals(time, tokenSetWithObjectAndTime.get(0).getTimestamp());
    }
}
