import java.util.*;

class Graph {
    private List<List<Edge>> adjList;

    public Graph(int numVertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjList.get(source).add(new Edge(destination, weight));
    }

    public List<List<Edge>> getAdjList() {
        return adjList;
    }
}
