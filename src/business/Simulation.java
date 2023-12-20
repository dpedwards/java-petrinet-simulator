/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import presentation.FrmViewSource;
import presentation.GUI;

/**
 * Represents a simulation thread that runs a Petri Net simulation.
 */
public class Simulation extends Thread {

    protected boolean step = false; // Flag for step-by-step execution
    protected boolean paused = false; // Flag for pausing the simulation
    protected boolean stop = false; // Flag for stopping the simulation
    /** Time delay between firing a transition */
    public static int DELAY = 0;
    /** Default time delay between each transition firing process */
    public static int COMPONENTDELAY = 100;
    protected GUI gui; // Reference to the GUI for user interaction

    private ArrayList<Transition> transitions;

    /** Initializes a new Simulation instance.
     *
     * @param step Flag for step-by-step execution.
     * @param gui Reference to the GUI.
     */
    public Simulation(boolean step, GUI gui) {
        this.step = step;
        this.gui = gui;
        NetClass n = new NetClass();

        try {
            n.compile(n.generateNetSource());
        } catch (Exception e) {
            this.stop = true;
            FrmViewSource jform = new FrmViewSource(JOptionPane.getFrameForComponent(gui), true, e.getMessage(), "Compilation errors");
            jform.setVisible(true);
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        while (!isFinished() && !stop) {
            fireTransition();
        }

        if (stop) {
            this.gui.getJTextArea1().append("Stopped.\n");
        } else {
            this.gui.getJTextArea1().append("Deadlock.\n");
        }

        this.gui.getJTextArea1().setCaretPosition(this.gui.getJTextArea1().getText().length());
    }

    /** Checks whether the simulation has finished. */
    public boolean isFinished() {
        return Global.petriNet.isDead();
    }

    /** Fires a single transition from the enabled transitions list. */
    protected void fireTransition() {
        enabledTransitionList();

        if (!this.enabledTransitionList().isEmpty()) {
            getRandomTransition().fire(this.gui, 0);

            pauseResumeSimulation();
        }
    }

    /** Pauses or resumes the simulation based on the step flag and user interaction. */
    public synchronized void pauseResumeSimulation() {
        if (step && !stop) {
            paused = true;

            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException l) {
            }
        }
    }

    /** Returns a random transition from the enabled transitions list. */
    public Transition getRandomTransition() {
        ArrayList<Transition> enabledTransitions = this.enabledTransitionList();

        // Check if the enabledTransitions list is empty or null
        if (enabledTransitions == null || enabledTransitions.isEmpty()) {
            // Return null or throw an exception as appropriate
            return null; // Or throw a new IllegalStateException("No enabled transitions available");
        }

        Random generator = new Random();
        int rand = generator.nextInt(enabledTransitions.size());
        return enabledTransitions.get(rand);
    }

    /** Returns a list of enabled transitions. */
    public ArrayList<Transition> enabledTransitionList() {
        Iterator<Transition> it = Global.petriNet.getTransitions().iterator();
        ArrayList<Transition> enabledTransitions = new ArrayList<>();
        
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            
            if (transition.enabled(0)) {
                enabledTransitions.add(transition);
            }
        }
        
        return enabledTransitions;
    }

    /**
     * @return the step
     */
    public boolean isStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(boolean step) {
        this.step = step;
    }

    /**
     * @return the paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused the paused to set
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return the stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(boolean stop) {
        if (this.paused) {
            synchronized (this) {
                this.notify();
            }
        }
        this.stop = stop;
    }

    /**
     * @param transitions
     */
    public void setTransitions(ArrayList<Transition> transitions) {
    this.transitions = transitions;
    }
}
