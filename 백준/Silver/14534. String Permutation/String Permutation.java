import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final var N = Integer.parseInt(br.readLine().trim());
        for (var i = 1; i <= N; i++) {
            bw.write("Case # " + i + ":");
            bw.write('\n');
            new Permutation(br.readLine()).printAll(bw);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Permutation {

        private final String input;

        private Permutation(final String input) {
            this.input = input;
        }

        private void printAll(final BufferedWriter writer) throws IOException {
            final var length = input.length();
            permutation(0, new char[length], new boolean[length], input.toCharArray(), length, writer);
        }

        private void permutation(
                final int depth,
                final char[] buffer,
                final boolean[] visited,
                final char[] chars,
                final int length,
                final BufferedWriter writer)
                throws IOException {
            if (depth == length) {
                writer.write(buffer);
                writer.append('\n');
                return;
            }

            for (var i = 0; i < length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    buffer[depth] = chars[i];
                    permutation(depth + 1, buffer, visited, chars, length, writer);
                    visited[i] = false;
                }
            }
        }
    }
}
