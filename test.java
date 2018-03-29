import java.io.File;

public class test {
	
	public static void main(String[] args) {

	// minHeap heap = new minHeap();
	// System.out.println(heap.isEmpty());
	// heap.insert(new heapNode('a', 4));
	// heap.insert(new heapNode('b', 3));
	// heap.insert(new heapNode('c', 2));
	// heap.insert(new heapNode('d', 1));
	// heap.insert(new heapNode('e', 88));
	// heap.insert(new heapNode('f', 32));
	// heap.insert(new heapNode('g', 44));
	// heap.insert(new heapNode('h', 12));
	// heap.insert(new heapNode('i', 11));
	// heap.insert(new heapNode('j', 31));
	// heap.insert(new heapNode('k', 13));
	// heap.insert(new heapNode('l', 311));
	// heap.insert(new heapNode('m', 131));
	// System.out.println(heap.isEmpty());
	// heap.print();
	// heap.deleteMin();
	// heap.print();
	// heap.deleteMin();
	// heap.print();

	generateCharFiles.generate("chars.txt", 5);

	huffmanAlgorithm test = new huffmanAlgorithm();

	String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/chars.txt";
	System.out.print(test.getFrequencies(new File(inputFile)));

	}
}