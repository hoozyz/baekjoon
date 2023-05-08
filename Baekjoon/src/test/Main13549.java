package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13549 {

	private int n, k;
	private Queue<Integer> queue = new LinkedList<>();
	private int result = 100002;
	private int[] time;
	private boolean[] visit; // 방문 체크

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(k <= n) {
			bw.write(n-k + "\n");
			bw.flush();
			bw.close();
			return;
		}

		time = new int[100001];
		visit = new boolean[100001];

		bfs();

		bw.write(time[k] + "\n");
		bw.flush();
		bw.close();
	}

	private void bfs() {
		queue.add(n);
		time[n] = 0;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (result < time[now]) {
				return;
			}

			int next;

			// 2배일때는 시간 증가 x -> 2배 부터 해야한다.
			next = now * 2;

			if (next <= 100000) {
				if (next == k) {
					result = time[now];
				}

				if (time[next] == 0 || time[next] == time[now]) {
					queue.add(next);
					time[next] = time[now];
					visit[next] = true; // 2배일때만 방문 체크
				}
			}

			for (int i = 0; i < 2; i++) {

				if (i == 0) {
					next = now - 1;

				} else {
					next = now + 1;

				}

				if (next < 0 || next > 100000) {
					continue;
				}

				if (next == k) {
					result = time[now];
				}
				
				if (time[next] == 0 || time[next] == time[now] + 1) {
					if(!visit[next]) { // 방문했다면 -> 2배이후 +1했을 때 같은 숫자면 패스 
						queue.add(next);
						time[next] = time[now] + 1;
					}
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		new Main13549().solution();
	}
}
