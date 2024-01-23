import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final var N = Integer.parseInt(br.readLine());
        final var colors = new boolean[N][N];
        for (var i = 0; i < N; i++) {
            final var line = br.readLine().split(" ");
            for (var j = 0; j < N; j++) {
                colors[i][j] = line[j].equals("1");
            }
        }

        final var paper = new Paper(colors);
        paper.solve();

        bw.write(String.valueOf(paper.getWhiteCount()));
        bw.append('\n');
        bw.write(String.valueOf(paper.getBlueCount()));

        br.close();
        bw.flush();
        bw.close();
    }

    private static class Paper {

        private final boolean[][] colors;

        private int blueCount = 0;

        private int whiteCount = 0;

        private Paper(final boolean[][] colors) {
            this.colors = colors;
        }

        private void solve() {
            doCountRegion(0, 0, colors.length);
        }

        private void doCountRegion(final int x, final int y, final int length) {
            if (checkColor(x, y, length)) {
                if (isBlue(x, y)) {
                    blueCount++;
                } else {
                    whiteCount++;
                }
                return;
            }

            final var halfLength = length / 2;
            doCountRegion(x, y, halfLength);
            doCountRegion(x + halfLength, y, halfLength);
            doCountRegion(x, y + halfLength, halfLength);
            doCountRegion(x + halfLength, y + halfLength, halfLength);
        }

        private boolean checkColor(final int x, final int y, final int length) {
            final boolean color = colors[y][x];
            for (var i = y; i < y + length; i++) {
                for (var j = x; j < x + length; j++) {
                    if (color != colors[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isBlue(final int x, final int y) {
            return colors[y][x];
        }

        private int getBlueCount() {
            return blueCount;
        }

        private int getWhiteCount() {
            return whiteCount;
        }
    }
}
