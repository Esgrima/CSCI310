package edu.cofc.csci310;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/** CSCI310 Advanced Algorithms
 *  GraphADT Homework Assignment
 */
public class UndirectedGraphMain extends GraphMain {
  public static void main(String[] args) {

    for (int graphSize: EXPERIMENT_SIZES) {
      // Part 1 Set-up, Check if every vertex has an edge
      UndirectedGraph<Integer> graph = new UndirectedGraph<>();

      initializeVertices(graph, graphSize);
      initializeEdges(graph, graphSize);

      System.out.println("Graph Size: " + graph.nodeCount());
      System.out.println("# of Edges: " + graph.edgeCount());
      System.out.printf("Every Vertex has an edge?: %b\n", graph.eachVertexHasEdge());

      // Part 2 Strongly Connected Components
      Instant start = Instant.now();
      ArrayList<ArrayList<Vertex<Integer>>> stronglyConnectedComponents = graph.getStronglyConnectedComponents();
      Duration duration = Duration.between(start, Instant.now());
      System.out.println("Strongly Connected Components: " + stronglyConnectedComponents);
      System.out.println("Number of Strongly connected Components: " + stronglyConnectedComponents.size());
      System.out.println("Duration of computation: " + duration.toMillis() + " millisecond(s)");


      // Part 3 Determine if the graph is bipartite
      // Approach 1: Color / Chromatic number == 2
      System.out.println("Graph is bipartite?: " + graph.isBipartite());

      // Approach 2: (TODO) Check for odd length cycles
//      cycles = graph.getCycles();

      System.out.println("-----------------------------------------------------------------------");
    }


  }
}
