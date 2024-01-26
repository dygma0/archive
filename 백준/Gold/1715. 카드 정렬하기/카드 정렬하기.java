import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final var N = Integer.parseInt(br.readLine());
        final var cardHolder = new CardHolder();

        for (int i = 0; i < N; i++) {
            cardHolder.add(Integer.parseInt(br.readLine()));
        }
        bw.write(String.valueOf(cardHolder.getAnswer()));

        br.close();
        bw.flush();
        bw.close();
    }

    private static class CardHolder {
        
        private final Queue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        
        private void add(final Integer card) {
            queue.add(card);
        }
        
        private int getAnswer() {
            var sum = 0;
            while (queue.size() > 1) {
                final var a = queue.poll();
                final var b = queue.poll();
                sum += a + b;
                queue.add(a + b);
            }
            return sum;
        } 
    }
}
