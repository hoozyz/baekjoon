package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1966 { // 1966
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(st.nextToken()); // 시작 값

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken()); // 문서 개수
			int num = Integer.parseInt(st.nextToken()); // 원하는 문서 번호

			
			st = new StringTokenizer(br.readLine()); // 큐
			Queue<String> queue = new LinkedList<>(); // 큐 선언
			
			// 우선순위 큐 -> 정렬 큐
			// Collections.reverseOrder)() -> 내림차순
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			
			for(int j = 0; j < count; j++) {
				int nNum = Integer.parseInt(st.nextToken());
				queue.add(nNum + "-" + j); // 큐에 추가 -> 중복 숫자 구별을 위해 인덱스 추가
				pq.add(nNum);
			}
			
 			int check = 1;
			while (true) {
				if(Integer.parseInt(queue.peek().substring(0,1)) != pq.peek()) { // 값이 최댓값이 아닐 때
					queue.add(queue.poll()); // poll() : 첫 번째 값 반환 후 제거
					
				} else { // 최댓값이면 출력 -> 우선순위 큐에서도 삭제
					if(num == Integer.parseInt(queue.poll().substring(2))) { // 궁금한 문서면 
						bw.write(check + "\n");
						break;
					}
					pq.poll();
					check++;
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws Exception {
		new Main1966().solution();
	}
}
