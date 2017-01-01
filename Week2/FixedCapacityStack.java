// Stack: LIFO collection

// Fixed-capacity generic stack. FixedCapacityStack.java implements 
// a generic fixed-capacity stack.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStack<Item> implements Iterable<Item> {
	private Item[] stackArray;
	private int capacity;
	private int numElements;

	public FixedCapacityStack(int capacity) {
		stackArray = (Item[]) new Object[capacity]; // generic array creation disallowed in Java
		this.capacity = capacity;
		this.numElements = 0;
	}

	public void push(Item item) {
		stackArray[numElements++] = item;
	}

	public Item pop() {
		return stackArray[--numElements];
	}

	public int capacity() {
		return capacity;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
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
		int max = Integer.parseInt(args[0]);
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item); 
            else if (stack.isEmpty())  StdOut.println("BAD INPUT"); 
            else                       StdOut.print(stack.pop() + " ");
        }
        StdOut.println();

        // print what's left on the stack
        StdOut.print("Left on stack: ");
        for (String s : stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
	}
}
