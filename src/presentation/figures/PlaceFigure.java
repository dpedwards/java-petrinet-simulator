/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import presentation.Grid;

/**
 * Represents a graphical figure for a place in the Petri net.
 * The `PlaceFigure` class is an example of the Decorator design pattern. 
 * This pattern is characterized by a class that 'decorates' or adds behavior to 
 * instances of another class without affecting other instances of the same class.
 * In this case, `PlaceFigure` extends `AbstractFigure`, suggesting that it adds 
 * behavior to `AbstractFigure` instances. It adds a `placeId`, an `ellipse`, 
 * and a `tokenFigure` to the figure, and provides methods for interacting with these new properties. 
 * This is typical of the Decorator pattern.
 */
public class PlaceFigure extends AbstractFigure {

    private String placeId;
    private Ellipse2D ellipse;
    final public static int DIAMETER = Grid.cellSize;
    protected TokenSetFigure tokenFigure;

    /**
     * Constructs a PlaceFigure object.
     *
     * @param placeId  The unique identifier for the place.
     * @param position The position of the place on the canvas.
     */
    public PlaceFigure(String placeId, Point2D position) {
        this.placeId = placeId;
        this.position = position;
        this.label = new TextFigure(this);
        this.tokenFigure = new TokenSetFigure(this);
        this.ellipse = generateEllipse();
    }

    /**
     * Checks if a point is contained within the place's ellipse.
     *
     * @param position The point to check.
     * @return True if the point is inside the ellipse, false otherwise.
     */
    @Override
    public boolean contains(Point2D position) {
        return this.ellipse.contains(position);
    }

    /**
     * Gets the bounding shape of the place.
     *
     * @return The bounding ellipse shape of the place.
     */
    @Override
    public RectangularShape getBounds() {
        return new Ellipse2D.Double(position.getX() - DIAMETER / 2, position.getY() - DIAMETER / 2, DIAMETER, DIAMETER);
    }

    /**
     * Draws the place figure on the canvas.
     *
     * @param g The graphics context.
     */
    @Override
    public void draw(Graphics2D g) {
        this.ellipse = generateEllipse();
        drawFill(g);
        drawStroke(g);
        tokenFigure.draw(g);
    }

    /**
     * Draws the filled portion of the place.
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
        g.fill(ellipse);
    }

    /**
     * Draws the stroke (outline) of the place.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        g.setStroke(new java.awt.BasicStroke(2f));
        if (highlighted) {
            g.setPaint(highlightedColor);
        } else {
            g.setPaint(strokeColor);
        }
        g.draw(ellipse);
    }

    /**
     * Generates the ellipse shape of the place.
     *
     * @return The ellipse shape of the place.
     */
    public Ellipse2D generateEllipse() {
        return new Ellipse2D.Double(position.getX() - DIAMETER / 2, position.getY() - DIAMETER / 2, DIAMETER, DIAMETER);
    }

    /**
     * Sets the position of the place figure.
     *
     * @param newPosition The new position for the place.
     */
    @Override
    public void setPosition(Point2D newPosition) {
        position = newPosition;
        label.setRelativePosition(newPosition);
        tokenFigure.setRelativePosition(newPosition);
    }

    /**
     * Gets the unique identifier of the place.
     *
     * @return The place's identifier.
     */
    @Override
    public String getElementId() {
        return this.placeId;
    }

    /**
     * Sets the unique identifier of the place (not supported yet).
     *
     * @param id The new identifier for the place.
     * @throws UnsupportedOperationException when called.
     */
    @Override
    public void setElementId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}