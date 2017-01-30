import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Key Ideas: 
// 	1. 2-way/3-way Partition the arrays firstly at a random item
//	2. Recursively sort both sides of the partitioning item

public class Quick3way {
	private Quick3way() { }	// cannot instantiate

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
		HashMap<String, Integer> ltgt = partition(a, lo, hi);
		int lt = ltgt.get("lt");
		int gt = ltgt.get("gt");
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	private static HashMap<String, Integer> partition(Comparable[] a, int lo, int hi) {
		StdOut.println("Before Partition: ");
		for (int x = lo; x <= hi; x++) {
			StdOut.println(a[x]);
		}

		int lt = lo;
		int gt = hi;
		int i = lo;
		Comparable v = a[lo];

		while(i <= gt) {
			int compr = a[i].compareTo(v);
			if (compr == 0) {
				i++;
			}
			else if (compr < 0) {
				exch(a, lt, i);
				lt++;
				i++;
			}
			else if (compr > 0) {
				exch(a, gt, i);
				gt--;
			}
		}

		StdOut.println("After Partition: ");
		for (int x = lo; x <= hi; x++) {
			StdOut.println(a[x]);
		}
		
		// Invariants: a[j] is in place, a[lo : j] <= j, a[j+1 : hi] > j
		Comparable[] lowerHalf = Arrays.copyOfRange(a, lo, lt + 1);
		Comparable[] upperHalf = Arrays.copyOfRange(a, gt, hi + 1);

		StdOut.println("a[lt]: " + a[lt]);
		StdOut.println("lowerHalf: ");
		for (int x = 0; x < lowerHalf.length; x++) {
			StdOut.println(lowerHalf[x]);
		}
		StdOut.println("upperHalf: ");
		for (int x = 0; x < upperHalf.length; x++) {
			StdOut.println(upperHalf[x]);
		}
		assert elemInPlace(a[lt], lowerHalf, upperHalf);

		HashMap<String, Integer> ltgt = new HashMap<String, Integer>();
		ltgt.put("lt", lt);
		ltgt.put("gt", gt);
		return ltgt;
	}

	private static boolean less(Comparable item1, Comparable item2) {
		return item1.compareTo(item2) < 0;
	}

	private static boolean equal(Comparable item1, Comparable item2) {
		return item1.compareTo(item2) == 0;
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
		// String[] comparableStr = new String[]{"a"};
		Quick3way.sort(comparableStr);
		StdOut.println("Final Results:");
		Quick3way.show(comparableStr);
	}
}