package test.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import business.Token;

/**
 * Test class for Token.
 * This class performs unit tests on the Token class, ensuring that tokens are correctly
 * created with various constructors and that their properties are set as expected.
 */
public class TokenTest {

    private Token token;

    /**
     * Sets up the test environment before each test.
     * Initializes a new instance of Token for testing.
     */
    @Before
    public void setUp() {
        token = new Token();
    }

    /**
     * Tests the constructor of Token with an object.
     * Verifies that the Token is correctly created with the provided object.
     */
    @Test
    public void testConstructorWithObject() {
        Object obj = new Object();
        Token token = new Token(obj);
        assertEquals(obj, token.getObject());
    }

    /**
     * Tests the constructor of Token with an object and a timestamp.
     * Verifies that the Token is correctly created with the provided object and timestamp.
     */
    @Test
    public void testConstructorWithObjectAndTimestamp() {
        Object obj = new Object();
        long timestamp = System.currentTimeMillis();
        Token token = new Token(obj, timestamp);
        assertEquals(obj, token.getObject());
        assertEquals(timestamp, token.getTimestamp());
    }

    /**
     * Tests the constructor of Token with an object, timestamp, and initial marking expression.
     * Verifies that the Token is correctly created with the provided object, timestamp,
     * and initial marking expression.
     */
    @Test
    public void testConstructorWithObjectTimestampAndInitialMarkingExpression() {
        Object obj = new Object();
        long timestamp = System.currentTimeMillis();
        String initialMarkingExpression = "testExpression";
        Token token = new Token(obj, timestamp, initialMarkingExpression);
        assertEquals(obj, token.getObject());
        assertEquals(timestamp, token.getTimestamp());
        assertEquals(initialMarkingExpression, token.getInitialMarkingExpression());
    }
}
