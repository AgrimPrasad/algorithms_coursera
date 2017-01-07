// Shell sort implementation
// Firstly, h-sort an array where you only exchange the hth elements
// Finally, 1-sort (same as insertion sort): Sort all entries left to and including the current entry
// Entries to the right of the current entry have not been seen yet

import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;

public class Shell{
	private Shell() { }		// do not instantiate

	public static void sort(Comparable[] array) {
		// Find largest h such that 3h + 1 <= array.length
		int h = 0;
		int n = array.length;
		while (h < n/3) h = 3*h + 1;

		while (h > 0) {
			for (int i = 0; i + h < array.length; i++) {
				for (int j = i + h; j - h >= 0; j = j - h) {
					if (less(array[j], array[j - h])) exch(array, j, j - h);
				}
			}
			StdOut.println(h);
			assert isHSorted(array, h);
			h = (h - 1) / 3;
		}

		assert isSorted(array);
	}

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}


	private static boolean less(Comparable item1, Comparable item2) {
		return item1.compareTo(item2) < 0;
	}

	private static boolean isHSorted(Comparable[] a, int h) {
		for (int i = 0; i + h < a.length; i = i + h) {
			for (int j = i + h; j - h >= 0; j = j - h) {
				if (less(a[j], a[j - h])) return false;
			}
		}

		return true;
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

	private static void exch(Comparable[] array, int idx1, int idx2) {
		Comparable temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}

	public static void main(String[] args) {
		String[] comparableStr = new String[]{"blah", "a", "sorting", "me", "crap", "hello", "world", "hkust", "hkust", "b"};
		Shell.sort(comparableStr);
		Shell.show(comparableStr);
	}

}