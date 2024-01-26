import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final var N = Integer.parseInt(br.readLine());
        final var holder = new Holder();
        for (var i = 0; i < N; i++) {
            holder.add(Integer.parseInt(br.readLine()));
        }

        bw.write(String.valueOf(holder.getAnswer()));

        br.close();
        bw.flush();
        bw.close();
    }

    private static class Holder {

        private final List<Integer> positive = new ArrayList<>();
        private final List<Integer> negative = new ArrayList<>();

        private void add(final Integer card) {
            if (card > 0) {
                positive.add(card);
                return;
            }
            negative.add(card);
        }

        private int getAnswer() {
            positive.sort(Comparator.reverseOrder());
            negative.sort(Comparator.naturalOrder());

            return getSum(getSum(0, positive), negative);
        }

        private int getSum(final int base, final List<Integer> positive) {
            var sum = base;
            for (var i = 0; i < positive.size();) {
                if (i + 1 < positive.size() && positive.get(i + 1) != 1 && positive.get(i) != 1) {
                    sum += positive.get(i++) * positive.get(i++);
                    continue;
                }
                sum += positive.get(i++);
            }
            return sum;
        }
    }
}
