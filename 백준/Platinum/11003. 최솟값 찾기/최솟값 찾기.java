import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력 개수 N과 구간 수 L 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		// N개의 수 입력
		st = new StringTokenizer(br.readLine());

		// 값을 보관 및 정렬 해 줄 deque
		Deque<Node> mydeque = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			int now = Integer.parseInt(st.nextToken());

			// 덱이 비거나, 덱 마지막에 현재 값(now)보다 큰 값 지우기
			while(!mydeque.isEmpty() && mydeque.getLast().value > now) 
				mydeque.removeLast();
			
			// 덱 마지막에 값 삽입
			mydeque.addLast(new Node(i, now));
				
			// 덱의 첫번째 인덱스에서 범위를 벗어난 값 지우기
			while(mydeque.getFirst().index <= i-L)
				mydeque.removeFirst();
			
          		  // 최소값 출력
			bw.write(mydeque.getFirst().value +" ");
		}
		bw.flush();
	}
}

class Node {
	public int index, value;
	public Node(int index, int value) {
		this.index = index;
		this.value = value;
	}
}