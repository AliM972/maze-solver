/**
 * Represents a node in the graph, corresponding to a room in the maze.
 * Each node has a unique name (identifier) and a mark status that can be used
 * for traversal algorithms to keep track of visited nodes.
 */
public class GraphNode {
    /**
     * The unique identifier for the node.
     */
    private int nodeName;

    /**
     * Mark status of the node, useful for graph traversal algorithms.
     */
    private boolean nodeMark;

    /**
     * Constructs a new GraphNode with a specified name.
     * 
     * @param name the unique identifier for the node
     */
    public GraphNode(int name) {
        nodeName = name;
    }

    /**
     * Marks the node with a specified boolean value.
     * 
     * @param mark the mark status to set for the node
     */
    public void mark(boolean mark) {
        nodeMark = mark;
    }

    /**
     * Checks if the node is marked.
     * 
     * @return the current mark status of the node
     */
    public boolean isMarked() {
        return nodeMark;
    }

    /**
     * Retrieves the name (identifier) of the node.
     * 
     * @return the name of the node
     */
    public int getName() {
        return nodeName;
    }
}
