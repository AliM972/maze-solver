import java.util.*;
import java.io.*;

/**
 * The Solve class serves as the entry point for the maze solver application.
 * It reads input arguments to get the maze file path and optionally the delay between drawing steps,
 * initializes the game environment, and starts the maze solving process.
 */
public class Solve {

    /**
     * The main method that executes the application.
     * It sets up the maze from a specified file, attempts to find a solution, and visualizes the process.
     *
     * @param args Command line arguments, expecting the maze file path as the first argument
     *             and optionally the speed of the visualization as the second argument.
     */
    public static void main (String[] args) {
        GraphNode u, v; // Variables to hold current and next node in the solution path.
        DrawMaze display; // The display window for the maze.
        int delay = 0; // Delay between drawing steps, defaults to 0 for no delay.
        BufferedReader in; // Reader for user input to control the flow of the visualization.
        String line;

        // Check for the correct number of arguments.
        if (args.length != 1 && args.length != 2)
            System.out.println("Usage: java Solve labyrinthFile [speed]");
        else {
            if (args.length == 2) delay = Integer.parseInt(args[1]); // Parse the delay if provided.
            display = new DrawMaze(args[0]); // Initialize the display with the maze file.

            try {
                in = new BufferedReader(new InputStreamReader(System.in));

                Maze theMaze = new Maze(args[0]); // Initialize the maze from the file.
                
                System.out.println("Press RET to continue");
                line = in.readLine(); // Wait for user input to start.

                Iterator solution = theMaze.solve(); // Attempt to solve the maze.

                // Visualize the solution path if one was found.
                if (solution != null) {
                    if (solution.hasNext()) u = (GraphNode)solution.next();
                    else return; // Exit if no solution exists.
                    while (solution.hasNext()) {
                        v = (GraphNode)solution.next();
                        Thread.sleep(delay); // Wait for the specified delay between steps.
                        display.drawEdge(u, v); // Draw the current step in the solution path.
                        u = v; // Move to the next node.
                    }
                }
                else {
                    System.out.println("No solution was found");
                }

                System.out.println("Press RET to finish");
                line = in.readLine(); // Wait for user input to end the visualization.

            } catch (MazeException e) {
                System.out.println("Error reading maze file");
            } catch (IOException inex) {
                System.out.println("Error reading from keyboard");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            display.dispose(); // Close the display window.
            System.exit(0); // Terminate the program.
        }
    }
}
