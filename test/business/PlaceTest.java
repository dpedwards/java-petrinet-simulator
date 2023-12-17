package test.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import business.Place;
import business.*;

public class PlaceTest {

    private Place place;
    private TokenSet tokenSet;

    @Before
    public void setUp() {
        place = new Place();
        tokenSet = new TokenSet();
        tokenSet.add(new Token()); // Assuming Token is a valid class
    }

    @Test
    public void testGetTokens() {
        place.setTokens(tokenSet);
        assertEquals(tokenSet, place.getTokens());
    }

    @Test
    public void testSetTokens() {
        place.setTokens(tokenSet);
        assertEquals(tokenSet, place.getTokens());
    }

    /*@Test
    public void testAddToken() {
        place.setTokens(new TokenSet());
        place.addToken(tokenSet);
        assertEquals(tokenSet, place.getTokens());
    }*/
}