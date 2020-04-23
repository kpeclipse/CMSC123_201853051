import java.util.*;

public class TopologicalSort {
    boolean[] visited;
    int[] indegree;
    int numberOfVertices;
    Stack<String> sequence = new Stack<String>();
    DGraph G;

    public TopologicalSort (DGraph G) {
        this.G = G;
        // If there are vertices
        if(this.G.vertices != null && this.G.vertices.size() > 0){
            initialize();
            tSort(visited, indegree, sequence);
        }
    }

    // Recursion
    public void tSort(boolean[] visited, int[] indegree, Stack<String> sequence){
        boolean allAreVisited = false;
        boolean thereIsZero = false;
        // CHECK IF THERE ARE NO IN-DEGREE OF ZEROES
        for(int i = 0; i < numberOfVertices; i++)
            if(indegree[i] == 0){
                thereIsZero = true;
                break;
            }

        if(thereIsZero == true){
            for (int i = 0; i < numberOfVertices; i++) { 
                // if vertex is unvisited and has an in-degree of zero 
                if (visited[i] == false && indegree[i] == 0) { 
                    visited[i] = true; 
                    sequence.push(G.vertices.get(i).name); 
                    
                    // Decrease the number of in-degrees connected to the visited vertex
                    for (int j = 0; j < numberOfVertices; j++) { 
                        // If the vertex has the current vertex as a parent
                        if(G.vertices.get(j).parents.contains(G.vertices.get(i)))
                            indegree[j]--;
                    }
                    /*
                    // Print the number of parents in each node
                    for(int x = 0; x < numberOfVertices; x++)
                        System.out.print(indegree[x] + " ");*/
                    tSort(visited, indegree, sequence); 
                    
                    // Reset for backtracking
                    visited[i] = false; 
                    sequence.pop(); 
                    for (int j = 0; j < numberOfVertices; j++) { 
                        if(G.vertices.get(j).parents.contains(G.vertices.get(i)))
                            indegree[j]++; 
                    } 
    
                    allAreVisited = true; 
                } 
            } 
            // We reach here if all vertices are visited. 
            // So we print the solution here 
            if (allAreVisited == false) { 
                sequence.forEach(i -> System.out.print(i + " ")); 
                System.out.println(); 
            }
        }

        // If there is no vertex with in-degree of zero then there is a cycle
        else System.out.print("GRAPH IS NOT DIRECTED ACYCLIC!");
    }

    public void initialize(){
        // Initialize vertices as unvisited
        numberOfVertices = G.vertices.size();
        visited = new boolean[numberOfVertices];
        //  Initialize In-Degree Vertices
        indegree = new int[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++){
            // If vertex has parent/s
            if(G.vertices.get(i).parents != null && G.vertices.get(i).parents.size() > 0)
                // Set its in-degree
                indegree[i] = G.vertices.get(i).parents.size();
            else indegree[i] = 0;
        }
    }
}