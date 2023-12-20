package test.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import business.Global;

/**
 * Test class for Global.
 * This class contains unit tests for the Global class, ensuring its functionality 
 * works as expected, particularly focusing on the manipulation and verification of 
 * global modes and the Petri Net instance.
 */
public class GlobalTest {

    private Global global;

    /**
     * Sets up the test environment.
     * This method initializes a new instance of Global before each test execution.
     */
    @Before
    public void setUp() {
        global = new Global();
    }

    /**
     * Tests the default mode.
     * Verifies that the initial state of the mode in Global class is set to SELECTMODE.
     */
    @Test
    public void testDefaultMode() {
        assertEquals(Global.SELECTMODE, Global.mode);
    }

    /**
     * Tests Petri Net instance for null.
     * Ensures that the Petri Net instance in the Global class is not null by default.
     */
    @Test
    public void testPetriNetNotNull() {
        assertNotNull(Global.petriNet);
    }

    /**
     * Tests changing mode to Place Mode.
     * Verifies that the mode in Global class can be successfully changed to PLACEMODE.
     */
    @Test
    public void testChangeModeToPlaceMode() {
        Global.mode = Global.PLACEMODE;
        assertEquals(Global.PLACEMODE, Global.mode);
    }

    /**
     * Tests changing mode to Transition Mode.
     * Verifies that the mode in Global class can be successfully changed to TRANSITIONMODE.
     */
    @Test
    public void testChangeModeToTransitionMode() {
        Global.mode = Global.TRANSITIONMODE;
        assertEquals(Global.TRANSITIONMODE, Global.mode);
    }
}
