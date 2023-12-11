/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents a edge aka the normal arc figure that extends AbstractArcFigure and defines its behavior.
 */
public class NormalArcFigure extends AbstractArcFigure {

    /**
     * Determines whether a point is contained within the arc figure.
     *
     * @param position The point to check.
     * @return True if the point is contained within the figure, false otherwise.
     */
    @Override
    public boolean contains(Point2D position) {
        double flatness = 0.01;
        boolean intersect = false;
        PathIterator pit = path.getPathIterator(null, flatness);
        double[] coords = new double[6];
        double lastX = 0, lastY = 0;

        // Iterate through the path segments
        while (!intersect && !pit.isDone()) {
            int type = pit.currentSegment(coords);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    lastX = coords[0];
                    lastY = coords[1];
                    break;
                case PathIterator.SEG_LINETO:
                    BasicStroke stroke = new BasicStroke(10.0f);
                    Line2D.Double line = new Line2D.Double(lastX, lastY, coords[0], coords[1]);
                    Shape shape = stroke.createStrokedShape(line);

                    // Check if the stroked line shape contains the given point
                    if (shape.contains(position)) {
                        intersect = true;
                    }

                    lastX = coords[0];
                    lastY = coords[1];
            }
            pit.next();
        }
        return intersect;
    }

    /**
     * Get the bounding rectangle of the arc figure.
     *
     * @return The bounding rectangle (null in this case).
     */
    @Override
    public Rectangle2D getBounds() {
        return null;
    }

    /**
     * Draw the arc figure on a graphics context.
     *
     * @param g The graphics context to draw on.
     */
    @Override
    public void draw(Graphics2D g) {
        generatePath();
        drawStroke(g);
    }

    /**
     * Draw the filled portion of the arc figure (unsupported in this case).
     *
     * @param g The graphics context to draw on.
     */
    @Override
    public void drawFill(Graphics2D g) {
        throw new UnsupportedOperationException("Filling not supported for NormalArcFigure.");
    }

    /**
     * Draw the stroke of the arc figure, which defines its outline.
     *
     * @param g The graphics context to draw on.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        g.setStroke(new BasicStroke(1f));

        // Set stroke and paint based on highlight, selected, or default state
        if (highlighted) {
            g.setStroke(new BasicStroke(2f));
            g.setPaint(highlightedColor);
        } else if (selected) {
            g.setPaint(selectedColor);
        } else {
            g.setPaint(strokeColor);
        }

        g.draw(path);
    }

    /**
     * Get the unique element ID of the arc figure.
     *
     * @return The element ID.
     */
    @Override
    public String getElementId() {
        return this.arcId;
    }

    /**
     * Set the element ID of the arc figure.
     *
     * @param id The new element ID to set.
     */
    @Override
    public void setElementId(String id) {
        this.arcId = id;
    }
}