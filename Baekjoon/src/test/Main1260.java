package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main1260 { 
	
	private boolean[] visit; // 방문 
	private int[][] graph; // 그래프 이차원 배열
	private int n; // 정점 개수
	private Set<Integer> set = new HashSet<>(); // 연결이 된 정점 집합
	private List<Integer> list = new ArrayList<>(); // 오름차순 리스트
	private Queue<Integer> queue = new LinkedList<>(); // 결과를 넣을 큐
	private Queue<Integer> bfsQueue = new LinkedList<>(); // bfs의 정점당 자식 정점 큐
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];
		visit = new boolean[n+1];
		
		for (int i = 0; i < m; i++) { // 그래프 생성
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향
			graph[a][b] = 1; // a와 b 연결된 것을 표현
			graph[b][a] = 1; // a와 b 연결된 것을 표현
			set.add(a);
			set.add(b);
		}
		
		list = new ArrayList<>(set); 
		Collections.sort(list); // 오름차순 정렬
		
		dfs(v);
		int count = queue.size();
		for(int i = 0; i < count; i++) {
			if(i == count - 1) {
				bw.write(queue.poll() + "\n");
			} else {
				bw.write(queue.poll() + " ");
			}
		}
		
		visit = new boolean[n+1]; // 방문 배열 초기화
		
		bfs(v);
		count = queue.size();
		for(int i = 0; i < count; i++) {
			if(i == count - 1) {
				bw.write(queue.poll() + "\n");
			} else {
				bw.write(queue.poll() + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private void dfs(int a) {
		visit[a] = true; // 방문했다는 것을 표시
		queue.add(a); // 순서대로 넣기
		
		for(int i = 0; i < list.size(); i++) {
			if(graph[a][list.get(i)] == 1 && visit[list.get(i)] == false) { // 연결되어 있고, 방문하지 않았을 때
				dfs(list.get(i));
			}
		}
	}
	
	private void bfs(int a) {
		visit[a] = true;
		bfsQueue.add(a);
		
		while(!bfsQueue.isEmpty()) {
			int num = bfsQueue.poll(); // 현재 정점의 연결된 정점 확인을 위해 큐에 넣은거 제거
			queue.add(num);
			
			for(int i = 0; i < list.size(); i++) {
				// 현재 정점의 자식 정점을 작은 순서대로 방문
				if(graph[num][list.get(i)] == 1 && visit[list.get(i)] == false) {
					bfsQueue.add(list.get(i));
					visit[list.get(i)] = true;
				}
			}
		}
		
	}
	 
	public static void main(String[] args) throws Exception {
		new Main1260().solution();
	}
}
