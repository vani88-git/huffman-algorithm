public class HuffTree {
	
	private huffNode root;

	public HuffTree(char element, int weight) {

		root = new huffLeafNode(element, weight);
	}

	public huffNode getRoot() {

		return this.root;
	}

	public int getWeight() {

		return root.getWeight();
	}

	// public int compareTo(Object tree) {

	// 	return 0;
	// }
}