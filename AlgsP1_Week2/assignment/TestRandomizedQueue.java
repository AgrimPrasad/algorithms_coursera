// TestRandomizedQueue: Unit tests for RandomizedQueue


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.javamex.classmexer.MemoryUtil;

public class TestRandomizedQueue {
	private TestRandomizedQueue() { } // cannot be instantiated

	public static void isEmptyTest() {
		RandomizedQueue<String> dq = new RandomizedQueue<>();
		assert dq.isEmpty();

		dq.enqueue("firstAdded");
		assert !dq.isEmpty();

		dq.dequeue();
		assert dq.isEmpty();

		dq.enqueue("firstAdded");
		dq.enqueue("lastAdded");
		assert !dq.isEmpty();

		dq.dequeue();
		dq.dequeue();
		assert dq.isEmpty();

		dq.enqueue("lastAdded");
		assert !dq.isEmpty();

		dq.dequeue();
		assert dq.isEmpty();

		dq.enqueue("firstAdded");
		dq.enqueue("firstAdded");
		dq.enqueue("lastAdded");
		dq.enqueue("lastAdded");

		assert !dq.isEmpty();
		assert dq.size() == 4;

		for (String i : dq) {
			assert dq.size() == 4;
		}
		assert !dq.isEmpty();

	}

	public static void sizeTest() {
		RandomizedQueue<String> dq = new RandomizedQueue<>();
		assert dq.size() == 0;

		dq.enqueue("firstAdded");
		assert dq.size() == 1;

		dq.dequeue();
		assert dq.size() == 0;

		dq.enqueue("firstAdded");
		dq.enqueue("lastAdded");
		assert dq.size() == 2;

		dq.dequeue();
		dq.dequeue();
		assert dq.size() == 0;

		dq.enqueue("lastAdded");
		assert dq.size() == 1;

		dq.dequeue();
		assert dq.size() == 0;

		dq.enqueue("firstAdded");
		dq.enqueue("firstAdded");
		dq.enqueue("lastAdded");
		dq.enqueue("lastAdded");

		assert dq.size() == 4;

		for (String i : dq) {
			assert dq.size() == 4;
		}

		assert dq.size() == 4;
	}

	public static void enqueueTest() {
		RandomizedQueue<Integer> dq = new RandomizedQueue<>();

		// Positive tests: 0, 100, -100, -1000
		dq.enqueue(0);
		assert dq.dequeue() == 0;

		dq.enqueue(100);
		dq.enqueue(-100);
		dq.enqueue(-1000);

		assert dq.size() == 3;

		//Negative test: add null item
		try {
			dq.enqueue(null);
		}
		catch (NullPointerException e) { }
	}

	public static void dequeueTest() {
		RandomizedQueue<Integer> dq = new RandomizedQueue<>();

		try {
			dq.dequeue();
		}
		catch (NoSuchElementException e) { }

		dq.enqueue(100);
		dq.enqueue(-100);
		dq.enqueue(-1000);
		assert dq.size() == 3;
		dq.dequeue();
		assert dq.size() == 2;
		dq.dequeue();
		assert dq.size() == 1;
		dq.dequeue();
		assert dq.size() == 0;

		boolean dequeued100 = false;
		boolean dequeuedneg100 = false;
		boolean dequeuedneg1000 = false;
		for (int i = 0; i < 100; i++) {
			dq.enqueue(100);
			dq.enqueue(-100);
			dq.enqueue(-1000);
			assert dq.size() == 3;
			int randomItem = dq.dequeue();
			assert dq.size() == 2;
			dq.dequeue();
			assert dq.size() == 1;
			dq.dequeue();
			assert dq.size() == 0;
			switch (randomItem) {
				case 100: dequeued100 = true;
						  break;
				case -100: dequeuedneg100 = true;
						  break;
				case -1000: dequeuedneg1000 = true;
						  break;
				default:  break;
			}
		}
		assert dequeued100;
		assert dequeuedneg100;
		assert dequeuedneg1000; // probabilistically, all should be true

		dq.enqueue(32);
		assert dq.dequeue() == 32;

		dq.enqueue(-100);
		assert dq.dequeue() == -100;

		try {
			dq.dequeue();
		}
		catch (NoSuchElementException e) { }
	}

	public static void iteratorTest() {
		RandomizedQueue<Integer> dq = new RandomizedQueue<>();
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
			dq.enqueue(firstNum);
			dq.enqueue(lastNum);

			if (i == 0) firstElement = firstNum;
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

		for (int i : dq) {
			assert i >= 0 && i < 1000;
			for (int j : dq) {
				assert j >= 0 && j < 1000; 
			}
		}

		for (int i = 0; i < 500; i++) {
			dq.dequeue();
			dq.dequeue();
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

	public static void sampleTest() { 
		RandomizedQueue<Integer> dq = new RandomizedQueue<>();

		try {
			dq.sample();
		}
		catch (NoSuchElementException e) { }

		dq.enqueue(100);
		assert dq.sample() == 100;

		dq.dequeue();
		try {
			dq.sample();
		}
		catch (NoSuchElementException e) { }

		boolean dequeued100 = false;
		boolean dequeuedneg100 = false;
		boolean dequeuedneg1000 = false;
		int expectedSize = 0;
		for (int i = 0; i < 100; i++) {
			dq.enqueue(100);
			dq.enqueue(-100);
			dq.enqueue(-1000);

			expectedSize += 3;
			assert dq.size() == expectedSize;
			int randomItem = dq.sample();
			assert dq.size() == expectedSize;
			dq.sample();
			assert dq.size() == expectedSize;
			dq.sample();
			assert dq.size() == expectedSize;
			switch (randomItem) {
				case 100: dequeued100 = true;
						  break;
				case -100: dequeuedneg100 = true;
						  break;
				case -1000: dequeuedneg1000 = true;
						  break;
				default:  break;
			}
		}
		assert dequeued100;
		assert dequeuedneg100;
		assert dequeuedneg1000; // probabilistically, all should be true
		assert dq.size() == expectedSize;
	}

	public static void memoryTest() {
		RandomizedQueue<Integer> dq = new RandomizedQueue<>();

		for (int i = 0; i < 500; i++) {
			int firstNum = StdRandom.uniform(1000);
			int lastNum = StdRandom.uniform(1000);
			dq.enqueue(firstNum);
			dq.enqueue(lastNum);
		}

		long noBytesthousandObj = MemoryUtil.deepMemoryUsageOf(dq);

		StdOut.println("Memory used with 1000 objects = " + noBytesthousandObj + " bytes.");

		// Test that Memory used <= 48n + 192
		assert noBytesthousandObj <= 48*1000 + 192;

		for (int i = 0; i < 250; i++) {
			dq.dequeue();
			dq.dequeue();
		}

		long noBytes500Obj = MemoryUtil.deepMemoryUsageOf(dq);
		StdOut.println("Memory used with 500 objects = " + noBytes500Obj + " bytes.");
		// Test that Memory used <= 48n + 192
		assert noBytes500Obj <= 48*500 + 192;
	}

	public static void main(String[] args) {
		StdOut.println("Testing isEmpty()");
		isEmptyTest();
		StdOut.println("Testing size()");
		sizeTest();
		StdOut.println("Testing enqueue()");
		enqueueTest();
		StdOut.println("Testing dequeue()");
		dequeueTest();
		StdOut.println("Testing sample()");
		sampleTest();
		StdOut.println("Testing iterator()");
		iteratorTest();
		StdOut.println("Testing memory usage");
		memoryTest();
		StdOut.println("All tests passed.");
	}
}
