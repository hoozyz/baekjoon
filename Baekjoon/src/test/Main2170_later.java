package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main2170_later { 
	
	private static Vector<Pair> v = new Vector<>();
	private static int result = 0; // 결과 길이
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
//		n = Integer.parseInt(st.nextToken());
//		m = Integer.parseInt(st.nextToken());

		
		
//		bw.flush();
//		bw.close();
	}
	
	class Pair implements Comparable<Pair>{
		int start;
		int end;
		
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Pair p) {
			if(this.start > p.start) {
				return 1;
			} else if (this.start == p.start && this.end > p.end) {
				return 1;
			}
			
			return -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main2170_later().solution();
	}
}
