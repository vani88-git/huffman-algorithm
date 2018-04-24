import java.io.File;

public class test {
	
	public static void main(String[] args) {

		//generateCharFiles.generate("sample.txt", 10000);

		huffman test = new huffman();

		String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/sample.txt";

		//get frequencies
		String frequencies = test.getFrequencies(new File(inputFile));
		System.out.println(frequencies);

		HuffTree tree = test.buildTree(test.buildHeap(frequencies));

		//get encode
		String encoded = test.encodeFile(new File(inputFile), tree);
		System.out.println(encoded);

		//get decode
		String decoded = test.decodeFile( encoded, tree );
		System.out.println(decoded);

		//traverse huffman tree
		System.out.println(test.traverseHuffmanTree(tree));
	}
}