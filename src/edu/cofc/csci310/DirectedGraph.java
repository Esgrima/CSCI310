package edu.cofc.csci310;

import java.util.ArrayList;
import java.util.Stack;

public class DirectedGraph<Object extends Comparable<Object>> extends Graph<Object> {

  public DirectedGraph() {
    super();
  } // end constructor

  @Override
  public int edgeCount() {
    int edges = 0;

    for (Vertex<Object> vertex : adjacency_list) {
      edges += vertex.numberOfAdjacentVertices();
    }
    return edges;
  }

  @Override
  public boolean addEdge(Object start, Object end) throws VertexException {
    // ------------------------------------
    // Basic steps:
    // ------------------------------------
    // 1) For Vertex A and B, check to see if the vertex exits in the
    //    adjacency_list. If not, then add new vertex (with given value)
    //    to the adjacency_list.
    // 2) In the Vertex class, if Vertex B is in Vertex A's
    //    adjacent_vertices and vice versa (i.e. an edge exists). If an
    //    edge already exists, then return false, otherwise goto step 3.
    // 3) Add Vertex B to Vertex A's adjacent_vertices and vice versa.
    //    Lastly, return true indicating the edge was successfully added.

    Vertex<Object> startVertex = new Vertex<>(start);
    Vertex<Object> endVertex = new Vertex<>(end);

    int startVertex_exists = -1;
    int endVertex_exists = -1;

    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(startVertex) == 0) {
        startVertex_exists = i;
      }

      if (adjacency_list.get(i).compareTo(endVertex) == 0) {
        endVertex_exists = i;
      }

    }

    if (startVertex_exists == -1) {
      adjacency_list.add(startVertex);
    } else {
      startVertex = adjacency_list.get(startVertex_exists);
    }

    if (endVertex_exists == -1) {
      adjacency_list.add(endVertex);
    } else {
      endVertex = adjacency_list.get(endVertex_exists);
    }

    // Step 2

    if (startVertex.hasAdjacentVertex(endVertex)) {
      return false;
    } else {
      // Step 3
      startVertex.addAdjacentVertex(endVertex);
    }

    return true;
  }

  @Override
  public boolean removeEdge(Object start, Object end) {

    // ------------------------------------
    // Basic steps:
    // ------------------------------------
    // 1) For each vertex, see if the vertex exists in
    //    the adjacency_list. If not, return false that indicates the
    //    edge does not exist. Otherwise goto step 2.
    // 2) In Vertex class, see if Vertex B is in Vertex A's
    //    adjacent_vertices and vice versa (i.e. an edge exists).
    //    If the edge does not exist return false, otherwise goto
    //    step 3.
    // 3) In the Vertex class, remove Vertex B from Vertex A's
    //    adjacent_vertices and vice versa, and then goto step 4.
    //    Does not exist and return false, otherwise proceed to step 4.
    // 4) If number of adjacent vertices for Vertex A is zero, then
    //    remove from the adjacency_list. Likewise, if the number of
    //    adjacent vertices for Vertex B is zero, then remove from
    //    adjacency_list. Lastly, return true indicating the edge was
    //    successfully removed.

    Vertex<Object> startVertex = new Vertex<>(end);
    Vertex<Object> endVertex = new Vertex<>(start);

    int startVertex_exists = -1;
    int endVertex_exists = -1;

    // -----------------------------------------------------------------
    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(startVertex) == 0) {
        startVertex_exists = i;
      }

      if (adjacency_list.get(i).compareTo(endVertex) == 0) {
        endVertex_exists = i;
      }

    }

    // -----------------------------------------------------------------
    // Step 2
    if (startVertex_exists == 1 || endVertex_exists == -1) {
      return false;
    }

    startVertex = adjacency_list.get(startVertex_exists);
    endVertex = adjacency_list.get(endVertex_exists);

    // -----------------------------------------------------------------
    // Step 3
    if (!startVertex.hasAdjacentVertex(endVertex)) {
      return false;
    } else {

      startVertex.removeAdjacentVertex(endVertex);

    }

    // -----------------------------------------------------------------
    // Step 4
    if (startVertex.numberOfAdjacentVertices() == 0) {
      adjacency_list.remove(startVertex_exists);
    }

    return true;
  }

  /**
   * Implementation of Kosaraju's Algorithm CSCI310 Advanced Algorithms GraphADT Homework
   * Assignment
   */
  @Override
  public ArrayList<ArrayList<Vertex<Object>>> getStronglyConnectedComponents() {
    Stack<Vertex<Object>> stack = getKosarajuStack();
    DirectedGraph<Object> transpose = this.getTranspose();
    this.graphVisitReset();

    final ArrayList<ArrayList<Vertex<Object>>> stronglyConnectedComponents = new ArrayList<>();

    while (!stack.isEmpty()) {
      Vertex<Object> source = findVertexIn(stack.pop(), transpose);

      if (source.hasBeenVisited()) {
        continue;
      }

      ArrayList<Vertex<Object>> component = new ArrayList<>();
      getStronglyConnectedComponentsHelper(source, component);
      stronglyConnectedComponents.add(component);
    }

    this.graphVisitReset();
    return stronglyConnectedComponents;
  }

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private void getStronglyConnectedComponentsHelper(
      Vertex<Object> source,
      ArrayList<Vertex<Object>> component) {

    source.setVisited(1);
    component.add(source);

    for (Vertex<Object> adjacentVertex : source.getAdjacentVertices()) {
      if (!adjacentVertex.hasBeenVisited()) {
        getStronglyConnectedComponentsHelper(adjacentVertex, component);
      }
    }
  }

  /**
   * Finds the equivalent vertex from another graph in the provided graph.
   *
   * @param vertex1
   * @param graph
   * @return
   */
  public Vertex<Object> findVertexIn(Vertex<Object> vertex1, DirectedGraph<Object> graph)
      throws VertexException {
    for (Vertex<Object> vertex2 : graph.getAdjacencyList()) {
      if (vertex1.compareTo(vertex2) == 0) {
        return vertex2;
      }
    }
    throw new VertexException("Vertex does not exist");
  }


  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private Stack<Vertex<Object>> getKosarajuStack() {
    this.graphVisitReset();

    Stack<Vertex<Object>> stack = new Stack<>();

    for (Vertex<Object> vertex : this.adjacency_list) {
      if (!vertex.hasBeenVisited()) {
        getKosarajuStackHelper(vertex, stack);
      }
    }

    return stack;
  }

  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private void getKosarajuStackHelper(Vertex<Object> vertex, Stack<Vertex<Object>> stack) {
    vertex.setVisited(1);

    for (Vertex<Object> adjacentVertex : vertex.getAdjacentVertices()) {
      if (!adjacentVertex.hasBeenVisited()) {
        getKosarajuStackHelper(adjacentVertex, stack);
      }
    }
    stack.push(vertex);
  }


  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  private DirectedGraph<Object> getTranspose() {
    DirectedGraph<Object> transpose = new DirectedGraph<>();

    // Reversing the directions of each edge
    for (Vertex<Object> vertex : this.adjacency_list) {
      for (Vertex<Object> adjacentVertex : vertex.getAdjacentVertices()) {
        transpose.addEdge(adjacentVertex.getValue(), vertex.getValue());
      }
    }
    return transpose;
  }
}
