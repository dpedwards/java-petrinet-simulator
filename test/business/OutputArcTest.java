package test.business;

import org.junit.Test;
import static org.junit.Assert.*;

import business.OutputArc;
import business.Place;
import business.TokenSet;
import business.Transition;

public class OutputArcTest {

    @Test
    public void testOutputArcConstructor() {
        Place place = new Place("p1");
        Transition transition = new Transition("t1");
        OutputArc outputArc = new OutputArc(place, transition);

        assertNotNull(outputArc);
        assertTrue(outputArc.getId().startsWith("o"));
        assertEquals(place, outputArc.getPlace());
        assertEquals(transition, outputArc.getTransition());
        assertEquals("1", outputArc.getExecuteText());
    }

    @Test
    public void testOutputArcConstructorWithAction() {
        Place place = new Place("p1");
        Transition transition = new Transition("t1");
        String action = "2";
        OutputArc outputArc = new OutputArc("o1", place, transition, action);

        assertNotNull(outputArc);
        assertEquals("o1", outputArc.getId());
        assertEquals(place, outputArc.getPlace());
        assertEquals(transition, outputArc.getTransition());
        assertEquals(action, outputArc.getExecuteText());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEvaluate() {
        Place place = new Place("p1");
        Transition transition = new Transition("t1");
        OutputArc outputArc = new OutputArc(place, transition);

        outputArc.evaluate();
    }

    @Test
    public void testExecute() {
        Place place = new Place("p1");
        Transition transition = new Transition("t1");
        OutputArc outputArc = new OutputArc(place, transition);

        TokenSet tokenSet = outputArc.execute();

        assertNotNull(tokenSet);
        //assertEquals("1", tokenSet.toString());
    }
}