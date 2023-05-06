package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main1700_later { // 나중에 풀거
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 멀티탭 구멍 개수
		int k = Integer.parseInt(st.nextToken()); // 전기용품
		
		for (int i = 0; i < k; i++) {
			
		}
		
		bw.flush();
		bw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new Main1700_later().solution();
	}
}
