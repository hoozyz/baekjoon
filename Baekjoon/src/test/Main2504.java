package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2504 {
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(),"");

		boolean check = true; // false 면 올바르지 않은 괄호열
		int result = 0; // 결과 담을 정수
		int multi = 1; // 곱 담을 정수
		String str = st.nextToken();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char token = str.charAt(i);

			if (token == '(') {
				stack.push('(');
				multi *= 2; // 만약 바로 다음에 푸시되는게 ) 이면 2 나누고 2로 바꾸기
			}
			if (token == ')') {
				if(stack.isEmpty() || stack.peek() != '(') { // ) 가 그대로 들어갈 때 -> 
					check = false; // ) 는 ( 없으면 올바르지 않은 괄호열
					break;
				}

				if(str.charAt(i-1) == '(') { // () 인 경우만 더하기
					result += multi;
				}
				stack.pop(); // ( 와 ) 만나서 없어지므로 ( 제거
				multi /= 2; // 위에서 결과에 곱한 값 더했으므로 곱한 값 나누기
			}
			if (token == '[') {
				stack.push('[');
				multi *= 3;
			}
			if (token == ']') {
				if(stack.isEmpty() || stack.peek() != '[') { // ) 가 그대로 들어갈 때 -> 
					check = false; // ] 는 [ 없으면 올바르지 않은 괄호열
					break;
				}
				if(str.charAt(i-1) == '[') { // [] 인 경우만 더하기
					result += multi;
				}
				stack.pop(); // [ 와 ] 만나서 없어지므로 [ 제거
				multi /= 3; // 위에서 결과에 곱한 값 더했으므로 곱한 값 나누기
			}
		}
		
		if(!check || !stack.isEmpty()) {
			bw.write(0 + "\n");
		} else {
			bw.write(result + "\n");
		}
		
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws Exception {
		new Main2504().solution();
	}
}
