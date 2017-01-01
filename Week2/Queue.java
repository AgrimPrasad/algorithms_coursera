// Queue: FIFO collection

// Linked list based generic queue. Queue.java implements 
// a generic linked list queue. enqueue() to the tail of the linked list
// and dequeue() from the front of the linked list

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private Node head;
	private Node tail;
	private int numElements;

	public Queue() {
		this.head = null;
		this.tail = null;
	}

	public void enqueue(Item item) {
		if (isEmpty()) {
			head = tail = new Node(item, null);
		} else {
			Node oldTail = tail;
			tail = new Node(item, null);
			oldTail.next = tail;
		}
		numElements++;
	}

	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item oldHead = head.item;
		head = head.next;
		this.numElements--;
		if (isEmpty()) tail = null;		// to prevent loitering 
		return oldHead;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return numElements;
	}

	// Returns (but does not remove) the item least recently added to the queue
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return head.item;
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
		Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
	}
}
