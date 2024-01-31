import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var N = Integer.parseInt(br.readLine().trim());

        final var arr = new int[N];
        final var st = new StringTokenizer(br.readLine().trim());
        for (var i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        final var dp = new int[N][N];
        for (var i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(arr, dp, 0, N - 1));
    }

    private static int solve(final int[] arr, final int[][] dp, final int left, final int right) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        var count = 0;
        if (arr[left] == arr[right]) {
            count = dp[left][right] = solve(arr, dp, left + 1, right - 1);
        } else {
            count = Math.min(solve(arr, dp, left + 1, right), solve(arr, dp, left, right - 1)) + 1;
        }

        return dp[left][right] = count;
    }
}
