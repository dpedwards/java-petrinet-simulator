package test.business;

import business.InputArc;
import business.Place;
import business.TokenSet;
import business.Transition;
import business.Token;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for InputArc.
 * This class tests the functionality of the InputArc class, 
 * ensuring that its constructors, evaluation, and token manipulation behaviors
 * are functioning as expected.
 */
public class InputArcTest {

    /**
     * Tests the constructor of InputArc with two parameters.
     * Verifies that the constructed InputArc object is not null and that the Place 
     * and Transition passed to the constructor are correctly set.
     */
    @Test
    public void testConstructorWithTwoParameters() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        assertNotNull("The arc object should not be null", arc);
        assertEquals("The place should match", place, arc.getPlace());
        assertEquals("The transition should match", transition, arc.getTransition());
    }

    /**
     * Tests the constructor of InputArc with four parameters.
     * Verifies the correct assignment of ID, Place, Transition, and Action attributes.
     */
    @Test
    public void testConstructorWithFourParameters() {
        String id = "testId";
        Place place = new Place();
        Transition transition = new Transition();
        String action = "testAction";
        InputArc arc = new InputArc(id, place, transition, action);
        assertNotNull(arc);
        assertEquals(id, arc.getId());
        assertEquals(place, arc.getPlace());
        assertEquals(transition, arc.getTransition());
        assertEquals(action, arc.getExecuteText());
    }

    /**
     * Tests the evaluate method of InputArc.
     * Verifies that the evaluation returns false when the Place has no tokens
     * and true when it does have tokens.
     */
    @Test
    public void testEvaluate() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        assertFalse(arc.evaluate());
        TokenSet tokenSet = new TokenSet();
        tokenSet.add(new Token());
        place.addToken(tokenSet);
        assertTrue(arc.evaluate());
    }

    /**
     * Tests the execute method of InputArc.
     * Verifies that the method returns a non-null TokenSet and that its size is correct.
     */
    @Test
    public void testExecute() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        TokenSet tokenSet = arc.execute();
        assertNotNull(tokenSet);
        assertEquals(1, tokenSet.size());
    }

    /**
     * Tests the getTokenSet method of InputArc.
     * Verifies that the method returns the correct set of tokens associated with the Place.
     */
    @Test
    public void testGetTokenSet() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        assertEquals(place.getTokens(), arc.getTokenSet());
    }

    /**
     * Tests the removeTimedToken method of InputArc.
     * Verifies that the method correctly removes a timed token from a given TokenSet.
     */
    @Test
    public void testRemoveTimedToken() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        Token token = new Token();
        TokenSet tokenSet = new TokenSet();
        tokenSet.add(token);
        place.addToken(tokenSet);
        assertEquals(token, arc.removeTimedToken(place.getTokens()));
    }

    /**
     * Tests the get and set methods for the evaluate text attribute of InputArc.
     * Verifies that the setEvaluateText correctly assigns the evaluate text 
     * and getEvaluateText retrieves it accurately.
     */
    @Test
    public void testGetSetEvaluateText() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        String evaluateText = "testEvaluateText";
        arc.setEvaluateText(evaluateText);
        assertEquals(evaluateText, arc.getEvaluateText());
    }

    /**
     * Tests the get and set methods for the execute text attribute of InputArc.
     * Verifies that the setExecuteText correctly assigns the execute text 
     * and getExecuteText retrieves it accurately.
     */
    @Test
    public void testGetSetExecuteText() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        String executeText = "testExecuteText";
        arc.setExecuteText(executeText);
        assertEquals(executeText, arc.getExecuteText());
    }
}
