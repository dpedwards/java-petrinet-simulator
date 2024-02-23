package business;

import org.junit.Before;
import org.junit.Test;
import business.PetriNet;
import business.Place;
import business.Transition;
import static org.junit.Assert.*;

/**
 * Test class for PetriNet.
 * This class performs unit tests on the PetriNet class, verifying the functionality
 * of adding places and transitions, and checking the state (dead or alive) of the PetriNet.
 */
public class PetriNetTest {

    private PetriNet petriNet;
    private Place place;
    private Transition transition;

    /**
     * Sets up the test environment before each test.
     * Initializes a new PetriNet, along with a Place and a Transition to be used in the tests.
     */
    @Before
    public void setUp() {
        petriNet = new PetriNet();
        place = new Place();
        transition = new Transition();
    }

    /**
     * Tests the isDead method of PetriNet.
     * Initially checks if a new PetriNet is dead, and then verifies that adding a transition
     * changes its state to not dead (assuming adding a transition makes the PetriNet not dead).
     */
    @Test
    public void testIsDead() {
        assertTrue(petriNet.isDead());
        petriNet.addTransition(transition);
        assertFalse(petriNet.isDead());
    }

    /**
     * Tests the addPlace method of PetriNet.
     * Verifies that a Place can be successfully added to the PetriNet.
     * Assumes the existence of a method to retrieve places from the PetriNet.
     */
    @Test
    public void testAddPlace() {
        petriNet.addPlace(place);
        assertTrue(petriNet.getPlaces().contains(place));
    }

    /**
     * Tests the addTransition method of PetriNet.
     * Verifies that a Transition can be successfully added to the PetriNet.
     * Assumes the existence of a method to retrieve transitions from the PetriNet.
     */
    @Test
    public void testAddTransition() {
        petriNet.addTransition(transition);
        assertTrue(petriNet.getTransitions().contains(transition));
    }
}
