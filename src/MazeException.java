/**
 * Custom exception class for handling errors related to maze operations.
 * This class extends the standard Exception class to provide a mechanism
 * for throwing and catching errors specific to maze processing tasks,
 * such as reading maze configuration files or solving the maze.
 */
public class MazeException extends Exception {

  /**
   * Constructor for MazeException.
   * @param mssg The detailed error message that this exception should carry, 
   *             providing more context about the error encountered during maze operations.
   */
  public MazeException(String mssg) {
    super(mssg); // Invoke the constructor of the superclass (Exception) with the given message.
  }
}
