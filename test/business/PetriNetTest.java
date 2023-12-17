package test.business;

import org.junit.Before;
import org.junit.Test;

import business.PetriNet;
import business.Place;
import business.Transition;

import static org.junit.Assert.*;

public class PetriNetTest {

    private PetriNet petriNet;
    private Place place;
    private Transition transition;

    @Before
    public void setUp() {
        petriNet = new PetriNet();
        place = new Place();
        transition = new Transition();
    }

    @Test
    public void testIsDead() {
        assertTrue(petriNet.isDead());
        petriNet.addTransition(transition);
        // Assuming that adding a transition makes the PetriNet not dead
        assertFalse(petriNet.isDead());
    }

    @Test
    public void testAddPlace() {
        petriNet.addPlace(place);
        // Assuming that you have a method to get places
        assertTrue(petriNet.getPlaces().contains(place));
    }

    @Test
    public void testAddTransition() {
        petriNet.addTransition(transition);
        // Assuming that you have a method to get transitions
        assertTrue(petriNet.getTransitions().contains(transition));
    }
}