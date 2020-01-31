import java.util.*;

public class  UGraph{
    int numberOfVertices;
    int numberOfEdges;
    int choice;
    String[][] graph;
    Scanner scan = new Scanner(System.in);

    GraphTraversalsUGraph traverse = new GraphTraversalsUGraph();

    public UGraph(){
        // ASK FOR NUMBER OF VERTICES AND EDGES
        System.out.println("UNDIRECTED GRAPH\n");
        
        setInitialVertices();
        
        // MAIN MENU
        do{
            System.out.println("\nQUERIES\n");
            System.out.println("1 - Number of Vertices");
            System.out.println("2 - Number of Edges");
            System.out.println("3 - List of Vertices adjacent to a given vertex");
            System.out.println("4 - Check if two vertices are adjacent or not");
            System.out.println("5 - Check if two vertices are connected or not\n");

            System.out.println("MODIFICATION\n");
            System.out.println("6 - Insert an edge");
            System.out.println("7 - Remove an edge");
            System.out.println("8 - Insert a vertex");
            System.out.println("9 - Remove a vertex");

            System.out.println("TRAVERSALS\n");
            System.out.println("10 - Breadth-first");
            System.out.println("11 - Depth-first");
            
            System.out.print("CHOICE (Choose 0 to EXIT): ");
            choice = scan.nextInt();
            
            switch(choice){
                case 1:
                    System.out.println("Number of Vertices: " + numberOfVertices);
                    break;
                case 2:
                    System.out.println("Number of Edges: " + numberOfEdges);
                    break;
                case 3: // List of Vertices adjacent to a given vertex
                    showList();
                    break;
                case 4: // Check if adjacent or not
                    break;
                case 5:
                    break;
                case 6: // Insert an edge
                    insertEdge();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    System.exit(0);
                    break;
                case 10:
                    break;
                case 11:
                    break;
            }
            System.out.println("----------------------------");
        }while(choice != 0);
    }

    public void insertEdge(){
        int i;
        int j;

        numberOfEdges += 1;

        if(numberOfEdges > ((numberOfVertices * (numberOfVertices-1))/2)){
            System.out.println("MAXIMUM NUMBER OF EDGES REACHED!");
            numberOfEdges -= 1;
        }

        else{
            do{
                System.out.print("From what vertex? ");
                i = scan.nextInt();
            }while(i > numberOfVertices || i <= 0);

            do{
                System.out.print("To which vertex? ");
                j = scan.nextInt();
            }while(j > numberOfVertices || j <= 0 || j == i);

            graph[i-1][j-1] = "1";
            graph[j-1][i-1] = "1";

            System.out.println("\nAn edge is inserted");
        }
    }

    public void showList(){
        if(numberOfEdges == 0)
            System.out.println("NULL GRAPH (no edges)");

        else{
            // BINARY RELATION
            System.out.println("BINARY RELATION:");
            for(int i = 0; i < numberOfVertices; i++){
                for(int j = 0; j < numberOfVertices; j++){
                    if(graph[i][j] == "1" && i <= j)
                        System.out.print("(" + (i + 1) + ", " + (j + 1) + ") ");
                }
            }
        }
        // ADJACENCY MATRIX
        System.out.println("\n\nADJACENCY MATRIX:");
        for(int i = 0; i <= numberOfVertices; i++)
            System.out.print(i + " ");
           
        System.out.println("");

        for(int i = 0; i < numberOfVertices; i++){
            System.out.print(i + 1 + " ");
            for(int j = 0; j < numberOfVertices; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println("");
        }
    }

    public void setInitialVertices(){
        do{
            System.out.print("Enter number of vertices: ");
            numberOfVertices = scan.nextInt();
        }while(numberOfVertices < 0);

        if(numberOfVertices > 0){
            graph = new String[numberOfVertices][numberOfVertices];
            
            for(int i = 0; i < numberOfVertices; i++)
                for(int j = 0; j < numberOfVertices; j++)
                    graph[i][j] = "0";
        }
        
        /*for(int i = 0; i <= numberOfVertices; i++)
            System.out.print(i + " ");
           
        System.out.println("");

        for(int i = 0; i < numberOfVertices; i++){
            System.out.print(i + 1 + " ");
            for(int j = 0; j < numberOfVertices; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println("");
        }*/       
    }

    public static void main(String[] args) {
        new UGraph();
    }
}