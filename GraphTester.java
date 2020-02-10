import java.util.*;

public class GraphTester{
    Scanner scan = new Scanner(System.in);
    int choice;
    boolean directed;
    boolean weighted;
    int[] pathLength;

    UGraph uGraph;
    DGraph dGraph;

    public GraphTester(){
        System.out.println("GRAPH TESTER\n");
        directed = UorDGraph();
        weighted = UorWGraph();

        if(directed == false)
            uGraph = new UGraph();
        else dGraph = new DGraph();

        mainMenu(directed, weighted);
    }

    // DISPLAYS MAIN MENU
    public void mainMenu(boolean d, boolean w){
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
            System.out.println("10 - Breadth-first");
            System.out.println("11 - Depth-first");
            System.out.println("12 - SSUPL");
            
            if(d == true)
                System.out.println("13 - Topological Sort");
                
            System.out.print("\nCHOICE (Choose 0 to EXIT): ");
            choice = scan.nextInt();
            
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    if(d == false)
                        System.out.println("\nNumber of Vertices: " + uGraph.numberOfVertices);
                    else System.out.println("\nNumber of Vertices: " + dGraph.numberOfVertices);
                    break;
                case 2:
                    if(d == false)
                        System.out.println("\nNumber of Edges: " + uGraph.numberOfEdges);
                    else System.out.println("\nNumber of Edges: " + dGraph.numberOfEdges);
                    break;
                case 3:
                    if(d == false)
                        uGraph.showList(weighted);
                    else dGraph.showList(weighted);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    if(d == false)
                        uGraph.insertEdge(weighted);
                    else dGraph.insertEdge(weighted);
                    break;
                case 7:
                    if(d == false)
                        uGraph.removeEdge(weighted);
                    else dGraph.removeEdge(weighted);
                    break;
                case 8:
                    if(d == false)
                        uGraph.insertVertex(weighted);
                    else dGraph.insertVertex(weighted);
                    break;
                case 9:
                    if(d == false)
                        uGraph.removeVertex(weighted);
                    else dGraph.removeVertex(weighted);
                    break;
                case 10:
                    if(d == false)
                        uGraph.breadthFirst();
                    else dGraph.breadthFirst();
                    break;
                case 11:
                    if(d == false)
                        uGraph.depthFirst();
                    else dGraph.depthFirst();
                    break;
                case 12:
                    if(d == false){
                        new SSUPL(uGraph);
                    }
                    else new SSUPL(dGraph);
                    break;
                case 13:
            }
            System.out.println("\n----------------------------");
        }while(choice != 0);
    }
    
    // ASKS IF DIRECTED OR UNDIRECTED
    public boolean UorDGraph() {
        boolean DGraph = false;

        do{
            System.out.println("Choose Graph: ");
            System.out.println("1 - Undirected Graph");
            System.out.println("2 - Directed Graph\n");

            System.out.print("Choice (Press 0 to EXIT): ");
            choice = scan.nextInt();

            if(choice == 0)
                System.exit(0);
            else if(choice == 1){
                DGraph = false;
                break;
            }
            else if(choice == 2){
                DGraph = true;
                break;
            }
            System.out.println();
        }while(choice > 2 || choice < 0);

        return DGraph;
    }

    // ASKS IF WEIGHTED OR UNWEIGHTED
    public boolean UorWGraph() {
        boolean WGraph = false;
        do{
            System.out.println("Unweighted or Weighted?");
            System.out.println("1 - Unweighted Graph");
            System.out.println("2 - Weighted Graph\n");

            System.out.print("Choice (Press 0 to EXIT): ");
            choice = scan.nextInt();

            if(choice == 0)
                System.exit(0);

            else if(choice == 1){
                WGraph = false;
                break;
            }
            else if(choice == 2){
                WGraph = true;
                break;
            }

            System.out.println();
        }while(choice > 2 || choice < 0);

        return WGraph;
    }

    public static void main(String[] args) {
        new GraphTester();
    }
}