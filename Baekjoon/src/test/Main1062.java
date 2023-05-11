package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1062 { 
	
	private static int n, k; 
	private static int max = 0; // 0으로 설정 후 크면 바꾸기 -> 문자열로 만들 수 있는 단어 최대 개수
	private static String[] str; // 중간 문자열 배열
	private static boolean[] visit; // 알파벳 배웠는지 체크
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		str = new String[n];
		visit = new boolean[26];
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			s = s.substring(4, s.length() - 4); // 앞 뒤 뺀 문자열
			s.replace("a",""); // 미리 제거해서 간편하게
			s.replace("n","");
			s.replace("t","");
			s.replace("i","");
			s.replace("c","");
			str[i] = s;
		}
		
		visit['a' - 'a'] = true; // 알파벳을 숫자로 바꾸기
		visit['n' - 'a'] = true;
		visit['t' - 'a'] = true;
		visit['i' - 'a'] = true;
		visit['c' - 'a'] = true;
		
		if(k < 5) { // anta tica 는 5개로 가능해서 5개 미만이면 단어 불가능
			bw.write(0 + "\n");
			bw.flush();
			bw.close();
			return;
		} else if(k == 26) { // 다 읽을 수 있다.
			bw.write(n + "\n");
			bw.flush();
			bw.close();
			return;
		}
		
		backTracking(0,5); // 알파벳 길이 5개 기본적으로
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
	
	// 알파벳과 길이
	private static void backTracking(int alpha, int len) {
		if(len == k) { // k 개 일때마다 닫어 n개 돌면서 읽을 수 있는 문자 개수 세기
			int count = 0;
			for(int i = 0; i < n; i++) {
				boolean read = true; // 읽을 수 있다고 가정하고 아래에서 읽을 수 없다는 것을 판별하면 false
				for(int j =0; j < str[i].length(); j++) {
					if(!visit[str[i].charAt(j) - 'a']) { // 알파벳이 추가된적이 없으면 읽을 수 없음
						read = false;
						break;
					}
				}
				if(read) count++; // 읽을 수 있을때만 개수 더하기
			}
			max = Math.max(max, count); // 알파벳 조합마다 최대 개수로 바꾸기
			return;
		}
		
		// 알파벳을 하나씩 추가하면서 k개 일때마다 모든 단어 순회
		for(int i = alpha; i < 26; i++) {
			if(!visit[i]) {
				visit[i] = true;
				backTracking(i, len + 1);
				visit[i] = false; // 되돌리기
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main1062().solution();
	}
}
