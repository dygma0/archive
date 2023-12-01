import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    final var br = new BufferedReader(new InputStreamReader(System.in));
    final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

    var st = new StringTokenizer(br.readLine().trim());
    var N = Integer.parseInt(st.nextToken());
    var K = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine().trim());
    final var solver = new Solver(N);
    while (st.hasMoreTokens()) {
      final var value = Integer.parseInt(st.nextToken());
      solver.add(value);
    }

    final var sortedSolver = solver.sorted();
    final var answer = sortedSolver.get(K);
    bw.write(answer + "");
    bw.flush();
  }

  static class Solver {

    private final List<Integer> buffer;

    Solver(int size) {
      buffer = new ArrayList<>(size);
    }

    Solver(List<Integer> buffer) {
      this.buffer = buffer;
    }

    void add(int value) {
      buffer.add(value);
    }

    Solver sorted() {
      final var result = new ArrayList<>(buffer);
      result.sort(Integer::compareTo);
      return new Solver(result);
    }

    Integer get(int position) {
      return buffer.get(position - 1);
    }
  }
}