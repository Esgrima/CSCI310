package edu.cofc.csci310;


import edu.cofc.csci310.support.ConstantTimeQueue;
import edu.cofc.csci310.support.ConstantTimeStack;
import edu.cofc.csci310.support.Queue;
import edu.cofc.csci310.support.Stack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Undirected and unweighted graph data structure.
 * <p>
 * <p>
 * CSCI 230: Data Structures and Algorithms
 * <p>
 * Fall 2018
 */
public class UndirectedGraph<Object extends Comparable<Object>> extends Graph<Object> {

  /**
   * Constructor
   */
  public UndirectedGraph() {
    super();
  } // end constructor


  /**
   * See supplemental course textbook for definition of connected graph (page 31)
   * <p>
   * This method returns TRUE if the graph is connected, or FALSE if it is not.
   * <p>
   * Furthermore, if the adjacency list does not have any vertices, i.e. the graph is empty, the
   * method will return false.
   * <p>
   * ----------------- Note: -----------------
   * <p>
   * You may add additional methods or private instance variables to this class to support your
   * solution (i.e. if it is recursive). However, you MUST use the provided adjacency list and the
   * vertex class, and you MAY NOT create an additional class. If in doubt about these restrictions
   * -> you must ask!
   *
   * @author Rex Ferrer
   */
  public Boolean isConnected() {

    // ----------------------------
    // DONE:Add your code here
    // ----------------------------
    if (this.adjacency_list.isEmpty()) {
      return false;
    }

    this.depthFirstSearch(this.adjacency_list.get(0).getValue());

    for (int i = 0; i < this.adjacency_list.size(); i++) {
      Vertex<Object> curr = this.adjacency_list.get(i);
      //Checks if the vertex has no adjacent vertices
      if (!curr.hasBeenVisited()) {
        return false;
      }
    }
    return true; // this is only here for the code to compile, you must change!
  } // end isConnected() method

  /**
   * Create a formatted string that lists all the graph cycles
   * <p>
   * For example, if the adjacency list is:
   * <p>
   * Vertex (1): [ 2, 3, 4 ] Vertex (2): [ 1, 4 ] Vertex (3): [ 1, 4 ] Vertex (4): [ 1, 2, 3 ]
   * <p>
   * then this method would return the string
   * <p>
   * cycle (1): [ 1, 2, 4 ] cycle (2): [ 1, 3, 4 ] cycle (3): [ 1, 2, 3, 4 ]
   * <p>
   * As shown above, the format of the string MUST be:
   * <p>
   * cycle( <cycle_number> ): [ <comma delimited sequence of vertex numbers> ]\n
   * <p>
   * <p>
   * ----------------- Notes: ----------------- 1) Your cycle results must be formatted in ascending
   * vertex value order, e.g. below would NOT BE correct cycle (1): [ 2, 1, 4 ]
   * <p>
   * 2) No duplicate cycles are included, e.g. this result is NOT correct cycle (1): [ 1, 2, 4 ]
   * cycle (2): [ 1, 2, 4 ]
   * <p>
   * 3) The order of the cycles must be sorted (ascending order) by the number of vertices that form
   * the cycle. i.e., cycles formed by three vertices first, then cycles formed by four vertices
   * second, etc. For example, the result below is NOT correct. cycle (1): [ 1, 2, 4 ] cycle (2): [
   * 1, 2, 3, 4 ] cycle (3): [ 1, 3, 4 ] Lastly, cycles that have the same number of vertices are
   * ordered by the sum of the vertex values, with the minimum vertex sum appearing first. For
   * example, the result shown below is NOT correct because 1+3+4 > 1+2+4 cycle (1): [ 1, 3, 4 ]
   * cycle (2): [ 1, 2, 4 ] cycle (3): [ 1, 2, 3, 4 ]
   * <p>
   * 4) You may add additional methods or private instance variables to this class to support your
   * solution (i.e. if it is recursive). However, you MUST use the provided adjacency list and the
   * vertex class, and you MAY NOT create an additional class.
   * <p>
   * 5) If there are no vertices in the adjacency list OR the graph is not connected the method
   * would simply return the following string values exactly (including case): - no vertices in the
   * adjacency list (i.e. graph is empty) then return the string "empty graph". - the graph is not
   * connected then return the string "not connected".
   *
   * @author Rex Ferrer
   */
  public String findAllCycles() {

    // ----------------------------
    // DONE:Add your code here
    // ----------------------------
    if (this.adjacency_list.isEmpty()) {
      return "empty graph";
    }

    if (!this.isConnected()) {
      return "not connected";
    }

    // n > 3 for cycles to be present
    if (this.adjacency_list.size() < 3) {
      return "Graphs of less than 3 vertices can't have cycles";
    }

    TreeSet<TreeSet<Vertex<Object>>> cycles = new TreeSet<>(cycleOrder);
//    graphVisitReset();

    for (Vertex<Object> vertex : this.adjacency_list) {
      graphVisitReset();
      //Reset the cycle at each new start_vertex
      java.util.Stack<Vertex<Object>> cycle = new java.util.Stack<>();

      Vertex<Object> start_vertex = vertex;

      // Order of visits
      int count = 0;

      findAllCycles(count, start_vertex, start_vertex, cycles, cycle);
      graphVisitReset();

      //Reset visit counts to -1

    }

    return printCycles(cycles);
  } // end findAllCycles()

