/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import presentation.Grid;

/**
 * An abstract class representing a figure for connections between two figures.
 * Extends {@link AbstractFigure} and implements {@link ConnectionFigure}.
 */
public abstract class AbstractArcFigure extends AbstractFigure implements ConnectionFigure {

    /** Color of the figure when highlighted. */
    protected Color highlightedColor = new Color(0, 0, 128);

    /** The starting figure of the connection. */
    private AbstractFigure start;

    /** The ending figure of the connection. */
    private AbstractFigure end;

    /** Identifier for the arc figure. */
    protected String arcId;

    /** The path representing the arc. */
    protected GeneralPath path = new GeneralPath();

    /** List of path points that define the shape of the arc. */
    protected LinkedList<PathPoint> pathPoints = new LinkedList<>();

    /** The size of the arrowhead barb. */
    final int BARB = 10;

    /** The angle (in radians) of the arrowhead barb. */
    final double PHI = Math.PI / 8;

    /** Mapping of selected points to their indices within pathPoints. */
    protected HashMap<Point2D, Integer> selectedPoints = new HashMap<>();

    /**
     * Adds a path point to the arc figure.
     *
     * @param point The point to be added as a path point.
     */
    public void addPoint(Point2D point) {
        PathPoint p = new PathPoint(point, "_pathpoint_" + this.pathPoints.size());
        pathPoints.add(p);
    }

    /**
     * Removes a path point from the arc figure.
     *
     * @param pathPoint The path point to be removed.
     */
    public void removePoint(PathPoint pathPoint) {
        pathPoints.remove(pathPoint);
    }

    /**
     * Sets the starting figure of the connection.
     *
     * @param start The starting figure.
     */
    public void setConnectionStart(AbstractFigure start) {
        this.start = start;
    }

    /**
     * Gets the starting figure of the connection.
     *
     * @return The starting figure.
     */
    public AbstractFigure getStartConnector() {
        return start;
    }

    /**
     * Sets the ending figure of the connection.
     *
     * @param end The ending figure.
     */
    public void setConnectionEnd(AbstractFigure end) {
        this.end = end;
    }

    /**
     * Gets the ending figure of the connection.
     *
     * @return The ending figure.
     */
    public AbstractFigure getEndConnector() {
        return end;
    }

    /**
     * Sets the label of the arc figure.
     * The label is represented by a {@link TextFigure}.
     */
    public void setLabel() {
        this.label = new TextFigure(this);
    }

    /**
     * Sets a point as a selected point.
     * Points are identified by their positions and indices in pathPoints.
     *
     * @param position The position of the selected point.
     */
    public void setSelectedPoint(Point2D position) {
        int index = -1; // Initialize with a sentinel value
        for (int i = 0; i < pathPoints.size(); i++) {
            PathPoint pathPoint = pathPoints.get(i);
            if (pathPoint.getPosition().equals(position)) {
                index = i;
                break; // Stop searching when found
            }
        }
    
        if (index != -1) {
            this.selectedPoints.put(position, index);
        }
    }

    /**
     * Removes all selected points from the arc figure.
     */
    public void removeSelectedPoints() {
        this.selectedPoints.clear();
    }

    /**
     * Checks if a given position is contained within a path point of the arc figure.
     *
     * @param position The position to check.
     * @return The path point containing the position, or null if not found.
     */
    public Point2D containsPoint(Point2D position) {
        boolean intersect = false;
        Point2D point = null;
        Iterator<PathPoint> it = this.pathPoints.iterator();
        while (!intersect && it.hasNext()) {
            PathPoint pathPoint = it.next();
            point = pathPoint.getPosition();
            RectangularShape rect = pathPoint.getBounds();
            if (rect instanceof Rectangle2D) {
                Rectangle2D rectangle2D = (Rectangle2D) rect;
                if (rectangle2D.contains(position)) {
                    intersect = true;
                }
            }
        }
        if (!intersect) {
            point = null;
        }
        return point;
    }

