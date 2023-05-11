package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15652 { 
	
	private static int n, m;
	private static int[] num;
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[m];
		
		backTracking(0);
		
		bw.flush();
		bw.close();
	}
	
	private static void backTracking(int len) throws IOException {
		if(len == m) {
			for(int i = 0; i < m; i++) {
				bw.write(num[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(len > 0 && num[len-1] > i) { // 이전 숫자보다 작으면 중복되므로 제거
				continue;
			}
			num[len] = i;
			backTracking(len + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main15652().solution();
	}
}
