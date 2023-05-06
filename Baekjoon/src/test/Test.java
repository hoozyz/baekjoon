package test;

import java.util.Scanner;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = scan.next();
		
		int mul = 1, result = 0; // @1
		Stack<Character> stack = new Stack<Character>(); // @2
		for (int i = 0; i < in.length(); i++) {
			switch (in.charAt(i)) { // @3
			// @4
			case '(': 
				stack.push('(');
				mul *= 2;
				break;
			// @5
			case '[':
				stack.push('[');
				mul *= 3;
				break;
			// @6
			case ')':
				if (stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				}
				
				if (in.charAt(i - 1) == '(') result += mul;
				stack.pop();
				mul /= 2;
				break;
			// @7
			case ']':
				if (stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				}
				
				if (in.charAt(i - 1) == '[') result += mul;
				stack.pop();
				mul /= 3;
				break;
			}
		}
		
		// @8
		System.out.println(!stack.isEmpty() ? 0 : result);
	}
}
