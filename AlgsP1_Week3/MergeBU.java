import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	private MergeBU() { }	// cannot instantiate

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	public static void sort(Comparable[] a) { 
		Comparable[] aux = new Comparable[a.length];
		// sort(a, aux, 0, a.length - 1);
		// int lo = 0;
		// int hi = 1;
		// int mid = 0;
		int n = a.length;
		for (int sz = 1; sz < n; sz *= 2) {
			StdOut.println(sz);
			for (int lo = 0; lo < n - sz; lo += sz+sz) {
				int hi = Math.min(lo + sz + sz - 1, n - 1);
				int mid = lo + sz - 1;
				merge(a, aux, lo, mid, hi);
			}
			// if (sz < n && sz * 2 > n) sz = n;
			// else sz *= 2;
			// mid = lo + (hi - lo) / 2;
			// merge(a, aux, lo, mid, hi);
			// lo += sz;
			// hi += sz;
		}
		assert isSorted(a);
	}

	// private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
	// 	int mid = lo + (hi - lo) / 2;

	// 	if (hi <= lo) {						// base case
	// 		return;
	// 	}
	// 	sort(a, aux, lo, mid);
	// 	sort(a, aux, mid + 1, hi);
	// 	merge(a, aux, lo, mid, hi);
	// }

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// copy array a to array aux from lo to hi
		System.arraycopy(a, lo, aux, lo, hi - lo + 1);

		// copy from aux to a in order
		// k -> goes through array a
		// i -> goes through first sub-array of length == length1
		// j -> goes through second array of length == length2
		int k = lo;
		int i = lo;
		int j = mid + 1;
		for (; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			}
			else if (j > hi) {
				a[k] = aux[i++];
			}
			else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			}
			else {	// for equal, take from first sub-array
				a[k] = aux[i++];
			}
		}
		assert isSorted(a, lo, hi);
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

	public static void main (String[] args) {
		String[] comparableStr = new String[]{"blah", "a", "sorting", "me", "crap", "hello", "world", "hkust", "hkust", "b"};
		MergeBU.sort(comparableStr);
		MergeBU.show(comparableStr);
	}
}