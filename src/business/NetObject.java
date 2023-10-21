/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

/**
 * Abstract class representing a net object.
 */
public abstract class NetObject {

    /** Latest id assigned to a net object*/
    public static long LATEST_ID = 0;
    
    /** Id of this net object*/
    protected String id;
    
    /** Label of this net object*/
    protected String label = "";

    /**
     * Constructor that assigns the LATEST_ID to this object and increments it by 1.
     */
    public NetObject() {
        this.id = "" + LATEST_ID;
        LATEST_ID++;
    }

    /**
     * Get the id of the net object.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of the net object.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the label of the net object.
     * If the label is empty, it defaults to the id.
     *
     * @return the label
     */
    public String getLabel() {
        if (label.equals("")) {
            label = id;
        }
        return label;
    }

    /**
     * Set the label of the net object.
     *
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}

