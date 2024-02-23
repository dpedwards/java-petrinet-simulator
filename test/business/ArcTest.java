package business;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Arc.
 * This class contains unit tests for the Arc class, ensuring that the functionalities 
 * related to setting and getting the associated Place of an Arc work as expected.
 */
public class ArcTest {

    /**
     * Tests the getPlace method of Arc.
     * This test creates a Place instance and an anonymous subclass of Arc (since Arc is abstract).
     * It verifies if the getPlace method correctly returns the Place instance set in the Arc.
     */
    @Test
    public void testGetPlace() {
        Place place = new Place();
        Arc arc = new Arc() {}; // Create an anonymous subclass because Arc is abstract
        arc.setPlace(place);
        assertEquals(place, arc.getPlace());
    }

    /**
     * Tests the setPlace method of Arc.
     * This test creates two Place instances and an anonymous subclass of Arc.
     * It sets a Place in the Arc and then changes it to another Place.
     * The test verifies if the Arc's place is updated correctly to the second Place instance.
     */
    @Test
    public void testSetPlace() {
        Place place1 = new Place();
        Place place2 = new Place();
        Arc arc = new Arc() {}; // Create an anonymous subclass because Arc is abstract
        arc.setPlace(place1);
        arc.setPlace(place2);
        assertEquals(place2, arc.getPlace());
    }
}
