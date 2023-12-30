/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import presentation.Grid;

/**
 * Represents a graphical figure for a transition.
 * The `TransitionFigure` class is an example of the Decorator design pattern. 
 * This pattern is characterized by a class that 'decorates' or adds behavior to 
 * instances of another class without affecting other instances of the same class.
 * In this case, `TransitionFigure` extends `AbstractFigure`, 
 * suggesting that it adds behavior to `AbstractFigure` instances. 
 * It adds a `transitionId` and a `rectangle`, and provides methods for interacting with these new properties. 
 * This is typical of the Decorator pattern.
 */
public class TransitionFigure extends AbstractFigure {

    // Constants for width and height of the transition figure
    final public static int WIDTH = Grid.cellSize;
    final public static int HEIGHT = 10;

    // The rectangle representing the transition's shape
    private Rectangle2D rectangle;
    
    // Identifier for the transition
    private String transitionId;

    /**
     * Constructs a TransitionFigure with a given transition identifier and position.
     *
     * @param transitionId The identifier of the transition.
     * @param position     The position of the transition figure.
     */
    public TransitionFigure(String transitionId, Point2D position) {
        this.transitionId = transitionId;
        this.position = position;
        this.label = new TextFigure(this);
        // Initialize the rectangle shape based on the bounds
        this.rectangle = (Rectangle2D) getBounds();
    }

    /**
     * Checks if a point is contained within the transition figure.
     *
     * @param position The point to check.
     * @return True if the point is contained, false otherwise.
     */
    @Override
    public boolean contains(Point2D position) {
        return rectangle.contains(position);
    }

    /**
     * Gets the bounding shape of the transition figure.
     *
     * @return The rectangular shape representing the bounds.
     */
    @Override
    public RectangularShape getBounds() {
        return new Rectangle2D.Double(position.getX() - WIDTH / 2, position.getY() - HEIGHT / 2, WIDTH, HEIGHT);
    }

    /**
     * Draws the transition figure on the graphics context.
     *
     * @param g The graphics context.
     */
    @Override
    public void draw(Graphics2D g) {
        // Update the rectangle bounds and draw the figure
        rectangle = (Rectangle2D) getBounds();
        drawFill(g);
        drawStroke(g);
    }

    /**
     * Draws the filled portion of the transition figure.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawFill(Graphics2D g) {
        if (selected) {
            g.setPaint(selectedColor);
        } else {
            g.setPaint(fillColor);
        }
        g.fill(rectangle);
    }

    /**
     * Draws the stroke (outline) of the transition figure.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        if (highlighted) {
            g.setPaint(highlightedColor);
        } else {
            g.setPaint(strokeColor);
        }
        g.setStroke(new java.awt.BasicStroke(2f));
        g.draw(rectangle);
    }

    /**
     * Sets the position of the transition figure.
     *
     * @param newPosition The new position.
     */
    @Override
    public void setPosition(Point2D newPosition) {
        position = newPosition;
        label.setRelativePosition(newPosition);
    }

    /**
     * Gets the identifier of the transition.
     *
     * @return The transition identifier.
     */
    @Override
    public String getElementId() {
        return transitionId;
    }

    /**
     * Gets the rectangle representing the transition's shape.
     *
     * @return The rectangle shape.
     */
    public Rectangle2D getRectangle() {
        return rectangle;
    }

    /**
     * Sets the identifier of the transition (not supported).
     *
     * @param id The new identifier.
     */
    @Override
    public void setElementId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}