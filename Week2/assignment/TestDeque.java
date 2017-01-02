// TestDeque: Unit tests for Deque


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
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

	public static void removeFirstTest() {
		Deque<Double> dq = new Deque<>();

		try {
			dq.removeFirst();
		}
		catch (NoSuchElementException e) { }

		dq.addFirst(32.33);
		assert dq.removeFirst() == 32.33;

		dq.addLast(-100.32);
		assert dq.removeFirst() == -100.32;

		dq.addFirst(50.0);
		dq.addLast(75.0);
		dq.addFirst(0.0);
		dq.addLast(100.0);
		assert dq.removeFirst() == 0.0;
		assert dq.removeFirst() == 50.0;
		assert dq.removeFirst() == 75.0;
		assert dq.removeFirst() == 100.0;

		try {
			dq.removeFirst();
		}
		catch (NoSuchElementException e) { }
	}

	public static void removeLastTest() {
		Deque<Double> dq = new Deque<>();

		try {
			dq.removeLast();
		}
		catch (NoSuchElementException e) { }

		dq.addFirst(32.33);
		assert dq.removeLast() == 32.33;

		dq.addLast(-100.32);
		assert dq.removeLast() == -100.32;

		dq.addLast(75.0);
		dq.addFirst(50.0);
		dq.addFirst(0.0);
		dq.addLast(100.0);
		assert dq.removeLast() == 100.0;
		assert dq.removeLast() == 75.0;
		assert dq.removeLast() == 50.0;
		assert dq.removeLast() == 0.0;

		try {
			dq.removeFirst();
		}
		catch (NoSuchElementException e) { }
	}

	public static void iteratorTest() {
		Deque<Integer> dq = new Deque<>();
		Iterator<Integer> iter = dq.iterator();

		assert iter != null;
		assert iter.hasNext() == false;
		try {
			iter.next();
		}
		catch (NoSuchElementException e) { }
		try {
			iter.remove();
		}
		catch (UnsupportedOperationException e) { }

		try {
			for (int i : dq) {
				assert dq.isEmpty();
			}
		}
		catch (NoSuchElementException e) { }

		int firstElement = 1001;
		for (int i = 0; i < 500; i++) {
			int firstNum = StdRandom.uniform(1000);
			int lastNum = StdRandom.uniform(1000);
			dq.addFirst(firstNum);
			dq.addLast(lastNum);

			if (i == 0) firstElement = firstNum;
		}

		for (int j : dq) {
			assert j >= 0 && j < 1000;
		}

		assert iter != null;
		assert iter.hasNext() == false;
		try {
			iter.next();
		}
		catch (NoSuchElementException e) { }
		try {
			iter.remove();
		}
		catch (UnsupportedOperationException e) { }

		assert dq.size() == 1000;

		Iterator<Integer> iter1 = dq.iterator();

		while (iter1.hasNext()) {
			int nextItem = iter1.next();
			assert nextItem >= 0 && nextItem < 1000;
		}

		for (int i = 0; i < 500; i++) {
			dq.removeFirst();
			dq.removeLast();
		}

		try {
			for (int i : dq) {
				assert dq.isEmpty();
			}
		}
		catch (NoSuchElementException e) { }

		assert dq.size() == 0;
		assert dq.isEmpty();
	}

	public static void main(String[] args) {
		StdOut.println("Testing isEmpty()");
		isEmptyTest();
		StdOut.println("Testing size()");
		sizeTest();
		StdOut.println("Testing addFirst()");
		addFirstTest();
		StdOut.println("Testing addLast()");
		addLastTest();
		StdOut.println("Testing removeFirst()");
		removeFirstTest();
		StdOut.println("Testing removeLast()");
		removeLastTest();
		StdOut.println("Testing iterator()");
		iteratorTest();
		StdOut.println("All tests passed.");
	}
}
