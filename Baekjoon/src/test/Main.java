package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main { 
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		//n = Integer.parseInt(st.nextToken());
		//m = Integer.parseInt(st.nextToken());
		
		
		
		//bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
