import java.util.*;

public class SSUPL{
    Integer[] sequence;
    boolean[] visited;
    Scanner scanString = new Scanner(System.in);
    Vertex input;
    boolean exists = false;
    
    // For Directed Graph
    public SSUPL (DGraph G, boolean withET){
        G.showListOfVertices(withET);
        input = askSourceVertex(G.vertices);
        breadthFirst(G.vertices, input);
        showSequence(G.vertices);
    }

    // For Undirected Graph
    public SSUPL(UGraph G, boolean withET){
        G.showListOfVertices(withET);
        input = askSourceVertex(G.vertices);
        breadthFirst(G.vertices, input);
        showSequence(G.vertices);
    }

    // Printing of SSUPL
    public void showSequence(ArrayList<Vertex> vertices){
        System.out.println("\nSingle-source Unweighted Path Length");
        for(int i = 0; i < sequence.length; i++){
            if(sequence[i] == null)
                System.out.println("From " + input.name + " to " + vertices.get(i).name +": infinity");
            else System.out.println("From " + input.name + " to " + vertices.get(i).name +": " + sequence[i]);
        }
    }

    // Ask for source vertex
    public Vertex askSourceVertex(ArrayList<Vertex> listOfVertices){
        Integer index = null;
        do{        
            System.out.print("Source Vertex: ");
            String source = scanString.next();

            for(int i = 0; i < listOfVertices.size(); i++){
                if(listOfVertices.get(i).name.matches(source)){
                    index = i;
                    exists = true;
                    break;
                }
            }
        }while(exists == false);

        return listOfVertices.get(index);
    }

    // BFS
    public void breadthFirst (ArrayList<Vertex> vertices, Vertex source){
        sequence = new Integer[vertices.size()];
        
        // Set to infinity
        for(int i = 0; i < vertices.size(); i++){
            if(i == vertices.indexOf(source))
                sequence[i] = 0;
            else sequence[i] = null;
        }

        visited = new boolean[vertices.size()];
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(source);
        visited[vertices.indexOf(source)] = true;

        while(queue.size() > 0){
            Vertex dequeue = queue.remove();

            Iterator<Vertex> list = dequeue.adjacentVertices.listIterator();
    
            while(list.hasNext()){
                Vertex enqueue = list.next();
                while(visited[vertices.indexOf(enqueue)] == false){
                    if(sequence[vertices.indexOf(enqueue)] == null || sequence[vertices.indexOf(enqueue)] == 0)
                    sequence[vertices.indexOf(enqueue)] = sequence[vertices.indexOf(dequeue)] + 1;
                    queue.add(enqueue);
                    visited[vertices.indexOf(enqueue)] = true;
                }
            }
        }
    }
}