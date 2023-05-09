package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 백준은 클래스명 Main
public class Main11659 {
	
	private static int[] arr;
	
	// 프로그래머스도 같이 하기 위해서 -> 여기에 백준, 프로그래머스 다 가능
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 글자씩 읽어옴
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력
		
		
		int n = Integer.parseInt(st.nextToken()); // nextToken 은 처음 문자만
		int m = Integer.parseInt(st.nextToken()); // nextToken은 이전 토큰(문자열)은 제거 하 새로운 다음꺼 가져옴
		arr = new int[n+1]; // 누적합 넣기 위해서 한개 더 추가
		
		st = new StringTokenizer(br.readLine()); // 다음 줄 읽어오기 -> 배열
		
		// 누적합 구하기
		for(int i = 1; i <= n; i++) { // i 까지 누적합
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		// 누적합으로 구간합 구하기
		for(int i = 0; i < m; i++) { // m 은 구해야할 구간 값 개수
			st = new StringTokenizer(br.readLine()); // 계속 다음 줄 읽어오기
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// a ~ b 구간 합
			bw.write(arr[b] - arr[a-1] + "\n");
		}
		bw.flush(); // 남아있는 데이터 출력
		bw.close(); // 스트림 닫음
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main11659().solution();
	}
}
