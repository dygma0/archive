import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(
                    Arrays.stream(br.readLine().split(" "))
                            .filter(s -> !s.isEmpty())
                            .map(Integer::parseInt)
                            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
        }

        final int[] parents = new int[N + 1];
        final int[] ranks = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
            ranks[i] = 0;
        }

        final List<Load> alreadyConnected = new ArrayList<>();
        final List<Load> notConnected = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = i; j < graph.get(i).size(); j++) {
                final int value = graph.get(i).get(j);

                if (i == j && value == 0) {
                    continue;
                }

                if (value < 0) {
                    alreadyConnected.add(new Load(i + 1, j + 1, -value));
                } else {
                    notConnected.add(new Load(i + 1, j + 1, value));
                }
            }
        }
        notConnected.sort(Comparator.comparingInt(o -> o.value));

        int totalValue = 0;
        final List<Load> result = new ArrayList<>();
        for (final Load load : alreadyConnected) {
            union(parents, ranks, load.from, load.to);
            totalValue += load.value;
        }

        for (final Load load : notConnected) {
            final int from = load.from;
            final int to = load.to;
            final int value = load.value;
            if (find(parents, from) != find(parents, to)) {
                union(parents, ranks, from, to);
                totalValue += value;
                result.add(load);
            }
        }

        bw.write(totalValue + " " + result.size() + "\n");
        for (final Load load : result) {
            bw.write(load.from + " " + load.to + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(final int[] parents, final int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents, parents[x]);
    }

    private static void union(final int[] parents, final int[] ranks, final int x, final int y) {
        final int px = find(parents, x);
        final int py = find(parents, y);

        if (px == py) {
            return;
        }

        if (ranks[px] < ranks[px]) {
            parents[px] = py;
        } else {
            parents[py] = px;
            if (ranks[px] == ranks[py]) {
                ranks[px]++;
            }
        }
    }

    private static class Load {

        private int from;

        private int to;

        private int value;

        private Load(final int from, final int to, final int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
