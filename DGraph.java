import java.util.*;

public class DGraph{
    int numberOfVertices;
    int numberOfEdges;
    String[][] uwGraph;
    int[][] wGraph;
    ArrayList<Node> vertices;
    ArrayList<Node> zeroInDegreeVertices;
    ArrayList<Edge> edges;
    Scanner scanInt = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);

    public DGraph(){
        System.out.println("DIRECTED GRAPH");
    }

    public void showList(boolean weighted){
        // If there are no vertices
        if(numberOfVertices == 0)
            System.out.println("NO GRAPH");

        // If there is at least one vertex
        else{
            // List of Vertices
            System.out.print("\nVertices In Order: (");
            for(int i = 0; i < vertices.size() - 1; i++)
                System.out.print(vertices.get(i).name + ", ");
            System.out.print(vertices.get(vertices.size()-1).name + ")\n");

            // List of Vertices with in-degree of zero
            if(zeroInDegreeVertices.size() > 0){
                System.out.print("Vertices with in-degree of ZERO: (");
                for(int x = 0; x < zeroInDegreeVertices.size() - 1; x++)
                    System.out.print(zeroInDegreeVertices.get(x).name +", ");
                System.out.print(zeroInDegreeVertices.get(zeroInDegreeVertices.size() - 1).name + ")\n");
            }

            // ADJACENCY MATRIX --- Default display of graph
            System.out.println("\nADJACENCY MATRIX: (Order is based on list of vertices)\n");
            for(int i = 0; i < numberOfVertices; i++){    
                System.out.print("\t");
                for(int j = 0; j < numberOfVertices; j++){
                    // If there is an edge
                    if(vertices.get(i).adjacentVertices.contains(vertices.get(j))){
                        // For Unweighted Graph
                        if(weighted == false){
                            uwGraph[i][j] = "T";
                            System.out.print("[" + uwGraph[i][j] + "]");
                        }
                        // For Weighted Graph
                        else{
                            for(int k = 0; k < edges.size(); k++){
                                if(edges.get(k).first == vertices.get(i) && edges.get(k).second == vertices.get(j))
                                    wGraph[i][j] = edges.get(k).value;
                            }
                            System.out.print("[" + wGraph[i][j] + "]");
                        }
                    }
                    // If there is no edge
                    else{
                        // For Unweighted Graph
                        if(weighted == false){
                            uwGraph[i][j] = "F";
                            System.out.print("[" + uwGraph[i][j] + "]");
                        }
                        // For Weighted Graph
                        else{
                            wGraph[i][j] = 0;
                            System.out.print("[" + wGraph[i][j] + "]");
                        }
                    }
                }
                System.out.println("");
            }
            
            // Show list of adjacent vertices
            if(numberOfEdges == 0)
                System.out.println("\nNULL GRAPH (no edges)");

            else{
                // NAIVE OR BINARY RELATION (V1, V2)
                System.out.println("\nBINARY RELATION:");
                for(int i = 0; i < numberOfVertices; i++){
                    for(int j = 0; j < numberOfVertices; j++){
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

    /*public void checkAdjacency(boolean weighted){
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
    }*/

    public void insertEdge(boolean weighted){
        int firstV = -1;
        int secondV = -1;
        boolean hasEdge = false;

        numberOfEdges += 1;

        // IF THE CURRENT NUMBER OF EDGES HAS REACHED THE MAXIMUM NUMBER OF EDGES FOR DIRECTED Graph
        if(numberOfEdges > (numberOfVertices * (numberOfVertices - 1))){
            System.out.println("MAXIMUM NUMBER OF EDGES REACHED!");
            numberOfEdges -= 1;
        }

        else{
            // Show list of vertices
            System.out.print("Vertices: ");
            for(int x = 0; x < vertices.size(); x++)
                System.out.print(vertices.get(x).name + " ");
            
            // Checks if an edge already exists between two vertices
            do{
                // Ask for first vertex
                do{
                    System.out.print("\nFrom what vertex? ");
                    String first = scanString.next();
                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(first)){
                            firstV = i;
                        }
                    }
                }while(firstV == -1);

                // Ask for second vertex
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

                // Check if edge exists in unweighted graph
                if(weighted == false){
                    if(uwGraph[firstV][secondV] == "T")
                        hasEdge = true;
                    else hasEdge = false;
                }

                // Check if edge exists in weighted graph
                else{
                    if(wGraph[firstV][secondV] > 0)
                        hasEdge = true;
                    else hasEdge = false;
                }
            }while(hasEdge == true || firstV == secondV);

            // Add edge for unweighted graph
            if(weighted == false)
                uwGraph[firstV][secondV] = "T";
            
            // Add edge for weigted graph
            else{
                // Ask for weight
                int value;
                do{
                    System.out.print("Weight of Edge between " + vertices.get(firstV).name + " and " + vertices.get(secondV).name + ": ");
                    value = scanInt.nextInt();                    
                }while(value <= 0);

                // Create new weighted edge
                Edge edge = new Edge(vertices.get(firstV), vertices.get(secondV), value);
                
                if(edges == null)
                    edges = new ArrayList<Edge>();

                // Add new edge to list of edges
                edges.add(edge);
                wGraph[firstV][secondV] = edge.value;
            }
            // Add second vertex to adjacency list of the first vertex
            vertices.get(firstV).addAdj(vertices.get(secondV));
            zeroInDegreeVertices.remove(vertices.get(secondV));

            System.out.println("\nAn edge is inserted");
        }
    }

    public void removeEdge(boolean weighted){
        int firstV = -1;
        int secondV = -1;

        // If there are no edges
        if(numberOfEdges == 0)
            System.out.println("NULL GRAPH! (no edges)");

        // If there is at least one edge
        else{
            boolean again = true;
            // If there is no edge
            do{
                // Ask for first vertex
                do{
                    System.out.print("\nFrom what vertex? ");
                    String first = scanString.next();
                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.contains(first)){
                            firstV = i;
                            break;
                        }
                    }
                }while(firstV == -1);
            
                // Ask for second vertex
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
        
                // Unweighted Graph
                if(weighted == false){
                    // No edge
                    if(uwGraph[firstV][secondV] == "F")
                        System.out.println("NO EDGE EXISTS");
                    // Edge exists
                    else{
                        uwGraph[firstV][secondV] = "F";
                        vertices.get(firstV).removeAdj(vertices.get(secondV)); 
                        System.out.println("\nAn edge is removed");
                        numberOfEdges -= 1;
                        again = false;
                    }
                }

                // Weighted Graph
                else{
                    // No edge
                    if(wGraph[firstV][secondV] == 0)
                        System.out.println("NO EDGE EXISTS");
                    // Edge exists
                    else{
                        wGraph[firstV][secondV] = 0;
                        vertices.get(firstV).removeAdj(vertices.get(secondV));
                        System.out.println("\nAn edge is removed");
                        numberOfEdges -= 1;
                        again = false;
                    }
                }
            }while(again == true);
        }
    }

    public void insertVertex(boolean weighted){
        boolean exists;
        String name;

        numberOfVertices += 1;

        // If there are no existing vertices
        if(vertices == null){
            System.out.print("Name of Vertex: ");
            name = scanString.next();
            vertices = new ArrayList<Node>();
            zeroInDegreeVertices = new ArrayList<Node>();
            
            // Create an unweighted directed graph
            if(weighted == false)
                uwGraph = new String[numberOfVertices][numberOfVertices];

            // Create a weighted directed graph
            else
                wGraph = new int[numberOfVertices][numberOfVertices];
        }

        // If there is at least one vertex
        else{
            // Checks if name of vertex already exists
            do{
                System.out.print("Name of Vertex: ");
                name = scanString.next();
                exists = false;
                
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(name))
                        exists = true;
                }
            }while(exists == true);
        
            // Adding new vertex to adjacency matrix -- Unweighted
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

            // Adding new vertex to adjacency matrix -- Weighted
            else{
                int[][] wTemp = wGraph;
                wGraph = new int[numberOfVertices][numberOfVertices];

                for(int i = 0; i < numberOfVertices; i++)
                    wGraph[i][numberOfVertices-1] = 0;
        
                for(int i = 0; i < numberOfVertices-1; i++)
                    for(int j = 0; j < numberOfVertices-1; j++)
                        wGraph[i][j] = wTemp[i][j];
            }
        }
        
        // Add to list of vertices
        Node addVertex = new Node(name);
        vertices.add(addVertex);
        zeroInDegreeVertices.add(addVertex);
        System.out.println("\nVertex " + name + " is inserted");
    }

    public void removeVertex(boolean weighted){
        boolean vertexInList = false;
        String vertex;

        // If there are no vertices at all
        if(numberOfVertices == 0)
            System.out.println("\nNO VERTEX");
        
        // There is at least one vertex
        else if(numberOfVertices > 0){
            do{
                System.out.print("What vertex? ");
                vertex = scanString.next();
    
                // Check if input is in the list of vertices
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(vertex)){
                        vertexInList = true;

                        // Checks if other vertices are adjacent to input
                        for(int j = 0; j < vertices.size(); j++){
                            if(vertices.get(j).adjacentVertices.contains(vertices.get(i))){
                                vertices.get(j).adjacentVertices.remove(vertices.get(i));
                            }
                        }
                        
                        // Removing the edges adjacent to the input from the list of edges
                        if(weighted == true){
                            for(int k = 0; k < edges.size(); k++){
                                if(edges.get(k).first == vertices.get(i) || edges.get(k).second == vertices.get(i)){
                                    edges.remove(k);
                                    k = -1;
                                    numberOfEdges -= 1;
                                }
                            }
                        }
                        
                        else{
                            for(int j = 0; j < vertices.size(); j++){
                                if(vertices.get(j).adjacentVertices.contains(vertices.get(i))){
                                    vertices.get(j).adjacentVertices.remove(vertices.get(i));
                                }
                                if(j == i)
                                    vertices.get(j).adjacentVertices.clear();

                            }
                        }

                        // Remove input from the list of vertices
                        zeroInDegreeVertices.remove(vertices.get(i));
                        vertices.remove(i);

                        numberOfEdges = 0;
                        for(int x = 0; x < vertices.size(); x++){
                            numberOfEdges += vertices.get(x).adjacentVertices.size();
                        }

                        numberOfVertices -= 1;

                        // Resetting vertices with in-degree of ZERO
                        zeroInDegreeVertices = vertices;
                        for(int x = 0; x < vertices.size(); x++)
                            for(int y = 0; y < vertices.size(); y++)
                                if(vertices.get(x).adjacentVertices.contains(vertices.get(y)))
                                    zeroInDegreeVertices.remove(vertices.get(y));
                    }                    
                }

                if(vertexInList == true){
                    if(weighted == false)
                        wGraph = new int[numberOfVertices][numberOfVertices];
                    else
                        uwGraph = new String[numberOfVertices][numberOfVertices];
                }

                else
                    System.out.println("Vertex does not exist");
            }while(vertexInList == false);

            System.out.println("\nVertex "+ vertex + " is removed");
        }
    }

    public void breadthFirst(){
        boolean[] visited;
        boolean exists = false;
        boolean allAreVisited = true;
        LinkedList<String> queue = new LinkedList<String>();
        int index = 0;
        
        // If there is at least one vertex
        if(numberOfVertices > 0){
            showListOfVertices();
            visited = new boolean[vertices.size()];

            // Checks if vertex exists
            do{
                // Ask for Source Vertex
                System.out.print("\nChoose Source Vertex: ");
                String vertex = scanString.next();

                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(vertex)){
                        exists = true;
                        visited[i] = true;  // Marked as visited vertex
                        index = i;
                        queue.add(vertices.get(i).name); // Enqueue source vertex in queue
                        break;
                    }
                }

                if(exists == true){
                    // BFS until all vertices are visited
                    do{
                        while(queue.size() > 0){
                            String print = queue.poll();
                            System.out.print(print + " ");
            
                            Iterator<Node> list = vertices.get(index).adjacentVertices.listIterator();
                           
                            while(list.hasNext()){
                                Node i = list.next();
                                
                                if(visited[vertices.indexOf(i)] == false){
                                    visited[vertices.indexOf(i)] = true;
                                    index = vertices.indexOf(i);
                                    queue.add(i.name);
                                }
                            }
                        }

                        // In Case Queue is Empty but there are unvisited vertices
                        allAreVisited = true;
                        for(int i = 0; i < visited.length; i++){
                            if(visited[i] == false){
                                allAreVisited = false;
                                visited[i] = true;
                                index = i;
                                queue.add(vertices.get(i).name); // Enqueue unvisited vertex
                                break;
                            }
                        }
                    }while(allAreVisited != true);
                }
            }while(exists == false);
        }

        else System.out.println("\nNO VERTEX\n");
    }

    public void depthFirst(){
        boolean[] visited;
        boolean exists = false;
        boolean allAreVisited = true;
        Stack<Node> stack = new Stack<Node>();

        if(numberOfVertices > 0){
            showListOfVertices();
            visited = new boolean[vertices.size()];

            do{
                // Ask for Source Vertex
                System.out.print("\nChoose Source Vertex: ");
                String vertex = scanString.next();

                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(vertex)){
                        exists = true;
                        visited[i] = true;
                        stack.push(vertices.get(i));
                        break;
                    }
                }

                if(exists == true){
                    do{
                        while(stack.size() > 0){
                            Node pop = stack.pop();
                            System.out.print(pop.name + " ");
    
                            Iterator<Node> list = pop.adjacentVertices.listIterator();
    
                            while(list.hasNext()){
                                Node push = list.next();
                                if(visited[vertices.indexOf(push)] == false){
                                    stack.push(push);
                                    visited[vertices.indexOf(push)] = true;
                                }
                            }
                        }
                        
                        if(stack.isEmpty()){
                            allAreVisited = true;
                            for(int i = 0; i < visited.length; i++)
                                if(visited[i] == false){
                                    allAreVisited = false;
                                    stack.push(vertices.get(i));
                                    break;
                                }
                        }
                    }while(allAreVisited == false);
                }
            }while(exists == false);

        }

        else System.out.println("\nNO VERTEX\n");        
    }

    public void showListOfVertices(){
        System.out.print("\nVertices In Order: (");
        for(int i = 0; i < vertices.size() - 1; i++)
            System.out.print(vertices.get(i).name + ", ");
        System.out.print(vertices.get(vertices.size()-1).name + ")\n");
    }
}