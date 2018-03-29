import java.util.Scanner;
import java.io.File;

public class huffmanAlgorithm implements HuffmanCoding {
	
	//take a file as input and create a table with characters and frequencies
	//print the characters and frequencies
	@Override
	public String getFrequencies(File inputFile) {

		Scanner sc;

		try {

			sc = new Scanner(inputFile);
		} 
		catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		sc.useDelimiter("");


		int[] frequencies = new int[127];
		char index = 0;

		while (sc.hasNext()) {

			index = sc.next().charAt(0);
			frequencies[index]++;
		}

		String temp = "";

		for (int i = 32; i < 127; i++) {

			temp += String.valueOf( (char)i ) + " " + frequencies[i] + "\n";
		}

		// return temp.substring(0,temp.length()-1);
		return temp;
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