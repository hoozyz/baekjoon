package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1991 {

	private static Node root; // 루트 노드
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			char data = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			createNode(data, left, right); // 노드 생성
		}

		preOrder(root);
		bw.write("\n");
		inOrder(root);
		bw.write("\n");
		postOrder(root);
		
		bw.flush();
		bw.close();
	}

	private void createNode(char data, char left, char right) {
		if (root == null) {
			root = new Node(data);

			if (left != '.') {
				root.left = new Node(left);
			}
			if (right != '.') {
				root.right = new Node(right);
			}
		} else {
			searchNode(root, data, left, right);
		}
	}

	private void searchNode(Node node, char data, char left, char right) {
		if (node == null) { // 도착한 노드가 null이면 재귀 종료
			return;
		} else if (node.data == data) { // 들어갈 위치를 찾았다면 (현재 노드의 값과같으면)
			if (left != '.') {
				node.left = new Node(left);
			}
			if (right != '.') {
				node.right = new Node(right);
			}
		} else { // 아직 들어갈 위치 못찾으면 재귀
			searchNode(node.left, data, left, right);
			searchNode(node.right, data, left, right);
		}
	}

	private static void preOrder(Node data) throws IOException { // 전위순회 : 루트 -> 왼쪽 -> 오른쪽
		bw.write(data.data + "");

		if (data.left != null) {
			preOrder(data.left);
		}
		if (data.right != null) {
			preOrder(data.right);
		}
	}

	private static void inOrder(Node data) throws IOException { // 중위순회 : 왼쪽 -> 루트 -> 오른쪽
		if (data.left != null) {
			inOrder(data.left);
		}
		bw.write(data.data + "");
		
		if (data.right != null) {
			inOrder(data.right);
		}
	}

	private static void postOrder(Node data) throws IOException { // 후위순회 : 왼쪽 -> 오른쪽 -> 루트
		if (data.left != null) {
			postOrder(data.left);
		}
		if (data.right != null) {
			postOrder(data.right);
		}
		
		bw.write(data.data + "");
	}

	class Node {
		char data; // 현재 데이터
		Node left; // 왼쪽자식
		Node right; // 오른쪽 자식

		// 노드 생성 -> 자식은 모르니까 현재 데이터만
		public Node(char data) {
			this.data = data;
		}
	}

	public static void main(String[] args) throws Exception {
		new Main1991().solution();
	}
}
