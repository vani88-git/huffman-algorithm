import java.io.File;

public class huffmanAlgorithm implements HuffmanCoding {
	
	//take a file as input and create a table with characters and frequencies
	//print the characters and frequencies
	@Override
	public String getFrequencies(File inputFile) {

		int[] frequencies = new int[256];

		

		return "";
	}

	//take a file as input and create a Huffman Tree
	@Override
	public HuffTree buildTree(File inputFile) {

		return null;
	}
	
	//take a file and a HuffTree and encode the file.  
	//output a string of 1's and 0's representing the file
	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) {

		return "";
	}

	//take a String and HuffTree and output the decoded words
	@Override
	public String decodeFile(String code, HuffTree huffTree) {

		return "";
	}

	//print the characters and their codes
	@Override
	public String traverseHuffmanTree(HuffTree huffTree) {

		return "";
	}

	public HuffTree buildTree(minHeap heap) {

		return null;
	}
}