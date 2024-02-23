package business;

import org.junit.Test;
import static org.junit.Assert.*;
import business.OutputArc;
import business.Place;
import business.TokenSet;
import business.Transition;

/**
 * Test class for OutputArc.
 * This class conducts unit tests on various constructors and methods of the OutputArc class,
 * verifying correct initialization, behavior, and handling of tokens in a Petri net context.
 */
public class OutputArcTest {

    /**
     * Tests the constructor of OutputArc with a place and a transition.
     * Verifies that the OutputArc object is correctly instantiated with the provided Place and Transition.
     * Also checks the default execute text and that the ID starts with a specific character.
     */
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

    /**
     * Tests the constructor of OutputArc with an ID, place, transition, and action.
     * Checks that the OutputArc is properly initialized with the specified ID, Place, Transition, and action.
     */
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

    /**
     * Tests the evaluate method of OutputArc.
     * Expects an UnsupportedOperationException to be thrown, as OutputArc should not support evaluation.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testEvaluate() {
        Place place = new Place("p1");
        Transition transition = new Transition("t1");
        OutputArc outputArc = new OutputArc(place, transition);

        outputArc.evaluate();
    }

    /**
     * Tests the execute method of OutputArc.
     * Verifies that the method returns a non-null TokenSet.
     * Note: Uncomment the assertEquals line and adjust as needed for specific implementations.
     */
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
