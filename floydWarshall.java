public class floydWarshall{
    double[][] dist;
    int V;
    double MAX = Double.POSITIVE_INFINITY;

    public floydWarshall(DGraph G){
        V = G.vertices.size();
        dist = new double[V][V];
        // If graph is unweighted
        if(G.wGraph == null)
            System.out.print("GRAPH IS UNWEIGHTED!");
        
        else{
            initialize(G.wGraph);

            // Algorithm
            for(int k = 0; k < V; k++){
                // Source Vertex
                for(int i = 0; i < V; i++)
                    // Destination Vertex
                    for(int j = 0; j < V; j++)
                        if(dist[i][j] > dist[i][k] + dist[k][j])
                            dist[i][j] = dist[i][k] + dist[k][j];
            }

            showMatrix();
        }
    }

    public void initialize(double[][] wGraph){
        for(int i = 0; i < V; i++)
            for(int j = 0; j < V; j++){
                if(wGraph[i][j] > 0)
                    dist[i][j] = wGraph[i][j];
                else dist[i][j] = MAX;
            }
    }

    public void showMatrix(){
        System.out.println("\nFLOYD WARSHALL ALGORITHM: (Matrix Below)\n");

        for(int i = 0; i < V; i++){    
            System.out.print("\t");
            for(int j = 0; j < V; j++){
                if(dist[i][j] != MAX)
                    System.out.print("[" + dist[i][j] + "]");
                else System.out.print("[INF]");
            }
            System.out.println();
        }
    }
}