import java.util.*;

public class GraphTester{
    Scanner scan = new Scanner(System.in);
    boolean directed;
    boolean weighted;
    boolean withExecutionTime;
    int[] pathLength;

    UGraph uGraph;
    DGraph dGraph;

    public GraphTester(){
        System.out.println("GRAPH TESTER\n");
        
        directed = UorDGraph();
        
        if(directed == false)
            uGraph = new UGraph();
        else dGraph = new DGraph();

        weighted = UorWGraph();
        withExecutionTime = executionTime();

        mainMenu(directed, weighted, withExecutionTime);
    }

    // DISPLAYS MAIN MENU
    public void mainMenu(boolean d, boolean w, boolean e){
        String input;
        Integer choice = null;

        do{
            do{
                System.out.println("\n1 - Number of Vertices");
                System.out.println("2 - Number of Edges");
                System.out.println("3 - List of Vertices adjacent to a given vertex");
                System.out.println("4 - Check if two vertices are adjacent or not");
                System.out.println("5 - Check if two vertices are connected or not");
                System.out.println("6 - Insert an edge");
                System.out.println("7 - Remove an edge");
                System.out.println("8 - Insert a vertex");
                System.out.println("9 - Remove a vertex");
                
                // Lab 2
                System.out.println("10 - Breadth-first");
                System.out.println("11 - Depth-first");

                // Lab 3
                System.out.println("12 - SSUPL");

                // Labs 4 5 6 7
                /*if(d == true){
                    System.out.println("13 - Topological Sort");
                    System.out.println("14 - Find Critical Path");
                    System.out.println("15 - Floyd-Warshall Algorithm");
                }*/
                
                System.out.print("\nCHOICE (Choose 0 to EXIT): ");
                input = scan.next();

                try{
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException error) {choice = null;}
            
            } while (choice == null);

            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    if(d == false)
                        uGraph.getVertexSize();
                    else dGraph.getVertexSize();
                    break;
                case 2:
                    if(d == false)
                        uGraph.getEdgeSize();
                    else dGraph.getEdgeSize();
                    break;
                case 3:
                    if(d == false){
                        System.out.println("UNDIRECTED GRAPH");
                        uGraph.showList(w, e);
                    }
                    else {
                        System.out.println("DIRECTED GRAPH");
                        dGraph.showList(w, e);
                    }
                    break;
                case 4:
                    if(d == false)
                        uGraph.checkAdjacency(e);
                    else dGraph.checkAdjacency(e);
                    break;
                case 5:
                    if(d == false)
                        uGraph.checkConnectedness(e);
                    else dGraph.checkConnectedness(e);
                    break;
                case 6:
                    if(d == false)
                        uGraph.insertEdge(w, e);
                    else dGraph.insertEdge(w, e);
                    break;
                case 7:
                    if(d == false)
                        uGraph.removeEdge(w, e);
                    else dGraph.removeEdge(w, e);
                    break;
                case 8:
                    if(d == false)
                        uGraph.insertVertex(w, e);
                    else dGraph.insertVertex(w, e);
                    break;
                case 9:
                    if(d == false)
                        uGraph.removeVertex(w, e);
                    else dGraph.removeVertex(w, e);
                    break;
                
                // Lab 2
                case 10:
                    if(d == false)
                        uGraph.breadthFirst(e);
                    else dGraph.breadthFirst(e);
                    break;
                case 11:
                    if(d == false)
                        uGraph.depthFirst(e);
                    else dGraph.depthFirst(e);
                    break;
               
                // Lab 3
                case 12:
                // If graph is unweighted
                    if(w == false){
                        if(d == false && uGraph.vertices != null && uGraph.vertices.size() > 0)
                            new SSUPL(uGraph, e);
                        else if(d == true && dGraph.vertices != null && dGraph.vertices.size() > 0)
                            new SSUPL(dGraph, e);
                    }
                    else System.out.print("GRAPH IS WEIGHTED!");
                    break;

                // Lab 4
                case 13:
                    if(d == true)
                        new TopologicalSort(dGraph);
                    break;

                // Lab 5 and Lab 6
                case 14:
                    if(d == true)
                        new CriticalPath(dGraph, w, e);
                    break;

                // Lab 7
                case 15:
                    if(d == true && w == true)
                        new floydWarshall(dGraph);
                    else System.out.print("GRAPH IS UNWEIGHTED AND/OR UNDIRECTED!");
                    break;
            }

            System.out.println("\n----------------------------");
        
        }while(choice != 0);
    }
    
    // ASKS IF DIRECTED OR UNDIRECTED
    public boolean UorDGraph() {
        boolean DGraph = false;
        String input;
        Integer choice = null;
        
        do{
            do {
                System.out.println("Choose Graph: ");
                System.out.println("1 - Undirected Graph");
                System.out.println("2 - Directed Graph\n");
                System.out.print("Choice (Press 0 to EXIT): ");
    
                // ask for input
                input = scan.next();
                
                // check if input is an integer or not
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {choice = null;}
            } while (choice == null);
            
            // Exit Option
            if(choice == 0)
                System.exit(0);
            
            // Undirected Graph Option
            else if(choice == 1){
                DGraph = false;
                break;
            }

            // Directed Graph Option
            else if(choice == 2){
                DGraph = true;
                break;
            }
            System.out.println();
        } while (choice > 2 || choice < 0); // None of the above

        return DGraph;
    }

    // ASKS IF WEIGHTED OR UNWEIGHTED
    public boolean UorWGraph() {
        boolean WGraph = false;
        String input;
        Integer choice = null;

        do {
            // Check if choice is integer or not
            do {
                System.out.println("EDGES: Unweighted or Weighted?");
                System.out.println("1 - Unweighted Graph");
                System.out.println("2 - Weighted Graph\n");
                System.out.print("Choice (Press 0 to EXIT): ");
                
                // Ask for input
                input = scan.next();

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e){choice = null;}
            } while (choice == null);

            // Exit Option
            if(choice == 0)
                System.exit(0);

            // Unweighted Option
            else if(choice == 1){
                WGraph = false;
                break;
            }
            
            // Weighted Option
            else if(choice == 2){
                WGraph = true;
                break;
            }

            System.out.println();
        }while(choice > 2 || choice < 0); // None of the above

        return WGraph;
    }

    public boolean executionTime() {
        boolean withET = false;
        String input;
        Integer choice = null;

        do {
            // Check if choice is integer or not
            do {
                System.out.println("VERTICES: With or Without Execution Time?");
                System.out.println("1 - Without Execution Time");
                System.out.println("2 - With Execution Time\n");
                System.out.print("Choice (Press 0 to EXIT): ");
                
                // Ask for input
                input = scan.next();

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e){choice = null;}
            } while (choice == null);

            // Exit Option
            if(choice == 0)
                System.exit(0);

            // Without Execution Time Option
            else if(choice == 1){
                withET = false;
                break;
            }
            
            // With Execution Time Option
            else if(choice == 2){
                withET = true;
                break;
            }

            System.out.println();
        }while(choice > 2 || choice < 0); // None of the above

        return withET;
    }

    public static void main(String[] args) {
        new GraphTester();
    }
}