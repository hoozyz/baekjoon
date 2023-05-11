package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15649 { 
	
	private static int n, m;
	private static boolean[] visit;
	private static int[] num;
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[m];
		visit = new boolean[n+1];
		
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
			if(!visit[i]) {
				num[len] = i;
				visit[i] = true;
				backTracking(len + 1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main15649().solution();
	}
}
