/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

/**
 * An interface for figures that represent connections between two abstract figures.
 */
public interface ConnectionFigure {

    /**
     * Sets the start connector of the connection.
     *
     * @param start The abstract figure representing the start connector.
     */
    public void setConnectionStart(AbstractFigure start);

    /**
     * Gets the start connector of the connection.
     *
     * @return The abstract figure representing the start connector.
     */
    public AbstractFigure getStartConnector();

    /**
     * Sets the end connector of the connection.
     *
     * @param end The abstract figure representing the end connector.
     */
    public void setConnectionEnd(AbstractFigure end);

    /**
     * Gets the end connector of the connection.
     *
     * @return The abstract figure representing the end connector.
     */
    public AbstractFigure getEndConnector();
}