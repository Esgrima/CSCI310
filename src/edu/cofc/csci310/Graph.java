package edu.cofc.csci310;

import edu.cofc.csci310.Vertex.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Graph<Object extends Comparable<Object>> {

  public Graph() {
    this.adjacency_list = new ArrayList<>();
  }

  /**
   * Adjacency list data structure. Used in conjunction with adjacent_vertices in each Vertex
   * class.
   * <p>
   * Note: Each vertex in the adjacency list must be unique, i.e. the list cannot contain two
   * vertices that have the same value.
   */
  protected List<Vertex<Object>> adjacency_list;

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  public List<Vertex<Object>> getAdjacencyList() {
    return this.adjacency_list;
  }

  // Return the number of vertices
  public int nodeCount() {
    return this.adjacency_list.size();
  }

  // Return the current number of edges
  abstract int edgeCount();

  // Get the value of node with index v
  public Object getValue(int v) {
    return this.adjacency_list.get(v).getValue();
  }

  // Set the value of node with index v
  public void setValue(int v, Object val) {
    this.adjacency_list.get(v).setValue(val);
  }

  // Adds a new edge from node v to node w with weight wgt
  abstract boolean addEdge(Object start, Object end);

  // Removes the edge from the graph.
  abstract boolean removeEdge(Object start, Object end);

  // Returns true if the graph has the edge
  public boolean hasEdge(Vertex<Object> start, Vertex<Object> end) {
    if (!this.adjacency_list.contains(start)) {
      return false;
    }
    return start.hasAdjacentVertex(end);
  }

  public boolean add(Vertex<Object> vertex) {
    if (!this.adjacency_list.contains(vertex)) {
      this.adjacency_list.add(vertex);
      return true;
    }
    return false;
  }

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  public boolean eachVertexHasEdge() {
    for (Vertex<Object> vertex : this.adjacency_list) {
      if (vertex.numberOfAdjacentVertices() < 1) {
        return false;
      }
    }
    return true;
  }

  abstract ArrayList<ArrayList<Vertex<Object>>> getStronglyConnectedComponents();

  /**
   * Resets the vertices' VISITED field
   *
   * @author Rex Ferrer
   */
  protected void graphVisitReset() {
    //Marks each vertex as not visited
    for (Vertex<Object> vertex : this.adjacency_list) {
      vertex.setVisited(Vertex.NOT_VISITED);
    }
  } // end graphVisitReset()

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   *
   * @return whether the graph is bipartite
   */
  private void graphColorReset() {
    //Marks each vertex as not visited
    for (Vertex<Object> vertex : this.adjacency_list) {
      vertex.setColor(null);
    }
  } // end graphVisitReset()


  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private void colorVertices() {
    this.graphVisitReset();
    this.graphColorReset();

    LinkedList<Vertex<Object>> queue = new LinkedList<>();
    int count = 0;
    Color vertexColor = Color.GREEN;

    for (Vertex<Object> vertex : this.adjacency_list) {

      if (!vertex.hasBeenVisited()) {
        vertex.setVisited(++count);
        vertex.setColor(vertexColor);

        queue.add(vertex);
        colorVerticesHelper(queue, count, vertexColor);
      }
    }

  }

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private Color toggleColor(Color vertexColor) {
    return vertexColor == Color.GREEN ? Color.GREY : Color.GREY;
  }


  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private void colorVerticesHelper(LinkedList<Vertex<Object>> queue, int count, Color vertexColor) {
    if (queue.isEmpty()) {
      return;
    }

    Vertex<Object> vertex = queue.pollFirst();

    // Flips the color from the initial vertex for each adjacent vertex
    vertexColor = this.toggleColor(vertexColor);

    for (int i = 0; vertex != null && i < vertex.numberOfAdjacentVertices(); i++) {
      Vertex<Object> adjacentVertex = vertex.getAdjacentVertex(i);
      if (!adjacentVertex.hasBeenVisited()) {

        adjacentVertex.setVisited(++count);
        adjacentVertex.setColor(vertexColor);

        queue.add(adjacentVertex);
      }
    }

    colorVerticesHelper(queue, count, vertexColor);
  }

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   *
   * @return whether the graph is bipartite
   */
  public boolean isBipartite() {
    this.colorVertices();
    boolean isBipartite = true;

    for (Vertex<Object> vertex1 : this.adjacency_list) {
      // Builds a list of vertices of matching colors
      List<Vertex<Object>> colorMatches = vertex1.getAdjacentVertices().stream()
          .filter((vertex2) -> vertex2.getColor().equals(vertex1.getColor()))
          .collect(Collectors.toList());

      // This list should be empty in order for the graph to be bipartite
      if (!colorMatches.isEmpty()) {
        isBipartite = false;
        break;
      }
    }

    this.graphColorReset();
    this.graphVisitReset();

    return isBipartite;
  }
}
