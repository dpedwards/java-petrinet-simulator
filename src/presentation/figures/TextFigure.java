/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

import business.Global;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents a text figure associated with another figure.
 */
public class TextFigure extends AbstractFigure {

    // Distance from the parent figure
    private Point2D offsetToParent = new Point2D.Double(50, 0);
    
    // The parent figure of this text figure
    private AbstractFigure parent;
    
    // The shape of this text figure
    private Rectangle2D rectangle;

    /**
     * Constructs a TextFigure associated with a parent figure.
     *
     * @param parent The parent figure.
     */
    public TextFigure(AbstractFigure parent) {
        this.position = new Point2D.Double(offsetToParent.getX() + parent.getPosition().getX(), offsetToParent.getY() + parent.getPosition().getY());
        this.parent = parent;
    }

    /**
     * Checks if a point is contained within the text figure.
     *
     * @param position The point to check.
     * @return True if the point is contained, false otherwise.
     */
    @Override
    public boolean contains(Point2D position) {
        return this.rectangle.contains(position);
    }

    /**
     * Draws the text figure on the graphics context.
     *
     * @param g The graphics context.
     */
    @Override
    public void draw(Graphics2D g) {
        drawStroke(g);
    }

    /**
     * Draws the filled portion of the text figure (not supported).
     *
     * @param g The graphics context.
     */
    @Override
    public void drawFill(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Draws the stroke (outline) of the text figure.
     *
     * @param g The graphics context.
     */
    @Override
    public void drawStroke(Graphics2D g) {
        drawText(g);
    }

    /**
     * Gets the text content for this figure.
     *
     * @return The text content.
     */
    public String getText() {
        String lbl = Global.petriNet.getNetElement(parent.getElementId()).getLabel();
        if (!lbl.equals(parent.getElementId())) {
            lbl = parent.getElementId() + ":" + lbl;
        }
        return lbl;
    }

    /**
     * Gets the label text from the associated parent figure.
     *
     * @return The label text.
     */
    public String getTextLabel() {
        return Global.petriNet.getNetElement(parent.getElementId()).getLabel();
    }

    /**
     * Draws the text on the graphics context.
     *
     * @param g The graphics context.
     */
    public void drawText(Graphics2D g) {
        g.setStroke(new java.awt.BasicStroke(1f));
        g.setPaint(strokeColor);

        Font font = new Font(null, java.awt.Font.BOLD, 12);
        FontRenderContext fontRenderContext = g.getFontRenderContext();
        TextLayout textLayout = new TextLayout(getText(), font, fontRenderContext);

        Rectangle2D rectangle2D = textLayout.getBounds();

        rectangle = new Rectangle2D.Double(position.getX() - rectangle2D.getWidth() / 2,
                position.getY() - rectangle2D.getHeight() / 2, rectangle2D.getWidth(),
                rectangle2D.getHeight());

        g.drawString(getText(), (float) (position.getX() - rectangle2D.getWidth() / 2),
                (float) (position.getY() + rectangle2D.getHeight() / 2));
    }

    /**
     * Sets the position of the text figure.
     *
     * @param position The new position.
     */
    @Override
    public void setPosition(Point2D position) {
        offsetToParent = new Point2D.Double(position.getX() - parent.getPosition().getX(),
                position.getY() - parent.getPosition().getY());
        this.position = position;
    }

    /**
     * Sets the relative position of the text figure based on the parent position.
     *
     * @param parentPosition The parent's position.
     */
    public void setRelativePosition(Point2D parentPosition) {
        position = new Point2D.Double(parentPosition.getX() + offsetToParent.getX(),
                parentPosition.getY() + offsetToParent.getY());
    }

    /**
     * Gets the bounds of the text figure.
     *
     * @return The rectangular bounds.
     */
    public Rectangle2D getBounds() {
        return this.rectangle;
    }

    /**
     * Gets the element identifier of the text figure.
     *
     * @return The element identifier.
     */
    @Override
    public String getElementId() {
        return this.parent.getElementId() + "label";
    }

    /**
     * Gets the offset to the parent figure.
     *
     * @return The offset to the parent.
     */
    public Point2D getOffsetToParent() {
        return offsetToParent;
    }

    /**
     * Sets the offset to the parent figure.
     *
     * @param offset The new offset to the parent.
     */
    public void setOffsetToParent(Point2D offset) {
        offsetToParent = offset;
    }

    /**
    * Sets the element identifier (not supported).
    *
    * @param id The new element identifier.
    */
    @Override
    public void setElementId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}