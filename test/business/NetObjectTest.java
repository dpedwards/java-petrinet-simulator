package test.business;

import org.junit.Test;
import static org.junit.Assert.*;
import business.NetObject;

/**
 * Test class for NetObject.
 */
public class NetObjectTest {

    /**
     * Test class that extends NetObject for testing purposes.
     */
    private class TestNetObject extends NetObject {}

    @Test
    public void testConstructor() {
        TestNetObject obj = new TestNetObject();
        assertEquals("0", obj.getId());
        TestNetObject anotherObj = new TestNetObject();
        assertEquals("1", anotherObj.getId());
    }

    @Test
    public void testGetId() {
        TestNetObject obj = new TestNetObject();
        obj.setId("123");
        assertEquals("123", obj.getId());
    }

    @Test
    public void testSetId() {
        TestNetObject obj = new TestNetObject();
        obj.setId("456");
        assertEquals("456", obj.getId());
    }

    @Test
    public void testGetLabel() {
        TestNetObject obj = new TestNetObject();
        obj.setLabel("testLabel");
        assertEquals("testLabel", obj.getLabel());
        obj.setLabel("");
        assertEquals(obj.getId(), obj.getLabel());
    }

    @Test
    public void testSetLabel() {
        TestNetObject obj = new TestNetObject();
        obj.setLabel("newLabel");
        assertEquals("newLabel", obj.getLabel());
    }
}