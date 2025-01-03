import java.io.*;

public class Main {
   static final int MOD = 1_000_000_007;
   
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String[] input = br.readLine().split(" ");
       int n = Integer.parseInt(input[0]);
       int m = Integer.parseInt(input[1]);
       
       int[] parent = new int[n + 1];
       int[] size = new int[n + 1];
       int[] rank = new int[n + 1];
       
       for (int i = 1; i <= n; i++) {
           parent[i] = i;
           size[i] = 1;
       }
       
       for (int i = 0; i < m; i++) {
           input = br.readLine().split(" ");
           int u = Integer.parseInt(input[0]);
           int v = Integer.parseInt(input[1]);
           union(parent, size, rank, u, v);
       }
       
       long answer = 1L;
       for (int i = 1; i <= n; i++) {
           if (find(parent, i) == i && size[i] > 0) {
               answer = (answer * size[i]) % MOD;
           }
       }
       System.out.println(answer);
   }
   
   static int find(int[] parent, int x) {
       if (parent[x] != x) {
           parent[x] = find(parent, parent[x]);
       }
       return parent[x];
   }
   
   static void union(int[] parent, int[] size, int[] rank, int a, int b) {
       int x = find(parent, a);
       int y = find(parent, b);
       
       if (x != y) {
           if (rank[x] < rank[y]) {
               parent[x] = y;
               size[y] += size[x];
               size[x] = 0;
           } else {
               parent[y] = x;
               size[x] += size[y];
               size[y] = 0;
               if (rank[x] == rank[y]) rank[x]++;
           }
       }
   }
}