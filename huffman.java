import java.util.Scanner;
import java.io.File;

public class huffman implements HuffmanCoding {
	
	//take a file as input and create a table with characters and frequencies
	//print the characters and frequencies
	@Override
	public String getFrequencies(File inputFile) {

		//declare scanner and wrap the file instantiation in a try catch
		Scanner sc;

		try {

			sc = new Scanner(inputFile);
		} 
		catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		//use an empty string as delimiter such that you can pull each character from the input file
		sc.useDelimiter("");

		//instantiate the frequencies table with the index as the character and the element the number of times that it occurs
		int[] frequencies = new int[128];

		//iterate over each character in the file and increment it's element if it is found in the array
		while (sc.hasNext()) {

			frequencies[sc.next().charAt(0)]++;
		}

		//build the string to return. formatted as char space frequency newline
		String temp = "";

		//iterate over the 
		for (int i = 32; i < 128; i++) {

			//if the character did not occur in the file then don't include it in the frequency string
			if (frequencies[i] == 0) continue;

			//else we want to append the character space frequency newline to the string
			temp += String.valueOf( (char)i ) + " " + frequencies[i] + "\n";
		}

		//return the string we have created
		return temp;
	}

	//take a file as input and create a Huffman Tree
	@Override
	public HuffTree buildTree(File inputFile) {

		//build a tree from a heap that is built from the frequencies that is obtained from the input file
		return buildTree(buildHeap(getFrequencies(inputFile)));
	}
	
	//take a file and a HuffTree and encode the file.  
	//output a string of 1's and 0's representing the file
	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) {

		//if the input is not valid then return null
		if (inputFile == null || huffTree == null) return null;

		//build the code table and initialize the encoded string as an empty string
		String[] table = buildCodeTable ( traverseHuffmanTree(huffTree) );
		StringBuilder encoded = new StringBuilder();

		//declare scanner
		Scanner sc;

		//wrap scanner file instantiation in try catch
		try {

			sc = new Scanner(inputFile);
		} 
		catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		//use delimiter to pull each character. each next() pulls the next character in the string
		sc.useDelimiter("");

		//as longa s scanner has a next value
		while (sc.hasNext()) {

			//append the encoded string of 0 and 1 to the end of the encoded string, followed by a space
			encoded.append(String.valueOf(table[sc.next().charAt(0)]));
		}

		//return the encoded string
		return encoded.toString().replaceAll( "null", "" );
	}

	//take a String and HuffTree and output the decoded words
	//assumes a string of "\x " + "\x " + ...
	//where \x is a code of 0 and 1
	@Override
	public String decodeFile(String code, HuffTree huffTree) {

		//the string we're going to be adding characters onto
		StringBuilder decoded = new StringBuilder();
		StringBuilder coded = new StringBuilder(code);

		while (coded.length() != 0) {

			coded = decode(coded, huffTree.getRoot(), decoded);
		}
		// // System.out.println();

		// code = decode(code, huffTree.getRoot(), decoded);

		// decode(code, huffTree.getRoot(), decoded);

		//return the decoded string
		return decoded.toString();
	}

	//print the characters and their codes
	@Override
	public String traverseHuffmanTree(HuffTree tree) {

		StringBuilder codeString = new StringBuilder();
		String[] codes = new String[128];

		tree.traverseTree(tree.getRoot(), codes);

		for (int i = 0; i < 128; i++) {

			if (codes[i] == null) continue;

			codeString.append( ((char)(i)) + " " + codes[i] + "\n" );
		}

		return codeString.toString();
	}

	public StringBuilder decode(StringBuilder code, huffNode root, StringBuilder decoded) {

		if (root.isLeaf()) {

			decoded.append(  String.valueOf( ((huffLeafNode)root).getValue() )  );
			return code;
		}

		else if (code.length() == 0) return new StringBuilder();

		else if (code.charAt(0) == '1') {
			return decode(code.deleteCharAt(0), ((huffInterNode)root).getRight(), decoded);
		}

		else if (code.charAt(0) == '0') {
			return decode(code.deleteCharAt(0), ((huffInterNode)root).getLeft(), decoded);
		}

		else  {
			decoded.append("\n");
			return code.deleteCharAt(0);
		}
	}

	//build a tree from a min heap priority queue
	public HuffTree buildTree(minHeap heap) {

		HuffTree a, b;

		while (heap.size() > 1) {

			//remove a and b from the heap
			a = heap.removeMin();
			b = heap.removeMin();

			//pair the nodes together into a huffman tree and insert back into the heap with the minimum on the left and the maximum on the right
			heap.insert( new HuffTree( new huffInterNode( a.getRoot(), b.getRoot(), a.getWeight()+b.getWeight() ) ) );
		}

		//return the only variable present in the heap, the root of the huffman tree
		return heap.removeMin();
	}

	/* builds a table that holds the code associated with each character in the huff tree */
	public String[] buildCodeTable(String code) {

		//if the string of characters from the huffman tree traversal is invalid, then return null
		if (code == null || code.isEmpty()) return null;

		//instantiate the table to be returned
		String[] table = new String[128];

		//the start of the first occurence begins at index 0
		int start = 0;
		String occurence;

		//iterate over each character in code string
		for (int i = 0; i < code.length(); i++) {

			//if the character is equal to a newline, then we have found the end of an occurence
			if (code.charAt(i) == '\n') {

				//substring the occurence from its start to i (the newline, it is noninclusive)
				occurence = code.substring(start, i);

				/* the character associated with the code is at the first space in the occurence
				 while the code associated with the character is located after the space */
				table[occurence.charAt(0)] = occurence.substring(occurence.lastIndexOf(' ')+1);

				//the new start is immediately after the newline that we found before.
				start = i + 1;
			}
		}

		//return the filled table. if it equals null then it isnt present in the file.
		return table;
	}

	//build a heap from the string of frequencies
	public minHeap buildHeap(String frequencies) {

		minHeap heap = new minHeap();

		//the start of the new occurence. starts at 0 and then occurs at i + 2
		int start = 0;
		String occurence;

		//iterate over each character in the frequency string
		for (int i = 0; i < frequencies.length(); i++) {

			//if its a newline then weve found the end of an occurence
			if (frequencies.charAt(i) == '\n') {

				//grab the occurence
				occurence = frequencies.substring(start, i);

				//if (occurence.charAt(0) == 0) heap.insert( new HuffTree( occurence.charAt(0), Integer.valueOf(occurence.substring(occurence.lastIndexOf(' ')+1)) ));

				//insert a new huffTree into the heap, building it from the occurence
				//else
					heap.insert( new HuffTree( occurence.charAt(0), Integer.valueOf(occurence.substring(occurence.lastIndexOf(' ')+1)) ));

				//move i to the character after the newline
				start = i + 1;
			}
		}

		//return the heap
		return heap;
	}
}