package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1717 { 
	
	private int[] elements; // 원소 배열 1 ~ n 까지
	private int[] parents; // 부모 노트 배열
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 원소 개수
		int m = Integer.parseInt(st.nextToken()); // 연산 개수
		
		elements = new int[n+1];
		parents = new int[n+1];
		
		for (int i = 0; i <= n; i++) {
			elements[i] = i;
			parents[i] = i; // 처음에 부모 노드는 자신
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int check = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(check == 0) { // Union(같은 집합으로 묶는) 연산
				
				if(a == b) { // 둘이 같으면 연산 필요 없음
					continue;
				} else if (findParent(a) < findParent(b)){
					
					// 부모 노드의 값이 큰 그룹의 부모 노드의 부모 노드를 값이 작은 그룹의 부모 노드의 값으로 바꾸기
					// 나중에 findParent 메소드로 그룹 노드들의 부모 노드를 작은 그룹의 부모 노드로 연결
					parents[parents[b]] = parents[a];
					
				} else if (findParent(b) < findParent(a)) {
					
					parents[parents[a]] = parents[b];
				}
				
			} else { // Find(같은 집합인지 확인하는) 연산
				
				if(a == b) {
					bw.write("YES" + "\n");
				} else {

					if(findParent(a) == findParent(b)) { // 최종 제일 작은 부모 노드랑 같을 때
						bw.write("YES" + "\n");
					} else {
						bw.write("NO" + "\n");
					}
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private int findParent(int a) { // a가 속한 집합의 제일 작은 숫자 찾기 -> 부모 노드를 계속 찾는 재귀
		
		if(parents[a] == a) { // 부모 노드가 같으면 현재 노드가 제일 작은 숫자
			return parents[a];
		}
			
		parents[a] = findParent(parents[a]);
		
		return parents[a];
	}
	
	
	public static void main(String[] args) throws Exception {
		new Main1717().solution();
	}
}
