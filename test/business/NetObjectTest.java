package business;

import org.junit.Test;
import static org.junit.Assert.*;
import business.NetObject;

/**
 * Test class for NetObject.
 * This class conducts unit tests for the NetObject class, ensuring the correct
 * functionality of its constructors, getters, and setters.
 */
public class NetObjectTest {

    /**
     * A test class that extends NetObject for testing purposes.
     * It inherits the properties and methods of NetObject, allowing for testing
     * the functionality of NetObject in an isolated environment.
     */
    private class TestNetObject extends NetObject {}

    /**
     * Tests the constructor of TestNetObject (and by extension, NetObject).
     * Verifies that newly created objects have correct, sequentially assigned IDs.
     */
    @Test
    public void testConstructor() {
        TestNetObject obj = new TestNetObject();
        assertEquals("0", obj.getId());
        TestNetObject anotherObj = new TestNetObject();
        assertEquals("1", anotherObj.getId());
    }

    /**
     * Tests the getId method of NetObject.
     * Ensures that the method returns the correct ID after being set.
     */
    @Test
    public void testGetId() {
        TestNetObject obj = new TestNetObject();
        obj.setId("123");
        assertEquals("123", obj.getId());
    }

    /**
     * Tests the setId method of NetObject.
     * Verifies that the ID of the object is correctly updated after being set.
     */
    @Test
    public void testSetId() {
        TestNetObject obj = new TestNetObject();
        obj.setId("456");
        assertEquals("456", obj.getId());
    }

    /**
     * Tests the getLabel method of NetObject.
     * Verifies that the method returns the correct label, and defaults to the ID
     * if no label is set or an empty label is provided.
     */
    @Test
    public void testGetLabel() {
        TestNetObject obj = new TestNetObject();
        obj.setLabel("testLabel");
        assertEquals("testLabel", obj.getLabel());
        obj.setLabel("");
        assertEquals(obj.getId(), obj.getLabel());
    }

    /**
     * Tests the setLabel method of NetObject.
     * Confirms that the label of the object is correctly updated after being set.
     */
    @Test
    public void testSetLabel() {
        TestNetObject obj = new TestNetObject();
        obj.setLabel("newLabel");
        assertEquals("newLabel", obj.getLabel());
    }
}
