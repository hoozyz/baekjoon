package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17086 { 
	
	private static int n, m;
	private static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}; // 8방향
	private static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1}; // 8방향
	private static int[][] aria; // 공간
	private static int result = 0;
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		aria = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				aria[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(aria[i][j] == 0) {
					bfs(i,j);
				}
			}
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	// 모든 점 돌면서 1찾아가기 -> result와 비교 후 거리 최댓값 넣기
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visit; // 방문 체크
		visit = new boolean[n][m];
		
		visit[x][y] = true;
		queue.add(new int[] {x,y,0});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int nx = now[0]; // 현재 x 좌표
			int ny = now[1]; // 현재 y 좌표
			int time = now[2]; // 현재 좌표 안전거리 + 1
			
			for (int i = 0; i < 8; i++) {
				int nextX = nx + dx[i];
				int nextY = ny + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
					continue;
				}
				
				if(aria[nextX][nextY] == 1) { // 상어만나면 bfs 끝
					if(result < time + 1) {
						result = time + 1;
					}
					queue.clear();
					break;
				}
				
				if(!visit[nextX][nextY]) {
					visit[nextX][nextY] = true;
					queue.add(new int[] {nextX,nextY,time+1});
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main17086().solution();
	}
}
