import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int n = Integer.parseInt(br.readLine().trim());

        final Pair[] arr = new Pair[n];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(i, Long.parseLong(st.nextToken()));
        }
        Arrays.sort(arr);

        final Solver segmentTree = new Solver(n);
        long count = 0L;
        for (int i = 0; i < n; i++) {
            final int index = arr[i].getIndex();
            count += segmentTree.sum(1, 0, n - 1, index + 1, n - 1);
            segmentTree.update(1, 0, n - 1, index, 1);
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Pair implements Comparable<Pair> {
        private final int index;
        private final long value;

        private Pair(int index, long value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.value, o.value);
        }

        private int getIndex() {
            return index;
        }
    }

    private static class Solver {

        private final int height;
        private final int size;
        private final long[] tree;

        private Solver(long n) {
            this.height = (int) Math.ceil(Math.log(n) / Math.log(2));
            this.size = (int) Math.pow(2, height + 1);
            this.tree = new long[size];
            // init(arr, 1, 0, n - 1);
        }

        private long[] getTree() {
            return tree;
        }

        private long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }
            int mid = (start + end) / 2;
            return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
        }

        private long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
        }

        private long max(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return Math.max(max(node * 2, start, mid, left, right), max(node * 2 + 1, mid + 1, end, left, right));
        }

        private long min(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return Math.min(min(node * 2, start, mid, left, right), min(node * 2 + 1, mid + 1, end, left, right));
        }

        private void update(int node, int start, int end, int index, long diff) {
            if (index < start || index > end) {
                return;
            }
            tree[node] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, index, diff);
                update(node * 2 + 1, mid + 1, end, index, diff);
            }
        }
    }
}
