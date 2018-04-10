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

	//NOT CORRECT
	public void traverseTree(huffNode node, StringBuilder code) {

		if (node.isLeaf()) {

			String theString = ( (huffLeafNode) node ).getValue() + " " + ( (huffLeafNode) node ).getWeight() + "\n";

			System.out.println(theString);

			code.append(  ( (huffLeafNode) node ).getValue() + " " + ( (huffLeafNode) node ).getWeight() + "\n"  );
		}

		else {

			traverseTree(  ( (huffInterNode)(node) ).getRight(), code  );

			traverseTree(  ( (huffInterNode)(node) ).getLeft(), code  );
		}
	}
}