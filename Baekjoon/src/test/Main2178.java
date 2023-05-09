package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2178 {

	private static int[][] maze; // 미로 배열
	private static int n, m;
	private static boolean[][] visit; // 방문했나 확인
	private static int[] dx = {-1, 1, 0, 0}; // x방향 좌표이동
	private static int[] dy = {0, 0, -1, 1}; // y방향 좌표이동

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		maze = new int[n][m]; // 2칸 더 추가해서 4방향 다 숫자 있게
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(str.substring(j, j+1));
			}
		}

		// 0,0 부터 bfs 시작
		// 정점마다 4방향 중 1이면 이동하면서 최단거리 찾는거기 때문에 bfs
		bfs(0,0);
		bw.write(maze[n-1][m-1] + "\n");

		bw.flush();
		bw.close();
	}

	private static void bfs(int a, int b) {
		visit[0][0] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a,b});
		
		while(!queue.isEmpty()) {
			int num[] = queue.poll();
			int nx = num[0]; // x좌표
			int ny = num[1]; // y좌표
			
			for(int i = 0; i < 4; i++) { // x좌표, y좌표 만큼 더하기
				int nextX = nx + dx[i];
				int nextY = ny + dy[i];
				
				// 범위 벗어나면
				if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
					continue;
				}
				
				// 방문했거나 값이 0이면
				if(visit[nextX][nextY] || maze[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new int[] {nextX, nextY}); // 큐에 이동한 좌표 넣기
				maze[nextX][nextY] = maze[nx][ny] + 1; // 이동한 좌표에 1추가해서 이동한거 표시
				visit[nextX][nextY] = true; // 방문한거 표시
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main2178().solution();
	}
}
