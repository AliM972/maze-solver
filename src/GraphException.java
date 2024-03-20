/**
 * Custom exception class for handling errors related to graph operations.
 * This class extends the standard Exception class, providing a way to generate
 * error messages specific to issues encountered within graph-related methods.
 */
public class GraphException extends Exception {
  
  /**
   * Constructor for GraphException.
   * @param mssg The error message that this exception should carry.
   */
  public GraphException(String mssg) {
    super(mssg); // Call to the superclass (Exception) constructor with the provided message.
  }
}
