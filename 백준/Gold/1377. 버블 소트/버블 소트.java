import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final int N = Integer.parseInt(br.readLine().trim());
    final Solver solver = new Solver(N);
    for (int i = 0; i < N; i++) {
      final int n = Integer.parseInt(br.readLine().trim());
      solver.add(n);
    }

    final Solver sortedSolver = solver.sorted();
    final int answer = solver.diff(sortedSolver) + 1;

    bw.write(String.valueOf(answer));
    bw.flush();
  }

  static class Solver {
    private final List<Node> list;

    private final Map<Solver, Integer> cache = new HashMap<>();

    private int index = 0;

    public Solver(final int bufferSize) {
      list = new ArrayList<>(bufferSize);
    }

    public Solver(final List<Node> list) {
      this.list = list;
    }

    public void add(final int value) {
      list.add(new Node(value, index++));
    }

    public Solver sorted() {
      // list sort는 원본 데이터를 복사 후 정렬하기 때문에 메모리가 2배 그리고 O(nlogn + N)
      // 데이터 정렬 후 정렬된 데이터를 기존 버퍼에 복사하는 과정이 추가되기 때문에 O(N)이 추가된다.
      List<Node> sortedNodes = new ArrayList<>(list);
      sortedNodes.sort(Comparator.comparingInt(Node::getValue));
      return new Solver(sortedNodes);
    }

    public int diff(Solver solver) {
      if (cache.containsKey(solver)) {
        return cache.get(solver);
      }

      int maxDiff = getMaxDiff(solver);
      cache.put(solver, maxDiff);
      return maxDiff;
    }

    private int getMaxDiff(Solver solver) {
      int maxDiff = 0;
      int i = 0;

      for (Node node : list) {
        Node otherNode = solver.getNode(i);
        int diff = otherNode.getIndex() - node.getIndex();
        maxDiff = Math.max(maxDiff, diff);
        i++;
      }

      return maxDiff;
    }

    private Node getNode(int index) {
      return list.get(index);
    }

  }

  static class Node {
    private final int value;
    private final int index;

    Node(int value, int index) {
      this.value = value;
      this.index = index;
    }

    public int getValue() {
      return value;
    }

    public int getIndex() {
      return index;
    }
  }
}
