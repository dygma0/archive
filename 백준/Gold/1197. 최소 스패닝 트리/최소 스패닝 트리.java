import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] edges = new int[E][3];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        int[] parent = new int[V + 1];
        int[] rank = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int answer = 0;
        for (int i = 0; i < E; i++) {
            int a = find(parent, edges[i][0]);
            int b = find(parent, edges[i][1]);
            if (a != b) {
                union(parent, rank, a, b);
                answer += edges[i][2];
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    private static void union(final int[] parent, final int[] rank, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a == b) {
            return;
        }

        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
            if (rank[a] == rank[b]) {
                rank[a]++;
            }
        }
    }
}
