import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final int N = Integer.parseInt(br.readLine().trim());

    Solver solver = new Solver(N);
    solver.solve();

    bw.write(solver.getAnswer().toString());
    bw.flush();
  }

  static class Solver {
    private final Queue<Integer> queue = new LinkedList<>();

    public Solver(int N) {
      initQueue(N);
    }

    public Integer getAnswer() {
      return queue.peek();
    }

    public void solve() {
      while (queue.size() > 1) {
        queue.poll();
        queue.add(queue.poll());
      }
    }

    private void initQueue(int n) {
      for (int i = 1; i <= n; i++) {
        queue.add(i);
      }
    }
  }
}
