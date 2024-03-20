import java.util.*;
// This class represents an undirected graph using an adjacency list representation
public class Graph implements GraphADT {
	
	private GraphNode[] vertices;          // Array to store the graph nodes
	private ArrayList<LinkedList<GraphEdge>> adjacencyList;  // Adjacency list representation of the graph
	private int numNodes;                  // Number of nodes in the graph
	
	// Constructor to initialize the graph with a given number of nodes
	public Graph(int n) {
		this.numNodes = n;
		adjacencyList = new ArrayList<>();
		vertices = new GraphNode[numNodes];
		
		// Initialize each node and its corresponding linked list in the adjacency list
		for(int i = 0; i < n; i++) {
			vertices[i] = new GraphNode(i);
			LinkedList<GraphEdge> currentList = new LinkedList<>();
			adjacencyList.add(currentList);
		}
	}

	// Insert an undirected edge between two nodes with a given type and label
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
		LinkedList<GraphEdge> firstNodeList = null, secondNodeList = null;
		try {
			firstNodeList = adjacencyList.get(nodeu.getName());
			secondNodeList =  adjacencyList.get(nodev.getName());
			
			// Create a new edge and add it to the adjacency lists of both nodes
			GraphEdge newEdge = new GraphEdge(nodeu, nodev, type, label);
			if(firstNodeList.contains(newEdge)) {
				throw new GraphException("There is already an edge connecting these nodes");
			}
			firstNodeList.add(newEdge);
			
			newEdge = new GraphEdge(nodev, nodeu, type, label);
			if(secondNodeList.contains(newEdge)) {
				throw new GraphException("There is already an edge connecting these nodes");
			}
			secondNodeList.add(newEdge);
		}
		catch (IndexOutOfBoundsException e) {
			throw new GraphException("Either one or both nodes not in the graph");
		}
	}

	// Get the graph node with a given name
	@Override
	public GraphNode getNode(int u) throws GraphException {
		try {
			return vertices[u];
		}
		catch (IndexOutOfBoundsException e) {
			throw new GraphException("Node with this name doesn't exist");
		}
	}

	// Get an iterator over the incident edges of a given node
	@Override
	public Iterator incidentEdges(GraphNode u) throws GraphException {
		try {
			LinkedList<GraphEdge> edgeList = adjacencyList.get(u.getName());
			
			// Check if the edge list is not empty and return an iterator
			if(edgeList.iterator().hasNext()) {
				return edgeList.iterator();
			}
			
			// If the edge list is empty, return null
			else {
				return null;
			}
		}
		catch (IndexOutOfBoundsException e){
			throw new GraphException("Node doesn't exist");
		}
	}

	// Get the edge between two nodes
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		try {
			LinkedList<GraphEdge> wantedList = adjacencyList.get(u.getName());
			
			// Search for the edge with the second endpoint equal to v
			for(GraphEdge edge : wantedList) {
				if(edge.secondEndpoint().getName() == v.getName()) {
					return edge;
				}
			}
			
			// If no such edge is found, throw an exception
			throw new GraphException("There is no edge containing these two nodes"); 
		}
		catch(IndexOutOfBoundsException e) {
			throw new GraphException("Either one or both nodes not in the graph");
		}
	}

	// Check if two nodes are adjacent
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		try {
			LinkedList<GraphEdge> wantedList = adjacencyList.get(u.getName());
			
			// Check if there is an edge with the second endpoint equal to v
			for(GraphEdge edge : wantedList) {
				if(edge.secondEndpoint().getName() == v.getName()) {
					return true;
				}
			}
			
			// If no such edge is found, return false
			return false;
		}
		catch(IndexOutOfBoundsException e) {
			throw new GraphException("Either one or both nodes not in the graph");
		}
	}
}
