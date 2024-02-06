import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        final StringTokenizer inputTokens = new StringTokenizer(reader.readLine());
        final int N = Integer.parseInt(inputTokens.nextToken());
        final int S = Integer.parseInt(inputTokens.nextToken());

        final StringTokenizer sequential = new StringTokenizer(reader.readLine());
        final int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(sequential.nextToken());
        }

        partialSequenceSum(numbers, S, 0, 0);

        if (S == 0) {
            answer--;
        }
        writer.write(String.valueOf(answer));

        writer.flush();
        writer.close();
        reader.close();
    }

    private static void partialSequenceSum(
            final int[] numbers, final int goal, final int index, final int sum) {
        if (index == numbers.length) {
            if (sum == goal) {
                answer++;
            }
            return;
        }
        partialSequenceSum(numbers, goal, index + 1, sum + numbers[index]);
        partialSequenceSum(numbers, goal, index + 1, sum);
    }
}
