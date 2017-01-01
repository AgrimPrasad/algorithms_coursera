// Stack: LIFO collection

// Linked List based generic stack. Stack.java implements 
// a generic singly linked list stack. push() to the front of the linked list
// and pop() from the front of the linked list

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
	private Node first = null;
	private int numElements;

	public Stack() {
		this.numElements = 0;
	}

	public void push(Item item) {
		Node oldFirst = first;
		first = new Node(item, oldFirst);
		this.numElements++;
	}

	public Item pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item oldFirst = first.item;
		first = first.next;
		this.numElements--;
		return oldFirst;
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
		return first.item;
	}

	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}

	private class Node {
		private Item item;
		private Node next;

		public Node() { }

		public Node(Item item, Node next) {
			this.item = item;
			this.next = next;
		}
	}

	// hasNext(), next(), remove()->not implemented
	private class LinkedListIterator implements Iterator<Item> {
		private Node i = first;

		public boolean hasNext() {
			return i != null;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item current = i.item;
			i = i.next;
			return current;
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove is evil and not supported.");
		}
	}

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
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
