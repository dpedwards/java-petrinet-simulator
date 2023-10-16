/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package business;

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
public class ArcTest {
    
    public ArcTest() {
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
     * Test of getPlace method, of class Arc.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        Arc instance = new ArcImpl();
        Place expResult = null;
        Place result = instance.getPlace();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlace method, of class Arc.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        Place place = null;
        Arc instance = new ArcImpl();
        instance.setPlace(place);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransition method, of class Arc.
     */
    @Test
    public void testGetTransition() {
        System.out.println("getTransition");
        Arc instance = new ArcImpl();
        Transition expResult = null;
        Transition result = instance.getTransition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTransition method, of class Arc.
     */
    @Test
    public void testSetTransition() {
        System.out.println("setTransition");
        Transition transition = null;
        Arc instance = new ArcImpl();
        instance.setTransition(transition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ArcImpl extends Arc {
    }
    
}
