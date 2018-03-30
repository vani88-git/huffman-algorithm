import java.util.ArrayList;

public class minHeap {
	
	 private int size;
	 private ArrayList<HuffTree> trees;

	 public minHeap() {

	 	trees = new ArrayList<HuffTree>();
	 	size = 0;
	 }

	 public int size() {

	 	return this.size;
	 }

	 public void print() {

	 	String temp = "[";

	 	for (int i = 0; i < size; i++) {

	 		temp += trees.get(i).getWeight() + ", ";
	 	}

	 	if (size > 0) temp = temp.substring(0, temp.length()-2);

	 	temp += "]";

	 	System.out.println(temp);
	 }

	 public boolean isEmpty() {

	 	return size == 0;
	 }

	 public void insert(HuffTree toAdd) {

	 	//add to the end of the list and increment size
	 	trees.add(toAdd);
	 	size++;

	 	//the child was the node that we inserted
	 	int child = size-1, parent = (child-1)/2;
	 	HuffTree temp = null;

	 	/* while the parent isnt the child (means we must be at the root) and the frequency of the parent is 
	 	greater than that of the child  */
	 	while (parent != child && trees.get(parent).getWeight() > trees.get(child).getWeight()) {

	 		//swap parent and child
	 		temp = trees.get(parent);
	 		trees.set(parent, trees.get(child));
	 		trees.set(child, temp);

	 		child = parent;
	 		parent = (child-1)/2;
	 	}
	 }

	 public HuffTree removeMin() {

	 	//edge case protection
	 	if (size == 0) return null;

	 	//grab the minimum element at the root and place it in min
	 	HuffTree min = trees.get(0);
	 	
	 	//set the root equal to the last element in the list
	 	trees.set(0, trees.get(size-1));

	 	//remove the last element in the list as it is now at the root
	 	trees.remove(size-1);
	 	size--;

	 	/* reheapify! */

	 	//current node, its left and right children, a temporary variable holding the minimum between left and right, and a temp HuffTree holder
	 	int curr = 0, left = 1, right = 2, toSwap = -1;
	 	HuffTree temp = null;

	 	/* while the left node is a valid node and the frequency of the current node is greater then the frequency of the left node
	 	 or the right node is a valid node and the frequency of the current node is greater then the frequency of the right node*/
	 	while (true) {

	 		if (right >= size) {

                  if (left >= size) break;
                  else toSwap = left;
            }
			else {
                  
                  if (trees.get(left).getWeight() <= trees.get(right).getWeight()) toSwap = left;
                  else toSwap = right;
            }
			if (trees.get(curr).getWeight() > trees.get(toSwap).getWeight()) {

				temp = trees.get(curr);
	 			trees.set(curr, trees.get(toSwap));
				trees.set(toSwap, temp);

	 			curr = toSwap;
	 			left = 2*curr+1;
	 			right = 2*curr+2;
            }
            else break;
	 	}
	 	return min;
	 }
}