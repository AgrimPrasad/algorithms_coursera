// Bag: LIFO collection

// Linked List based generic Bag. Bag.java implements 
// a generic singly linked list Bag. add() to the bag randomly, cannot remove.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private Node first = null;
	private int numElements;

	public Bag() {
		this.numElements = 0;
	}

	public void add(Item item) {
		Node oldFirst = first;
		first = new Node(item, oldFirst);
		this.numElements++;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public int size() {
		return numElements;
	}

	// Returns (but does not remove) the item most recently added to the Bag
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Bag underflow");
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
		Bag<String> bag = new Bag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
	}
}
