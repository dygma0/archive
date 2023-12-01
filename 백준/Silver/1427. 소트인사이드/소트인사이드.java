import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    
    public static void main(String[] args) throws Exception {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        final var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final var solver = new Solver(br.readLine().trim());
        final var descSolver = solver.orderByDesc();
        for (var value : descSolver) {
            bw.write(value + "");
        }
        bw.flush();
    }
    
    static class Solver {
        
        private final List<Integer> buffer;
        
        Solver(String numberChars) {
            buffer = Arrays.stream(numberChars.trim().split("")).map(Integer::parseInt).collect(Collectors.toList());
        }
        
        List<Integer> orderByDesc() {
            final var result = new ArrayList<>(buffer);
            result.sort((a, b) -> b - a);
            return result;
        }
    }
}