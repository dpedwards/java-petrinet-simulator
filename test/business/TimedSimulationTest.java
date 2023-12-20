package test.business;

import business.TimedSimulation;
import business.Transition;
import java.lang.reflect.Method;
import javax.swing.JTextField;
import presentation.GUI;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Test class for TimedSimulation.
 * This class tests the functionality of TimedSimulation, particularly focusing on time management
 * and the firing of transitions. It uses Mockito to mock external dependencies and Java Reflection
 * to access and test non-public methods.
 */
public class TimedSimulationTest {

    private TimedSimulation simulation;
    private GUI mockGui;
    private JTextField mockTxtClock;

    /**
     * Sets up the test environment before each test.
     * This involves creating mock instances of GUI and JTextField and initializing the TimedSimulation instance.
     */
    @Before
    public void setUp() {
        mockGui = mock(GUI.class);
        mockTxtClock = mock(JTextField.class);

        // Configure the mock GUI to return the mock JTextField when getTxtClock() is called
        when(mockGui.getTxtClock()).thenReturn(mockTxtClock);

        // Initialize TimedSimulation with the mock GUI
        simulation = new TimedSimulation(false, mockGui);

        // Additional setup for Global.petriNet if needed
    }

    /**
     * Tests the behavior of TimedSimulation when there are no enabled transitions.
     * Assumes that the petri net is set up without any enabled transitions.
     */
    @Test
    public void testIsFinishedWithNoEnabledTransitions() {
        assertTrue("Simulation should be finished when no transitions are enabled", simulation.isFinished());
    }

    /**
     * Tests the behavior of TimedSimulation when there are enabled transitions.
     * Assumes the petri net is set up with some enabled transitions.
     */
    @Test
    public void testIsFinishedWithEnabledTransitions() {
        assertFalse("Simulation should not be finished when there are enabled transitions", simulation.isFinished());
    }

    /**
     * Tests the incrementTime method of TimedSimulation.
     * Assumes a specific setup of the petri net with tokens having various timestamps.
     */
    @Test
    public void testIncrementTime() {
        long expectedTime = 100; // The expected simulation time after increment
        simulation.incrementTime();
        assertEquals("Simulation time should be incremented correctly", expectedTime, simulation.getTime());
    }

    /**
     * Tests the fireTransition method of TimedSimulation using Reflection.
     * This test checks whether an enabled transition is correctly fired.
     * @throws Exception if there is an error accessing or invoking the fireTransition method.
     */
    @Test
    public void testFireTransition() throws Exception {
        Transition mockTransition = mock(Transition.class);
        when(mockTransition.enabled(anyLong())).thenReturn(true);

        // Use reflection to test the fireTransition method
        Method method = TimedSimulation.class.getDeclaredMethod("fireTransition");
        method.setAccessible(true);
        method.invoke(simulation);

        verify(mockTransition, times(1)).fire(eq(mockGui), anyLong());
    }
}
