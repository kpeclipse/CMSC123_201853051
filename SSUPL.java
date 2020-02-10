import java.util.*;

public class SSUPL{
    int[] sequence;
    boolean[] visited;
    Scanner scanString = new Scanner(System.in);
    Node input;
    boolean exists = false;
    
    public SSUPL (DGraph G){
        do{        
            System.out.print("Source Vertex: ");
            String source = scanString.next();

            for(int i = 0; i < G.vertices.size(); i++){
                if(G.vertices.get(i).name.contains(source)){
                    input = G.vertices.get(i);
                    exists = true;
                    break;
                }
            }
        }while(exists == false);

        dSSUPL(G, input);
        for(int i = 0; i < sequence.length; i++){
            System.out.print(sequence[i] + " ");
        }
    }

    public SSUPL(UGraph G){
        do{        
            System.out.print("Source Vertex: ");
            String source = scanString.next();

            for(int i = 0; i < G.vertices.size(); i++){
                if(G.vertices.get(i).name.contains(source)){
                    input = G.vertices.get(i);
                    exists = true;
                    break;
                }
            }
        }while(exists == false);
        
        uSSUPL(G, input);
        for(int i = 0; i < sequence.length; i++){
            System.out.print(sequence[i] + " ");
        }
    }

    public void dSSUPL (DGraph G, Node source){
        sequence = new int[G.vertices.size()];
        visited = new boolean[G.vertices.size()];

        sequence[G.vertices.indexOf(source)] = 0;

        Queue<Node> breadthFirst = new LinkedList<Node>();
        breadthFirst.add(source);

        while(breadthFirst.size() > 0){
            Node current = breadthFirst.remove();
            visited[G.vertices.indexOf(current)] = true;

            ArrayList<Node> list = current.adjacentVertices;

            for(int i = 0; i < list.size(); i ++){
                if(visited[G.vertices.indexOf(list.get(i))] == false){
                    if(sequence[G.vertices.indexOf(list.get(i))] == 0 )
                        sequence[G.vertices.indexOf(list.get(i))] = sequence[G.vertices.indexOf(current)] + 1;
                    
                    breadthFirst.add(G.vertices.get(G.vertices.indexOf(list.get(i))));
                }                    
            }
        }
    }

    public void uSSUPL (UGraph G, Node source){
        sequence = new int[G.vertices.size()];
        visited = new boolean[G.vertices.size()];

        sequence[G.vertices.indexOf(source)] = 0;

        Queue<Node> breadthFirst = new LinkedList<Node>();
        breadthFirst.add(source);

        while(breadthFirst.size() > 0){
            Node current = breadthFirst.remove();
            visited[G.vertices.indexOf(current)] = true;

            ArrayList<Node> list = current.adjacentVertices;

            for(int i = 0; i < list.size(); i ++){
                if(visited[G.vertices.indexOf(list.get(i))] == false){
                    if(sequence[G.vertices.indexOf(list.get(i))] == 0 )
                        sequence[G.vertices.indexOf(list.get(i))] = sequence[G.vertices.indexOf(current)] + 1;
                    
                    breadthFirst.add(G.vertices.get(G.vertices.indexOf(list.get(i))));
                }                    
            }
        }
    }
}