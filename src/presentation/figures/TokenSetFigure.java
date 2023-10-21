/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import business.Global;
import business.Place;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Represents a graphical figure for displaying tokens in a place.
 * Extends TextFigure.
 */
public class TokenSetFigure extends TextFigure {

    private String placeId;
    /** Distance from parent Figure */
    private Point2D offsetToParent = new Point2D.Double(0, 0);
    final private static int DIAMETER = 30;

    /**
     * Constructs a TokenSetFigure associated with a parent PlaceFigure.
     *
     * @param parent The parent PlaceFigure.
     */
    public TokenSetFigure(PlaceFigure parent) {
        super(parent);
        this.position = new Point2D.Double(offsetToParent.getX() + parent.getPosition().getX(), offsetToParent.getY() + parent.getPosition().getY());
        this.placeId = parent.getElementId();
    }

    /**
     * Draws the TokenSetFigure on the graphics context.
     *
     * @param g The graphics context.
     */
    @Override
    public void draw(Graphics2D g) {
        drawFill(g);
        drawStroke(g);
    }

    /**
     * Draws the filled portion of the TokenSetFigure.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawFill(Graphics2D g) {
        g.setPaint(fillColor);
        // Fill the ellipse (circle)
        g.fillOval((int) position.getX() - DIAMETER / 2, (int) position.getY() - DIAMETER / 2, DIAMETER, DIAMETER);
    }

    /**
     * Draws the stroke (outline) of the TokenSetFigure.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        super.drawStroke(g);
        // Draw the outline of the ellipse (circle)
        g.drawOval((int) position.getX() - DIAMETER / 2, (int) position.getY() - DIAMETER / 2, DIAMETER, DIAMETER);
    }

    /**
     * Sets the relative position of the TokenSetFigure.
     *
     * @param parentPosition The position of the parent figure.
     */
    @Override
    public void setRelativePosition(Point2D parentPosition) {
        position = new Point2D.Double(parentPosition.getX() + offsetToParent.getX(), parentPosition.getY() + offsetToParent.getY());
    }

    /**
     * Gets the text to display in the TokenSetFigure (number of tokens in the associated place).
     *
     * @return The text representing the number of tokens.
     */
    @Override
    public String getText() {
        Place place = (Place) Global.petriNet.getNetElement(placeId);
        return "" + place.getTokens().size();
    }
}