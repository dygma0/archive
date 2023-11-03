import java.io.*;
import java.util.*;

public class Main {
    static Map<Character, Integer> map1 = new HashMap<>();
    static Map<Character, Integer> map2 = new HashMap<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        st = new StringTokenizer(br.readLine());
        map1.put('A', Integer.parseInt(st.nextToken()));
        map1.put('C', Integer.parseInt(st.nextToken()));
        map1.put('G', Integer.parseInt(st.nextToken()));
        map1.put('T', Integer.parseInt(st.nextToken()));
        map2.put('A', 0);
        map2.put('C', 0);
        map2.put('G', 0);
        map2.put('T', 0);

        //Initial setting
        for(int i=0; i<=P-2; i++)
            if(isACGT(str.charAt(i))) map2.put(str.charAt(i), map2.get(str.charAt(i))+1);

        int lp = 0; int rp = P-2;
        while (rp<S-1){
            rp++;
            if(isACGT(str.charAt(rp))) map2.put(str.charAt(rp), map2.get(str.charAt(rp))+1);

            if (isPossiblePassword()) count++;

            if(isACGT(str.charAt(lp))) map2.put(str.charAt(lp), map2.get(str.charAt(lp))-1);
            lp++;
        }
        System.out.println(count);
    }

    // Methods
    static boolean isACGT(char c) {
        return (c == 'A' || c == 'C' || c == 'G' || c == 'T');
    }

    static boolean isPossiblePassword() {
        return map2.keySet().stream().filter(x -> map2.get(x)>=map1.get(x)).count() == 4;
    }
}