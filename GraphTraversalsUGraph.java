import java.util.*;

public class GraphTraversalsUGraph{
    String[][] unweighted;
    int[][] weighted;
    boolean[] vertices;
    int start;

    Scanner scan = new Scanner(System.in);

    public void depthFirst(String[][] graph, int vertices){

    }

    public void depthFirst(int[][] graph, int vertices){

    }

    public void breadthFirst(String[][] graph, int vertices){
        int i;

        this.vertices = new boolean[vertices];
        unweighted = graph;

        do{
            System.out.print("\nFirst Marked Vertex: ");
            start = scan.nextInt();
        }while(start > vertices || start <= 0);

        Queue<Integer> sequence = new LinkedList<Integer>();

        sequence.add(start);
        
        System.out.print("BREADTH FIRST SEQUENCE: ");

        while(sequence.isEmpty() == false){
            int dequeue = sequence.poll();
            System.out.print(dequeue + " ");
            this.vertices[dequeue] = true;
            for(i = 0; i < vertices; i++)
                if(unweighted[dequeue][i] == "T" && (this.vertices[i]) == false)
                    sequence.add(i);
                    

        }
    }

    public void breadthFirst(int[][] graph, int vertices){
        weighted = graph;
    }
}