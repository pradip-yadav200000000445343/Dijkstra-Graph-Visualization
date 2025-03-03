import java.util.*;

class Dijkstra {
    public static List<Integer> findShortestPath(Graph graph, int start, int end) {
        int numVertices = graph.getAdjList().size();
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(u -> dist[u]));
        pq.add(start);

        int[] prev = new int[numVertices];
        Arrays.fill(prev, -1);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            if (u == end) break;

            for (Edge edge : graph.getAdjList().get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                    pq.add(v);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

