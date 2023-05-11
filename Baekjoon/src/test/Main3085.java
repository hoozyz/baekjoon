package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main3085 {

	private static int n, max;
	private static char[][] candy;

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		candy = new char[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();

			for (int j = 0; j < n; j++) {
				candy[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 범위 내에서
				if (j + 1 < n) {
					swap(i, j, i, j + 1);
					max = Math.max(max, row(i)); // 현재 행 계산
					max = Math.max(max, column(j)); // 바뀐 양 열 다 계산
					max = Math.max(max, column(j + 1));
					swap(i, j, i, j + 1); // 다시 되돌리기
				}

				if (i + 1 < n) {
					swap(i, j, i + 1, j);
					max = Math.max(max, row(i)); // 바뀐 양 행 다 계산
					max = Math.max(max, row(i + 1));
					max = Math.max(max, column(j)); // 현재 열 계산
					swap(i, j, i + 1, j);
				}
			}
		}

		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}

	// x행 같은 사탕수 계산
	private static int row(int x) {
		int result = 1; // 결과
		int count = 1;
		char c = candy[x][0]; // 처음 문자

		for (int i = 1; i < n; i++) {
			if (c != candy[x][i]) { // 다음 문자가 다를 때
				c = candy[x][i];
				result = Math.max(result, count);
				count = 1;

			} else {
				count++;
			}
		}
		return Math.max(result, count);
	}

	// x열 같은 사탕수 계산
	private static int column(int x) {
		int result = 1; // 결과
		int count = 1;
		char c = candy[0][x]; // 처음 문자

		for (int i = 1; i < n; i++) {
			if (c != candy[i][x]) { // 다음 문자가 다를 때
				c = candy[i][x];
				result = Math.max(result, count);
				count = 1;

			} else {
				count++;
			}
		}
		return Math.max(result, count);
	}

	private static void swap(int x1, int y1, int x2, int y2) {
		char temp = candy[x1][y1];
		candy[x1][y1] = candy[x2][y2];
		candy[x2][y2] = temp;
	}

	public static void main(String[] args) throws Exception {
		new Main3085().solution();
	}
}
