// Deque: FIFO/LIFO collection (generalization of stack + queue)

// Linked list based generic Deque. Deque.java implements 
// a generic linked list Deque. enDeque() to the tail of the linked list
// and deDeque() from the front of the linked list

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node head;
	private Node tail;
	private int numElements;

	public Deque() {
		this.head = null;
		this.tail = null;

		assert check();
	}

	public void enDeque(Item item) {
		if (isEmpty()) {
			head = tail = new Node(item, null);
		} else {
			Node oldTail = tail;
			tail = new Node(item, null);
			oldTail.next = tail;
		}
		numElements++;
		assert check();
	}

	public Item deDeque() {
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item oldHead = head.item;
		head = head.next;
		this.numElements--;
		if (isEmpty()) tail = null;		// to prevent loitering

		assert check();
		return oldHead;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return numElements;
	}

	// Returns (but does not remove) the item least recently added to the Deque
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		return head.item;
	}

	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}

	// check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'head'
        if (numElements < 0) {
            return false;
        }
        if (numElements == 0) {
            if (head != null || tail != null) return false;
        }
        else if (numElements == 1) {
            if (head == null)      return false;
            if (head.next != null) return false;
            if (head != tail) return false;
        }
        else {
            if (head == null || tail == null)      return false;
            if (head.next == null) return false;
            if (tail.next != null) return false;
        }

        // check internal consistency of instance variable numElements
        int numberOfNodes = 0;
        for (Node x = head; x != null && numberOfNodes <= numElements; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != numElements) return false;

        return true;
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
		private Node i = head;

		public boolean hasNext() {
			return i != null;
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item nextItem = i.item;
			i = i.next;
			return nextItem;
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove is evil and not supported.");
		}
	}

	public static void main(String[] args) {
		Deque<String> Deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) Deque.enDeque(item);
            else if (!Deque.isEmpty()) StdOut.print(Deque.deDeque() + " ");
        }
        StdOut.println("(" + Deque.size() + " left on Deque)");
	}
}
