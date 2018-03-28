public class testMinHeap {
	
	public static void main(String[] args) {

	minHeap heap = new minHeap();

	System.out.println(heap.isEmpty());

	heap.insert(new heapNode('a', 4));

	heap.insert(new heapNode('b', 3));

	System.out.println(heap.isEmpty());

	heap.print();
	}
}