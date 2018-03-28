public class huffInterNode implements huffNode {

	private huffNode left, right;
	private int weight;

	public boolean isLeaf() {

		return false;
	}

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