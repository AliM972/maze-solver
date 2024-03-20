import java.io.*;
import java.util.*;
// This class represents the maze. An object of the class Graph stores the maze and finds a solution for it
public class Maze {
	// Instance variables that are used throughout the class
	private Graph graph;
	private int width, length, numRows, numCols, nodeCount = 0, type, maxCoins, availableCoins;
	private char[][] mazeArray;
	private String row;
	private GraphNode start, exit, left, top, right, bottom;

	public Maze(String inputFile) throws MazeException {

		try {
			// We will use a BufferedReader to read the input file
			BufferedReader inFile = new BufferedReader(new FileReader(inputFile));
			Integer.parseInt(inFile.readLine()); // We will not store the first row (scale factor), it's unused
															
			width = Integer.parseInt(inFile.readLine());	// Store the first two numbers as the 
			length = Integer.parseInt(inFile.readLine());	// width and length respectively
			numRows = (2 * length) - 1;	// The actual columns and rows are 2*(width or length) - 1. 
			numCols = (2 * width) - 1;	// The width and length are only for the graph's dimensions
			maxCoins = Integer.parseInt(inFile.readLine());	// Max coins we're allowed for the maze

			mazeArray = new char[numRows][numCols];	// Making a 2d array for the maze.

			for (int i = 0; i < numRows; i++) {	// Iterating through the array and storing characters accordingly
				row = inFile.readLine();

				for (int j = 0; j < numCols; j++) {
					mazeArray[i][j] = row.charAt(j);
				}
			}
			if (row == null) {	// Closing file (always null at the end of a txt file)
				inFile.close();
			}

		} catch (IOException e) { 	// Exceptions to catch one for error in recieving the file
			throw new MazeException("Input file does not exist, or the format of the input file is incorrect");
		} catch (Exception e) {	// Catch other errors
			System.out.println("Error in input file: " + e.getMessage());
			System.exit(0);
		}
		
		graph = new Graph(width * length);	//creating graph by multiplying width & length to get total amount of nodes
		try {
			// We will now iterate through the maze array
			for (int i = 0; i < numRows; i++) {	
				for (int j = 0; j < numCols; j++) {
					// When both i and j are positive, it is a node location
					if ((i % 2) == 0 && (j % 2) == 0) { 
						// Nodes are either the start node (s), exit node (x), or a room (i)
						if (mazeArray[i][j] == 's') { 
							start = graph.getNode(nodeCount);
						}	// We will create a reference to the start and exit nodes
						else if (mazeArray[i][j] == 'x') {
							exit = graph.getNode(nodeCount);
						} 
						else if (mazeArray[i][j] == 'i') {

						}
						nodeCount++;	// Also keep a reference to the node count
					} 
					
					else { // Not at a node location, we will now start adding edges
						// There are 3 cases: corners(don't need to check, they will be nodes), edges, other nodes

						// EDGES -> 4 cases: top, bottom, left, right
						if (i == 0 || i == (numRows - 1)) { // we are at top edge, dealing with horizontal edges
							left = graph.getNode(nodeCount - 1);//getting the left and right nodes references
							right = graph.getNode(nodeCount);
							// Inserting edges between the nodes based on input file contents
							if (mazeArray[i][j] == 'c') { // corridor, (0 coins required to pass so type 0)
								graph.insertEdge(left, right, 0, "corridor");

							} else if (mazeArray[i][j] == 'w') { // wall -> no connection between nodes; ie. no edge

							} else {	// It is a door, get numerical value of the coins required and store in type
								type = Character.getNumericValue(mazeArray[i][j]);
								graph.insertEdge(left, right, type, "door");
							}
						} 
						
						else if (j == 0) { // We are at left edge, dealing with vertical edges
							top = graph.getNode(nodeCount - width);	// Get top node (up 1 row by subtracting width)
							bottom = graph.getNode(nodeCount);	// Get bottom node
							// Same deal with edge parameters
							if (mazeArray[i][j] == 'c') { 
								graph.insertEdge(top, bottom, 0, "corridor");
							} 
							else if (mazeArray[i][j] == 'w') { 
							} 
							else {
								type = Character.getNumericValue(mazeArray[i][j]);
								graph.insertEdge(top, bottom, type, "door");
							}
						} 
						
						else if (j == (numCols - 1)) { // We are at right edge
							top = graph.getNode(nodeCount - 1);	// Getting top node
							bottom = graph.getNode(nodeCount + width - 1);	// Getting bottom node (add a row to top Node)
							// Same deal with edge parameters
							if (mazeArray[i][j] == 'c') { 
								graph.insertEdge(top, bottom, 0, "corridor");
							} 
							else if (mazeArray[i][j] == 'w') { 
							} 
							else {
								type = Character.getNumericValue(mazeArray[i][j]);
								graph.insertEdge(top, bottom, type, "door");
							}
						}

						else { // Any other node (not at edges or corners)
							top = graph.getNode(nodeCount - width + j / 2);	// Finding top node and bottom nodes relative to current edge
							bottom = graph.getNode(nodeCount + j / 2);
							left = graph.getNode(nodeCount - 1);	// left and right nodes are same
							right = graph.getNode(nodeCount);

							if (i % 2 != 0) { // Meaning this only deals with veritcal edges
								if (mazeArray[i][j] == 'c') {
									graph.insertEdge(top, bottom, 0, "corridor");
								} 
								else if (mazeArray[i][j] == 'w') {
								} 
								else {
									type = Character.getNumericValue(mazeArray[i][j]);
									graph.insertEdge(top, bottom, type, "door");
								}

							} else { // Means that this row is only dealing with horizontal edges
								if (mazeArray[i][j] == 'c') { 
									graph.insertEdge(left, right, 0, "corridor");
								} 
								else if (mazeArray[i][j] == 'w') {
								} 
								else {
									type = Character.getNumericValue(mazeArray[i][j]);
									graph.insertEdge(left, right, type, "door");
								}
							}
						}
					}
				}
			}
		} catch (GraphException e) {	// Catch any errors with the graph
			System.out.println("Node not in the graph: " + e.getMessage());
			System.exit(0);
		}
	}

