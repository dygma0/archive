import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int M = Integer.parseInt(tokenizer.nextToken());
        final int K = Integer.parseInt(tokenizer.nextToken());

        final Color[][] board = new Color[N][M];
        for (int i = 0; i < N; i++) {
            final String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) == 'W' ? Color.WHITE : Color.BLACK;
            }
        }

        final int whiteResult = calculateBoard(N, M, K, board, Color.WHITE);
        final int blackResult = calculateBoard(N, M, K, board, Color.BLACK);
        writer.write(String.valueOf(Math.min(whiteResult, blackResult)));

        writer.flush();
        writer.close();
        reader.close();
    }

    private static int calculateBoard(
            final int N, final int M, final int K, final Color[][] board, final Color color) {
        final int[][] prefixSum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value;
                if ((i + j) % 2 == 0) {
                    value = board[i - 1][j - 1] == color ? 0 : 1;
                } else {
                    value = board[i - 1][j - 1] == color ? 1 : 0;
                }
                prefixSum[i][j] =
                        prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + value;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= N - K; i++) {
            for (int j = 0; j <= M - K; j++) {
                final int sum =
                        prefixSum[i + K][j + K] - prefixSum[i][j + K] - prefixSum[i + K][j] + prefixSum[i][j];
                result = Math.min(result, sum);
            }
        }

        return result;
    }

    private enum Color {
        WHITE,
        BLACK;
    }
}
