package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main12851 { 
	
	private static int n, k;
	private static int[] time; // 방문하는 좌표마다 이동시간
	private static int result = 100002; // 방문시간이 제일 적은 시간 -> 처음에 가장 긴 시간보다 큰 값을 넣음
	private static Queue<Integer> queue = new LinkedList<>(); // 경로(리스트)를 넣을 큐 
	private static int count = 0;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		time = new int[100001];
		
		if(k <= n) { // 동생이 누나보다 앞에 있으면 방법 한가지
			bw.write(n - k + "\n" + 1);
			bw.flush();
			bw.close();
			return;
		}
		
		bfs();
		
		bw.write(time[k] + "\n" + count);
		bw.flush();
		bw.close();
	}
	
	private static void bfs() {
		queue.add(n);
		time[n] = 0; // 처음 좌표는 시간 0
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			// 시간이 제일 적은 거 보다 크면 이미 최단 경로 찾은 이후
			if(result < time[now]) {
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
				
				if(next == k) { // 동생을 찾았을 때
					count++;
					result = time[now]; // 시간을 넣음
				}
				
				// 첫 방문이거나, 이미 방문했어도 같은 시간에 방문하면 -> 1에서 2는 +1, *2 로 다 방문 가능하다.
				if(time[next] == 0 || time[next] == time[now] + 1) {
					queue.add(next);
					time[next] = time[now] + 1;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main12851().solution();
	}
}
