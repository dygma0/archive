import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        final int[] arr = {2, 3, 5, 7};

        final Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            queue.offer(String.valueOf(arr[i]));
        }

        for (int i = 0; i < N - 1; i++) {
            final int size = queue.size();
            for (int j = 0; j < size; j++) {
                final String str = queue.poll();
                for (int k = 1; k <= 9; k++) {
                    final int num = Integer.parseInt(str + k);
                    boolean isPrime = true;
                    for (int l = 2; l <= Math.sqrt(num); l++) {
                        if (num % l == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                    if (isPrime) {
                        queue.offer(String.valueOf(num));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            bw.write(queue.poll());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
