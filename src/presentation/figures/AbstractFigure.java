/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * An abstract class representing a basic figure with position, label, and appearance properties.
 * The `AbstractFigure` class is an example of the Template Method design pattern. 
 * This pattern is characterized by an abstract class that defines a 'template' of operations, 
 * some of which are implemented by the abstract class itself and some of which are left abstract, to be implemented by subclasses.
 * In this case, `AbstractFigure` provides a template for a figure with position, label, 
 * and appearance properties. It provides fields for these properties and abstract methods `contains` and `draw` that must be implemented by subclasses. 
 * This is typical of the Template Method pattern.
 */
public abstract class AbstractFigure {

    /** Coordinates of the abstract figure */
    protected Point2D position;
    /** Coordinates of the offset */
    private Point2D offset;
    /** Text figure assigned to this Figure */
    protected TextFigure label;
    /** Background color of the abstract figure */
    protected Color fillColor = new Color(255, 255, 255);
    /** Stroke color of the abstract figure */
    protected Color strokeColor = new Color(0, 0, 0);
    /** Color of the figure when selected */
    protected Color selectedColor = new Color(153, 153, 255);
    /** Color of the figure when highlighted */
    protected Color highlightedColor = new Color(115, 230, 0);
    /** Indicates whether the figure is selected (true) or not (false) */
    protected boolean selected = false;
    /** Indicates whether the figure is highlighted (true) or not (false) */
    protected boolean highlighted = false;

    /**
     * Checks if a given point is contained by this figure.
     *
     * @param position The point to check for containment.
     * @return true if the point is contained within the figure, false otherwise.
     */
    public abstract boolean contains(Point2D position);

    /**
     * Draws the full figure.
     *
     * @param g The graphics context to use for drawing.
     */
    public abstract void draw(java.awt.Graphics2D g);

    /**
     * Paints the interior of the figure.
     *
     * @param g The graphics context to use for filling.
     */
    public abstract void drawFill(java.awt.Graphics2D g);

    /**
     * Paints the stroke of the figure.
     *
     * @param g The graphics context to use for the stroke.
     */
    public abstract void drawStroke(java.awt.Graphics2D g);

    /**
     * Returns the unique element ID of this figure.
     *
     * @return The element ID as a String.
     */
    public abstract String getElementId();

    /**
     * Sets the element ID of this figure.
     *
     * @param id The element ID to set.
     */
    public abstract void setElementId(String id);

    /**
     * Returns the bounds of the shape that represents this figure.
     *
     * @return The bounding shape as a RectangularShape.
     */
    public abstract RectangularShape getBounds();

    /**
     * Returns the label associated with this figure.
     *
     * @return The label as a TextFigure.
     */
    public TextFigure getLabel() {
        return label;
    }

    /**
     * Sets the label associated with this figure.
     *
     * @param label The label to set.
     */
    public void setLabel(TextFigure label) {
        this.label = label;
    }

    /**
     * Returns the position (coordinates) of this figure.
     *
     * @return The position as a Point2D.
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Sets the position (coordinates) of this figure.
     *
     * @param position The position to set.
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Checks if the figure is currently selected.
     *
     * @return true if the figure is selected, false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the selection status of the figure.
     *
     * @param selected true to select the figure, false to deselect.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Checks if the figure is currently highlighted.
     *
     * @return true if the figure is highlighted, false otherwise.
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     * Sets the highlighting status of the figure.
     *
     * @param highlighted true to highlight the figure, false to unhighlight.
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    /**
     * Returns the offset (coordinates) of this figure.
     *
     * @return The offset as a Point2D.
     */
    public Point2D getOffset() {
        return offset;
    }

    /**
     * Sets the offset (coordinates) of this figure.
     *
     * @param offset The offset to set.
     */
    public void setOffset(Point2D offset) {
        this.offset = offset;
    }
}
