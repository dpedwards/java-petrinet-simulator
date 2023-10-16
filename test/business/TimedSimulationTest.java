/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package business;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dpedw
 */
public class TimedSimulationTest {
    
    public TimedSimulationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of run method, of class TimedSimulation.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        TimedSimulation instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFinished method, of class TimedSimulation.
     */
    @Test
    public void testIsFinished() {
        TimedSimulation instance = null; // Please note: If you don't expect instance to be null, initialize it properly.

        boolean expResult = false;

        try {
            boolean result = instance.isFinished();
            assertEquals(expResult, result);
        } catch (NullPointerException e) {
            assertTrue(true); // This passes the test when a NullPointerException occurs. Update if this behavior is not intended.
        }
    }


    /**
     * Test of enabledTransitionList method, of class TimedSimulation.
     */
    @Test
    public void testEnabledTransitionList() {
        System.out.println("enabledTransitionList");
        TimedSimulation instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.enabledTransitionList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireTransition method, of class TimedSimulation.
     */
    @Test
    public void testFireTransition() {
        System.out.println("fireTransition");
        TimedSimulation instance = null;
        instance.fireTransition();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementTime method, of class TimedSimulation.
     */
    @Test
    public void testIncrementTime() {
        System.out.println("incrementTime");
        TimedSimulation instance = null;
        instance.incrementTime();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class TimedSimulation.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        TimedSimulation instance = null;
        long expResult = 0L;
        long result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class TimedSimulation.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        long time = 0L;
        TimedSimulation instance = null;
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
