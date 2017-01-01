// Queue: FIFO collection

// Resizing array generic queue. ResizingArrayQueue.java implements 
// a generic resizing queue whose size doubles when the array gets full
// and halves when the array is one-quarter full.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
	private Item[] stackArray;
	private int head;
	private int tail;

	public ResizingArrayQueue() {
		stackArray = (Item[]) new Object[2]; // generic array creation disallowed in Java
		this.head = 0;
		this.tail = 0;
	}

	public void enqueue(Item item) {
		stackArray[tail++] = item;
		if (tail == stackArray.length) {
			resize(stackArray.length * 2);
		}
	}

	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item dequeuedElement = stackArray[head--];
		stackArray[head - 1] = null;
		if (head - tail == stackArray.length / 4) {
			resize(stackArray.length / 2);
		}
		return dequeuedElement;
	}

	public boolean isEmpty() {
		return tail - head == 0;
	}

	public int size() {
		return tail - head;
	}

	// Returns (but does not remove) the item most recently added to the queue
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return stackArray[tail - 1];
	}

	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private void resize(int newCapacity) {
		assert newCapacity >= this.head - this.tail;

		Item[] copyArray = (Item[]) new Object[newCapacity];
		for (int i = head, j = 0; i < tail; i++, j++) {
			copyArray[j] = stackArray[i];
		}
		stackArray = copyArray;
	}

	// hasNext(), next(), remove()->not implemented
	private class ArrayIterator implements Iterator<Item> {
		private int i = head;

		public boolean hasNext() {
			return i > tail;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return stackArray[i++];
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove is evil and not supported.");
		}
	}

	public static void main(String[] args) {
		ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
	}
}
