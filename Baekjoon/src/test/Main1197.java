package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main1197 { 
	
	private static int[] vertax; // 정점 배열
	private static int[] parents; // 정점의 부모 노드 배열
	private static Set<Integer> weight = new HashSet<>();; // 간선 가중치 배열
	private static Map<Integer, List<Integer>> edge = new HashMap<>(); // 가중치 마다 정점 연결 정보 map
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken()); // 정점 개수
		int e = Integer.parseInt(st.nextToken()); // 간선 개수
		
		int result = 0; // 가중치 더한 결과
		vertax = new int[v];
		parents = new int[v];
		
		for (int i = 0; i < v; i++) {
			vertax[i] = i+1;
			parents[i] = i+1; // 초기 부모 노드는 자기 자신 값
		}
		
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>(); // 가중치마다 간선 목록 (2개마다(정점 2개) 한 간선)
			weight.add(c); // 중복안되게 가중치 넣기
			
			if(edge.containsKey(c)) { // 이미 가중치가 c인 값이 전에 있었을 때
				list = edge.get(c); // 기존 가중치가 c인 간선정보
				list.add(a);
				list.add(b);
				
				edge.replace(c, list);
			} else {
				list.add(a);
				list.add(b);
				
				edge.put(c, list);
			}
		}
		List<Integer> weightList = new ArrayList<>(weight);
		
		Collections.sort(weightList); // 무게set을 리스트로 바꾸고 오름차순 정렬

		// 가중치 순서대로 사이클이 안생길때만 더하기
		for(int w : weightList) {
			List<Integer> list = new ArrayList<>();
			list = edge.get(w);
			
			for(int i = 0; i < list.size(); i += 2) { // 2개마다 간선 하나여서 2개씩
				
				if(findParent(list.get(i)) < findParent(list.get(i+1))) { 
					
					parents[parents[list.get(i+1) - 1] - 1] = parents[list.get(i) - 1];
					result += w;
					
				} else if (findParent(list.get(i+1)) < findParent(list.get(i))) { 
					
					parents[parents[list.get(i) - 1] - 1] = parents[list.get(i+1) - 1];
					result += w;
					
				} else { // 같은 그룹일 때
					continue;
				}
			}
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	private static int findParent(int a) {
		if(parents[a-1] == a) {
			return parents[a-1];
		}
		
		parents[a-1] = findParent(parents[a-1]);
		
		return parents[a-1];
	}
	
	public static void main(String[] args) throws Exception {
		new Main1197().solution();
	}
}
