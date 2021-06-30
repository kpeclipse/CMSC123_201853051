import java.util.*;

// Creates a new vertex here
class Vertex{
    String name;
    double time;
    ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();
    ArrayList<Vertex> parents = new ArrayList<Vertex>();

    // Vertex without weight
    public Vertex(String vertex){
        name = vertex;
    }

    // Vertex with weight
    public Vertex(String vertex, double weight){
        name = vertex;
        time = weight;
    }
    
    public void addAdj(Vertex insert){
        adjacentVertices.add(insert);
    }

    public void removeAdj(Vertex remove){
        adjacentVertices.remove(remove);
    }

    public void addParent(Vertex parent){
        parents.add(parent);
    }

    public void removeParent(Vertex parent){
        parents.remove(parent);
    }
}