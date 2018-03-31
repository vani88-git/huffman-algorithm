import java.util.LinkedList;
import java.util.Iterator;

public class huffStack {
	
	private LinkedList<Integer> contents;

	public huffStack() {

		contents = new LinkedList<Integer>();
	}

	public boolean push(int toInsert) {

		if (toInsert == 1 || toInsert == 0) {

			contents.offerFirst(toInsert);
			return true;
		}
		else return false;
	}

	public int pop() {

		return contents.pop();
	}

	public int peak() {

		return contents.peek();
	}

	public String toString() {

		Iterator<Integer> iter =  contents.iterator();

		String temp = "";

		while (iter.hasNext()) {

			temp += String.valueOf(iter.next());
		}

		return new StringBuilder(temp).reverse().toString();
	}
}