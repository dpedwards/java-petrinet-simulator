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
                for (Token token : tokensWithTimestamp(tokenList)) { // Iterate directly over tokenList
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
    
    /**
     * Filters the tokens in the given TokenSet and returns an array of tokens
     * that have a timestamp greater than zero.
     *
     * @param tokenList The TokenSet to filter tokens from.
     * @return An array of tokens with timestamps greater than zero.
     */
    private Token[] tokensWithTimestamp(TokenSet tokenList) {
        // Create an ArrayList to store the filtered tokens.
        ArrayList<Token> tokens = new ArrayList<>();

        // Iterate through the tokens in the TokenSet.
        for (Token token : tokenList) {
            // Check if the token has a timestamp greater than zero.
            if (token.getTimestamp() > 0) {
                // If the condition is met, add the token to the ArrayList.
                tokens.add(token);
            }
        }

        // Convert the ArrayList of tokens to an array and return it.
        return tokens.toArray(new Token[tokens.size()]);
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

