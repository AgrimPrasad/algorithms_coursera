import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Key Ideas: 
// 	1. 2-way/3-way Partition the arrays firstly at a random item
//	2. Recursively sort both sides of the partitioning item

public class Quick {
	private Quick() { }	// cannot instantiate

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;	// base case
		int j = partition(a, lo, hi);
		sort(a, lo, j);
		sort(a, j + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		// StdOut.println("Before Partition: ");
		// for (int x = lo; x <= hi; x++) {
		// 	StdOut.println(a[x]);
		// }

		int i = lo;
		int j = hi + 1;

		while (true) {
			while (less(a[++i], a[lo])) {
				if (i == hi) break;
			}
			// StdOut.println("i: " + i);

			while (less (a[lo], a[--j])) {
				if (j == lo) break;	// redundant
			}
			// StdOut.println("j: " + j);

			if (j <= i) break;
			exch(a, i, j);
		}
		exch(a, lo, j);

		// StdOut.println("After Partition: ");
		// for (int x = lo; x <= hi; x++) {
		// 	StdOut.println(a[x]);
		// }
		
		// Invariants: a[j] is in place, a[lo : j] <= j, a[j+1 : hi] > j
		assert elemInPlace(a[j], Arrays.copyOfRange(a, lo, j), 
						   Arrays.copyOfRange(a, j + 1, hi + 1));

		return j;
	}

	private static boolean less(Comparable item1, Comparable item2) {
		return item1.compareTo(item2) < 0;
	}

	private static void exch(Comparable[] array, int idx1, int idx2) {
		Comparable temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		if (lo == hi) return true;
		for (int i = lo + 1; i <= hi; i++) {
			if (less(a[i],a[i - 1])) return false;
		}
		return true;
	}

	private static boolean elemInPlace(Comparable elem, Comparable[] lowerHalf, Comparable[] upperHalf) {
		for (int i = 0; i < lowerHalf.length; i++) {
			if (less(elem, lowerHalf[i])) return false;
		}

		for (int j = 0; j < upperHalf.length; j++) {
			if (less(upperHalf[j], elem)) return false;
		}

		return true;
	}

	public static void main (String[] args) {
		String[] comparableStr = new String[]{"blah", "a", "sorting", "me", "crap", "hello", "world", "hkust", "b"};
		// String[] comparableStr = new String[]{"a", "b", "c", "d", "e", "s"};
		Quick.sort(comparableStr);
		StdOut.println("Final Results:");
		Quick.show(comparableStr);
	}
}