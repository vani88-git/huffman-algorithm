public class huffLeafNode implements huffNode {

	private char element;
	private int weight;

	public huffLeafNode(char element, int weight) {

		this.element = element;
		this.weight = weight;
	}

	@Override	
	public boolean isLeaf() {

		return true;
	}

	public char getValue() {

		return this.element;
	}

	@Override
	public int getWeight() {

		return this.weight;
	}
}