import java.io.File;

public class test {
	
	public static void main(String[] args) {

	minHeap heap = new minHeap();
	heap.insert(new heapNode('a', 1));
	heap.insert(new heapNode('b', 2));
	heap.insert(new heapNode('c', 3));
	heap.insert(new heapNode('d', 4));
	heap.insert(new heapNode('e', 5));
	heap.insert(new heapNode('f', 6));
	heap.insert(new heapNode('g', 7));
	heap.insert(new heapNode('h', 8));
	heap.insert(new heapNode('i', 9));
	heap.insert(new heapNode('j', 10));
	// heap.insert(new heapNode('k', 13));
	// heap.insert(new heapNode('l', 311));
	// heap.insert(new heapNode('m', 131));

	int n = 8;
	for (int i = 0; i < n; i++) {
		heap.print();
		heap.deleteMin();
		if (i == n-1) heap.print();

	}
	heap.insert(new heapNode('a', 7));
	heap.print();

	heap.insert(new heapNode('a', 8));
	heap.print();

	// heap.insert(new heapNode('a', 7));
	// heap.print();

	// 	heap.insert(new heapNode('a', 1));
	// heap.print();

	// 	heap.insert(new heapNode('a', 4));
	// heap.print();


	// generateCharFiles.generate("chars.txt", 5);

	// huffmanAlgorithm test = new huffmanAlgorithm();

	// String inputFile = "/Users/johnhodson/GitHub/huffman-algorithm/chars.txt";
	// System.out.print(test.getFrequencies(new File(inputFile)));

	}
}