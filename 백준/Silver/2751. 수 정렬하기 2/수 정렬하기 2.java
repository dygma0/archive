import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int size = Integer.parseInt(br.readLine().trim());
        final var solver = new Solver(size);
        for (var i = 0; i < size; i++) {
            final var value = Integer.parseInt(br.readLine().trim());
            solver.add(value);
        }
        
        final var sorted = solver.sorted();
        for (Integer value : sorted) {
            bw.write(value + "\n");
        }
        bw.flush();
    }
    
    static class Solver {
        private List<Integer> list = new ArrayList<>();
        
        Solver(int bufferSize) {
            this.list = new ArrayList(bufferSize);
        }
        
        void add(int value) {
            this.list.add(value);
        }
        
        List<Integer> sorted() {
            final var result = new ArrayList<>(list);
            result.sort(Integer::compareTo);
            return result;
        }
    }
}