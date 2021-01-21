package edu.cofc.csci310;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex Class Definition
 * <p>
 * CSCI 230: Data Structures and Algorithms
 * <p>
 * Fall 2018
 *
 * @param <Object>
 */
public class Vertex<Object extends Comparable<Object>> implements Comparable<Vertex<Object>> {

  /** CSCI310 Advanced Algorithms
   *  GraphADT Homework Assignment
   *
   */
  public Color getColor() {
    return color;
  }

  /** CSCI310 Advanced Algorithms
   *  GraphADT Homework Assignment
   *
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /** CSCI310 Advanced Algorithms
   *  GraphADT Homework Assignment
   *
   */
  public enum Color {
    GREEN, GREY
  }

  // -------------------------------------
  // Constants
  public static final int NOT_VISITED = -1;
  public static final int NOT_FOUND = -1;

  private Object value;
  private Color color;

  // -------------------------------------
  // List to store adjacent vertices
  // -------------------------------------
  private final List<Vertex<Object>> adjacent_vertices = new ArrayList<>();

  // ----------------------------------------
  // visited variable
  private int visited = NOT_VISITED;


  /**
   * @param value
   */
  public Vertex(Object value) {

    this.value = value;

  } // end constructor



  /**
   * @param value
   */
  public void setValue(Object value) {

    this.value = value;

  } // end setValue() method

  /**
   * @return
   */
  public Object getValue() {

    return value;

  } // end getValue() method

  /**
   *
   */
  public void clear() {

    adjacent_vertices.clear();

  } // end clear() method

  /**
   * @return
   */
  public int numberOfAdjacentVertices() {

    return adjacent_vertices.size();

  } // end numberOfAdjacentVertices() method


  /**
   * @param vertex
   * @throws VertexException
   */
  public void addAdjacentVertex(Vertex<Object> vertex) throws VertexException {
    //// For troubleshooting
//    System.out.println(this.adjacent_vertices);
//    System.out.println("Contains: "+ this.adjacent_vertices.contains(vertex));
//    System.out.println("Vertex: " + this.hasAdjacentVertex(vertex));
    if (!this.hasAdjacentVertex(vertex)) {

      this.adjacent_vertices.add(vertex);

    } else {

      throw new VertexException(String.format("Vertex %s Exists\n", vertex));

    }

  } // end addAdjacentVertex() method


  /**
   * @param vertex
   * @throws VertexException
   */
  public void removeAdjacentVertex(Vertex<Object> vertex) throws VertexException {

    if (this.hasAdjacentVertex(vertex)) {

      adjacent_vertices.remove(this.getAdjacentVertexIndex(vertex));

    } else {

      throw new VertexException(String.format("Vertex %s is not adjacent\n", vertex));

    }

  } // end removeAdjacentVertex() method

  /**
   * @param index
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Vertex<Object> getAdjacentVertex(int index) throws IndexOutOfBoundsException {

    return this.adjacent_vertices.get(index);

  } // end getAdjacentVertex() method

  /**
   * @param vertex
   * @return
   * @throws IndexOutOfBoundsException
   */
  public boolean hasAdjacentVertex(Vertex<Object> vertex) throws IndexOutOfBoundsException {
    for (Vertex<Object> adjacent_vertex : this.adjacent_vertices) {
      if (adjacent_vertex.compareTo(vertex) == 0) {
        return true;
      }
    }
    return false;
  } // end hasAdjacentVertex() method


  /**
   * @param vertex
   * @return
   * @throws IndexOutOfBoundsException
   */
  public int getAdjacentVertexIndex(Vertex<Object> vertex) throws IndexOutOfBoundsException {

    for (int i = 0; i < adjacent_vertices.size(); i++) {

      if (adjacent_vertices.get(i).compareTo(vertex) == 0) {
        return i;
      }

    }

    return NOT_FOUND;

  } // end getAdjacentVertexIndex() method

  /**
   * @param visited
   */
  public void setVisited(int visited) {

    this.visited = visited;

  } // end setVisited() method

  /**
   * @return
   */
  public int getVisited() {

    return visited;

  } // end getVisited();

  /**
   * @return
   */
  public boolean hasBeenVisited() {

    return (visited != NOT_VISITED);

  } // end hasBeenVisited() method


  /**
   *
   */
  public String toString() {

    return value.toString();

  } // end toString() method


  /**
   *
   */
  public int compareTo(Vertex<Object> o) {

    return this.value.compareTo(o.getValue());

  } // end compareTo() method


  /**
   * @return
   */
  public String printVertex() {

    StringBuilder buffer = new StringBuilder();

    buffer.append(String.format("Vertex (%s): [ ", this));

    for (int i = 0; i < adjacent_vertices.size(); i++) {

      if (i == adjacent_vertices.size() - 1) {
        buffer.append(String.format("%s", adjacent_vertices.get(i)));
      } else {
        buffer.append(String.format("%s, ", adjacent_vertices.get(i)));
      }
    }

    buffer.append(" ]\n");

    return buffer.toString();

  } // end printVertex() method


  /** CSCI310 Advanced Algorithms
   *  GraphADT Homework Assignment
   *
   */
  public List<Vertex<Object>> getAdjacentVertices() {
    return this.adjacent_vertices;
  }
} // end Vertex class definition