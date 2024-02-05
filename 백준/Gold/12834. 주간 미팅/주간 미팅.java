import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int V = Integer.parseInt(tokenizer.nextToken());
        final int E = Integer.parseInt(tokenizer.nextToken());

        final StringTokenizer ABTokenizer = new StringTokenizer(reader.readLine());
        final int A = Integer.parseInt(ABTokenizer.nextToken()) - 1;
        final int B = Integer.parseInt(ABTokenizer.nextToken()) - 1;

        final int[] home = new int[N];
        final StringTokenizer homeTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(homeTokenizer.nextToken()) - 1;
        }

        final List<Node>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            final StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            final int a = Integer.parseInt(edgeTokenizer.nextToken()) - 1;
            final int b = Integer.parseInt(edgeTokenizer.nextToken()) - 1;
            final int c = Integer.parseInt(edgeTokenizer.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        final int[] shortestPathForA = calculateShortestPath(A, graph);
        final int[] shortestPathForB = calculateShortestPath(B, graph);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            final int a = shortestPathForA[home[i]] == Integer.MAX_VALUE ? -1 : shortestPathForA[home[i]];
            final int b = shortestPathForB[home[i]] == Integer.MAX_VALUE ? -1 : shortestPathForB[home[i]];
            answer += a + b;
        }

        writer.write(String.valueOf(answer));

        writer.flush();
        writer.close();
        reader.close();
    }

    private static int[] calculateShortestPath(final int start, final List<Node>[] graph) {
        final Queue<Node> pq = new PriorityQueue<>();
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        distances[start] = 0;
        while (!pq.isEmpty()) {
            final Node current = pq.poll();

            if (distances[current.index] < current.cost) {
                continue;
            }

            for (final Node next : graph[current.index]) {
                final int newDistance = distances[current.index] + next.cost;
                if (newDistance < distances[next.index]) {
                    distances[next.index] = newDistance;
                    pq.add(new Node(next.index, newDistance));
                }
            }
        }

        return distances;
    }

    private static class Node implements Comparable<Node> {

        private final int index;
        private final int cost;

        private Node(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
