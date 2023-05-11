package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main21278 { 
	
	private static int n, m; // start 는 시작 숫자
	private static int[] chicken; // 치킨집 2개 위치 배열
	private static boolean[][] line; // 연결 되면 true
	private static int[] result; // 최종 거리 -> 100만으로 잡기
	private static int[] dist;
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		line = new boolean[n+1][n+1];
		chicken = new int[2];
		result = new int[3];
		result[2] = 10000000; // 총 합의 최단거리
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			line[a][b] = true; // 양쪽 다 연결
			line[b][a] = true;
		}
		
		combination(1,0);

		bw.write(result[0] + " " + result[1] + " " + result[2] * 2); // 왕복해야 하므로 결과 거리의 * 2
		bw.flush();
		bw.close();
	}
	
	// 치킨집 2개를 중복x, 순서x 선택하기 -> combination
	private static void combination(int start, int count) {
		if(count == 2) {
			bfs(); // 치킨집이 2개일 때 -> 치킨집 좌표 빼고 가까운 치킨집까지 거리 계산
			return;
		}
		
		for(int i = start; i <= n; i++) {
			chicken[count] = i; // 0번쨰, 1번째 넣기
			combination(i + 1, count + 1); // 
		}
	}
	
	// 치킨집 좌표 2개를 가져와서 가까운 치킨집 거리 계산
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(chicken[0]);
		queue.add(chicken[1]);
		
		dist = new int[n+1]; // 좌표마다 치킨집까지 최단거리
		boolean[] visit = new boolean[n+1]; // 방문 체크
		for(int i = 1; i <= n; i++) dist[i] = n+1; // 최대 거리로 설정 -> 비교를 위해서
		dist[chicken[0]] = 0; // 치킨집은 거리 0
		dist[chicken[1]] = 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i = 1; i <= n; i++) {
				if(line[now][i]) { // now와 연결되어 있는 좌표
					// i좌표의 최단 거리가 현재 좌표+1보다 크면 바꾸기
					if(dist[i] > dist[now] + 1) dist[i] = dist[now] + 1; 
					if(visit[i]) continue;
					queue.add(i); // 다음 좌표로 넣기
					visit[i] = true; // 방문 표시
				}
			}
			
			int sum = 0;
			for(int i = 1; i <= n; i++) {
				sum += dist[i]; // 모든 거리 합치기
			}
			
			if(result[2] > sum) { // 결과값이 현재 치킨집 좌표의 거리 합친거보다 크면 바꾸기
				result[2] = sum;
				result[0] = chicken[0];
				result[1] = chicken[1];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main21278().solution();
	}
}
