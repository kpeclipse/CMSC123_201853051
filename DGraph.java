import java.util.*;

public class DGraph{
    int numberOfVertices;
    int numberOfEdges;

    String[][] uwGraph;
    int[][] wGraph;

    ArrayList<Node> vertices;
    ArrayList<Node> zeroInDegreeVertices;

    Scanner scanInt = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);

    public DGraph(){
        System.out.println("DIRECTED GRAPH");
    }

    public void showList(boolean weighted){
        if(numberOfVertices == 0)
            System.out.println("NO GRAPH");

        else{
            System.out.print("\nVertices: (");
        
            for(int i = 0; i < vertices.size() - 1; i++)
                System.out.print(vertices.get(i).name + ", ");
            System.out.print(vertices.get(vertices.size()-1).name + ")\n");

            // SHOW LIST OF VERTICES WITH IN-DEGREE OF ZERO
            System.out.print("Vertices with in-degree of ZERO: (");
            for(int x = 0; x < zeroInDegreeVertices.size() - 1; x++)
                System.out.print(zeroInDegreeVertices.get(x).name +",");
            System.out.print(zeroInDegreeVertices.get(zeroInDegreeVertices.size() - 1).name + ")\n");


            // ADJACENCY MATRIX --- Default display of graph
            System.out.println("\nADJACENCY MATRIX:");
            
            for(int i = 0; i <= numberOfVertices; i++){
                if(i == 0){
                    System.out.print("  ");
                    continue;
                }
                System.out.print(i + " ");
            }
        
            System.out.println("");

            for(int i = 0; i < numberOfVertices; i++){
                System.out.print((i + 1) + " ");

                for(int j = 0; j < numberOfVertices; j++){
                    if(vertices.get(i).adjacentVertices.contains(vertices.get(j))){
                        if(weighted == false)  
                            uwGraph[i][j] = "T";
                        System.out.print(uwGraph[i][j] + " ");
                    }

                    else{
                        if(weighted == false)
                            uwGraph[i][j] = "F";
                        System.out.print(uwGraph[i][j] + " ");
                    }
                }
                System.out.println("");
            }
        
            if(numberOfEdges == 0)
                System.out.println("\nNULL GRAPH (no edges)");

            else{
                // BINARY RELATION
                System.out.println("\nBINARY RELATION:");
                for(int i = 0; i < numberOfVertices; i++){
                    for(int j = 0; j < numberOfVertices; j++){
                        if(weighted == false){
                            if(uwGraph[i][j] == "T")
                                System.out.print("(" + vertices.get(i).name + ", " + vertices.get(j).name + ") ");
                        }
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

    public void checkAdjacency(boolean weighted){
        int i;
        int j;

        if(numberOfEdges == 0)
            System.out.println("\nNULL GRAPH (no edges)");  

        else{
            do{
                System.out.print("From what vertex: ");
                i = scanInt.nextInt();
            }while(i > numberOfVertices || i <= 0);
            
            do{
                System.out.print("To what vertex: ");
                j = scanInt.nextInt();
            }while(j > numberOfVertices || i < 0);
    
            if(weighted == false){
                if(uwGraph[i-1][j-1] == "T")
                    System.out.println("Vertex " + i + " and Vertex " + j + " are ADJACENT\n");
                else System.out.println("Vertex " + i + " and Vertex " + j + " are NOT ADJACENT\n");
            }
    
            else{
                if(wGraph[i-1][j-1] > 0)
                    System.out.println("Vertex " + i + " and Vertex " + j + " are ADJACENT\n");
                else System.out.println("Vertex " + i + " and Vertex " + j + " are NOT ADJACENT\n");
            }
        }
    }

    public void checkConnectedness(boolean weighted){
        int i;
        int j;

        if(numberOfEdges == 0)
            System.out.println("\nNULL GRAPH (no edges)");
        
        else{
            do{
                System.out.print("From what vertex: ");
                i = scanInt.nextInt();
            }while(i > numberOfVertices || i <= 0);
            
            do{
                System.out.print("To what vertex: ");
                j = scanInt.nextInt();
            }while(j > numberOfVertices || i < 0);
    
            if(weighted == false){
                if(uwGraph[i-1][j-1] == "T")
                    System.out.println("Vertex " + i + " and Vertex " + j + " are WEAKLY CONNECTED\n");
                else System.out.println("Vertex " + i + " and Vertex " + j + " are NOT CONNECTED\n");
            }
    
            else{
                if(wGraph[i-1][j-1] > 0)
                    System.out.println("Vertex " + i + " and Vertex " + j + " are WEAKLY CONNECTED\n");
                else System.out.println("Vertex " + i + " and Vertex " + j + " are NOT CONNECTED\n");
            }
        }
    }

    public void insertEdge(boolean weighted){
        int firstV = -1;
        int secondV = -1;
        boolean exists = false;

        numberOfEdges += 1;

        if(numberOfEdges > ((numberOfVertices * (numberOfVertices-1))/2)){
            System.out.println("MAXIMUM NUMBER OF EDGES REACHED!");
            numberOfEdges -= 1;
        }

        else{
            // SHOW LIST OF VERTICES
            System.out.print("Vertices: ");
            for(int x = 0; x < vertices.size(); x++)
                System.out.print(vertices.get(x).name + " ");
            
            do{
                // ASK FOR FIRST VERTEX
                do{
                    System.out.print("\nFrom what vertex? ");
                    String first = scanString.next();

                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(first)){
                            firstV = i;
                            exists = true;
                        }
                    }
                }while(exists == false);

                // ASK FOR SECOND VERTEX
                do{
                    System.out.print("To which vertex? ");
                    String second = scanString.next();

                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(second)){
                            secondV = i;
                            break;
                        }
                    }
                }while(secondV == -1);

            }while(uwGraph[firstV][secondV] == "T");

            if(weighted == false){
                uwGraph[firstV][secondV] = "T";

                vertices.get(firstV).addAdj(vertices.get(secondV));
                zeroInDegreeVertices.remove(vertices.get(secondV));
            }

            System.out.println("\nAn edge is inserted");
        }
    }

    public void removeEdge(boolean weighted){
        boolean exists = false;
        int firstV = -1;
        int secondV = -1;

        if(numberOfEdges == 0)
            System.out.println("NULL GRAPH! (no edges)");

        else{
            boolean again = true;
            // ASK FOR FIRST VERTEX
            do{
                do{
                    System.out.print("\nFrom what vertex? ");
                    String first = scanString.next();
        
                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(first)){
                            firstV = i;
                            exists = true;
                        }
                    }
                }while(exists == false);
            
                // ASK FOR SECOND VERTEX
                do{
                    System.out.print("To which vertex? ");
                    String second = scanString.next();
    
                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(second)){
                            secondV = i;
                            break;
                        }
                    }
                }while(secondV == -1);
        
                if(weighted == false){
                    if(uwGraph[firstV][secondV] == "F")
                        System.out.println("NO EDGE EXISTS");

                    else{
                        uwGraph[firstV][secondV] = "F";
                        vertices.get(firstV).removeAdj(vertices.get(secondV)); 
                        System.out.println("\nAn edge is removed");
                        numberOfEdges -= 1;
                        again = false;
                    }
                }
            }while(again == true);
        }
    }

    // OKAY NA
    public void insertVertex(boolean weighted){
        boolean exists;
        String name;

        numberOfVertices += 1;

        if(vertices == null){
            System.out.print("Name of Vertex: ");
            name = scanString.next();
            vertices = new ArrayList<Node>();
            zeroInDegreeVertices = new ArrayList<Node>();
            uwGraph = new String[numberOfVertices][numberOfVertices];
            uwGraph[0][0] = "F";
        }
        
        else{
            do{
                System.out.print("Name of Vertex: ");
                name = scanString.next();
                exists = false;
                
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(name))
                        exists = true;
                }
            }while(exists == true);
        
            if(weighted == false){
                String[][] uwTemp = uwGraph;
                uwGraph = new String[numberOfVertices][numberOfVertices];
        
                for(int i = 0; i < numberOfVertices; i++){
                    uwGraph[i][numberOfVertices-1] = "F";
                }
        
                for(int i = 0; i < numberOfVertices-1; i++)
                    for(int j = 0; j < numberOfVertices-1; j++)
                        uwGraph[i][j] = uwTemp[i][j];   
            }
        }
        
        Node addVertex = new Node(name);
        vertices.add(addVertex);
        System.out.println("\nVertex " + name + " is inserted");
    }

    // OKAY NA
    public void removeVertex(boolean weighted){
        boolean vertexInList = true;
        String vertex;

        if(numberOfVertices == 0)
            System.out.println("\nNO VERTEX");
        
        else if(numberOfVertices > 0){
            do{
                System.out.print("What vertex? ");
                vertex = scanString.next();
    
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(vertex)){
                        vertexInList = true;

                        for(int j = 0; j < vertices.size(); j++)
                            for(int k = 0; k < vertices.get(j).adjacentVertices.size(); k++)
                                if(vertices.get(j).adjacentVertices.contains(vertices.get(i))){
                                    vertices.get(j).adjacentVertices.remove(vertices.get(i));
                                    numberOfEdges -= 1;
                                }
                        vertices.remove(i);            
                        break;
                    }
                }
    
                if(vertexInList == false)
                    System.out.println("Vertex does not exist");
            }while(vertexInList == false);

            numberOfVertices -= 1;

            System.out.println("\nVertex "+ vertex + " is removed");
        }

        /*while(i < numberOfVertices){
            for(int j = 0; j < numberOfVertices; j++){
                if(weighted == false){
                    uwGraph[i-1][j] = uwGraph[i][j];
                    uwGraph[j][i-1] = uwGraph[j][i];
                }
                else wGraph[i-1][j] = wGraph[i][j];
            }
            i++;
        }*/

    }

    // Creates a new vertex here
    class Node{
        String name;
        ArrayList<Node> adjacentVertices = new ArrayList<Node>();

        public Node(String vertex){
            name = vertex;
        }
        
        public void addAdj(Node insert){
            adjacentVertices.add(insert);
        }

        public void removeAdj(Node remove){
            adjacentVertices.remove(remove);
        }
    }
}