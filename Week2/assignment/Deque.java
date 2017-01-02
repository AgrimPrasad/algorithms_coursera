// Deque: FIFO/LIFO collection (generalization of stack + queue with doubly linked list)

// Doubly Linked list based generic Deque. Deque.java implements 
// a generic doubly linked list Deque. addLast(), removeLast() to the tail of the linked list
// and addFirst(), removeFirst() from the front of the linked list

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
		numElements = 0;

		check();
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) throw new NullPointerException("Cannot add a null item.");
		if (isEmpty()) {
			head = tail = new Node(item, null, null);
		} else {
			Node oldHead = head;
			head = new Node(item, null, oldHead);
			oldHead.previous = head;
		}
		numElements++;
		check();
	}

	// add the item to the end
    public void addLast(Item item)	{
    	if (item == null) throw new NullPointerException("Cannot add a null item.");
    	if (isEmpty()) {
			head = tail = new Node(item, null, null);
		} else {
			Node oldTail = tail;
			tail = new Node(item, oldTail, null);
			oldTail.next = tail;
		}
		numElements++;
		check();
    }  
    
    // remove and return the item from the front
    public Item removeFirst()	{
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item oldHead = head.item;
		head = head.next;
		if (numElements > 1) head.previous = null;
		this.numElements--;
		if (isEmpty()) tail = null;		// to prevent loitering

		check();
		return oldHead;
    }
    
    // remove and return the item from the end
    public Item removeLast()	{
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
		Item oldTail = tail.item;
		tail = tail.previous;
		if (tail != null) tail.next = null;		// to prevent loitering
		this.numElements--;
		if (isEmpty()) head = null;				// to prevent loitering

		check();
		return oldTail;
    }

	public boolean isEmpty() {
		return numElements == 0;
	}

	public int size() {
		return numElements;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// check internal invariants with asserts
    private void check() {

        // check a few properties of instance variable 'head'
        assert numElements >= 0;
        if (numElements == 0) {
            assert head == null;
            assert tail == null;
        }
        else if (numElements == 1) {
            assert head != null;
            assert head.next == null;
            assert head.previous == null;
            assert tail != null;
            assert tail.next == null;
            assert tail.previous == null;
        }
        else {
            assert head != null;
            assert tail != null;
            assert head.next != null;
            assert tail.next == null;
            assert head.previous == null;
            assert tail.previous != null;
        }

        // check internal consistency of instance variable numElements
        int numberOfNodes = 0;
        for (Node x = head; x != null && numberOfNodes <= numElements; x = x.next) {
            numberOfNodes++;
        }
        assert numberOfNodes == numElements;
    }

	private class Node {
		private Item item;
		private Node previous;
		private Node next;

		public Node() { }

		public Node(Item item, Node previous, Node next) {
			this.item = item;
			this.previous = previous;
			this.next = next;
		}
	}

	// return an iterator over items in order from front to end
	private class ListIterator implements Iterator<Item> {
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
            // StdOut.println("Incoming item: " + item + "\n");
            if (!item.equals("-")) Deque.addFirst(item);
            else if (!Deque.isEmpty()) StdOut.print(Deque.removeLast() + " ");
        }
        StdOut.println("(" + Deque.size() + " left on Deque)");
	}
}
