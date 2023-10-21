/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.Iterator;
import presentation.GUI;

/**
 * Represents a transition in the Petri Net.
 */
public class Transition extends NetObject implements Inscription {

    private String guardText = "return true;";
    /** Global clock when the transition fires. */
    private long globalClock;

    public Transition() {
        this.id = "t" + this.id;
    }

    public Transition(String id) {
        this.id = id;
    }

    public Transition(String id, String guardText) {
        this.id = id;
        this.guardText = guardText;
    }

    /** Fires a transition. */
    public void fire(GUI gui, long globalClock) {

        Iterator<InputArc> inputIteratorArc = Global.petriNet.getInputArcs().iterator();
        Iterator<OutputArc> outputIteratorArc = Global.petriNet.getOutputArcs().iterator();
        this.globalClock = globalClock;

        // Highlight places ON
        gui.getCanvas().highlightPlaces(Global.petriNet.getInputArcs(), id, true, false);

        // Highlight inputArcs ON
        gui.getCanvas().highlightArcs(Global.petriNet.getInputArcs(), id, true, true);

        // Remove all tokens from places
        while (inputIteratorArc.hasNext()) {
            InputArc arc = (InputArc) inputIteratorArc.next();
            if (arc.getTransition().getId().equals(getId())) {
                arc.getPlace().removeTokens(arc.execute());
                gui.getJTextArea1().append("- " + arc.getExecuteText() + "\n");
                gui.getJTextArea1().setCaretPosition(gui.getJTextArea1().getText().length());
            }
        }

        // Highlight places OFF
        gui.getCanvas().highlightPlaces(Global.petriNet.getInputArcs(), id, false, false);

        // Highlight inputArcs OFF
        gui.getCanvas().highlightArcs(Global.petriNet.getInputArcs(), id, false, false);

        // Highlight transition ON
        gui.getCanvas().highlightTransition(id, true, true);
        if (!this.getLabel().equals(this.getId())) {
            gui.getJTextArea1().append(this.getLabel() + " (" + this.getId() + ") fired!\n");
        } else {
            gui.getJTextArea1().append(this.getId() + " fired.\n");
        }
        gui.getJTextArea1().setCaretPosition(gui.getJTextArea1().getText().length());

        // Highlight transition OFF
        gui.getCanvas().highlightTransition(id, false, false);

        // Highlight outputArcs ON
        gui.getCanvas().highlightArcs(Global.petriNet.getOutputArcs(), id, true, false);

        // Highlight places ON
        gui.getCanvas().highlightPlaces(Global.petriNet.getOutputArcs(), id, true, true);

        // Create all tokens to output places
        while (outputIteratorArc.hasNext()) {
            OutputArc arc = (OutputArc) outputIteratorArc.next();
            if (arc.getTransition().getId().equals(getId())) {
                TokenSet tokenSet = arc.execute();
                tokenSet.incrementTime(globalClock); // Set time of all new tokens in the tokenSet
                arc.getPlace().addToken(tokenSet);

                gui.getJTextArea1().append("+ " + arc.getExecuteText() + "\n");
                gui.getJTextArea1().setCaretPosition(gui.getJTextArea1().getText().length());
            }
        }

        // Highlight outputArcs OFF
        gui.getCanvas().highlightArcs(Global.petriNet.getOutputArcs(), id, false, false);

        // Highlight places OFF
        gui.getCanvas().highlightPlaces(Global.petriNet.getOutputArcs(), id, false, false);

        gui.getJTextArea1().append("----------------------------\n");
        gui.getCanvas().repaint();
    }

    /**
     * Checks if the transition is enabled at a given time.
     *
     * @param time the time to check against
     * @return true if enabled, false otherwise
     */
    public boolean enabled(long time) {

        Iterator<InputArc> inputIteratorArc = Global.petriNet.getInputArcs().iterator();
        Iterator<OutputArc> outputIteratorArc = Global.petriNet.getOutputArcs().iterator();

        // transition guard evaluation
        boolean enabled = evaluate();

        // input arc guards
        if (enabled && !Global.petriNet.getInputArcs().isEmpty()) {
            while (enabled && inputIteratorArc.hasNext()) {
                InputArc arc = (InputArc) inputIteratorArc.next();
                if (arc.getTransition().getId().equals(getId())) {
                    TokenSet tokensList = arc.getPlace().getTokens();
                    enabled = tokensList.containsTime(time);
                    // check arc's evaluation expression
                    enabled = enabled & arc.evaluate();
                }
            }
        }

        // check output arc place capacity restriction
        if (enabled && !Global.petriNet.getOutputArcs().isEmpty()) {
            while (enabled && outputIteratorArc.hasNext()) {
                OutputArc arc = (OutputArc) outputIteratorArc.next();
                if (arc.getTransition().getId().equals(getId())) {
                    TokenSet tokensList = arc.getPlace().getTokens();
                    // check if places have capacity limit
                    if (arc.getPlace().getCapacity() != 0) {
                        enabled = enabled & arc.getPlace().getCapacity() > tokensList.size();
                    }
                }
            }
        }

        return enabled;
    }

    /**
     * Evaluates the transition's guard condition.
     *
     * @return true if the guard condition is met, false otherwise
     */
    public boolean evaluate() {
        return true; // Modify this to implement your guard condition logic
    }

    /**
     * Executes the transition.
     *
     * @return the resulting token set
     */
    public TokenSet execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Gets the guard text for the transition.
     *
     * @return the guard text
     */
    public String getGuardText() {
        return guardText;
    }

    /**
     * Sets the guard text for the transition.
     *
     * @param guardText the guard text to set
     */
    public void setGuardText(String guardText) {
        this.guardText = guardText;
    }

    /**
     * Gets the global clock when the transition fires.
     *
     * @return the globalClock
     */
    public long getGlobalClock() {
        return globalClock;
    }


}