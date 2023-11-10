import java.io.*;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine().trim());
    int[] seq = new int[N];
    for (int i = 0; i < N; i++) {
      seq[i] = Integer.parseInt(br.readLine());
    }

    Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int seqIndex = 0;
    for (int i = 1; i <= N; i++) {
      stack.push(i);
      sb.append("+\n");
      while (!stack.isEmpty() && stack.peek() == seq[seqIndex]) {
        seqIndex += 1;
        stack.pop();
        sb.append("-\n");
      }
    }

    bw.write(stack.isEmpty() ? sb.toString() : "NO");
    bw.flush();
    bw.close();
    br.close();
  }
}
