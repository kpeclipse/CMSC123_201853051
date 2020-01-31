import java.util.*;

public class  UGraph{
    int numberOfVertices;
    int numberOfEdges;
    int choice;
    String[][] uwGraph, uwTemp;
    int[][] wGraph, wTemp;

    Scanner scan = new Scanner(System.in);

    public UGraph(boolean weighted){
        // ASK FOR NUMBER OF VERTICES AND EDGES
        System.out.println("----------------------------\nUNDIRECTED GRAPH\n");
        setInitialVertices(weighted);
        
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
            System.out.println("9 - Remove a vertex\n");

            System.out.println("TRAVERSALS\n");
            System.out.println("10 - Breadth-first");
            System.out.println("11 - Depth-first");
            
            System.out.print("CHOICE (Choose 0 to EXIT): ");
            choice = scan.nextInt();
            
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Number of Vertices: " + numberOfVertices);
                    break;
                case 2:
                    System.out.println("Number of Edges: " + numberOfEdges);
                    break;
                case 3:
                    showList(weighted);
                    break;
                case 4:
                    checkAdjacency(weighted);
                    break;
                case 5:
                    checkConnectedness(weighted);
                    break;
                case 6:
                    insertEdge(weighted);
                    break;
                case 7:
                    removeEdge(weighted);
                    break;
                case 8:
                    insertVertex(weighted);
                    break;
                case 9:
                    removeVertex(weighted);
                    break;
                case 10:
                    break;
                case 11:
                    break;
            }
            System.out.println("----------------------------");
        }while(choice != 0);
    }

    public void setInitialVertices(boolean weighted){
        do{
            System.out.print("Enter number of vertices: ");
            numberOfVertices = scan.nextInt();
        }while(numberOfVertices < 0);

        if(numberOfVertices > 0){
            if(weighted == false){
                uwGraph = new String[numberOfVertices][numberOfVertices];
            
                for(int i = 0; i < numberOfVertices; i++)
                    for(int j = 0; j < numberOfVertices; j++)
                        uwGraph[i][j] = "F";
            }

            else if(weighted == true){
                wGraph = new int[numberOfVertices][numberOfVertices];
                
                for(int i = 0; i < numberOfVertices; i++)
                    for(int j = 0; j < numberOfVertices; j++)
                        wGraph[i][j] = 0;
            }
        } 
    }

    public void showList(boolean weighted){
        if(numberOfEdges == 0)
            System.out.println("NULL GRAPH (no edges)");

        else{
                // BINARY RELATION
                System.out.println("BINARY RELATION:");
                    for(int i = 0; i < numberOfVertices; i++)
                        for(int j = 0; j < numberOfVertices; j++){
                            if(weighted == false){
                                if(uwGraph[i][j] == "T" && i <= j)
                                System.out.print("(" + (i + 1) + ", " + (j + 1) + ") ");
                            }
                            else{
                                if(wGraph[i][j] > 0 && i <= j)
                                System.out.print("(" + (i + 1) + ", " + (j + 1) + ") ");
                            }
                        }

                // ADJACENCY MATRIX
                System.out.println("\n\nADJACENCY MATRIX:");
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
                    for(int j = 0; j < numberOfVertices; j++)
                        if(weighted == false)
                            System.out.print(uwGraph[i][j] + " ");
                        else
                            System.out.print(wGraph[i][j] + " ");
                    System.out.println("");
                }
        }
    }

    public void checkAdjacency(boolean weighted){
        int i;
        int j;

        do{
            System.out.print("From what vertex: ");
            i = scan.nextInt();
        }while(i > numberOfVertices || i <= 0);
        
        do{
            System.out.print("To what vertex: ");
            j = scan.nextInt();
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

    public void checkConnectedness(boolean weighted){
        int i;
        int j;

        do{
            System.out.print("From what vertex: ");
            i = scan.nextInt();
        }while(i > numberOfVertices || i <= 0);
        
        do{
            System.out.print("To what vertex: ");
            j = scan.nextInt();
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

    public void insertEdge(boolean weighted){
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

            if(weighted == false){
                uwGraph[i-1][j-1] = "T";
                uwGraph[j-1][i-1] = "T";
            }

            else{
                System.out.print("Set Weight: ");
                wGraph[i-1][j-1] = scan.nextInt();
                wGraph[j-1][i-1] = wGraph[i-1][j-1];
            }

            System.out.println("\nAn edge is inserted");
        }
    }

    public void removeEdge(boolean weighted){
        int i;
        int j;

        if(numberOfEdges == 0)
            System.out.println("NULL GRAPH! (no edges)");

        else{
            do{
                System.out.print("From what vertex? ");
                i = scan.nextInt();
            }while(i > numberOfVertices || i <= 0);
    
            do{
                System.out.print("To which vertex? ");
                j = scan.nextInt();
            }while(j > numberOfVertices || j <= 0 || j == i);

            if(weighted == false){
                if(uwGraph[i-1][j-1] == "F")
                    System.out.println("NO EDGE EXISTS");
                else{
                    uwGraph[i-1][j-1] = "F";
                    uwGraph[j-1][i-1] = "F";    
                }
            }

            else{
                if(wGraph[i-1][j-1] == 0)
                    System.out.println("NO EDGE EXISTS");
                else{
                    wGraph[i-1][j-1] = 0;
                    wGraph[j-1][i-1] = 0;    
                }
            }

            System.out.println("\nAn edge is removed");
            numberOfEdges -= 1;
        }
    }

    public void insertVertex(boolean weighted){

        if(weighted == false){
            uwTemp = uwGraph;
            uwGraph = new String[numberOfVertices + 1][numberOfVertices + 1];

            for(int i = 0; i <= numberOfVertices; i++){
                uwGraph[i][numberOfVertices] = "F";
                uwGraph[numberOfVertices][i] = "F";
            }

            for(int i = 0; i < numberOfVertices; i++)
                    for(int j = 0; j < numberOfVertices; j++)
                        uwGraph[i][j] = uwTemp[i][j];
            
        }

        else{
            wTemp = wGraph;
            wGraph = new int[numberOfVertices + 1][numberOfVertices + 1];

            for(int i = 0; i <= numberOfVertices; i++){
                wGraph[i][numberOfVertices] = 0;
                wGraph[numberOfVertices][i] = 0;
            }

            for(int i = 0; i < numberOfVertices; i++)
                for(int j = 0; j < numberOfVertices; j++)
                    wGraph[i][j] = wTemp[i][j];
        }

        System.out.println("\nA vertex is inserted");
        numberOfVertices += 1;
    }

    public void removeVertex(boolean weighted){
        int i;
        do{
            System.out.print("What vertex? ");
            i = scan.nextInt();
        }while(i > numberOfVertices || i <= 0);

        while(i < numberOfVertices){
            for(int j = 0; j < numberOfVertices; j++)
                if(weighted == false)
                    uwGraph[j][i] = uwGraph[j][i + 1];
                else wGraph[j][i] = wGraph[j][i + 1];
            for(int j = 0; j < numberOfVertices; j++)
                if(weighted == false)
                    uwGraph[i][j] = uwGraph[i + 1][j];
                else wGraph[i][j] = wGraph[i + 1][j];
        }

        System.out.println("\nA vertex is removed");
        numberOfVertices -= 1;
    }

}