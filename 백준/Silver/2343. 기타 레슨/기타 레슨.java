import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final var nm = br.readLine().split(" ");
        final var M = Integer.parseInt(nm[1]);

        final var durations =
                Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(String.valueOf(new Studio(durations).calculateOptimalDuration(M)));

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Studio {

        private final int[] durations;

        private Studio(final int[] durations) {
            this.durations = Arrays.copyOf(durations, durations.length);
        }

        private int calculateOptimalDuration(final int requiredGroupSize) {
            var start = findMaxDuration();
            var end = sumDurations();
            while (start < end) {
                final var mid = (start + end) >> 1;
                final var groupCount = countGroups(mid);
                if (groupCount <= requiredGroupSize) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            return start;
        }

        private int countGroups(final int minimumPlayTime) {
            var groupCount = 0;
            var temp = 0;
            for (final var time : durations) {
                if (minimumPlayTime < temp + time) {
                    temp = time;
                    groupCount += 1;
                    continue;
                }

                temp += time;
            }

            final var LAST_VIDEO_GROUP = 1;
            return groupCount + LAST_VIDEO_GROUP;
        }

        private int findMaxDuration() {
            return Arrays.stream(durations).max().orElse(0);
        }

        private int sumDurations() {
            return Arrays.stream(durations).reduce(0, Integer::sum);
        }
    }
}
