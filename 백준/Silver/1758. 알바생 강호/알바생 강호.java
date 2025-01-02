import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        
        // ArrayList 대신 Arrays.sort를 사용하기 위해 배열 사용
        int[] tips = new int[n];
        for (int i = 0; i < n; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
        java.util.Arrays.sort(tips);

        long result = 0;  // 큰 수를 대비해 long 사용
        for (int i = n - 1; i >= 0; i--) {
            int tip = tips[i] - (n - i - 1);
            if (tip > 0) {
                result += tip;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}