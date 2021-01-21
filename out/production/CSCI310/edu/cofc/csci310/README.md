## CSCI310 Advanced Algorithms

### Graph ADT Assignment

#### File Structure Overview
- **Package**: edu.cofc.csci310

- support directory contains files for other data structures that were used to support the assignment
and not related to Graphs

- Graph.java (Original) abstract class that UndirectedGraph.java (Reused from CSCI230, but retrofitted significantly)
 and DirectedGraph.java (Original) extend. The new code in UndirectedGraph.java is towards the bottom
 and can be identified with:
 
            /**
            * CSCI310 Advanced Algorithms 
            * GraphADT Homework Assignment
            */
 
 This is also applies to most of the files.
 
- GraphMain.java (Original) abstract class provides shared code to UndirectedGraphMain.java (Original) 
and DirectedGraphMain.java (Original). Each of these effectively do the same, but I split it for 
readability and to run in isolation

- Vertex.java (Reused from CSCI230, but retrofitted)


##### Performance Issues: 
Experiment size of **10000** was taking too long. I stopped it after 40 minutes and removed 10000 
and 100000 from the code. 
- To view for yourself, add **10000** and **100000** to the constant EXPERIMENT_SIZES in Graph.java

Due to the randomness of initializing the graphs:
 1. there will seldomly be more than 1 strongly 
connected component. Repeated execution of DirectedGraphMain.java is capable of producing 2 strongly
connected components.


##### Instructions on Running
1. Unzip
2. Open in editor
3. Compile
4. Run either UndirectedGraphMain.java or DirectedGraphMain.java

Notes: Ctrl + Click on code to navigate to its implementation or usage.


