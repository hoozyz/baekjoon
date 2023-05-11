package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main9095 { 
	
	private static int[] dp; // 순서대로 dp의 결과를 담을 배열
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[11];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		// 4부터 반복
		for(int i = 4; i <= 10; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			
			bw.write(dp[x] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws Exception {
		new Main9095().solution();
	}
}
