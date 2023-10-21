/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.ArrayList;
import java.util.Iterator;
import presentation.GUI;

public class TimedSimulation extends Simulation {

    // Current simulation time
    private long time = 0;

    public TimedSimulation(boolean step, GUI gui) {
        super(step, gui);
        // Set the initial time on the GUI
        gui.getTxtClock().setText(String.valueOf(time));
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public boolean isFinished() {
        // Get the list of enabled transitions
        ArrayList<Transition> enabledTransitions = enabledTransitionList();
        // Check if there are any enabled transitions
        boolean isDead = enabledTransitions.isEmpty();

        if (isDead) {
            // If no enabled transitions, increment time and check again
            incrementTime();
            enabledTransitions = enabledTransitionList();
            if (!enabledTransitions.isEmpty()) {
                isDead = false;
            }
        }

        return isDead;
    }

    @Override
    public ArrayList<Transition> enabledTransitionList() {
        Iterator<Transition> it = Global.petriNet.getTransitions().iterator();
        ArrayList<Transition> enabledTransitions = new ArrayList<>();
        while (it.hasNext()) {
            Transition transition = it.next();
            // Check if the transition is enabled at the current time
            if (transition.enabled(time)) {
                enabledTransitions.add(transition);
            }
        }
        return enabledTransitions;
    }

    @Override
    protected void fireTransition() {
        enabledTransitionList();
        if (!enabledTransitionList().isEmpty()) {
            // Fire a random enabled transition
            getRandomTransition().fire(this.gui, this.time);
            pauseResumeSimulation();
        }
    }

    public void incrementTime() {
        // Visit all places' tokens and check whether they have timestamp>0 and less than the global clock
        // Assign the global clock to the minimum found
        long minTime = Long.MAX_VALUE;
        ArrayList<Place> places = Global.petriNet.getPlaces();
        for (Place place : places) {
            TokenSet tokenList = place.getTokens();
            if (tokenList.size() > 0) {
                for (Token token : Token(tokenList)) { // Iterate directly over tokenList
                    if (token.getTimestamp() != 0 && token.getTimestamp() < minTime) {
                        minTime = token.getTimestamp();
                    }
                }
            }
        }
        if (minTime != Long.MAX_VALUE) {
            this.time = minTime;
        }
        this.gui.getTxtClock().setText(String.valueOf(this.time)); // Update the time on the GUI
    }
    
    /* TODO: Fix 
     * Type mismatch: cannot convert from element type Object to TokenJava(16777796)
     * TokenSet tokenList - business.TimedSimulation.incrementTime()
     */
    private Token[] Token(TokenSet tokenList) {
        return null;
    }

    /**
     * @return the current time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }
}