  /**
   * @author Rex Ferrer
   */
  private void findAllCycles(int count, Vertex<Object> start_vertex,
      Vertex<Object> current_vertex,
      TreeSet<TreeSet<Vertex<Object>>> cycles, java.util.Stack<Vertex<Object>> cycle) {

    //Skips over vertices with only one adjacent vertex
    if (isLeaf(current_vertex)) {
      current_vertex.setVisited(++count);
      return;
    }

//    System.out.printf("Current: %s %d\n", current_vertex, current_vertex.getVisited());
//    System.out.printf("Cycle: %s\n", cycle);
    if (current_vertex.hasBeenVisited()) {

      // Checks for cycles beginning at the start_vertex
      if (cycle.contains(current_vertex) && current_vertex.compareTo(start_vertex) == 0) {

        // Ensures the cycle is length 3 or higher
        if (cycle.size() > 2) {

          //Current cycle gets converted to TreeSet and added to cycles
          TreeSet<Vertex<Object>> valid_cycle = new TreeSet<>(cycle);
          cycles.add(valid_cycle);
        }

        //Checks for cycles not beginning at the start_vertex
      } else if (cycle.contains(current_vertex) && current_vertex.compareTo(start_vertex) != 0) {

        // Ensures the cycle is length 3 or higher
        if (cycle.subList(cycle.indexOf(current_vertex), cycle.size()).size() > 2) {
          //Current cycle gets converted to TreeSet and added to cycles
          TreeSet<Vertex<Object>> valid_cycle = new TreeSet<>(
              cycle.subList(cycle.indexOf(current_vertex), cycle.size()));
          cycles.add(valid_cycle);
        }
      }
      //Vertex is added to cycle stack
    } else {

      cycle.push(current_vertex);
      current_vertex.setVisited(++count);

      //Iterate through the current vertex adjacent vertices
      for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {
        findAllCycles(count, start_vertex, current_vertex.getAdjacentVertex(i), cycles, cycle);
      }

      if (!cycle.isEmpty()) {
        cycle.pop();
      }
    }
  }

  public boolean add(Vertex<Object> vertex) {
    if (!this.adjacency_list.contains(vertex)) {
      this.adjacency_list.add(vertex);
      return true;
    }
    return false;
  }

  /**
   * Add an edge between two vertices
   */
  public boolean addEdge(Object vertex_value_A, Object vertex_value_B) throws VertexException {

    // ------------------------------------
    // Basic steps:
    // ------------------------------------
    // 1) For Vertex A and B, check to see if the vertex exists in the
    //    adjacency_list. If not, then add new vertex (with given value)
    //    to the adjacency_list.
    // 2) In the Vertex class, if Vertex B is in Vertex A's
    //    adjacent_vertices and vice versa (i.e. an edge exists). If an
    //    edge already exists, then return false, otherwise goto step 3.
    // 3) Add Vertex B to Vertex A's adjacent_vertices and vice versa.
    //    Lastly, return true indicating the edge was successfully added.

    //
    Vertex<Object> vertexA = new Vertex<>(vertex_value_A);
    Vertex<Object> vertexB = new Vertex<>(vertex_value_B);

    int vertexA_exists = -1;
    int vertexB_exists = -1;

    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(vertexA) == 0) {
        vertexA_exists = i;
      }

      if (adjacency_list.get(i).compareTo(vertexB) == 0) {
        vertexB_exists = i;
      }

    }

    // If it doesn't exist
    if (vertexA_exists == -1) {
      adjacency_list.add(vertexA);
    } else {
      vertexA = adjacency_list.get(vertexA_exists);
    }

    // If it doesn't exist
    if (vertexB_exists == -1) {
      adjacency_list.add(vertexB);
    } else {
      vertexB = adjacency_list.get(vertexB_exists);
    }

    // Step 2
    // // For Troubleshooting
