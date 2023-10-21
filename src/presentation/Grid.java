/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package presentation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 * Represents a grid that can be drawn on a graphical surface.
 */
public class Grid {

    /** Grid width.*/
    private int width;
    /** Grid height.*/
    private int height;
    /** Size of the foreground cell.*/
    public static int cellSize = 50;
    /** Color of foreground cell.*/
    private Color strongColor = new Color(235, 235, 235);
    /** Color of the background cell.*/
    private Color weakColor = new Color(220, 220, 220);
    /** Background grid.*/
    private GeneralPath backgroundGrid;
    /** Foreground grid.*/
    private GeneralPath foregroundGrid;

    /**
     * Constructor for a new Grid given a width and height.
     *
     * @param width  The width of the grid.
     * @param height The height of the grid.
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Generates a new grid with a predefined number of columns.
     *
     * @param numCells The number of cells (columns) in the grid.
     * @return A GeneralPath representing the grid.
     */
    public GeneralPath generateGrid(int numCells) {
        GeneralPath grid = new GeneralPath();
        
        // Draw vertical lines
        for (float i = 0; i <= width; i += cellSize / numCells) {
            grid.moveTo(i, 2);
            grid.lineTo(i, height);
        }

        // Draw horizontal lines
        for (float i = 0; i <= height; i += cellSize / numCells) {
            grid.moveTo(2, i);
            grid.lineTo(width, i);
        }

        return grid;
    }

    /**
     * Generates and draws the background and foreground grids.
     *
     * @param g2 The Graphics2D context to draw on.
     */
    public void drawGrid(Graphics2D g2) {
        // Generate the background grid if it's null
        if (backgroundGrid == null) {
            backgroundGrid = generateGrid(5);
        }
        g2.setPaint(strongColor);
        g2.draw(backgroundGrid);

        // Generate the foreground grid if it's null
        if (foregroundGrid == null) {
            foregroundGrid = generateGrid(1);
        }
        g2.setPaint(weakColor);
        g2.draw(foregroundGrid);
    }
}

