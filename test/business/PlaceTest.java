package business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import business.Place;
import business.TokenSet;
import business.Token;

/**
 * Test class for Place.
 * This class performs unit tests on the Place class to ensure that its token handling capabilities
 * are functioning as expected. It covers testing the setting and getting of tokens in a place.
 */
public class PlaceTest {

    private Place place;
    private TokenSet tokenSet;

    /**
     * Sets up the test environment before each test.
     * Initializes a new Place and a TokenSet with a token (assuming Token is a valid class) for testing.
     */
    @Before
    public void setUp() {
        place = new Place();
        tokenSet = new TokenSet();
        tokenSet.add(new Token()); // Assuming Token is a valid class
    }

    /**
     * Tests the getTokens method of Place.
     * Verifies that the method correctly returns the set of tokens assigned to the place.
     */
    @Test
    public void testGetTokens() {
        place.setTokens(tokenSet);
        assertEquals(tokenSet, place.getTokens());
    }

    /**
     * Tests the setTokens method of Place.
     * Ensures that the method correctly assigns a set of tokens to the place.
     */
    @Test
    public void testSetTokens() {
        place.setTokens(tokenSet);
        assertEquals(tokenSet, place.getTokens());
    }
}
