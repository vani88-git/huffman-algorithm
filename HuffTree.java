public class HuffTree {
	
	private huffNode root;

	public HuffTree(char element, int weight) {

		this.root = new huffLeafNode(element, weight);
	}

	public HuffTree(huffNode left, huffNode right, int weight) {

		this.root = new huffInterNode(left, right, weight);
	}

	public HuffTree(huffNode root) {

		this.root = root;
	}

	public huffNode getRoot() {

		return this.root;
	}

	public int getWeight() {

		return root.getWeight();
	}

	public String toString(huffNode node) {

		// if (node == null) return "";

		// if (node.isLeaf()) return ( (huffLeafNode) node ).getValue() + " " + ( (huffLeafNode) node ).getWeight() + "\n";

		// else {

		// 	toString
		// }

		return "";
	}
}