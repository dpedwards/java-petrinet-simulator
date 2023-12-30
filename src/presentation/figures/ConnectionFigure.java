/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

/**
 * An interface for figures that represent connections between two abstract figures.
 * The `ConnectionFigure` is an interface in Java, not a class. However, it can be part of the Bridge design pattern. 
 * The Bridge pattern is characterized by the separation of an abstraction from its implementation so that the two can vary independently.
 * In this case, `ConnectionFigure` defines a common interface for a family of figures that represent connections between two abstract figures. 
 * The methods defined in the interface (`setConnectionStart`, `getStartConnector`, `setConnectionEnd`, `getEndConnector`) 
 * are typical of the Bridge pattern, as they provide a bridge between the abstract concept of a connection and its concrete implementation.
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