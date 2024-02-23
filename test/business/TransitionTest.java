package business;

import org.junit.Before;
import org.junit.Test;
import business.InputArc;
import business.OutputArc;
import business.PetriNet;
import business.Transition;
import presentation.Canvas;
import presentation.GUI;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import javax.swing.JTextArea;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Test class for Transition.
 * This class performs unit tests on the Transition class, focusing on its interaction
 * with the GUI and PetriNet, particularly the fire method.
 * The tests make use of Mockito to simulate interactions with GUI components and the PetriNet.
 */
public class TransitionTest {

    private Transition transition;
    private GUI mockGui;
    private PetriNet mockPetriNet;
    private Canvas mockCanvas;
    private JTextArea mockTextArea;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks for GUI, PetriNet, Canvas, and JTextArea.
     * Configures the behavior of mocked objects to facilitate testing.
     */
    @Before
    public void setUp() {
        mockGui = mock(GUI.class);
        mockPetriNet = mock(PetriNet.class);
        mockCanvas = mock(Canvas.class);
        mockTextArea = mock(JTextArea.class);
    
        when(mockGui.getCanvas()).thenReturn(mockCanvas);
        when(mockGui.getJTextArea1()).thenReturn(mockTextArea);
        when(mockPetriNet.getInputArcs()).thenReturn(new ArrayList<InputArc>());
        when(mockPetriNet.getOutputArcs()).thenReturn(new ArrayList<OutputArc>());
    
        transition = new Transition("t1");
        when(mockTextArea.getText()).thenReturn("Some text");
    }
    
    /**
     * Tests the fire method of Transition.
     * Verifies that the fire method correctly interacts with the Canvas to highlight places and arcs.
     * The behavior of the Canvas is simulated using Mockito.
     */
    @Test
    public void testFire() {
        transition.fire(mockGui, 1000L);

        verify(mockCanvas, times(1)).highlightPlaces((ArrayList<?>) anyList(), eq("t1"), eq(true), eq(false));
        verify(mockCanvas, times(1)).highlightArcs((ArrayList<?>) anyList(), eq("t1"), eq(true), eq(true));
    }
}
