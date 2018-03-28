public class heapNode {
	
	private int frequency;
	private char element;

	public heapNode(char element, int frequency) {

		this.frequency = frequency;
		this.element = element;
	}

	public int getFrequency() {

		return this.frequency;
	}

	public char getElement() {

		return this.element;
	}
}