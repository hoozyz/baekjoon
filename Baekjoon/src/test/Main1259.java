package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1259 { // 1259
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String n = st.nextToken();
			int l = n.length();
			if(n.equals("0")) {
				break;
			}
			int check = 1; // 0이면 no

			for(int i = 0; i < l/2; i++) {
				if(n.charAt(i) == n.charAt(l-i-1)) {
					continue;
				} else { // 한개라도 다르면
					check = 0;
					bw.write("no" + "\n");
					break;
				}
			}
			
			if(check == 1) {
				bw.write("yes" + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws Exception {
		new Main1259().solution();
	}
}