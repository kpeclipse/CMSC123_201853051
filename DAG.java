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

    public void showList(boolean weighted){}
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