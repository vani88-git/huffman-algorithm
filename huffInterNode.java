public class huffInterNode implements huffNode {

	private huffNode left, right;
	private int weight;

	public huffInterNode(huffNode left, huffNode right, int weight) {

		this.left = left;
		this.right = right;
		this.weight = weight;
	}

	@Override
	public boolean isLeaf() {

		return false;
	}

	@Override
	public int getWeight() {

		return this.weight;
	}

	public huffNode getLeft() {

		return this.left;
	}

	public huffNode getRight() {

		return this.right;
	}
}