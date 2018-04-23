import java.io.File;

public class test {
	
	public static void main(String[] args) {

	generateCharFiles.generate("chars.txt", 10000);

	huffman test = new huffman();

	String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/illiad.txt";
	HuffTree tree = test.buildTree(test.buildHeap(test.getFrequencies(new File(inputFile))));

	System.out.println(test.traverseHuffmanTree(tree));
	String encoded = test.encodeFile(new File(inputFile), tree);
	System.out.println(encoded);

	String transformed = test.decodeFile( encoded, tree );

	System.out.println(transformed);
	}
}