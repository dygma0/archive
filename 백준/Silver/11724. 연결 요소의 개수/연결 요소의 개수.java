import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);

        final Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            final String[] uv = br.readLine().split(" ");
            final int u = Integer.parseInt(uv[0]) - 1;
            final int v = Integer.parseInt(uv[1]) - 1;
            graph.addEdge(u, v);
        }
        bw.write(String.valueOf(graph.countConnectedComponents()));

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Graph {
        private final int n;
        private final List<List<Integer>> adj;

        public Graph(int n) {
            this.n = n;
            this.adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                this.adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            this.adj.get(u).add(v);
            this.adj.get(v).add(u);
        }

        public int countConnectedComponents() {
            final boolean[] visited = new boolean[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i, visited);
                    count++;
                }
            }
            return count;
        }

        private void dfs(int u, boolean[] visited) {
            visited[u] = true;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    dfs(v, visited);
                }
            }
        }

        private void bfs(int u, boolean[] visited) {
            final Queue<Integer> q = new LinkedList<>();
            q.offer(u);
            visited[u] = true;
            while (!q.isEmpty()) {
                final int v = q.poll();
                for (int w : adj.get(v)) {
                    if (!visited[w]) {
                        q.offer(w);
                        visited[w] = true;
                    }
                }
            }
        }
    }
}