//    System.out.println(
//        "Graph A:" + vertexA.hasAdjacentVertex(vertexB) + " " + vertexA + " has " + vertexB);
//    System.out.println(vertexA.getAdjacentVertices());
//    System.out.println(
//        "Graph B:" + vertexB.hasAdjacentVertex(vertexA) + " " + vertexB + " has " + vertexA);
//    System.out.println(vertexB.getAdjacentVertices());

    if (vertexA.hasAdjacentVertex(vertexB) && vertexB.hasAdjacentVertex(vertexA)) {
      return false;
    } else {
      vertexA.addAdjacentVertex(vertexB);

      // Prevents an error when vertexA == vertexB aka self loop
      if (vertexA.compareTo(vertexB) != 0) {
        vertexB.addAdjacentVertex(vertexA);
      }
    }

    return true;

  } // end addEdge() method


  /**
   * Remove the edge that connects two vertices
   */
  public boolean removeEdge(Object vertex_value_A, Object vertex_value_B) throws VertexException {

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

    Vertex<Object> vertexA = new Vertex<>(vertex_value_A);
    Vertex<Object> vertexB = new Vertex<>(vertex_value_B);

    int vertexA_exists = -1;
    int vertexB_exists = -1;

    // -----------------------------------------------------------------
    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(vertexA) == 0) {
        vertexA_exists = i;
      }

      if (adjacency_list.get(i).compareTo(vertexB) == 0) {
        vertexB_exists = i;
      }

    }

    // -----------------------------------------------------------------
    // Step 2
    if (vertexA_exists == 1 || vertexB_exists == -1) {
      return false;
    }

    vertexA = adjacency_list.get(vertexA_exists);
    vertexB = adjacency_list.get(vertexB_exists);

    // -----------------------------------------------------------------
    // Step 3
    if (!vertexA.hasAdjacentVertex(vertexB) || !vertexB.hasAdjacentVertex(vertexA)) {
      return false;
    } else {

      vertexA.removeAdjacentVertex(vertexB);
      vertexB.removeAdjacentVertex(vertexA);

    }

    // -----------------------------------------------------------------
    // Step 4
    if (vertexA.numberOfAdjacentVertices() == 0) {
      adjacency_list.remove(vertexA_exists);
    }

    if (vertexB.numberOfAdjacentVertices() == 0) {
      adjacency_list.remove(vertexB_exists);
    }

    return true;

  } // end removeEdge() method

  /**
   * Depth first search (DFS) using stack data structure. Specifically, the ConstantTimeStack
   * class.
   * <p>
   * Return a String that shows when each vertex was visited during the DFS. String format: <Vertex
   * Value>:<Visited Count>\n
   * <p>
   * Notes: (1) Mark each vertex as not visited before the the search begins. (2) If the
   * start_vertex value does not exist in the undirected graph throw a new VertexException. (3)
   * Vertices are pushed onto the Stack in the same order they were added to the adjacent_vertices
   */
  public String depthFirstSearch(Object start_vertex) throws VertexException {

    StringBuilder buffer = new StringBuilder();

    Vertex<Object> startVertex = new Vertex<>(start_vertex);

    int vertex_exists = -1;

    // Determines if the start vertex exists
    for (int i = 0; i < this.adjacency_list.size(); i++) {

      if (this.adjacency_list.get(i).compareTo(startVertex) == 0) {
        vertex_exists = i;
      }

    }

    if (vertex_exists == -1) {
      throw new VertexException(String.format("Vertex %s does not exist!", startVertex));
    }

    this.graphVisitReset();

    int count = 0;

    startVertex = this.adjacency_list.get(vertex_exists);

    Stack<Vertex<Object>> stack = new ConstantTimeStack<>();

    stack.push(startVertex);

    try {

      while (true) {

        Vertex<Object> current_vertex = stack.peek();

        // If the curr vertex has not been visited
        if (!current_vertex.hasBeenVisited()) {

          current_vertex.setVisited(++count);

          //Push all adjacent vertices to the stack
          for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {

            stack.push(current_vertex.getAdjacentVertex(i));

          }
          // Pop the vertex if it's been visited
        } else {
          stack.pop();
        }

      }

      // DFS is done
    } catch (EmptyStackException emptyStack) {

      //
      for (int i = 0; i < this.adjacency_list.size(); i++) {
        buffer.append(String.format("%s:%d\n", this.adjacency_list.get(i),
            this.adjacency_list.get(i).getVisited()));
      }
    }
    return buffer.toString();
  } // end depthFirstSearch() method

  /**
   * @author Rex Ferrer
   */
  public void recursiveDFS() {
    this.graphVisitReset();

    int count = 0;

    for (Vertex<Object> vertex : this.adjacency_list) {
      if (!vertex.hasBeenVisited()) {
        rDFS(vertex, count);
      }
    }

    this.printSearchPath();

    this.graphVisitReset();
  }

  /**
   * @author Rex Ferrer
   */
  private void rDFS(Vertex<Object> vertex, int count) {
    vertex.setVisited(++count);
//    System.out.printf("%s : %d \n", vertex.toString(), vertex.getVisited());

    for (int i = 0; i < vertex.numberOfAdjacentVertices(); i++) {
      if (!vertex.getAdjacentVertex(i).hasBeenVisited()) {
        rDFS(vertex.getAdjacentVertex(i), count);
      }
    }
  }


  /**
   * Breadth first search (BFS) using queue data structure. Specifically, the ConstantTimeQueue
   * class
   * <p>
   * Return a String that shows when each vertex was visited during the BFS. String format: <Vertex
   * Value>:<Visited Count>\n
   * <p>
   * Notes: (1) Mark each vertex as not visited before the search begins. (2) If the start_vertex
   * value does not exist throw a new VertexException. (4) Vertices are added to the Queue in the
   * same order they were added to the adjacent_vertices
   */
  public String breadthFirstSearch(Object start_vertex) throws VertexException {

    StringBuilder buffer = new StringBuilder();

    Vertex<Object> startVertex = new Vertex<>(start_vertex);

    int vertex_exists = -1;

    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(startVertex) == 0) {
        vertex_exists = i;
      }

    }

    if (vertex_exists == -1) {
      throw new VertexException(String.format("Vertex %s does not exist!", startVertex));
    }

    this.graphVisitReset();

    int count = 0;

    startVertex = this.adjacency_list.get(vertex_exists);

    Queue<Vertex<Object>> queue = new ConstantTimeQueue<>();

    queue.add(startVertex);

    try {

      while (true) {

        Vertex<Object> current_vertex = queue.remove();

        if (!current_vertex.hasBeenVisited()) {

          current_vertex.setVisited(++count);

          for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {

            queue.add(current_vertex.getAdjacentVertex(i));

          }

        }

      }

    } catch (NoSuchElementException emptyQueue) {

      for (Vertex<Object> vertex : adjacency_list) {

        buffer.append(String.format("%s:%d\n", vertex,
            vertex.getVisited()));

      }

    }

    return buffer.toString();

  } // end breadthFirstSearch() method

  /**
   * @author Rex Ferrer
   */
  public void iterativeBFS() {
    this.graphVisitReset();
    int count = 0;
    LinkedList<Vertex<Object>> queue = new LinkedList<>();

    Vertex<Object> current = this.adjacency_list.get(0);
    current.setVisited(++count);
    queue.add(current);

    while (!queue.isEmpty()) {
      current = queue.pollFirst();

      for (int i = 0; current != null && i < current.numberOfAdjacentVertices(); i++) {
        Vertex<Object> adjacentVertex = current.getAdjacentVertex(i);

        if (!adjacentVertex.hasBeenVisited()) {
          queue.add(adjacentVertex);
          adjacentVertex.setVisited(++count);
        }

      }
    }
    this.printSearchPath();

    this.graphVisitReset();
  }

  /**
   * Recursive breadth first search that starts at the adjacency list's first vertex
   *
   * @author Rex Ferrer
   */
  public void recursiveBFS() {
    this.graphVisitReset();
    LinkedList<Vertex<Object>> queue = new LinkedList<>();
    int count = 0;

    for (Vertex<Object> vertex : this.adjacency_list) {

      if (!vertex.hasBeenVisited()) {
        Vertex<Object> current = vertex;
        current.setVisited(++count);
        queue.add(current);
        rBFS(queue, count);
      }
    }

    this.printSearchPath();
    this.graphVisitReset();
  }

  /**
   * Helper function for recursiveBFS
   *
   * @param queue Enables level traversal
   * @param count Tracks order of traversal
   * @author Rex Ferrer
   */
  private void rBFS(LinkedList<Vertex<Object>> queue, int count) {
    if (queue.isEmpty()) {
      return;
    }

    Vertex<Object> vertex = queue.pollFirst();

    for (int i = 0; vertex != null && i < vertex.numberOfAdjacentVertices(); i++) {
      Vertex<Object> adjacentVertex = vertex.getAdjacentVertex(i);
      if (!adjacentVertex.hasBeenVisited()) {
        adjacentVertex.setVisited(++count);
        queue.add(adjacentVertex);
      }
    }

    rBFS(queue, count);
  }

  /**
   *
   */
  public void clear() {
    adjacency_list.clear();
  } // end clear()


  /**
   * convert adjacency list to string
   */
  public String toString() {

    StringBuilder buffer = new StringBuilder();

    for (Vertex<Object> vertex : this.adjacency_list) {

      buffer.append(vertex.printVertex());

    }

    return buffer.toString();

  } // end toString() method

  /**
   * Comparator that will enable cycles TreeSet object to sort it's cycle TreeSet objects by length
   * and/or sum of the vertices
   *
   * @author Rex Ferrer
   */
  private final Comparator<TreeSet<Vertex<Object>>> cycleOrder = (cycle1, cycle2) -> {
    if (cycle1.size() > cycle2.size()) {
      return 1;
    } else if (cycle1.size() < cycle2.size()) {
      return -1;
    } else {
      Integer sum1 = 0;
      Integer sum2 = 0;

      for (Vertex<Object> vertex : cycle1) {
        sum1 += (Integer) vertex.getValue();
      }

      for (Vertex<Object> vertex : cycle2) {
        sum2 += (Integer) vertex.getValue();
      }

      return Integer.compare(sum1, sum2);
    }
  };

  /**
   * @return [ <comma delimited sequence of vertex numbers> ]\n
   * @author Rex Ferrer
   */
  private String printCycle(TreeSet<Vertex<Object>> cycle) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("[ ");

    int index = 0;
    for (Vertex<Object> vertex : cycle) {
      if (index == cycle.size() - 1) {
        buffer.append(String.format("%s", vertex));
      } else {
        buffer.append(String.format("%s, ", vertex));
      }
      index++;
    }
    buffer.append(" ]\n");

