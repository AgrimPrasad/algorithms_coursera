// Selection sort implementation
// First, find the smallest entry and exchange it with the first entry. Then, find the second smallest item and exchange it with the second entry
// and so on until the array is sorted

import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;

public class Selection{
	private Selection() { }		// do not instantiate

	public static void sort(Comparable[] array) {
		for (int i = 0; i < array.length; i++) {
			int currentMin = i;
			for (int j = i + 1; j < array.length; j++) {
				if (less(array[j], array[currentMin])) currentMin = j;
			}
			exch(array, i, currentMin);
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

}