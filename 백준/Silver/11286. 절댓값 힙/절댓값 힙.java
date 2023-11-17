import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final int N = Integer.parseInt(br.readLine().trim());
    final Solver solver = new Solver();

    for (int i = 0; i < N; i++) {
      final int input = Integer.parseInt(br.readLine().trim());

      if (input == 0) {
        bw.write(solver.poll().toString() + "\n");
        continue;
      }
      solver.add(input);
    }

    bw.flush();
  }

  static class Solver {

    private final Comparator<Integer> comparator = (o1, o2) -> {
      int left = Math.abs(o1);
      int right = Math.abs(o2);
      if (left == right) {
        return o1 - o2;
      }
      return left - right;
    };

    private final Queue<Integer> queue = new PriorityQueue<>(comparator);

    public Solver() {
    }

    public void add(int input) {
      queue.add(input);
    }

    public Integer poll() {
      if (queue.isEmpty()) {
        return 0;
      }

      return queue.poll();
    }
  }
}