////    Test
//    System.out.println(buffer);

    return buffer.toString();
  }


  /**
   * @return cycle(< cycle_number >): [ <comma delimited sequence of vertex numbers> ]\n
   * @author Rex Ferrer
   */
  public String printCycles(TreeSet<TreeSet<Vertex<Object>>> cycles) {
    StringBuilder cycle_builder = new StringBuilder();
    int cycle_count = 1;
    for (TreeSet<Vertex<Object>> cycle : cycles) {
      cycle_builder.append(String.format("cycle (%d): %s\n", cycle_count, printCycle(cycle)));

      cycle_count++;
    }

    return cycle_builder.toString();
  }

  /**
   * Prints path order
   *
   * @author Rex Ferrer
   */
  private void printSearchPath() {
    for (int i = 0; i < this.adjacency_list.size(); i++) {
      Vertex<Object> curr = this.adjacency_list.get(i);
      System.out.printf("%s : %d \n", curr.toString(), curr.getVisited());
    }
  }

  /**
   * @author Rex Ferrer
   */
  private boolean isLeaf(Vertex<Object> vertex) {
    return vertex.numberOfAdjacentVertices() <= 1;
  }


  /**
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   */
  public int edgeCount() {
    int edges = 0;

    for (Vertex<Object> vertex : this.adjacency_list) {
      edges += vertex.numberOfAdjacentVertices();
    }
    return edges / 2;
  }


  /**
   * Computes the Strongly Connected Components of the UndirectedGraph.
   * <p>
   * CSCI310 Advanced Algorithms GraphADT Homework Assignment
   *
   * @return List of the strongly connected components
   */
  @Override
  public ArrayList<ArrayList<Vertex<Object>>> getStronglyConnectedComponents() {
    this.graphVisitReset();

    final ArrayList<ArrayList<Vertex<Object>>> stronglyConnectedComponents = new ArrayList<>();

    for (Vertex<Object> vertex : this.adjacency_list) {
      if (!vertex.hasBeenVisited()) {
        ArrayList<Vertex<Object>> component = new ArrayList<>();
        getStronglyConnectedComponentsHelper(vertex, component);
        stronglyConnectedComponents.add(component);
      }
    }
    this.graphVisitReset();

    return stronglyConnectedComponents;
  }

  /**
   * CSCI310 Advanced Algorithms
   * GraphADT Homework Assignment
   */
  private void getStronglyConnectedComponentsHelper(
      Vertex<Object> vertex,
      ArrayList<Vertex<Object>> component) {

    vertex.setVisited(1);
    component.add(vertex);

    for (Vertex<Object> adjacentVertex : vertex.getAdjacentVertices()) {

      if (!adjacentVertex.hasBeenVisited()) {
        getStronglyConnectedComponentsHelper(adjacentVertex, component);
      }
    }
  }


}