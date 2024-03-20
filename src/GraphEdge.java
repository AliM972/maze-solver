// This class represents an edge of the graph
public class GraphEdge {
	// Declaring instance variables (edge's endpoints, its type, and its label)
	private GraphNode endpoint1, endpoint2;
	private int edgeType;
	private String edgeLabel;
	
	// Constructor that initializes the instance variables with passed in parameters
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		endpoint1 = u;
		endpoint2 = v;
		edgeType = type;
		edgeLabel = label;
	}
	
	// Returns the first endpoint
	public GraphNode firstEndpoint() {
		return endpoint1;
	}
	
	// Returns the second endpoint
	public GraphNode secondEndpoint() {
		return endpoint2;
	}
	
	// Returns the edge's type value
	public int getType() {
		return edgeType;
	}
	
	// Set a new type for the edge
	public void setType(int newType) {
		edgeType = newType;
	}
	
	// Returns the label
	public String getLabel() {
		return edgeLabel;
	}
	
	// Set a new label
	public void setLabel(String newLabel) {
		this.edgeLabel = newLabel;
	}
	
	
}
