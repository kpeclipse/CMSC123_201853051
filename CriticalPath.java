import java.util.*;

class CriticalPath {
    CPResult result;
    boolean[] visited;
    int[] indegree;
    int numberOfVertices;
    Stack<String> sequence = new Stack<String>();
    double[] taskTime;
    double[] criticalTime;
    String[] prevTask;
    LinkedList<Vertex> queue = new LinkedList<Vertex>();
    boolean weighted;

    public CriticalPath (DGraph G, boolean weighted, boolean withET) {
        initialize(G, weighted, withET);
        if(weighted == true || withET == true){
            result = findCriticalPath(G);

            if(result != null){
                System.out.print("\nCritical Path: ");
                for(int i = 0; i < result.criticalPath.size() - 1; i++)
                    System.out.print(result.criticalPath.get(i) + " - ");
                System.out.println(result.criticalPath.get(result.criticalPath.size()-1));
                System.out.print("Critical Time: " + result.criticalTime);
            }
        }
    }

    // Critical Path
    public CPResult findCriticalPath(DGraph G){
        boolean allAreVisited = true;
        double CT = 0;
        Stack<String> stack = new Stack<String>();
        CPResult output;
        int index = -1;

        for(int i = 0; i < numberOfVertices; i++){
            if(indegree[i] == 0 && visited[i] == false){
                visited[i] = true;
                queue.add(G.vertices.get(i));
            }
        }
    
        while (queue.size() > 0) {
            Vertex dequeue = queue.remove();
            index = G.vertices.indexOf(dequeue);
            criticalTime[index] += taskTime[index];    
            
            Iterator<Vertex> list = dequeue.adjacentVertices.listIterator();

            while (list.hasNext()) {
            
                Vertex adjVertex = list.next();
                indegree[G.vertices.indexOf(adjVertex)] -= 1;
                boolean doChange = true;

                for(int i = 0; i < dequeue.adjacentVertices.size(); i++){
                    if(prevTask[G.vertices.indexOf(adjVertex)] != null){
                        if(prevTask[G.vertices.indexOf(adjVertex)] == prevTask[G.vertices.indexOf(dequeue.adjacentVertices.get(i))]){
                            if(G.vertices.indexOf(adjVertex) != G.vertices.indexOf(dequeue.adjacentVertices.get(i))){
                                doChange = false;
                                break;
                            }
                        }
                    }
                }

                if(doChange == true){
                    double edgeWeight = 0;

                    if(weighted == true){
                        for(int i = 0; i < G.edges.size(); i++){
                            if(G.edges.get(i).first == dequeue && G.edges.get(i).second == adjVertex){
                                edgeWeight = G.edges.get(i).value;
                                break;
                            }
                        }
                    }
                    criticalTime[G.vertices.indexOf(adjVertex)] = criticalTime[index] + edgeWeight;
                    prevTask[G.vertices.indexOf(adjVertex)] = dequeue.name;
                }
    
                if(indegree[G.vertices.indexOf(adjVertex)] == 0 && visited[G.vertices.indexOf(adjVertex)] == false){
                    visited[G.vertices.indexOf(adjVertex)] = true;
                    queue.add(adjVertex);
                }
            }
        
            // showCurrentInfos();
        }

        if(queue.isEmpty()){
            for(int i = 0; i < numberOfVertices; i++){
                if(visited[i] == false){
                    
                    System.out.print("GRAPH IS NOT DIRECTED ACYCLIC!");
                    allAreVisited = false;
                    break;
                }

                else {
                    if(CT < criticalTime[i]){
                        CT = criticalTime[i];
                        index = i;
                    }
                }
            }
        }

        if(allAreVisited == true){
            stack.push(G.vertices.get(index).name);
            String flag = prevTask[index];

            while(flag != null){
                stack.push(flag);
                for(int i = 0; i < numberOfVertices; i++){
                    if(G.vertices.get(i).name == flag)
                        flag = prevTask[i];
                }
            }

            LinkedList<String> CP = new LinkedList<String>();
            while(stack.isEmpty() == false){
                CP.add(stack.pop());
            }

            output = new CPResult(CP, CT);
            return output;
        }
        else return null;
    }

    // UPDATE ON THE CHANGES
    public void showCurrentInfos(){

        for(int i = 0; i < 3; i++){
            if(i == 0)
                System.out.print("In-Degree: ");
            else if(i == 1)
                System.out.print("Critical Time: ");
            else if(i == 2)
                System.out.print("Previous Task: ");


            for(int j = 0; j < numberOfVertices; j++){
                if(i == 0)
                    System.out.print(indegree[j] + " ");
                else if(i == 1)
                    System.out.print(criticalTime[j] + " ");
                else if(i == 2)
                    System.out.print(prevTask[j] + " ");
            }

            System.out.println();
        }
    }

    // Initializing tables
    public void initialize(DGraph G, boolean weighted, boolean withET){
        // If both edges and vertices have no weight
        if(withET == false && weighted == false)
            System.out.print("CANNOT FIND CRITICAL TIME");        

        else{
            numberOfVertices = G.vertices.size();
            this.weighted = weighted;
            visited = new boolean[numberOfVertices];
            indegree = new int[numberOfVertices];
            taskTime = new double[numberOfVertices];
            criticalTime = new double[numberOfVertices];
            prevTask = new String[numberOfVertices];

            for(int i = 0; i < numberOfVertices; i++){
                // If a vertex has execution time
                if(G.vertices.get(i).time > 0)
                    taskTime[i] = G.vertices.get(i).time;
                else taskTime[i] = 0;
    
                // If vertex has parent/s
                if(G.vertices.get(i).parents != null && G.vertices.get(i).parents.size() > 0)
                    // Set its in-degree
                    indegree[i] = G.vertices.get(i).parents.size();
                else indegree[i] = 0;
            }
        }
    }
}