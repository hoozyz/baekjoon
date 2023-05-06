package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2252 {

	private Map<Integer, Integer> level = new HashMap<>(); // 진입차수 맵
	private Map<Integer, List<Integer>> edgeMap = new HashMap<>();
	private Queue<Integer> queue = new LinkedList<>(); // 진입차수 순서대로 큐에 넣고 넣은 순서대로 빼면서 결과에 넣기

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			List<Integer> list = new ArrayList<>();

			// 진입차수 증가하기
			level.put(b, level.getOrDefault(b, 0) + 1);

			if (edgeMap.containsKey(a)) { // a와 연결된 정점이 있으면
				list = edgeMap.get(a);
				list.add(b);
				edgeMap.put(a, list);

			} else {
				list.add(b);
				edgeMap.put(a, list);

			}
		}

		for (int i = 1; i <= n; i++) {
			// 진입차수 0일 떄 큐에 넣기 -> 진입차수 0 은 안넣어서 디폴트로 0을 넣어준다.
			if (level.getOrDefault(i, 0) == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) { // 큐에 원소가 없을 때까지
			List<Integer> list = new ArrayList<>();
			int num = queue.poll(); // 큐에 있는 원소 가져오기(제거하면서) -> 진입차수 0인 숫자만
			bw.write(num + " ");
			list = edgeMap.get(num);
			
			if(list == null || list.isEmpty()) {} 
			
			else {
				for (int i = 0; i < list.size(); i++) {
					// 연결된 다음 숫자 진입차수 - 1
					level.put(list.get(i), level.get(list.get(i)) - 1);
					if (level.get(list.get(i)) == 0) { // 만약 진입차수 - 1 후 0이되면 큐에 추가
						queue.add(list.get(i));
					}
				}
			}
		}

		bw.flush();
		bw.close();
	}

	// 진입차수가 0인 수 부터 큐에 넣고, 빼면서 연결된 수 차수 - 1
	// 차수가 0이되면 큐에 넣고 빼면서 결과에 넣기 반복

	public static void main(String[] args) throws Exception {
		new Main2252().solution();
	}
}
