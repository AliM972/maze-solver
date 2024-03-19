# Maze Solver Game

## Description
Navigate through a maze with a set number of coins to open doors and find a path from the entrance to the exit. The game is built using Java, and it models the maze as an undirected graph where nodes represent rooms and edges represent corridors or doors that might be opened using coins.

## Setup and Execution

### Prerequisites
- Java Development Kit (JDK) installed on your machine.

### Running the Game
1. Clone the repository or download the source code.
2. Navigate to the directory containing the game files.
3. Compile the Java files using the following command:
   ```bash
   javac *.java
4. Run the game with the Solve class, providing a path to a maze file as an argument (example maze files are provided):
   ```bash
   java Solve path/to/maze_file.txt

### Input Maze File Format
The maze file is a plain text file with the following structure:

* The first line specifies the scale factor (not used in the game logic).
* The second line indicates the width of the maze (number of rooms horizontally).
* The third line indicates the length of the maze (number of rooms vertically).
* The fourth line specifies the number of coins available to the player.
* The remaining lines describe the maze layout, where:
  * 's' denotes the entrance.
  * 'x' denotes the exit.
  * 'o' denotes a room.
  * 'c' denotes a corridor.
  * 'w' denotes a wall.
  * Digits '0' to '9' denote doors that require a corresponding number of coins to open.

## Implementation Details

### Classes
* Board.java: Defines a component for displaying graphical elements of the maze, such as rooms, corridors, and walls. It's used as the drawing canvas for the maze visualization.
* DrawMaze.java: Handles the graphical representation of the maze, including drawing the maze structure, the solution path, and animation aspects (like moving through the maze). It uses the Board component for drawing.
* Graph.java: Implements the maze as an undirected graph. This class manages the graph's nodes (rooms) and edges (corridors/doors), providing functionality to add edges, check adjacency, and perform other graph-related operations.
* GraphADT.java: An interface that defines the abstract data type (ADT) for the graph structure used to represent the maze. It outlines methods for graph operations like inserting edges and checking if nodes are adjacent.
* GraphEdge.java: Represents an edge in the graph, which could be a corridor or a door in the maze. It stores information about the edge, including its endpoints (nodes) and attributes like type or label (e.g., "corridor" or "door").
* GraphException.java: A custom exception class used to handle errors related to graph operations, such as attempting to access a node or edge that doesn't exist.
* GraphNode.java: Represents a node in the graph, corresponding to a room in the maze. It stores the node's identifier and state (e.g., visited or unvisited).
* Maze.java: Manages the maze logic, including reading the maze configuration from a file, constructing the graph representation of the maze, and finding a solution path from the entrance to the exit.
* MazeException.java: A custom exception class for handling errors specific to maze operations, such as issues with reading the maze file or finding a solution.
* Solve.java: The main class that initiates the maze-solving process. It reads the maze file path from command-line arguments, creates a Maze instance, and triggers the process to find and display the solution path.

### Solving the Maze
The game uses a modified depth-first search (DFS) algorithm to find a path from the entrance to the exit, considering the number of coins available to open doors.

### Languages/ Libraries:
* The game is entirely written in Java
* Java AWT (Abstract Window Toolkit) and Swing libraries for the graphical user interface (GUI) components and graphics drawing. 