    public Graph getGraph() throws MazeException {	// Returns a reference to the Graph object representing the maze. 
    	
        if (graph == null) {
            throw new MazeException("The maze is empty");	// Throws a MazeException if the graph is null.
        }
        return graph;
    }

    public Iterator<GraphNode> solve() {
        // Create an empty ArrayList to store the path
        ArrayList<GraphNode> graphPath = new ArrayList<>();
        // Set the available coins to the maximum number of coins
        availableCoins = maxCoins;
        
        try {
            // Call the depth-first search traversal method with the start node, the path, and the available coins
            if(dfsTraversal(start, graphPath, availableCoins)) {
                // If the traversal returns true, return an iterator over the path
                return graphPath.iterator();
            }
        }
        // Catch any GraphException that might be thrown during the traversal
        catch (GraphException e) {
            // Print an error message along with the exception message
            System.out.println("There was an issue with the graph" + e.getMessage());
        }
        
        // If no path to the exit was found, return null
        return null;
    }

    private boolean dfsTraversal(GraphNode currentNode, ArrayList<GraphNode> currentPath, int coins) throws GraphException {
        // Mark the current node as visited
        currentNode.mark(true);
        
        // Add the current node to the path
        currentPath.add(currentNode);
        
        // If the current node is the exit, return true
        if(currentNode == exit) {
            return true;
        }
        
        // If the current node is not the exit, iterate over its incident edges
        else {
            Iterator<GraphEdge> incidentEdges = graph.incidentEdges(currentNode);
            while(incidentEdges.hasNext()) {
                // Get the next edge and the node at the other end of the edge
                GraphEdge currentEdge = incidentEdges.next();
                GraphNode nextNode = currentEdge.secondEndpoint();
                
                // If the next node has not been visited and the edge can be traversed with the available coins,
                // recursively call the dfsTraversal method with the next node, the path, and the remaining coins
                if(!nextNode.isMarked() && currentEdge.getType() <= coins) {
                    if(dfsTraversal(nextNode, currentPath, coins - currentEdge.getType())) {
                       return true;
                    }
                }
            }
        }
        
        // If no path to the exit was found from the current node, unmark the node and remove it from the path
        currentNode.mark(false);
        currentPath.remove(currentPath.size() - 1);
        
        // Return false to indicate that no path to the exit was found from the current node
        return false;
    }


}
