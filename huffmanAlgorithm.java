import java.util.Scanner;
import java.io.File;

public class huffmanAlgorithm implements HuffmanCoding {

	private String[] codeTable;

	public huffmanAlgorithm() {

		codeTable = null;
	}
	
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
		for (int i = 32; i < 127; i++) {

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
			encoded.append(String.valueOf(table[sc.next().charAt(0)]) + " ");
		}

		//return the encoded string
		return encoded.toString();
	}

	//take a String and HuffTree and output the decoded words
	//assumes a string of "\x " + "\x " + ...
	//where \x is a code of 0 and 1
	@Override
	public String decodeFile(String code, HuffTree huffTree) {

		//gets a code table where each character is an index in the String table and the data element is the string of 0 and 1
		String[] table = buildCodeTable ( traverseHuffmanTree(huffTree) );

		int start = 0;
		String occurence;
		StringBuilder decoded = new StringBuilder();

		//iterate over each character in the string
		for (int i = 0; i < code.length(); i++) {

			//if a character is equal to a space, then the last character of the occurence is equal to the character prior to the space
			if (code.charAt(i) == ' ') {

				//by this logic, grab the substring starting from start and ending (not including) with i the space to grab the occurence
				occurence = code.substring(start, i);

				//iterate over the table of codes and characters to find the code that matches the occurence
				for (int j = 0; j < table.length; j++) {

					//if the code equals the occurence, then we have found the character that it is equal to
					if (table[j] != null && table[j].equals(occurence)) {

						/* cast j as a character, as that is the character value that it is equal to, then take the value of it as a string 
						and append it to the decoded string */
						decoded.append(String.valueOf( (char)(j) ));
					}
				}
				//start now begins at the character immediately after the space (the space was at i)
				start = i + 1;
			}
		}

		//return the decoded string
		return decoded.toString();
	}

	//print the characters and their codes
	@Override
	public String traverseHuffmanTree(HuffTree tree) {

		StringBuilder codes = new StringBuilder();

		tree.traverseTree(tree.getRoot(), codes);

		return codes.toString();
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

		if (codeTable != null) return codeTable;

		//if the string of characters from the huffman tree traversal is invalid, then return null
		if (code == null || code.equals("")) return null;

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

		codeTable = table;

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

				//insert a new huffTree into the heap, building it from the occurence
				heap.insert( new HuffTree( occurence.charAt(0), Integer.valueOf(occurence.substring(occurence.lastIndexOf(' ')+1)) ));

				//move i to the character after the newline
				start = i + 1;
			}
		}

		//return the heap
		return heap;
	}
}