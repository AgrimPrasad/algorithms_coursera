// TestDeque: Unit tests for Deque


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestDeque {
	private TestDeque() { } // cannot be instantiated

	public static void isEmptyTest() {
		Deque<String> dq = new Deque<>();
		assert dq.isEmpty();

		dq.addFirst("firstAdded");
		assert !dq.isEmpty();

		dq.removeFirst();
		assert dq.isEmpty();

		dq.addFirst("firstAdded");
		dq.addLast("lastAdded");
		assert !dq.isEmpty();

		dq.removeLast();
		dq.removeFirst();
		assert dq.isEmpty();

		dq.addLast("lastAdded");
		assert !dq.isEmpty();

		dq.removeLast();
		assert dq.isEmpty();

		dq.addFirst("firstAdded");
		dq.addFirst("firstAdded");
		dq.addLast("lastAdded");
		dq.addLast("lastAdded");

		assert !dq.isEmpty();

		for (String i : dq) {
			assert dq.size() == 4;
		}

		assert !dq.isEmpty();

	}

	public static void sizeTest() {
		Deque<String> dq = new Deque<>();
		assert dq.size() == 0;

		dq.addFirst("firstAdded");
		assert dq.size() == 1;

		dq.removeFirst();
		assert dq.size() == 0;

		dq.addFirst("firstAdded");
		dq.addLast("lastAdded");
		assert dq.size() == 2;

		dq.removeLast();
		dq.removeFirst();
		assert dq.size() == 0;

		dq.addLast("lastAdded");
		assert dq.size() == 1;

		dq.removeLast();
		assert dq.size() == 0;

		dq.addFirst("firstAdded");
		dq.addFirst("firstAdded");
		dq.addLast("lastAdded");
		dq.addLast("lastAdded");

		assert dq.size() == 4;

		for (String i : dq) {
			assert dq.size() == 4;
		}

		assert dq.size() == 4;
	}

	public static void addFirstTest() {
		Deque<Integer> dq = new Deque<>();

		// Positive tests: 0, 100, -100, -1000
		dq.addFirst(0);
		assert dq.removeFirst() == 0;

		dq.addFirst(100);
		dq.addFirst(-100);
		assert dq.removeLast() == 100;

		dq.addFirst(-1000);
		assert dq.removeFirst() == -1000;
	}

	public static void addLastTest() {
		Deque<Integer> dq = new Deque<>();

		// Positive tests: 0, 100, -100, -1000
		dq.addLast(0);
		assert dq.removeFirst() == 0;

		dq.addLast(100);
		dq.addLast(-100);
		assert dq.removeLast() == -100;

		dq.addLast(-1000);
		assert dq.removeFirst() == 100;
	}

	public static void removeFirstTest() { }

	public static void removeLastTest() { }

	public static void iteratorTest() { }

	public static void main(String[] args) {
		StdOut.println("Testing isEmpty()");
		isEmptyTest();
		StdOut.println("Testing size()");
		sizeTest();
		StdOut.println("Testing addFirst()");
		addFirstTest();
		StdOut.println("Testing addLast()");
		addLastTest();
		StdOut.println("All tests passed.");
	}
}
