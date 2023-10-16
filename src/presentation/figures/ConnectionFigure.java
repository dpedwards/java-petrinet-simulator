/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation.figures;

/**
 *
 * @author Davain Pablo Edwards
 */
public interface ConnectionFigure {

    public void setConnectionStart(AbstractFigure start);

    public AbstractFigure getStartConnector();

    public void setConnectionEnd(AbstractFigure end);

    public AbstractFigure getEndConnector();
}
