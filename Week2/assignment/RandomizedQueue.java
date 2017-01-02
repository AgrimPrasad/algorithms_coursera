// Queue: FIFO collection

// Resizing array generic randomized queue. RandomizedQueue.java implements 
// a generic resizing randomized queue whose size doubles when the array gets full
// and halves when the array is one-quarter full.

// import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] stackArray;
	private int numElements;
	private int numElementsWithNulls;

	// construct an empty randomized queue
	public RandomizedQueue() {
		stackArray = (Item[]) new Object[2]; // generic array creation disallowed in Java
		this.numElements = 0;
		this.numElementsWithNulls = 0;

		// check();
	}

	// add the item
	public void enqueue(Item item) {
    	if (item == null) throw new NullPointerException("Cannot add a null item.");
		stackArray[numElementsWithNulls++] = item;
		numElements++;
		if (numElementsWithNulls == stackArray.length) {
			resize(stackArray.length * 2);
		}

		// check();
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item poppedElement = randomItemSelector(true);
		if (stackArray.length >= 4 && (numElements == stackArray.length / 4)) {
			resize(stackArray.length / 2);
		}

		// check();
		assert poppedElement != null;
		return poppedElement;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return numElements == 0;
	}

	// return the number of items on the queue
	public int size() {
		return numElements;
	}

	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return randomItemSelector();
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomArrayIterator();
	}

	private Item randomItemSelector() { return randomItemSelector(false);}

	private Item randomItemSelector(boolean pop) {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		int randomIdx = StdRandom.uniform(0, numElementsWithNulls + 1);
		while (stackArray[randomIdx] == null) {
			randomIdx = StdRandom.uniform(0, numElementsWithNulls + 1);
		}
		Item randomItem = stackArray[randomIdx];
		if (pop) {
			stackArray[randomIdx] = null;	// prevent loitering
			numElements--;
		}
		return randomItem;
	}

	private void resize(int newCapacity) {
		assert newCapacity >= this.numElements;

		Item[] copyArray = (Item[]) new Object[newCapacity];

		for (int i = 0, j = 0; i < numElementsWithNulls;) {
			if (stackArray[i] == null) {
				i++;
			}
			else {
				copyArray[j] = stackArray[i];
				i++;
				j++;
			}
		}
		stackArray = copyArray;
		numElementsWithNulls = numElements;	//Reset as no nulls between 0 & numElements in new array
	}

	// hasNext(), next(), remove()->not implemented
	private class RandomArrayIterator implements Iterator<Item> {
		private int numIterElements;
		private int idx = 0;
		private Item[] randomElementsArray;

		private RandomArrayIterator() {
			numIterElements = numElements;
			randomElementsArray = (Item[]) new Object[numIterElements];
			for (int i = 0; i < numIterElements; i++) {
				randomElementsArray[i] = randomItemSelector();
			}
		}

		public boolean hasNext() {
			return idx < numIterElements;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return randomElementsArray[idx++];
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove is evil and not supported.");
		}
	}

	// check internal invariants with asserts
    // private void check() {
    //     assert numElements >= 0;
    //     if (numElements == 0) {
    //         assert stackArray.length <= 4;
    //     }
    //     else {
    //     	assert numElements >= stackArray.length / 4;
    //     	assert numElements <= stackArray.length;
    //     }
    // }

	public static void main(String[] args) {
		// String filenames[] = {
		// 						// "data/tale.txt",
		// 						// "data/mediumTale.txt",
		// 						"data/tinyTale.txt"
		// 					};

		// for (String filename : filenames) {
		// 	In in = new In(filename);

		// 	RandomizedQueue<String> dq = new RandomizedQueue<>();
		// 	while (!in.isEmpty()) {
		// 		String item = in.readString();
		// 		dq.enqueue(item);
		// 	}

		// 	int dqSize = dq.size();
		// 	for (int i = 0; i < dqSize; i++) {
		// 		StdOut.println(dq.dequeue());
		// 	}

		// }
	}
}
