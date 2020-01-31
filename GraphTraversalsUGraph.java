import java.util.*;

public class GraphTraversalsUGraph{
    String[] nodes;
    String[] sequence;
    String[][] checkGraph;
    int nodeIndex = 0;
    String visted = "visited";

    public void depthFirst(String[][] graph, int vertices){

    }

    public void breadthFirst(String[][] graph, int vertices){
        int i;
        int j;

        nodes = new String[vertices];
        sequence = new String[vertices];
        checkGraph = graph;

        // 1 as first marked node
        for(i = 0; i < vertices; i++)
            for(j = 0; j < vertices; i++)
                if(graph[i][j] == "1" && checkGraph[i][j] != visited){
                    enqueue(Int.toString(i + 1));
                }
    }

    public void enqueue(String node){
        nodes[nodeIndex] = node;
        nodeIndex += 1;
    }
}