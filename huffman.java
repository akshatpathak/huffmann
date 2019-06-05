package april18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class huffman {
	static HashMap<Character, String> enc = new HashMap<>();
	static HashMap<String, Character> dec = new HashMap<>();

	public static class Node implements Comparable<Node> {
		char data;
		int freq;
		Node left;
		Node right;

		Node(char data, int freq) {
			this.data = data;
			this.freq = freq;
		}

		public int compareTo(Node other) {
			return this.freq - other.freq;
		}
	}

	public static void traversal(Node n, String asf) {
		if (n.left == null && n.right == null) {
			enc.put(n.data, asf);
			dec.put(asf, n.data);
			return;
		}
		traversal(n.left, asf + "0");
		traversal(n.right, asf + "1");

	}

	public static void Huffman(String feeder) {
		HashMap<Character, Integer> fmap = new HashMap<>();
		for (int i = 0; i < feeder.length(); i++) {
			char c = feeder.charAt(i);
			if (fmap.containsKey(c) == true) {
				int key = fmap.get(c);
				key++;
				fmap.put(c, key);
			} else {
				fmap.put(c, 1);
			}
		}
//		System.out.println(fmap);
		ArrayList<Character> keys = new ArrayList<>(fmap.keySet());
		System.out.println(keys);
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < keys.size(); i++) {
			q.add(new Node(keys.get(i), fmap.get(keys.get(i))));
		}

		while (q.size() > 1) {
			Node n1 = q.remove();
			Node n2 = q.remove();
			Node n3 = new Node('$', n1.freq + n2.freq);
			n3.left = n1;
			n3.right = n2;
			q.add(n3);
		}
		Node root = q.remove();
		traversal(root, "");

	}

	public static void main(String[] args) {
		Huffman("aaaabbbcc");
		System.out.println(enc);
		System.out.println(dec);
		

	}

}
