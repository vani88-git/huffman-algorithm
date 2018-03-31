import java.io.File;

public class test {
	
	public static void main(String[] args) {

	// generateCharFiles.generate("chars.txt", 1000);

	// huffmanAlgorithm test = new huffmanAlgorithm();

	// String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/chars.txt";
	// HuffTree tree = test.buildTree(test.buildHeap(test.getFrequencies(new File(inputFile))));

	// System.out.println(tree.getWeight());


	huffStack stack = new huffStack();

	System.out.println(stack.toString());
	System.out.println(stack.insert(1));
	System.out.println(stack.insert(0));
	System.out.println(stack.insert(1));
	System.out.println(stack.insert(2));
	System.out.println(stack.insert(1));
	System.out.println(stack.toString());
	System.out.println(stack.pop());
	System.out.println(stack.toString());
	System.out.println(stack.insert(0));
	System.out.println(stack.toString());
	System.out.println(stack.pop());
	System.out.println(stack.toString());
	}
}