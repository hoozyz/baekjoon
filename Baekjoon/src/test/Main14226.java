package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14226 { 
	
	private int s;
	private int[][] time;
	private int result = 2001;
	private Queue<int[]> queue = new LinkedList<>();
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		
		time = new int[2001][1001];
		
		bfs();
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	private void bfs() {
		int display = 1; // 화면 이모티콘 개수
		int clip = 0; // 클립보드 이모티콘 개수
		queue.add(new int[] {display, clip});
		
		while(!queue.isEmpty()) {
			int[] set = queue.poll();
			display = set[0];
			clip = set[1];
			
			// 클립보드의 이모티콘을 화면에 복사하는 경우 -> 복사했을 때 방문 한적이 없을때만 추가
			if(display < s && time[display+clip][clip] == 0) {
				queue.add(new int[] {display+clip,clip});
				time[display+clip][clip] = time[display][clip] + 1;
				if(display+clip == s) {
					result = time[display+clip][clip] - 1;
					return;
				}
			}
			
			// 화면 이모티콘 개수가 0보다 크면 -1하거나, 클립보드로 복사
			if(display > 0) { 
				// 이모티콘 개수 -1 한게 방문 안했을 때
				if(time[display-1][clip] == 0) {
					queue.add(new int[] {display-1,clip});
					time[display-1][clip] = time[display][clip] + 1;
					if(display-1 == s) {
						result = time[display-1][clip] - 1;
						return;
					}
				}
				
				// 이모티콘을 클립보드에 복사한게 방문 안했을 때 -> 복사
				if(display < s && time[display][display] == 0) {
					queue.add(new int[] {display,display});
					time[display][display] = time[display][clip] + 1;
				}
			}
			
		}
	}
 	
	public static void main(String[] args) throws Exception {
		new Main14226().solution();
	}
}
