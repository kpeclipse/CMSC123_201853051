import java.util.*;

public class GraphTester{
    Scanner scan = new Scanner(System.in);
    int choice;
    boolean directed;
    boolean weighted;

    UGraph uGraph;

    public GraphTester(){
        System.out.println("GRAPH TESTER\n");
        do{
            directed = UorDGraph();
            weighted = UorWGraph();

            if(directed == false)
                uGraph = new UGraph(weighted);
        }while(choice != 0);
    }
    
    public boolean UorDGraph() {
        boolean DGraph = false;

        do{
            System.out.println("Choose Graph: ");
            System.out.println("1 - Undirected Graph");
            System.out.println("2 - Directed Graph\n");

            System.out.print("Choice: ");
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
        }while(choice != 1 || choice != 2 || choice != 0);

        return DGraph;
    }

    public boolean UorWGraph() {
        boolean WGraph = false;
        
        do{
            System.out.println("\nUnweighted or Weighted?");
            System.out.println("1 - Unweighted Graph");
            System.out.println("2 - Weighted Graph\n");

            System.out.print("Choice: ");
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
        }while(choice != 1 || choice != 2 || choice != 0);

        return WGraph;
    }

    public static void main(String[] args) {
        new GraphTester();
    }
}