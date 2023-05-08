package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main22856_later { 
	
	private Node root;
	private int n;
	private int allCount = 0; // 전체 간선 개수
	private int rightCount = 0; // 오른쪽 간선 개수 
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		if(n == 0 || n == 1) {
			bw.write(0 + "\n");
			bw.flush();
			bw.close();
			return;
		}
		
		for (int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int data = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			createNode(data, left, right);
		}
		
		allCount(root);
		rightCount(root);
		
		// 전체 간선 개수 * 2 - 맨 오른족 간선 개수 -> 이동 횟수
		int result = allCount * 2 - rightCount;
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	// 전체 간선 개수 구하기
	private void allCount(Node node) {
		if(node.left != null) {
			allCount++;
			allCount(node.left);
			
		}
		if(node.right != null) {
			allCount++;
			allCount(node.right);
		}
	}
	
	// 맨 오른쪽 간선 구하기
	private void rightCount(Node node) {
		if(node.right != null) {
			rightCount++; // 오른쪽 간선
			rightCount(node.right);
		}
	}
	
	private void createNode(int data, int left, int right) {
		if(root == null) {
			root = new Node(data);
			
			if(left != -1) {
				root.left = new Node(left);
			}
			if(right != -1) {
				root.right = new Node(right);
			}
			
		} else {
			searchNode(root, data, left, right);
		}
	}
	
	private void searchNode(Node node, int data, int left, int right) {
		if(node == null) {
			return;
			
		} else if (node.data == data) { // 들어갈 자리를 찾으면
			
			if(left != -1) {
				node.left = new Node(left);
			}
			if(right != -1) {
				node.right = new Node(right);
			}
			
		} else {
			searchNode(node.left, data, left, right);
			searchNode(node.right, data, left, right);
		}
	}
	
	class Node {
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Main22856_later().solution();
	}
}
