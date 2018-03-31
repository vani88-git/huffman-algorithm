import java.io.File;

public class test {
	
	public static void main(String[] args) {

	generateCharFiles.generate("chars.txt", 50);

	huffmanAlgorithm test = new huffmanAlgorithm();

	String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/chars.txt";
	HuffTree tree = test.buildTree(test.buildHeap(test.getFrequencies(new File(inputFile))));

	System.out.println(test.traverseHuffmanTree(tree));
	}
}