    /**
     * Calculates and returns the intersection point of a line with a given angle and a rectangle.
     * 
     * @param theta The angle (in radians) of the line.
     * @param r The rectangle with which intersection is to be calculated.
     * @return The intersection point as a {@link Point2D.Double}.
     */
    private Point2D.Double getIntersectingPoint(double theta, Rectangle2D r) {
        double cx = r.getCenterX();
        double cy = r.getCenterY();
        double W = r.getWidth() / 2;
        double H = r.getHeight() / 2;
        double R = Point2D.distance(cx, cy, cx + W, cy + H);
        double x = cx + R * Math.cos(theta);
        double y = cy + R * Math.sin(theta);
        Point2D.Double p = new Point2D.Double();
        int outcode = r.outcode(x, y);
        switch (outcode) {
            case Rectangle2D.OUT_TOP:
                p.x = cx - H * ((x - cx) / (y - cy));
                p.y = cy - H;
                break;
            case Rectangle2D.OUT_LEFT:
                p.x = cx - W;
                p.y = cy - W * ((y - cy) / (x - cx));
                break;
            case Rectangle2D.OUT_BOTTOM:
                p.x = cx + H * ((x - cx) / (y - cy));
                p.y = cy + H;
                break;
            case Rectangle2D.OUT_RIGHT:
                p.x = cx + W;
                p.y = cy + W * ((y - cy) / (x - cx));
                break;
            default:
                p.x = cx + W;
                p.y = cy + W * ((y - cy) / (x - cx));
        }
        return p;
    }

    /**
     * Generates the path for the arc figure based on its start, end, and path points.
     */
    public void generatePath() {
        double bx, by, ex, ey, eFirstx, eFirsty, eLastx, eLasty;
        PathPoint pathPoint;
        if (start == null) {
            if (end == null) {
                pathPoint = pathPoints.getFirst();
                bx = pathPoint.getPosition().getX();
                by = pathPoint.getPosition().getY();
                pathPoint = pathPoints.getLast();
                ex = pathPoint.getPosition().getX();
                ey = pathPoint.getPosition().getY();
            } else {
                System.out.println("AbstractArcFigure GeneratePath()");
                bx = position.getX();
                by = position.getX();
                ex = round(end.getPosition().getX());
                ey = round(end.getPosition().getY());
            }
            eFirstx = ex;
            eFirsty = ey;
            eLastx = ex;
            eLasty = ey;
        } else if (end == null) {
            bx = round(start.getPosition().getX());
            by = round(start.getPosition().getY());
            pathPoint = pathPoints.get(pathPoints.size() - 1);
            ex = round(pathPoint.getPosition().getX());
            ey = round(pathPoint.getPosition().getY());
            eFirstx = bx;
            eFirsty = by;
            eLastx = ex;
            eLasty = ey;
            if (pathPoints.size() > 1) {
                pathPoint = pathPoints.get(1);
                eFirstx = round(pathPoint.getPosition().getX());
                eFirsty = round(pathPoint.getPosition().getY());
            }
            if (pathPoints.size() > 1) {
                pathPoint = pathPoints.get(pathPoints.size() - 2);
                eLastx = round(pathPoint.getPosition().getX());
                eLasty = round(pathPoint.getPosition().getY());
            }
        } else {
            bx = round(start.getPosition().getX());
            by = round(start.getPosition().getY());
            ex = round(end.getPosition().getX());
            ey = round(end.getPosition().getY());
            pathPoint = pathPoints.get(1);
            eFirstx = round(pathPoint.getPosition().getX());
            eFirsty = round(pathPoint.getPosition().getY());
            pathPoint = pathPoints.get(pathPoints.size() - 2);
            eLastx = round(pathPoint.getPosition().getX());
            eLasty = round(pathPoint.getPosition().getY());
        }

        int boffset = Grid.cellSize / 2;
        int eoffset = Grid.cellSize / 2;

        if (start == null) {
            boffset = 0;
        }

        if (end == null) {
            eoffset = 0;
        }
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        double dx = ex - eLastx;
        double dy = ey - eLasty;
        float v1 = operation(eFirstx, bx, eFirsty, by);
        float v2 = operation(eFirsty, by, eFirstx, bx);
        float v3 = operation(ex, eLastx, ey, eLasty);
        float v4 = operation(ey, eLasty, ex, eLastx);
        double theta = (Math.atan2(dy, dx));

        Point2D p0;
        Point2D p1;

        if (start instanceof PlaceFigure) {
            p0 = getPoint(new Point2D.Double(round(bx + boffset * v1), round(by + boffset * v2)), theta);
        } else if (start instanceof TransitionFigure) {
            dx = eFirstx - bx;
            dy = eFirsty - by;
            double theta1 = Math.atan2(dy, dx);
            TransitionFigure transitionFigure = (TransitionFigure) getStartConnector();
            p0 = getIntersectingPoint(theta1, (Rectangle2D) transitionFigure.getBounds());
        } else {
            pathPoint = pathPoints.getFirst();
            p0 = pathPoint.getPosition();
        }

        if (end != null) {
            if (end instanceof PlaceFigure) {
                p1 = getPoint(new Point2D.Double(round(ex - eoffset * v3), round(ey - eoffset * v4)), theta + Math.PI);
            } else {
                dx = eLastx - ex;
                dy = eLasty - ey;
                double theta1 = Math.atan2(dy, dx);
                TransitionFigure transitionFigure = (TransitionFigure) getEndConnector();
                p1 = getIntersectingPoint(theta1, (Rectangle2D) transitionFigure.getBounds());
            }
            pathPoint = pathPoints.get(this.pathPoints.size() - 1);
            pathPoints.set(this.pathPoints.size() - 1, new PathPoint(p1, pathPoint.getElementId()));
        }

        pathPoint = pathPoints.get(0);
        pathPoints.set(0, new PathPoint(p0, pathPoint.getElementId()));
        Iterator<PathPoint> it = pathPoints.iterator();

        int i = 0;
        while (i < pathPoints.size() && it.hasNext()) {
            pathPoint = it.next();
            if (i == 0) {
                path.moveTo((float) p0.getX(), (float) p0.getY());
            } else {
                this.path.lineTo(pathPoint.getPosition().getX(), pathPoint.getPosition().getY());
            }
            i++;
        }

        if (end == null) {
            theta += Math.PI;
            pathPoint = pathPoints.getLast();
            Line2D line = this.getLine(pathPoint.getPosition(), theta - PHI);
            Line2D line1 = this.getLine(pathPoint.getPosition(), theta + PHI);
            path.append(line, false);
            path.append(line1, false);
        }
    }

