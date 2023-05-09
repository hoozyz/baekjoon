package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main13913 { 
	
	private static int n, k;
	private static int[] time; // 방문하는 좌표마다 이동시간
	private static int[] parent; // 전(부모) 좌표 넣기
	private static Queue<Integer> queue = new LinkedList<>(); // 경로(리스트)를 넣을 큐 
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		time = new int[100001];
		parent = new int[100001];
		
		if(k <= n) {
			for(int i = n; i <= k; i--) {
				bw.write(i + " ");
			}
			bw.flush();
			bw.close();
			return;
		}
		
		bfs();
		
		// k부터 부모를 따라가면 출력을 위해 stack 이용
		Stack<Integer> stack = new Stack<>();
		int num = k;
		stack.add(k); // k 먼저 넣기
		
		while(num != n) { // n이면 빠져나가기
			num = parent[num]; 
			stack.add(num); // 전 좌표를 가져와서 넣기
		}
		
		bw.write(time[k] + "\n");
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void bfs() {
		queue.add(n);
		time[n] = 0; // 처음 좌표는 시간 0
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(now == k) {
				return;
			}
			
			for(int i = 0; i < 3; i++) {
				int next;
				
				if(i == 0) { // x-1
					next = now -1;
					
				} else if (i == 1) { // x+1
					next = now + 1;
					
				} else { // x*2
					next = now * 2;
					
				}
				
				// 범위 넘으면
				if(next < 0 || next > 100000) {
					continue;
				}
				
				// 첫 방문일떄만 -> 경로만 구하는거여서
				if(time[next] == 0) {
					queue.add(next);
					time[next] = time[now] + 1;
					parent[next] = now; // 다음거의 전 좌표를 현재 좌표로 설정 
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main13913().solution();
	}
}
