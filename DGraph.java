import java.util.*;

public class DGraph{
    String[][] uwGraph;
    double[][] wGraph;
    ArrayList<Vertex> vertices;
    ArrayList<Edge> edges;
    ArrayList<Vertex> zeroInDegreeVertices;
    Scanner scanInt = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);
    Scanner scanDouble = new Scanner(System.in);
    Edge edge;
    boolean ExecutionTime;
    CPResult criticalPath;

    public DGraph(){
        System.out.println("\nDIRECTED GRAPH");
    }

    public void showList(boolean weighted, boolean withET){
        // If there are no vertices
        if(vertices == null || vertices.size() == 0)
            System.out.println("NO GRAPH");

        // If there is at least one vertex
        else{
            showListOfVertices(withET);
            adjacencyMatrix(weighted);
        }

        // Show list of adjacent vertices
        if(edges == null || edges.size() == 0)
            System.out.println("\nNULL GRAPH (no edges)");

        else{
            // NAIVE OR BINARY RELATION
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
    
    public void checkAdjacency(boolean withET){
        Integer firstV = null;
        Integer secondV = null;

        if(vertices == null || vertices.size() == 0)
            System.out.println("NO VERTEX");
        
        else if(edges == null || edges.size() == 0)
            System.out.println("NULL GRAPH! (no edges)");
        
        else{
            showListOfVertices(withET);

            // Ask for first vertex
            do{
                System.out.print("\nFrom what vertex? ");
                String first = scanString.next();
                for(int i = 0; i < vertices.size(); i++)
                    if(vertices.get(i).name.matches(first)){
                        firstV = i;
                        break;
                    }
            }while(firstV == null);

            // Ask for second vertex
            do{
                System.out.print("To which vertex? ");
                String second = scanString.next();
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.matches(second)){
                        secondV = i;
                        break;
                    }
                }
            }while(secondV == null);

            if(vertices.get(firstV).adjacentVertices.contains(vertices.get(secondV))){
                System.out.println("Vertex " + vertices.get(firstV).name + " and Vertex " + vertices.get(secondV).name + " are adjacent!");
                System.out.println("Edge: (" + vertices.get(firstV).name + ", " + vertices.get(secondV).name + ")");
            }

            else System.out.println("Vertex " + vertices.get(firstV).name + " and Vertex " + vertices.get(secondV).name + " are NOT adjacent!");
        }
    }

    public void checkConnectedness(boolean withET){
        Integer firstV = null;
        Integer secondV = null;

        if(vertices == null || vertices.size() == 0)
            System.out.println("NO VERTEX");
        
        else if(edges == null || edges.size() == 0)
            System.out.println("NULL GRAPH! (no edges)");
        
        else{
            showListOfVertices(withET);

            // Ask for first vertex
            do{
                System.out.print("\nFrom what vertex? ");
                String first = scanString.next();
                for(int i = 0; i < vertices.size(); i++)
                    if(vertices.get(i).name.matches(first)){
                        firstV = i;
                        break;
                    }
            }while(firstV == null);

            // Ask for second vertex
            do{
                System.out.print("To which vertex? ");
                String second = scanString.next();
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.matches(second)){
                        secondV = i;
                        break;
                    }
                }
            }while(secondV == null);

            // do Breadth-First
            boolean[] visited = new boolean[vertices.size()];
            Queue<Vertex> queue = new LinkedList<Vertex>();
            queue.add(vertices.get(firstV));
            visited[firstV] = true;

            while(queue.size() > 0){
                Vertex dequeue = queue.remove();

                Iterator<Vertex> list = dequeue.adjacentVertices.listIterator();

                while(list.hasNext()){
                    Vertex enqueue = list.next();
                    while(visited[vertices.indexOf(enqueue)] == false){
                        visited[vertices.indexOf(enqueue)] = true;
                        
                        if(vertices.indexOf(enqueue) == secondV) {
                            System.out.println("Vertex " + vertices.get(firstV).name + " is connected to Vertex " + vertices.get(secondV).name);
                            break;
                        }
                        else queue.add(enqueue);
                    }
                }
            }

            if(queue.isEmpty())
                if(visited[secondV] == false)
                    System.out.println("Vertex " + vertices.get(firstV).name + " is NOT connected to Vertex " + vertices.get(secondV).name);
            
        }
    }
    
    public void insertEdge(boolean weighted, boolean withET){
        Integer firstV = null;
        Integer secondV = null;
        boolean hasEdge = false;
        int numberOfEdges = 0;

        // If there are no vertices
        if(vertices == null || vertices.size() == 0)
            System.out.println("NO VERTEX");

        // If there is at least one vertex
        else{
            showListOfVertices(withET);
            if(edges != null && edges.size()> 0)
                numberOfEdges = edges.size();

            // CHECK IF THE CURRENT NUMBER OF EDGES HAS REACHED THE MAXIMUM NUMBER OF EDGES FOR UNDIRECTED GRAPH
            if((numberOfEdges + 1) > ((vertices.size() * (vertices.size() - 1))/2))
                System.out.println("MAXIMUM NUMBER OF EDGES REACHED!");
            
            else{
                // Checks if an edge already exists between two vertices
                do{
                    // ASK FOR FIRST VERTEX
                    do{
                        System.out.print("\nFrom what vertex? ");
                        String first = scanString.next();
                        for(int i = 0; i < vertices.size(); i++)
                            if(vertices.get(i).name.matches(first)){
                                firstV = i;
                                break;
                            }
                    }while(firstV == null);

                    // Ask for second vertex
                    do{
                        System.out.print("To which vertex? ");
                        String second = scanString.next();
                        for(int i = 0; i < vertices.size(); i++)
                            if(vertices.get(i).name.matches(second)){
                                secondV = i;
                                break;
                            }
                    }while(secondV == null);
                
                    // Check if edge exists in unweighted graph
                    if(weighted == false){
                        if(uwGraph[firstV][secondV] == "T")
                            hasEdge = true;
                        else {
                            if(firstV != secondV){
                                uwGraph[firstV][secondV] = "T";
                                edge = new Edge(vertices.get(firstV), vertices.get(secondV));
                                hasEdge = false;
                            }
                        }
                    }

                    // Check if edge exists in weighted graph
                    else{
                        if(wGraph[firstV][secondV] > 0)
                            hasEdge = true;
                        
                        else {
                            if(firstV != secondV){
                                hasEdge = false;
                                // Ask for weight
                                Double value = null;
                                do{
                                    System.out.print("Weight of Edge between " + vertices.get(firstV).name + " and " + vertices.get(secondV).name + ": ");
                                    String input = scanString.next();
                    
                                    try{
                                        value = Double.parseDouble(input);
                                    } catch (NumberFormatException e){value = null;}
                                } while (value == null);

                                // Create new edge
                                edge = new Edge(vertices.get(firstV), vertices.get(secondV), value);
                                wGraph[firstV][secondV] = value;
                            }            
                        }
                    }

                    if(hasEdge == true || firstV == secondV){
                        firstV = null;
                        secondV = null;
                    }
                }while(hasEdge == true || firstV == secondV);

                // Add new edge to list of edges
                if(edges == null)
                    edges = new ArrayList<Edge>();
                edges.add(edge);
        
                // Add vertex to Adjacency List of other vertex
                vertices.get(firstV).addAdj(vertices.get(secondV));
                vertices.get(secondV).addParent(vertices.get(firstV));
                zeroInDegreeVertices.remove(vertices.get(secondV));
                System.out.println("\nVertices: " + vertices.get(firstV).name + " and " + vertices.get(secondV).name);
                System.out.println("An edge is inserted");
            }   
        }
    }

    public void removeEdge(boolean weighted, boolean withET){
        Integer firstV = null;
        Integer secondV = null;
        boolean hasEdge = true;

        // If there are no edges
        if(edges == null || edges.size() == 0)
            System.out.println("NULL GRAPH! (no edges)");

        // If there is at least one edge
        else{
            // Show the current list of vertices
            showListOfVertices(withET);
            // If there is no edge
            do{
                // Ask for first vertex
                do{
                    System.out.print("\nFrom what vertex? ");
                    String first = scanString.next();
                    for(int i = 0; i < vertices.size(); i++)
                        if(vertices.get(i).name.matches(first)){
                            firstV = i;
                            break;
                        }
                }while(firstV == null);
            
                // Ask for second vertex
                do{
                    System.out.print("To which vertex? ");
                    String second = scanString.next();
                    for(int i = 0; i < vertices.size(); i++){
                        if(vertices.get(i).name.matches(second)){
                            secondV = i;
                            break;
                        }
                    }
                }while(secondV == null);
        
                // If an Edge does exist
                if(vertices.get(firstV).adjacentVertices.contains(vertices.get(secondV))){
                    hasEdge = true;
                    // Remove Edge in Adjacent Matrix
                    if(weighted == false){
                        uwGraph[firstV][secondV] = "F";
                        uwGraph[secondV][firstV] = "F";
                    }
                    else if(weighted == true){
                        wGraph[firstV][secondV] = 0;
                        wGraph[secondV][firstV] = 0;
                    }
                }
                // If there is no edge
                else{
                    System.out.println("NO EDGE EXISTS");
                    hasEdge = false;    
                }

                // Reset vertices if no edge exists
                if(hasEdge == false){
                    firstV = null;
                    secondV = null;
                }
            }while(hasEdge == false);

            // Remove in adjacency lists
            vertices.get(firstV).removeAdj(vertices.get(secondV));
            vertices.get(secondV).removeParent(vertices.get(firstV));
            System.out.println("\nVertices: " + vertices.get(firstV).name + " and " + vertices.get(secondV).name);
            System.out.println("An edge is removed");
            // Check if second vertex has an in-degree of zero
            if(vertices.get(secondV).parents != null && vertices.get(secondV).parents.size() == 0)
                zeroInDegreeVertices.add(vertices.get(secondV));
            
            // Remove in list of edges
            for(int k = 0; k < edges.size(); k++)
                if(edges.get(k).first == vertices.get(firstV) && edges.get(k).second == vertices.get(secondV)) {
                    edges.remove(k);
                    break;
                }
        }
    }

    public void insertVertex(boolean weighted, boolean withET){
        boolean exists;
        String name;
        String input;
        Double time = null;
        Vertex addVertex;

        // If there are no existing vertices
        if(vertices == null || vertices.size() == 0){
            // Naming the vertex
            System.out.print("Name of Vertex: ");
            name = scanString.next();
            vertices = new ArrayList<Vertex>();
            zeroInDegreeVertices = new ArrayList<Vertex>();
            
            // Create an unweighted directed graph
            if(weighted == false){
                uwGraph = new String[1][1];
                uwGraph[0][0] = "F";
            }
            
            // Create a weighted directed graph
            else {
                wGraph = new double[1][1];
                wGraph[0][0] = 0;
            }
        }
        
        // If there is at least one vertex
        else{
            // Checks if name of vertex already exists
            do{
                System.out.print("Name of Vertex: ");
                name = scanString.next();
                exists = false;
                
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.matches(name))
                        exists = true;
                }
            }while(exists == true);
        
            // Adding new vertex to adjacency matrix -- Unweighted
            if(weighted == false){
                String[][] uwTemp = uwGraph;
                uwGraph = new String[vertices.size() + 1][vertices.size() + 1];
        
                for(int i = 0; i < vertices.size() + 1; i++){
                    uwGraph[i][vertices.size()] = "F";
                    uwGraph[vertices.size()][i] = "F";
                }
        
                for(int i = 0; i < vertices.size(); i++)
                    for(int j = 0; j < vertices.size(); j++)
                        uwGraph[i][j] = uwTemp[i][j];
            }

            // Adding new vertex to adjacency matrix -- Weighted
            else{
                double[][] wTemp = wGraph;
                wGraph = new double[vertices.size() + 1][vertices.size() + 1];

                for(int i = 0; i < vertices.size() + 1; i++){
                    wGraph[i][vertices.size()] = 0;
                    wGraph[vertices.size()][i] = 0;
                }
        
                for(int i = 0; i < vertices.size(); i++)
                    for(int j = 0; j < vertices.size(); j++)
                        wGraph[i][j] = wTemp[i][j];
            }
        }
        
        // Create Vertex Object
        // If all vertices should have execution time
        if(withET == true){
            // Ask for Execution Time
            do{
                System.out.print("Execution Time: ");
                input = scanString.next();

                try{
                    time = Double.parseDouble(input);
                } catch (NumberFormatException e){time = null;}
            } while (time == null || time < 0);

            addVertex = new Vertex(name, time);
        }
        
        else addVertex = new Vertex(name);
        
        // Add to list of vertices
        vertices.add(addVertex);
        zeroInDegreeVertices.add(addVertex);
        System.out.println("\nVertex " + name + " is inserted");
    }

    public void removeVertex(boolean weighted, boolean withET){
        boolean vertexInList = false;
        String vertexName;
        int index = 0;
    
        // If there are no vertices at all
        if(vertices == null || vertices.size() == 0)
            System.out.println("NO VERTEX");
        
        // There is at least one vertex
        else if(vertices.size() > 0){
            // Show the current list of vertices
            showListOfVertices(withET);
            
            // Check if the vertex is in the list of vertices
            do{
                System.out.print("What vertex? ");
                vertexName = scanString.next();
    
                // Check if input is in the list of vertices
                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.matches(vertexName)){
                        vertexInList = true;
                        index = i;
                        break;
                    }
                }
    
                if(vertexInList == false)
                    System.out.println("Vertex does not exist");
            } while (vertexInList == false);
    
            // Checks if other vertices are adjacent to input
            for(int j = 0; j < vertices.size(); j++){
                if((vertices.get(j).adjacentVertices != null && vertices.get(j).adjacentVertices.size() > 0) || (vertices.get(j).parents != null && vertices.get(j).parents.size() > 0)){
                    if(vertices.get(j).adjacentVertices.contains(vertices.get(index)))
                        vertices.get(j).removeAdj(vertices.get(index));
                    else if(vertices.get(j).parents.contains(vertices.get(index))){
                        vertices.get(j).removeParent(vertices.get(index));
                        if(vertices.get(j).parents.size() == 0)
                            zeroInDegreeVertices.add(vertices.get(j));
                    }
    
                    // Removing the edges adjacent to the input from the list of edges    
                    if(edges != null || edges.size() > 0){
                        for(int k = 0; k < edges.size(); k++){
                            if(edges.get(k).first == vertices.get(index) || edges.get(k).second == vertices.get(index)){
                                edges.remove(k);
                                k = -1;
                            }
                        }
                    }
                }
            }
            
            // Remove input from the list of vertices
            if(zeroInDegreeVertices.contains(vertices.get(index)))
                zeroInDegreeVertices.remove(zeroInDegreeVertices.indexOf(vertices.get(index)));
            vertices.remove(index);

            // Reset Matrix
            defaultMatrix(weighted);
    
            System.out.println("\nVertex "+ vertexName + " is removed");
        }
    }

    public void showListOfVertices(boolean withET){
        System.out.print("\nVertices In Order: (");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print(vertices.get(i).name);
            // If there is an execution time
            if(withET == true)
                System.out.print(": " + vertices.get(i).time);
    
            if(i < vertices.size() - 1)
                System.out.print(", ");

            // If it's the last vertex
            else System.out.print(")\n");
        }

        // List of Vertices with in-degree of zero
        if(zeroInDegreeVertices.size() > 0){
            System.out.print("Vertices with in-degree of ZERO: (");
            for(int x = 0; x < zeroInDegreeVertices.size() - 1; x++)
                System.out.print(zeroInDegreeVertices.get(x).name +", ");
            System.out.print(zeroInDegreeVertices.get(zeroInDegreeVertices.size() - 1).name + ")\n");
        }
    }

    public void getVertexSize(){
        System.out.print("Number of Vertices: ");
        if(vertices == null)
            System.out.println("0");
        else System.out.println(vertices.size());
    }

    public void getEdgeSize(){
        System.out.print("Number of Edges: ");
        if(edges == null)
            System.out.println("0");
        else System.out.println(edges.size());
    }

    public void adjacencyMatrix(boolean weighted){
        // ADJACENCY MATRIX --- Default display of graph
        System.out.println("\nADJACENCY MATRIX: (Order is based on list of vertices)\n");
        
        for(int i = 0; i < vertices.size(); i++){
            System.out.print("\t");

            for(int j = 0; j < vertices.size(); j++){
                // For Unweighted Graph
                if(weighted == false){
                    if(edges != null && edges.size() > 0){
                        for(int k = 0; k < edges.size(); k++)
                            if(edges.get(k).first == vertices.get(i) && edges.get(k).second == vertices.get(j))
                                uwGraph[i][j] = "T";
                    }            
                    System.out.print("[" + uwGraph[i][j] + "]");
                }
                
                // For Weighted Graph
                else{
                    if(edges != null && edges.size() > 0){
                        for(int k = 0; k < edges.size(); k++)
                            if(edges.get(k).first == vertices.get(i) && edges.get(k).second == vertices.get(j))
                                wGraph[i][j] = edges.get(k).value;
                    }
                    System.out.print("[" + wGraph[i][j] + "]");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }
    
    public void defaultMatrix(boolean weighted){
        if(weighted == false){
            uwGraph = new String[vertices.size()][vertices.size()];
            for(int i = 0; i < vertices.size(); i++)
                for(int j = 0; j < vertices.size(); j++)
                    uwGraph[i][j] = "F";
        }

        else {
            wGraph = new double[vertices.size()][vertices.size()];
            for(int i = 0; i < vertices.size(); i++)
                for(int j = 0; j < vertices.size(); j++)
                    wGraph[i][j] = 0;
        }
    }
    
    // Lab 2
    /*public void breadthFirst(boolean withET){
        boolean[] visited;
        boolean exists = false;
        boolean allAreVisited = true;
        LinkedList<Vertex> queue = new LinkedList<Vertex>();

        if(vertices.size() > 0){
            showListOfVertices(withET);
            visited = new boolean[vertices.size()];

            do{
                // Ask for Source Vertex
                System.out.print("\nChoose Source Vertex: ");
                String vertex = scanString.next();

                for(int i = 0; i < vertices.size(); i++){
                    if(vertices.get(i).name.contains(vertex)){
                        exists = true;
                        visited[i] = true;
                        queue.add(vertices.get(i));
                        break;
                    }
                }

                if(exists == true){
                    do{
                        while(queue.size() > 0){
                            Vertex dequeue = queue.remove();
                            System.out.print(dequeue.name + " ");
    
                            Iterator<Vertex> list = dequeue.adjacentVertices.listIterator();
    
                            while(list.hasNext()){
                                Vertex enqueue = list.next();
                                while(visited[vertices.indexOf(enqueue)] == false){
                                    queue.add(enqueue);
                                    visited[vertices.indexOf(enqueue)] = true;
                                }
                            }
                        }
                        
                        if(queue.isEmpty()){
                            allAreVisited = true;
                            for(int i = 0; i < visited.length; i++)
                                if(visited[i] == false){
                                    allAreVisited = false;
                                    queue.add(vertices.get(i));
                                    visited[i] = true;
                                    break;
                                }
                        }
                    }while(allAreVisited == false);
                }
            }while(exists == false);

        }

        else System.out.println("\nNO VERTEX\n");
    }

    public void depthFirst(boolean withET){
        boolean[] visited;
        boolean exists = false;
        boolean allAreVisited = true;
        Stack<Vertex> stack = new Stack<Vertex>();

        if(vertices.size() > 0){
            showListOfVertices(withET);
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
                            Vertex pop = stack.pop();
                            System.out.print(pop.name + " ");
    
                            Iterator<Vertex> list = pop.adjacentVertices.listIterator();
    
                            while(list.hasNext()){
                                Vertex push = list.next();
                                while(visited[vertices.indexOf(push)] == false){
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
                                    visited[i] = true;
                                    break;
                                }
                        }
                    }while(allAreVisited == false);
                }
            }while(exists == false);
        }
        else System.out.println("\nNO VERTEX\n");        
    }*/

}