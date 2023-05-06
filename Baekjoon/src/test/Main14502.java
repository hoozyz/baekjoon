package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14502 { // 14502
	
	private int[][] arr;
	private int[][] test;
	private int n;
	private int m;
	private int max;
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		test = new int[n][m]; // 테스트 전용
		max = 0; // 최대 안전지대 수
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
	
	private void bfs() {
		for(int i =0; i < n; i++) {
			for (int j =0; j < m; j++) {
				test[i][j] = arr[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			int safe = 0; // 안전지대 수
			
			// 바이러스 퍼뜨리기
			for (int j = 0; j < m; j++) {
				if(test[i][j] == 2) {
					System.out.println(i+","+j);
					virus(i,j);
				}
			}
			
			// 안전지대 수 
			for (int j = 0; j < m; j++) {
				if(test[i][j] == 0) {
					safe++;
				}
			}
			
			if(max < safe) {
				max = safe;
				
				
				System.out.println(safe);
				
				for(int j = 0; j < n; j++) {
					for (int k = 0; k < m; k++) {
						if(k != m-1) {
							System.out.print(test[j][k]);
						} else {
							System.out.println(test[j][k]);
						}
					}
				}
			}
		}
		
	}
	
	// 0,0 부터 0을 고른 횟수가 3개 일 때마다 바이러스 퍼뜨리고 안전지대 수 구하기
	private void dfs(int depth) {
		if(depth == 3) {
			bfs(); // 벽은 다 세웠으니 바이러스 퍼뜨리고 안전지대 세기
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1; // 벽 세우기
					dfs(depth+1);
					arr[i][j] = 0; // 다시 되돌리기 -> 원래 배열은 그대로
				}
			}
		}
	}
	
	private void virus(int x, int y) { // n,m 이 2인 바이러스 일 때 주변이 0이면 2로 변경 -> 회귀
		if(x-1 >= 0) { // 위 칸이 있을 때
			if(test[x-1][y] == 0) {
				test[x-1][y] = 2;
				virus(x-1,y); // 퍼진 바이러스가 주변에 벽이 없으면 퍼지게 회귀
			}
		}
		if(y-1 >= 0) { // 왼쪽 칸이 있을 떄
			if(test[x][y-1] == 0) {
				test[x][y-1] = 2;
				virus(x,y-1);
			}
		}
		if(x+1 <= n-1) { // 아래 칸이 있을 떄
			if(test[x+1][y] == 0) {
				test[x+1][y] = 2;
				virus(x+1,y);
			}
		}
		if(y+1 <= m-1) { // 오른쪽 칸이 있을 때
			if(test[x][y+1] == 0) {
				test[x][y+1] = 2;
				virus(x,y+1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main14502().solution();
	}
}
