import java.io.File;

public class test {
	
	public static void main(String[] args) {

	generateCharFiles.generate("chars.txt", 5);

	huffmanAlgorithm test = new huffmanAlgorithm();

	String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/chars.txt";
	HuffTree tree = test.buildTree(test.buildHeap(test.getFrequencies(new File(inputFile))));

	String encoded = test.encodeFile(new File(inputFile), tree);
	String transformed = test.decodeFile( encoded, tree );

	// System.out.println(encoded);
	System.out.println(transformed);
	}
}