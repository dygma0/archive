import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws Exception {
    final var br = new BufferedReader(new InputStreamReader(System.in));
    final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final var N = Integer.parseInt(br.readLine().trim());
    final var solver = new Solver(N);

    final var tokens = new StringTokenizer(br.readLine().trim());
    while (tokens.hasMoreTokens()) {
      final var value = Integer.parseInt(tokens.nextToken());
      solver.add(value);
    }
    solver.sort();

    bw.write(solver.getTotalDelayTimes() + "");
    bw.flush();
  }

  static class Solver {

    private final List<Integer> buffer;

    Solver(int size) {
      buffer = new ArrayList<>(size);
    }

    void add(int value) {
      buffer.add(value);
    }

    void sort() {
      buffer.sort(Integer::compareTo);
    }

    int getTotalDelayTimes() {
      int total = 0;
      int delay = 0;
      for (final var value : buffer) {
        delay += value;
        total += delay;
      }
      return total;
    }
  }
}
