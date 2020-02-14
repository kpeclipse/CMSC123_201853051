class Edge{
    Node first;
    Node second;
    int value;

    // With Weight
    public Edge(Node f, Node s, int v){
        first = f;
        second = s;
        value = v;
    }

    // Without Weight
    public Edge(Node f, Node s){
        first = f;
        second = s;
    }
}