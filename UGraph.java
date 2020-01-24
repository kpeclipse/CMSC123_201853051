import java.util.*;

public class  UGraph{
    int[] V;
    int[] E;
    Scanner scan = new Scanner(System.in);

    public UGraph(){
        // ASK FOR NUMBER OF VERTICES AND EDGES
        System.out.println("UNDIRECTED GRAPH\n");
        
        System.out.print("Enter number of vertices: ");
        int nV = scan.nextInt();
        V = new int[n];

        do{
            System.out.print("Enter number of vertices: ");
            int nE = scan.nextInt();
        }while(nE > ((nV*nV - nV)/2));

        // NAIVE
        naive(nV, nE);
        // ADJACENCY MATRIX
        // ADJACENCY LIST
    }

    public void naive(int vertices, int edges){
        int i = ;
        do{
            System.out.print("Name of Vertex: ");
            V[i] = scan.next();
        }while(i < vertices);

        // SET OF VERTICES
        do{

        }
    }

    public void adjacencyMatrix(){

    }

    public void adjacencyList(){

    }

    public static void main(String[] args) {
        new UGraph();
    }
}