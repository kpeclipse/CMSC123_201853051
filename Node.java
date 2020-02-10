import java.util.*;

// Creates a new vertex here
class Node{
    String name;
    ArrayList<Node> adjacentVertices = new ArrayList<Node>();

    public Node(String vertex){
        name = vertex;
    }
        
    public void addAdj(Node insert){
        adjacentVertices.add(insert);
    }

    public void removeAdj(Node remove){
        adjacentVertices.remove(remove);
    }
}