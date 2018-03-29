import java.util.ArrayList;

public class minHeap {
	
	 private int size;
	 private ArrayList<heapNode> heapNodes;

	 public minHeap() {

	 	heapNodes = new ArrayList<heapNode>();
	 	size = 0;
	 }

	 public int size() {

	 	return this.size;
	 }

	 public void print() {

	 	String temp = "[";

	 	for (int i = 0; i < size; i++) {

	 		temp += heapNodes.get(i).getFrequency() + ", ";
	 	}

	 	if (size > 0) temp = temp.substring(0, temp.length()-2);

	 	temp += "]";

	 	System.out.println(temp);
	 }

	 public boolean isEmpty() {

	 	return size == 0;
	 }

	 public void insert(heapNode toAdd) {

	 	//add to the end of the list and increment size
	 	heapNodes.add(toAdd);
	 	size++;

	 	//the child was the node that we inserted
	 	int child = size-1, parent = (child-1)/2;
	 	heapNode temp = null;

	 	/* while the parent isnt the child (means we must be at the root) and the frequency of the parent is 
	 	greater than that of the child  */
	 	while (parent != child && heapNodes.get(parent).getFrequency() > heapNodes.get(child).getFrequency()) {

	 		//swap parent and child
	 		temp = heapNodes.get(parent);
	 		heapNodes.set(parent, heapNodes.get(child));
	 		heapNodes.set(child, temp);

	 		child = parent;
	 		parent = (child-1)/2;
	 	}
	 }

	 public heapNode deleteMin() {

	 	//edge case protection
	 	if (size == 0) return null;

	 	//grab the minimum element at the root and place it in min
	 	heapNode min = heapNodes.get(0);
	 	
	 	//set the root equal to the last element in the list
	 	heapNodes.set(0, heapNodes.get(size-1));

	 	//remove the last element in the list as it is now at the root
	 	heapNodes.remove(size-1);
	 	size--;

	 	/* reheapify! */

	 	//current node, its left and right children, a temporary variable holding the minimum between left and right, and a temp heapNode holder
	 	int curr = 0, left = 1, right = 2, toSwap = -1;
	 	heapNode temp = null;

	 	/* while the left node is a valid node and the frequency of the current node is greater then the frequency of the left node
	 	 or the right node is a valid node and the frequency of the current node is greater then the frequency of the right node*/
	 	while (true) {

	 		if (right >= size) {

                  if (left >= size) break;
                  else toSwap = left;
            }
			else {
                  
                  if (heapNodes.get(left).getFrequency() <= heapNodes.get(right).getFrequency()) toSwap = left;
                  else toSwap = right;
            }
			if (heapNodes.get(curr).getFrequency() > heapNodes.get(toSwap).getFrequency()) {

				temp = heapNodes.get(curr);
	 			heapNodes.set(curr, heapNodes.get(toSwap));
	 			heapNodes.set(toSwap, temp);

	 			curr = toSwap;
	 			left = 2*curr+1;
	 			right = 2*curr+2;

            }
            else break;
	 	}
	 	return min;
	 }
}