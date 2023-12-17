package test.business;

import business.InputArc;
import business.Place;
import business.TokenSet;
import business.Transition;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import business.Token;

public class InputArcTest {
    @Test
    public void testConstructorWithTwoParameters() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        assertNotNull("The arc object should not be null", arc);
        assertEquals("The place should match", place, arc.getPlace());
        assertEquals("The transition should match", transition, arc.getTransition());
    }

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

    @Test
    public void testExecute() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        TokenSet tokenSet = arc.execute();
        assertNotNull(tokenSet);
        assertEquals(1, tokenSet.size());
    }

    @Test
    public void testGetTokenSet() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        assertEquals(place.getTokens(), arc.getTokenSet());
    }

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

    @Test
    public void testGetSetEvaluateText() {
        Place place = new Place();
        Transition transition = new Transition();
        InputArc arc = new InputArc(place, transition);
        String evaluateText = "testEvaluateText";
        arc.setEvaluateText(evaluateText);
        assertEquals(evaluateText, arc.getEvaluateText());
    }

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
