/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * This class represents a point on a path. It extends AbstractFigure and provides methods
 * for drawing and interacting with path points.
 * The `PathPoint` class is an example of the Composite design pattern. 
 * This pattern is used when you need to treat a group of objects in the same way as a single instance of an object. 
 * The Composite pattern composes objects into tree structures to represent part-whole hierarchies.
 * In this case, `PathPoint` extends `AbstractFigure`, suggesting that it can be treated like any other figure. 
 * It provides methods for interacting with path points, such as `contains`, which is typical of the Composite pattern.
 */
public class PathPoint extends AbstractFigure {

    /** Stroke color of the abstract figure  */
    protected Color strokeColor = new Color(204, 204, 204);
    public static int POINTSIZE = 6;
    private Rectangle2D rectangle;
    private String id;

    /**
     * Constructor to create a PathPoint with the specified position and ID.
     *
     * @param point The position (coordinates) of the PathPoint.
     * @param id    The unique identifier of the PathPoint.
     */
    public PathPoint(Point2D point, String id) {
        point = new Point2D.Double((int) point.getX(), (int) point.getY());
        this.position = point;
        this.id = id;
    }

    /**
     * Check if a given point is contained within this PathPoint.
     *
     * @param position The point to check for containment.
     * @return True if the given point is inside this PathPoint, false otherwise.
     */
    @Override
    public boolean contains(Point2D position) {
        return getBounds().contains(position);
    }

    /**
     * Check if this PathPoint is equal to another object.
     *
     * @param obj The object to compare to this PathPoint.
     * @return True if the objects are equal (have the same ID and position), false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PathPoint)) {
            return false;
        }
        PathPoint pathPoint = (PathPoint) obj;
        if (this.id.equals(pathPoint.getElementId()) && this.position.equals(pathPoint.getPosition())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Draw this PathPoint on a graphics context.
     *
     * @param g The Graphics2D context to draw on.
     */
    @Override
    public void draw(Graphics2D g) {
        if (selected) {
            rectangle = (Rectangle2D) getBounds();
            drawFill(g);
            drawStroke(g);
        }
    }

    /**
     * Draw the filled shape of this PathPoint.
     *
     * @param g The Graphics2D context to draw on.
     */
    @Override
    public void drawFill(Graphics2D g) {
        g.setPaint(fillColor);
        g.fill(rectangle);
        g.setPaint(new Color(0, 0, 0));
        g.fill(new Rectangle2D.Double(position.getX() - POINTSIZE / 2 + 2, position.getY() - POINTSIZE / 2 + 2, POINTSIZE - 3, POINTSIZE - 3));
    }

    /**
     * Draw the stroke (outline) of this PathPoint.
     *
     * @param g The Graphics2D context to draw on.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        g.setStroke(new java.awt.BasicStroke(1f));
        g.setPaint(strokeColor);
        g.draw(rectangle);
    }

    /**
     * Set the unique identifier (ID) of this PathPoint.
     *
     * @param id The new ID to set for this PathPoint.
     */
    @Override
    public void setElementId(String id) {
        this.id = id;
    }

    /**
     * Get the bounding shape of this PathPoint.
     *
     * @return A Rectangle2D representing the bounding shape of this PathPoint.
     */
    @Override
    public RectangularShape getBounds() {
        return new Rectangle2D.Double(position.getX() - POINTSIZE / 2, position.getY() - POINTSIZE / 2, POINTSIZE, POINTSIZE);
    }

    /**
     * Get the unique identifier (ID) of this PathPoint.
     *
     * @return The ID of this PathPoint.
     */
    @Override
    public String getElementId() {
        return this.id;
    }
}