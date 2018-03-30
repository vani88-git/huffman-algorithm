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


		int[] frequencies = new int[128];
		char index = 0;

		while (sc.hasNext()) {

			index = sc.next().charAt(0);
			frequencies[index]++;
		}

		String temp = "";

		for (int i = 32; i < 127; i++) {

			if (frequencies[i] == 0) continue;

			temp += String.valueOf( (char)i ) + " " + frequencies[i] + "\n";
		}

		return temp;
	}

	//take a file as input and create a Huffman Tree
	@Override
	public HuffTree buildTree(File inputFile) {

		return buildTree(buildHeap(getFrequencies(inputFile)));
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

		HuffTree a, b;
		boolean aIsMin;

		while (heap.size() > 1) {

			a = heap.removeMin();
			b = heap.removeMin();

			aIsMin = true;
			if (b.getWeight() < a.getWeight()) aIsMin = false;

			if (aIsMin) heap.insert( new HuffTree( new huffInterNode( a.getRoot(), b.getRoot(), a.getWeight()+b.getWeight() ) ) );
			else heap.insert( new HuffTree( new huffInterNode( b.getRoot(), a.getRoot(), a.getWeight()+b.getWeight() ) ) );
		}

		return heap.removeMin();
	}

	public minHeap buildHeap(String frequencies) {

		minHeap heap = new minHeap();

		//the start of the new occurence. starts at 0 and then occurs at i + 2
		int start = 0;
		String occurence;

		for (int i = 0; i < frequencies.length(); i++) {

			if (frequencies.charAt(i) == '\n') {

				occurence = frequencies.substring(start, i);
				heap.insert( new HuffTree( occurence.charAt(0), Integer.valueOf(occurence.substring(occurence.lastIndexOf(' ')+1)) ));
				start = i + 1;
			}
		}

		return heap;
	}
}