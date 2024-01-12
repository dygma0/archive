import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();
        final var map = new HashMap<Integer, Boolean>();
        for (final var s : br.readLine().split(" ")) {
            map.put(Integer.parseInt(s), true);
        }

        br.readLine();
        for (final var s : br.readLine().split(" ")) {
            bw.write(map.getOrDefault(Integer.parseInt(s), false) ? "1\n" : "0\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