    /**
     * Rounds a double value to the nearest integer.
     *
     * @param d The double value to round.
     * @return The rounded integer value.
     */
    private int round(double d) {
        return (int) Math.floor(d + 0.5);
    }

    /**
     * Computes a floating-point value representing a mathematical operation.
     *
     * @param a The first operand.
     * @param b The second operand.
     * @param c The third operand.
     * @param d The fourth operand.
     * @return The result of the operation.
     */
    public float operation(double a, double b, double c, double d) {
        return (float) ((a - b) / Math.sqrt(Math.pow(a - b, 2) + Math.pow(c - d, 2)));
    }

    /**
     * Calculates a new Point2D based on an input Point2D and an angle (theta).
     *
     * @param p     The input Point2D.
     * @param theta The angle in radians.
     * @return A new Point2D after applying the angle transformation.
     */
    private Point2D getPoint(Point2D p, double theta) {
        return new Point2D.Double(p.getX() + Math.cos(theta), p.getY() + Math.sin(theta));
    }

    /**
     * Calculates a new Line2D segment based on an input Point2D and an angle (theta).
     *
     * @param p     The input Point2D.
     * @param theta The angle in radians.
     * @return A new Line2D segment representing the line from the input point along the specified angle.
     */
    private Line2D getLine(Point2D p, double theta) {
        double x = p.getX() + BARB * Math.cos(theta);
        double y = p.getY() + BARB * Math.sin(theta);
        return new Line2D.Double(p.getX(), p.getY(), x, y);
    }

    /**
     * @return the points
     */
    public LinkedList<PathPoint> getPoints() {
        return pathPoints;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(LinkedList<PathPoint> points) {
        this.pathPoints = points;
    }

    public void setPosition(Point2D newPosition) {
        // Implement this method if needed
    }
}

