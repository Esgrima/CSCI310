package edu.cofc.csci310;

import java.util.Random;

public class GraphMain {
  // 10000 was taking too long. Removed 10000 and 100000
  protected static final int[] EXPERIMENT_SIZES = new int[]{10, 100, 1000};

  private static int getNumberOfEdges(int n){
    return (int) (.8 * (n * (n - 1) / 2));
  }

  protected static void initializeVertices(Graph<Integer> graph, int total){
    for(int i = 0; i < total; i++){
      graph.add(new Vertex<>(i));
    }
  }

  protected static void initializeEdges(Graph<Integer> graph, int total){
    // Step 2: Calculate # of edges, .8 * (n(n-1)/2)
    int numberOfEdgesToAdd = getNumberOfEdges(total);

    // Step 3: Randomly adds edges
    Random random = new Random();

    while (graph.edgeCount() < numberOfEdgesToAdd ){
      graph.addEdge(random.nextInt(total), random.nextInt(total));
    }
  }
}
