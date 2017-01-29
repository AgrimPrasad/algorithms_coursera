import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Implement a generic graph which can be processed by a graph processing class
public class Graph {
  private final int V;
  private int E;
  private LinkedList<Integer>[] adj;  // vertex-indexed list/array

  // create a V-vertex graph with no edges
  public static Graph(int V) {
    this.V = V;
    this.E = 0;

    adj = new LinkedList<Integer>[V]; // empty adjacency list

    // Initialize empty adjacency lists for each entry in the graph
    for (v : adj) {

    }
  }

  // Read a graph from input stream in
  // Format of file:
  // numVertices
  // numEdges
  // v1, w1
  // v2, w2...
  public static Graph(int V) {

  }

  // number of vertices
  public int V() {

  }

  // number of edges
  public int E() {

  }

  // add undirected (aka bi-directed) edge v<->w) to this graph
  void addEdge(int v, int w) {

  }

  // vertices adjacent to v
  Iterable<Integer> adj(int v) {

  }

  // string representation
  String toString() {

  }
}
