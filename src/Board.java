import java.awt.*;
import javax.swing.*;

/**
 * The Board class extends JComponent and serves as the graphical canvas for drawing the maze.
 * It provides a visual representation of the maze, including its layout and the path found by the solver.
 */
public class Board extends JComponent {

    /**
     * Constructor for the Board class.
     * Initializes a new Board instance. Additional initialization can be added if needed.
     */
    public Board() {}

    /**
     * Overrides the paint method to customize the drawing of the maze components.
     * This method is called by Swing to render the component and can be used to draw the maze,
     * the solution path, or any other graphical elements related to the maze.
     * 
     * @param display The Graphics object used for drawing.
     */
    public void paint(Graphics display) {
        display.setColor(Color.lightGray); // Set the drawing color. Placeholder for actual maze drawing logic.
        // Drawing code for the maze and its components would go here.
    }
}
