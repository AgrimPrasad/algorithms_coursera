// Stack: LIFO collection

// Resizing-capacity array generic stack. ResizingArrayStack.java implements 
// a generic resizing-capacity stack whose size doubles when the array gets full
// and halves when the array is one-quarter full.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] stackArray;
	private int numElements;

	public ResizingArrayStack() {
		stackArray = (Item[]) new Object[2]; // generic array creation disallowed in Java
		this.numElements = 0;
	}

	public void push(Item item) {
		stackArray[numElements++] = item;
		if (numElements == stackArray.length) {
			resize(stackArray.length * 2);
		}
	}

	public Item pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item poppedElement = stackArray[--numElements];
		stackArray[numElements + 1] = null;
		if (numElements == stackArray.length / 4) {
			resize(stackArray.length / 2);
		}
		return poppedElement;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public int size() {
		return numElements;
	}

	// Returns (but does not remove) the item most recently added to the stack
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return stackArray[numElements - 1];
	}

	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	private void resize(int newCapacity) {
		assert newCapacity >= this.numElements;

		Item[] copyArray = (Item[]) new Object[newCapacity];
		for (int i = 0; i < numElements; i++) {
			copyArray[i] = stackArray[i];
		}
		stackArray = copyArray;
	}

	// hasNext(), next(), remove()->not implemented
	private class ReverseArrayIterator implements Iterator<Item> {
		private int i = numElements - 1;

		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return stackArray[i--];
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove is evil and not supported.");
		}
	}

	public static void main(String[] args) {
		ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
		String originalString = "";
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            originalString = originalString + item + " ";
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
        StdOut.println("Original string was " + originalString);
	}
}
