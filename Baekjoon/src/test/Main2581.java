package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2581 { // 2581
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int m = Integer.parseInt(st.nextToken()); // 시작 값
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 최종 값

		int sum = 0;
		int first = 0;
		
		for (int i = m; i <= n; i++) {
			if(i == 1) {
				continue;
			}
			
			if(prime(i) == i) {
				sum += i;
				
				if (first == 0) {
					first = i;
				}
			} 
		}
		
		if(sum > 0) {
			bw.write(sum + "\n");
			bw.write(first + "\n");
		} else {
			bw.write(-1 + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	private int prime(int m) {
		
		for(int i = 2; i <= m/2; i++) {
			if(m % i == 0) {
				return -1;
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) throws Exception {
		new Main2581().solution();
	}
}
