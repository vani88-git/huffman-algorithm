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

	public void traverseTree(huffNode node, StringBuilder codes) {

		if (codes == null || node == null || node.isLeaf()) return;

		traverseTree(  ( (huffInterNode)(node) ).getRight(), codes, "1");

		traverseTree(  ( (huffInterNode)(node) ).getLeft(), codes, "0");
		
	}

	public void traverseTree(huffNode node, StringBuilder codes, String code) {

		if (node.isLeaf()) {

			codes.append(( (huffLeafNode) node ).getValue() + " " + code + "\n");
		}

		else {

			traverseTree(  ( (huffInterNode)(node) ).getRight(), codes, code + "1");

			traverseTree(  ( (huffInterNode)(node) ).getLeft(), codes, code + "0");
		}
	}
}