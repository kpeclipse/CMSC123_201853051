import java.util.*;

public class DAG{
    Scanner scanInt = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);
    ArrayList<Node> vertices;
    ArrayList<Edge> edges;
    String[][] uwDAG;
    int[][] wDAG;

    public DAG(){
        System.out.println("\nDIRECTED ACYCLIC GRAPH");
    }

    public void showList(boolean weighted){
        // If there are no vertices
        if(vertices.size() == 0 || vertices == null)
            System.out.println("NO GRAPH");

        // If there is at least one vertex
        else{
            // List of Vertices
            System.out.print("\nVertices In Order: (");
            for(int i = 0; i < vertices.size() - 1; i++)
                System.out.print(vertices.get(i).name + ", ");
            System.out.print(vertices.get(vertices.size()-1).name + ")\n");

            // ADJACENCY MATRIX --- Default display of graph
            System.out.println("\nADJACENCY MATRIX: (Order is based on list of vertices)\n");
            for(int i = 0; i < vertices.size(); i++){    
                System.out.print("\t");
                for(int j = 0; j < vertices.size(); j++){
                    // If there is an edge
                    if(vertices.get(i).adjacentVertices.contains(vertices.get(j))){
                        // For Unweighted Graph
                        if(weighted == false){
                            uwDAG[i][j] = "T";
                            System.out.print("[" + uwDAG[i][j] + "]");
                        }
                        // For Weighted Graph
                        else{
                            for(int k = 0; k < edges.size(); k++){
                                if(edges.get(k).first == vertices.get(i) && edges.get(k).second == vertices.get(j))
                                    wDAG[i][j] = edges.get(k).value;
                            }
                            System.out.print("[" + wDAG[i][j] + "]");
                        }
                    }
        
                    // If there is no edge
                    else{
                        // For Unweighted Graph
                        if(weighted == false){
                            uwDAG[i][j] = "F";
                            System.out.print("[" + uwDAG[i][j] + "]");
                        }
                        // For Weighted Graph
                        else{
                            wDAG[i][j] = 0;
                            System.out.print("[" + wDAG[i][j] + "]");
                        }
                    }
                }
                System.out.println("");
            }


            // Show list of adjacent vertices

            if(edges.size() == 0)

                System.out.println("\nNULL GRAPH (no edges)");


            else{
    // NAIVE OR BINARY RELATION (V1, V2)
    System.out.println("\nBINARY RELATION:");
    for(int i = 0; i < vertices.size(); i++){
        for(int j = 0; j < vertices.size(); j++){
            if(vertices.get(i).adjacentVertices.contains(vertices.get(j)))
                System.out.print("(" + vertices.get(i).name + ", " + vertices.get(j).name + ") ");
        }
    }
    
    // ADJACENCY LIST
    System.out.println("\n\nADJACENCY LIST:\n");
    for(int i = 0; i < vertices.size(); i++){
        System.out.print(vertices.get(i).name + " * ");
        for(int j = 0; j < vertices.get(i).adjacentVertices.size(); j++){
            System.out.print("--> " + vertices.get(i).adjacentVertices.get(j).name + " ");
        }
        System.out.println();       
    }
}
}




    }
    public void insertEdge(boolean weighted){}
    public void removeEdge(boolean weighted){}

    public String nameOfVertex(){
        System.out.print("Name of Vertex: ");
        return scanString.next();
    }
    
    public void insertVertex(boolean weighted){
        boolean exists;
        String name;

        // If there are no existing vertices
        if(vertices == null){
            name = nameOfVertex();
            vertices = new ArrayList<Node>();
            
            // Create an unweighted DAG
            if(weighted == false)
                uwDAG = new String[vertices.size()][vertices.size()];
            // Create a weighted DAG
            else wDAG = new int[vertices.size()][vertices.size()];
        }
        
        // If there is at least one vertex
        else{
            // Checks if name of vertex already exists
            do{
                name = nameOfVertex();
                exists = false;
                
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(name))
                        exists = true;
                }
            }while(exists == true);

            // Adding new vertex to adjacency matrix -- Unweighted
            if(weighted == false){
                String[][] uwTemp = uwDAG;
                uwDAG = new String[vertices.size() + 1][vertices.size() + 1];
        
                for(int i = 0; i < vertices.size() + 1; i++)
                    uwDAG[i][vertices.size()] = "F";
        
                for(int i = 0; i < vertices.size(); i++)
                    for(int j = 0; j < vertices.size(); j++)
                        uwDAG[i][j] = uwTemp[i][j];   
            }

            // Adding new vertex to adjacency matrix -- Weighted
            else{
                int[][] wTemp = wDAG;
                wDAG = new int[vertices.size() + 1][vertices.size() + 1];

                for(int i = 0; i < vertices.size() + 1; i++)
                    wDAG[i][vertices.size()] = 0;
        
                for(int i = 0; i < vertices.size(); i++)
                    for(int j = 0; j < vertices.size(); j++)
                        wDAG[i][j] = wTemp[i][j];
            }
        }
        
        // Add the new vertex to the list of vertices
        Node addVertex = new Node(name);
        vertices.add(addVertex);
        System.out.println("\nVertex " + name + " is inserted");
    }
    public void removeVertex(boolean weighted){}
    public void showListOfVertices(){}

    
}