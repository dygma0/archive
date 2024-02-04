import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    static final int[] directionX = new int[] {0, 0, -1, 1};
    static final int[] directionY = new int[] {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        final int length = Integer.parseInt(reader.readLine());
        final int[][] map = new int[length][length];
        for (int i = 0; i < length; i++) {
            final List<Integer> numbers =
                    Arrays.stream(reader.readLine().split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
            for (int j = 0; j < length; j++) {
                map[i][j] = numbers.get(j);
            }
        }

        final int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dfs(map, dp, j, i);
            }
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        writer.write(String.valueOf(max));

        writer.flush();
        writer.close();
        reader.close();
    }

    private static int dfs(final int[][] map, final int[][] dp, final int x, final int y) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        dp[y][x] = 1;
        for (int i = 0; i < 4; i++) {
            final int nextX = x + directionX[i];
            final int nextY = y + directionY[i];
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map.length) {
                continue;
            }
            if (map[nextY][nextX] > map[y][x]) {
                dp[y][x] = Math.max(dp[y][x], dfs(map, dp, nextX, nextY) + 1);
            }
        }

        return dp[y][x];
    }
}
